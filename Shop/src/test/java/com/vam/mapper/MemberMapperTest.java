package com.vam.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper membermapper;
	
	/*
	@Test
	public void memberJoin() throws Exception{
		MemberVO member = new MemberVO();
		
		member.setMemberId("test");
		member.setMemberPw("test");
		member.setMemberName("test");
		member.setMemberMail("test");
		member.setMemberAddr1("test");
		member.setMemberAddr2("test");
		member.setMemberAddr3("test");
		
		membermapper.memberJoin(member);
	}
	
	
	@Test
	public void memberIdChk() throws Exception{
		String id = "admin";
		String id2 ="lee";
		membermapper.idCheck(id);
		membermapper.idCheck(id2);
	} */
	
	
	@Test
	public void  memberLogin() throws Exception{
		MemberVO member = new MemberVO();
		
		member.setMemberId("lee");
		member.setMemberPw("1234");
		
		membermapper.memberLogin(member);
		System.out.println("결과값 : " +membermapper.memberLogin(member));
	}
}
