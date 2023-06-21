package com.vam.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.model.CartDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CartMapperTest {
	
	@Autowired
	private CartMapper mapper;
	
	//카트에 물건 넣기
	/*
	@Test
	public void addCart() throws Exception {
		String memberId = "12345";
		int bookId = 45;
		int count = 1;
		
		CartDTO cart = new CartDTO();
		cart.setMemberId(memberId);
		cart.setBookId(bookId);
		cart.setBookCount(count);
		
		int result = 0;
		result = mapper.addCart(cart);
		
		System.out.println("결과 : " + result);
		
	}	
	*/
	
	/* 카트 삭제 */
	/*
	@Test
	public void deleteCartTest() {
		int cartId = 7;
		
		mapper.deleteCart(cartId);
	}
	*/
	
	/* 카트 수량 수정 */
	/*
	@Test
	public void modifyCartTest() {
		int cartId = 8;
		int count = 5;
		
		CartDTO cart = new CartDTO();
		cart.setCartId(cartId);
		cart.setBookCount(count);
		
		mapper.modifyCount(cart);
		
	}*/
	
	/* 카트 목록 */ 
	/*
	@Test
	public void getCartTest() {
		String memberId = "12345";
		
		
		List<CartDTO> list = mapper.getCart(memberId);
		for(CartDTO cart : list) {
			System.out.println(cart);
			cart.initSaleTotal();
			System.out.println("init cart : " + cart);
		}
	}*/
	
	/* 카트 확인 */

	@Test
	public void checkCartTest() {
		
		String memberId = "12345";
		int bookId = 45;
		
		CartDTO cart = new CartDTO();
		cart.setMemberId(memberId);
		cart.setBookId(bookId);
		
		CartDTO resutlCart = mapper.checkCart(cart);
		System.out.println("결과 : " + resutlCart);
		
	}
	
}
