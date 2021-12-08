package com.js.freeproject.domain.user.dto;



import org.springframework.web.multipart.MultipartFile;

import com.js.freeproject.domain.user.domain.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserRequest {
    private String email;
    private String nickName;
    private String name;
    private String pass;
    private MultipartFile image;
    
    public User toEntity() {
    	return User.builder()
    			.email(email)
    			.nickName(nickName)
    			.name(name)
    			.pass(pass)
    			.build();
    }
}
