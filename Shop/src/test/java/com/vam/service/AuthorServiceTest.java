package com.vam.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.vam.model.AuthorVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorServiceTest {
	
	@Autowired
	private AuthorService service;
	
	/*
	 * @Test
	 
	public void authorEnrollTest() throws Exception{
		AuthorVO vo = new AuthorVO();
		
		vo.setNationId("02");
		vo.setAuthorName("마이콜");
		vo.setAuthorIntro("둘리 친구");
		
		service.authorEnroll(vo);
	}

	
	@Test
	public void authorGetDetailTest() throws Exception{
		int authorId = 20;
		Log.info("author...."+service.authorGetDetail(authorId)));
	}*/
	/*
	@Test
	public void authorModifyTest() throws Exception{
		
		AuthorVO author = new AuthorVO();
		author.setAuthorId(1);
		System.out.println("(service)수정 전........"+service.authorGetDetail(author.getAuthorId()));
	
		author.setAuthorName("(service)수정");
		author.setNationId("01");
		author.setAuthorIntro("(service)소개 수정하였습니다");
		
		service.authorModify(author);
		System.out.println("(service)수정 후......."+service.authorGetDetail(author.getAuthorId()));
		
	}*/
	
	/*작가 상세 페이지*/
	/*@Test
	public void authorGetDetailTest() throws Exception{
		
		int authorId = 150;
		
		Log.info("author......"+service.authorGetDetail(authorId));
	}*/
}
