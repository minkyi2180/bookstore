package com.vam.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.model.AuthorVO;
import com.vam.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorMapperTest {
	
	@Autowired
	private AuthorMapper mapper;
	
	/*
	@Test
	public void authorEnroll() throws Exception{
		AuthorVO author = new AuthorVO();
		
		author.setNationId("01");
		author.setAuthorName("테스트");
		author.setAuthorIntro("테스트 소개");
		
		mapper.authorEnroll(author);
	}
	*/
	
	/*
	@Test
	public void authorGetListTest() throws Exception{
		Criteria cri = new Criteria(3,10);
		
		List<AuthorVO> list = mapper.authorGetList(cri);
		
		for(int i = 0; i<list.size();i++) {
			System.out.println("list" +i+"........"+list.get(i));
		}
	}
	*/
	/*
	
	@Test
	public void authorGetTotalTest() throws Exception{
		Criteria cri = new Criteria();
		cri.setKeyword("고길동");
		int total = mapper.authorGetTotal(cri);
		
		System.out.println("total......"+total);
	}
	
	
	@Test
	public void authorGetDetailTest() {
		int authorId =300;
		AuthorVO author = mapper.authorGetDetail(authorId);
		
		System.out.println("author....."+author);
		
	}

	
	@Test
	public void authorModifyTest() {
		AuthorVO author = new AuthorVO();
		
		author.setAuthorId(123);
		System.out.println("수정전......."+mapper.authorGetDetail(author.getAuthorId()));
	
		author.setAuthorName("수정");
		author.setNationId("01");
		author.setAuthorIntro("소개 수정하였습니다");
		
		mapper.authorModify(author);
		System.out.println("수정후......"+mapper.authorGetDetail(author.getAuthorId()));
	}*/
	
	//writer delete
	@Test
	public void authorDeleteTest() {
		int authorId = 123;
		int result = mapper.authorDelete(authorId);
		if(result == 1) {
			System.out.println("삭제 성공");
		}
		
	}

}
