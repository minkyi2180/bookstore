package com.vam.model;

import lombok.Data;

@Data
public class OrderPageItemDTO {
	/*뷰로부터 전달받을 것*/
	private int bookId;
	private int bookCount;
	
	/*DB로부터 꺼내올것*/
	private String bookName;
	private int bookPrice;
	private double bookDiscount;
	
	/*만들어낼 값*/
	private int salePrice;
	private int totalPrice;
	private int point;
	private int totalPoint;
	
	public void initSaleTotal() {
		this.salePrice = (int)(this.bookPrice * (1-this.bookDiscount));
		this.totalPrice = this.salePrice * this.bookCount;
		this.point = (int)(Math.floor(this.salePrice*0.05));
		this.totalPoint = this.point * this.bookCount;
	}
}
