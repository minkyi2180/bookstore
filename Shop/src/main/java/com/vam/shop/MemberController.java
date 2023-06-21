package com.vam.shop;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.vam.model.MemberVO;
import com.vam.service.MemberService;


@Controller
@RequestMapping(value= "/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	
	//회원가입 페이지 이동
	@RequestMapping(value= "join", method = RequestMethod.GET)
	public void joinGET() {
		logger.info("회원가입 페이지 진입");
	}
	
	//로그인 페이지 이동
	@RequestMapping(value="login", method = RequestMethod.GET)
	public void loginGET() {
		logger.info("로그인 페이지 진입");
	}
	
	@PostMapping("/login.do")
	public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
		//System.out.println("login 메서드 진입");
		//System.out.println("전달된 데이터 : " +member);
		
		HttpSession session = request.getSession();
		String rawPw = "";
		String encodePw ="";
		MemberVO lvo = memberService.memberLogin(member);
		
		if(lvo != null) { //아이디와 비밀번호 일치- 로그인 성공 시
			rawPw = member.getMemberPw(); //사용자가 제출한 비밀번호
			encodePw = lvo.getMemberPw(); //db에 저장된 암호화(인코딩)된 비밀번호
			if(true == pwEncoder.matches(rawPw, encodePw)) {
				lvo.setMemberPw("");
				session.setAttribute("member", lvo);
				return "redirect:/main";
			}else {
				rttr.addFlashAttribute("result", 0);
				return "redirect:/member/login";
			}
			
		}else { //일치하는 아이디가 없을때 로그인 실패 시
			rttr.addFlashAttribute("result", 0);
			return "redirect:/member/login"; //로그인 페이지로 이동
		}
		
		/*
		if(lvo == null) { 
			int result = 0;
			rttr.addFlashAttribute("result", result);
			return "redirect:/member/login";
		}
		session.setAttribute("member", lvo);
		return "redirect:/main";
		*/
	}
	
	
	@PostMapping("/join")
	public String joinPost(MemberVO member) throws Exception{
		/*
		logger.info("join진입");
		//회원가입 서비스 실행
		memberService.memberJoin(member);
		logger.info("회원가입성공");
		*/
		
		String rawPw = "";  	//인코딩 전 비밀번호
		String encodePw = ""; 	//인코딩 후 비밀번호
		
		rawPw = member.getMemberPw(); 	//비밀번호 데이터 얻음
		encodePw = pwEncoder.encode(rawPw); 	//비밀번호 인코딩
		member.setMemberPw(encodePw); 	//인코딩된 비밀번호 member객체에 다시 저장
		
		memberService.memberJoin(member); //회원 쿼리 실행
		
		return "redirect:/main";
	}
	
	//아이디 중복검사
	@PostMapping("/memberIdChk")
	@ResponseBody
	public String memberIdChkPOST(String memberId) throws Exception{
		logger.info("memberIdChk() 진입");
		int result = memberService.idCheck(memberId);
		//logger.info("결과값 =" +result);
		if(result != 0) {
			return "fail";//중복아이디가 존재할때
		}else {
			return "success";//중복아이디가 존재안할때
		}
	}
	
	
	@GetMapping("/sendMail")
	public void sendMailTest() throws Exception{
		String subject = "test mail";
		String content = "메일 테스트 내용"+"<img src=\"https://t1.daumcdn.net/cfile/tistory/214DCD42594CC40625\">";
		String from = "ssuki0220@naver.com";
		String to ="ssuki0220@naver.com";
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true,"UTF-8");
			
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content, true);
			
			FileSystemResource file= new FileSystemResource(new File("c:\\Temp\\test1.txt"));
			mailHelper.addAttachment("test1.txt", file);
			
			mailSender.send(mail);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//이메일인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheckGET(String email) throws Exception{
		logger.info("이메일 데이터 전송 확인");
		logger.info("인증 메일:"+email);
	
		Random random = new Random();
		int checkNum = random.nextInt(888888) +111111;
		logger.info("인증번호"+checkNum);
		
		String setFrom = "ssuki0220@naver.com";
		String toMail = email;
		String title = "회원가입 인증 이메일입니다";
		String content =
				"홈페이지를 방문해주셔서 감사합니다"+
						"<br><br>"+
						"인증번호는 " +checkNum +"입니다"+
						"<br>"+
						"해당 인증번호를 인증번호 확인란에 기입하여 주세요";
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper Helper = new MimeMessageHelper(message, true,"UTF-8");
			
			Helper.setFrom(setFrom);
			Helper.setTo(toMail);
			Helper.setSubject(title);
			Helper.setText(content, true);
	
			mailSender.send(message);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		String num = Integer.toString(checkNum);
		return num;
	}
	
	@GetMapping("/logout.do")
	public String logoutGET(HttpServletRequest request) throws Exception{
		logger.info("logoutMainGET 메서드 진입");
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/main";
	}
	
	@PostMapping("/logout.do")
	@ResponseBody
	public void logoutPOST(HttpServletRequest request) throws Exception{
		logger.info("비동기 로그아웃 메서드 진입");
		
		HttpSession session = request.getSession();
		session.invalidate();
		
	}


	
	
	
}

