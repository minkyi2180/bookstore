package com.vam.model;

import lombok.Data;

@Data
public class AttachImageVO {
	private String uploadPath;		//경로
	private String uuid;	//uuid
	private String fileName;	//파일이름
	private int bookId;		//상품id

}
