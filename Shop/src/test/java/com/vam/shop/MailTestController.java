package com.vam.shop;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MailTestController {
	
	@Autowired
	JavaMailSenderImpl mailSender;

//	@Test
//	public void sendMailTest() throws Exception{
//		String subject = "test mail";
//		String content = "메일 테스트 내용"+"<img src=\"https://t1.daumcdn.net/cfile/tistory/214DCD42594CC40625\">";
//		String from = "ssuki0220@naver.com";
//		String to ="ssuki0220@naver.com";
//		
//		try {
//			MimeMessage mail = mailSender.createMimeMessage();
//			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true,"UTF-8");
//			
//			mailHelper.setFrom(from);
//			mailHelper.setTo(to);
//			mailHelper.setSubject(subject);
//			mailHelper.setText(content, true);
//			
//			FileSystemResource file= new FileSystemResource(new File("c:\\Temp\\test1.txt"));
//			mailHelper.addAttachment("test1.txt", file);
//			
//			mailSender.send(mail);
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void sendMailTest2() throws Exception{
		String subject = "test mail";
		String content = "메일 테스트 내용"+"<img src=\"https://t1.daumcdn.net/cfile/tistory/214DCD42594CC40625\">";
		String from = "ssuki0220@naver.com";
		String to ="ssuki0220@naver.com";
		
		try {
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				
				mailHelper.setFrom(from);
				mailHelper.setTo(to);
				mailHelper.setSubject(subject);
				mailHelper.setText(content, true);
				
				FileSystemResource file= new FileSystemResource(new File("c:\\Temp\\test1.txt"));
				mailHelper.addAttachment("test1.txt", file);
			}
		};
			mailSender.send(preparator);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
