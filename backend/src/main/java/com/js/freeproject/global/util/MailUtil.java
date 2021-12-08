package com.js.freeproject.global.util;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.js.freeproject.domain.mail.domain.Mail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailUtil {
	private final JavaMailSender javaMailSender;
	
	public void SendMail(Mail mail) throws MessagingException, SendFailedException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message);
		messageHelper.setTo(mail.getTo());
		messageHelper.setFrom("CSPS");
		messageHelper.setSubject(mail.getTitle());
		messageHelper.setText(mail.getContent(),true);
		
		javaMailSender.send(message);
	}
	
	public String getcontent(String type,String key) {
		StringBuilder content = new StringBuilder();
		content.append("<!DOCTYPE html>");
		content.append("<html>");
		content.append("<head></head>");
		content.append("<body>");
		content.append("<div>");
		if(type.equals("pass")) {
			content.append("<h1>").append("CSPS를 이용해주셔서 감사합니다.").append("</h1>");
			content.append("비밀번호 인증번호는 ").append("<span style='font-size:20px'>").append(key).append("</span>").append(" 입니다.");
		} else if(type.equals("mail")) {
			content.append("<h1>").append("CSPS 메일 인증입니다.").append("</h1>");
			content.append("이메일 인증번호는 ").append("<span style='font-size:20px'>").append(key).append("</span>").append(" 입니다.");
		}
		content.append("</div>");
		content.append("</body>");
		content.append("</html>");
		return content.toString();
	}
}
