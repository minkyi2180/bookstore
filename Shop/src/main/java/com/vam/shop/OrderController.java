package com.vam.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vam.model.OrderPageDTO;
import com.vam.service.MemberService;
import com.vam.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/order/{memberId}")
	public void orderPageGET(@PathVariable("memberId") String memberId, OrderPageDTO opd, Model model){
		System.out.println("memberId: "+memberId);
		System.out.println("orders: "+opd.getOrders());
	}
	
	//@GetMapping("/order/{memberId}")
	//public String orderPageGET
}
