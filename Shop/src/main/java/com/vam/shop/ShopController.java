package com.vam.shop;


import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.vam.mapper.AttachMapper;
import com.vam.model.AttachImageVO;
import com.vam.model.BookVO;
import com.vam.model.Criteria;
import com.vam.model.PageDTO;
import com.vam.service.BookService;

@Controller
public class ShopController {
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private BookService bookService;
	
	//메인페이지로 이동
	@GetMapping("/main")
	public void mainPageGET(Model model) {
		logger.info("메인페이지 진입");
		
		model.addAttribute("cate1", bookService.getCateCode1());
		model.addAttribute("cate2", bookService.getCateCode2());
		
	}
	
	
	/* 이미지 출력 */
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		
		logger.info("getImage()........" + fileName);
		
		File file = new File("c:\\upload\\" + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/* 이미지 정보 반환 */
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> getAttachList(int bookId){
		
		logger.info("getAttachList.........." + bookId);
		
		return new ResponseEntity<List<AttachImageVO>>(attachMapper.getAttachList(bookId), HttpStatus.OK);
	}
		
		
		
	
	//이미지 파일 삭제
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		logger.info("deleteFile....."+fileName);
		File file = null;
		
		try {
			//썸네일 파일 삭제
			file = new File("c:\\upload\\" +URLDecoder.decode(fileName, "UTF-8"));
			file.delete();
			
			//원본파일삭제
			String originFileName = file.getAbsolutePath().replace("s_", "");
			
			logger.info("originFileName : "+originFileName);
			file = new File(originFileName);
			file.delete();
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("fail",HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	
	//상품 검색
	@GetMapping("search")
	public String searchGoodsGET(Criteria cri, Model model) {
		logger.info("cri : "+cri);
		List<BookVO> list = bookService.getGoodsList(cri);
		logger.info("pre list : " +list);
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			logger.info("list : "+list);
		}else {
			model.addAttribute("listcheck","empty");
			return "search";
		}
		model.addAttribute("pageMaker", new PageDTO(cri, bookService.goodsGetTotal(cri)));
		
		String[] typeArr = cri.getType().split("");
		for(String s : typeArr) {
			if(s.equals("T") || s.equals("A")){
			model.addAttribute("filter_info", bookService.getCateInfoList(cri));
			}
		}return "search";
	}
	
	//상품상세
	@GetMapping("/goodsDetail/{bookId}")
	public String goodsDetailGET(@PathVariable("bookId")int bookId, Model model) {
		logger.info("goodsDetailsGet()......");
		
		model.addAttribute("goodsInfo", bookService.getGoodsInfo(bookId));
		return "/goodsDetail";
	}
	
	/* 리뷰 쓰기 */
	@GetMapping("/replyEnroll/{memberId}")
	public String replyEnrollWindowGET(@PathVariable("memberId")String memberId, int bookId, Model model) {
		BookVO book = bookService.getBookIdName(bookId);
		model.addAttribute("bookInfo", book);
		model.addAttribute("memberId", memberId);
		
		return "/replyEnroll";
	}
	
}
