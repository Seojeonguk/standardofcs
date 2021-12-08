package com.js.freeproject.domain.user.application;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.transaction.Transactional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.js.freeproject.domain.amazonS3.S3Service;
import com.js.freeproject.domain.mail.domain.Mail;
import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.domain.UserRepository;
import com.js.freeproject.domain.user.dto.UserRequest;
import com.js.freeproject.global.jwt.CustomUserDetails;
import com.js.freeproject.global.util.MailUtil;
import com.js.freeproject.global.util.RedisUtil;
import com.sun.jdi.request.DuplicateRequestException;

import io.lettuce.core.RedisCommandExecutionException;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepo;
    private final RedisUtil redisUtil;
    private final MailUtil mailUtil;
    private final S3Service s3Service;


    @Transactional
    public User createUser(UserRequest user) throws IOException {
        User finduser = userRepo.findByEmail(user.getEmail());
        String userImage = "https://freepjt.s3.ap-northeast-2.amazonaws.com/user/mainCharachter.png";
        if (finduser != null) {
            throw new DuplicateRequestException(user.getEmail());
        }
        if (user.getImage() != null) {
            userImage = s3Service.uploadImage(user.getImage(), "user");
        }
        User userEntity = User.builder()
                .email(user.getEmail())
                .nickName(user.getNickName())
                .name(user.getName())
                .pass(user.getPass())
                .image(userImage)
                .build();

        return userRepo.save(userEntity);
    }
    
    public User login(String email) {
    	User user = userRepo.findByEmail(email);
    	
    	if (user == null) {
            throw new NullPointerException(email + "를 찾을 수 없습니다.");
        }
		
		CustomUserDetails accountDetails = new CustomUserDetails(user);
		UsernamePasswordAuthenticationToken jwtAuthentication = new UsernamePasswordAuthenticationToken(email, null,
				accountDetails.getAuthorities());
		jwtAuthentication.setDetails(accountDetails);
		
		SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
		
		log.info("Security Context에 {} 인증정보를 저장했습니다.", jwtAuthentication.getName());
    	
        return user;
    }

    public User findByUserEmail(String email) {
    	return userRepo.findByEmail(email);
    }

    public boolean findByUserNickName(String nickName) {
        return userRepo.existsByNickName(nickName);
    }

    @Transactional
    public User modifyUser(UserRequest userRequest) throws NotFoundException, IOException {
        User user = userRepo.findByEmail(userRequest.getEmail());
        if (user == null) {
            throw new NotFoundException(userRequest.getEmail() + "를 찾을 수 없습니다.");
        }
        String url = user.getImage();
        
        if (userRequest.getImage() != null) {
            url = s3Service.uploadImage(userRequest.getImage(), "user");
            log.info("아마존: url = " + url);
        } else {
        	url = "https://freepjt.s3.ap-northeast-2.amazonaws.com/user/mainCharachter.png";
        }
        //user.updateUser(userRequest.toEntity(), url);
        user.updateUser(url);
        return user;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public String findpassword(String email) throws MessagingException,SendFailedException {
    	User user = userRepo.findByEmail(email);
    	if(user==null) {
    		log.info("{} 미존재",email);
    		throw new NullPointerException();
    	}
    	
    	String key = randomkey();

        Mail mail = Mail.builder()
                .title("CSPS 비밀번호 인증입니다.")
                .to(email)
                .content(mailUtil.getcontent("pass", key))
                .build();

        try {
        	mailUtil.SendMail(mail);
        } catch(Exception e) {
        	throw new SendFailedException();
        }
        
        redisUtil.setDataExpire(key, email, 500);

        return key;
    }

    public User fixpass(String key, String pass) {
        if (key == null) {
            throw new NullPointerException();
        }
        String mail = redisUtil.getData(key);

        if (mail == null) {
            throw new RedisCommandExecutionException("not found : " + key);
        }

        User user = userRepo.findByEmail(mail);
        user.setPass(pass);
        userRepo.save(user);

        return user;
    }

    
    public String duplemail(String email) throws MessagingException,SendFailedException {
    	User user = userRepo.findByEmail(email);
    	if(user!=null) {
    		log.info("{} 존재",email);
    		throw new NullPointerException();
    	}
    	
    	String key = randomkey();

        Mail mail = Mail.builder()
                .title("CSPS 이메일 인증입니다.")
                .to(email)
                .content(mailUtil.getcontent("mail", key))
                .build();

        try {
        	mailUtil.SendMail(mail);
        } catch(Exception e) {
        	throw new SendFailedException();
        }
        
        redisUtil.setDataExpire(key, email, 500);
        
        return key;
    }
    
    public String randomkey() {
    	int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String key = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        
        return key;
    }
}
