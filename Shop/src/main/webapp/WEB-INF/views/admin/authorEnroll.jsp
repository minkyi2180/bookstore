<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="../resources/css1/admin/authorEnroll.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<body>

<%@ include file="../includes/admin/header.jsp" %>
			
			<div class="admin_content_wrap">
				<div class="admin_content_subject">
				<span>작가 등록 페이지입니다.</span>
				</div>
				<div class="admin_content_main">
					<form action="/admin/authorEnroll.do" method="post" id="enrollForm">
						<div class="form_section">
							<div class="form_section_title">
								<label>작가 이름</label>
							</div>
							
							<div class="form_section_content">
								<input name="authorName">
								<span id="warn_authorName">작가 이름을 입력해주세요</span>
							</div>
						</div>
						
						<div class="form_section">
							<div class="form_section_title">
								<label>소속 국가</label>
							</div>
							<div class="form_section_content">
								<select name="nationId">
									<option value="none" selected>====== 선택 =====</option>
									<option value="01">국내</option>
									<option value="02">국외</option>
								</select>
								<span id="warn_nationId">소속 국가를 선택해주세요</span>
							</div>
						</div>
						
						<div class="form_section">
							<div class="form_section_title">
								<label>작가 소개</label>
							</div>
							<div class="form_section_content">
								<input name="authorIntro" type="text">
								<span id="warn_authorIntro">작가 소개를 입력해주세요</span>
							</div>
						</div>
					</form>
					<div class="btn_section">
						<button id="cancelBtn" class="btn">취 소</button>
						<button id="enrollBtn" class="btn enroll_btn">등 록</button>
					</div>
				</div>
				</div>
<%@ include file="../includes/admin/footer.jsp" %>

<script>
/*취소버튼*/
$("#cancelBtn").click(function(){
	location.href="/admin/authorManage"
});

/*등록버튼*/
$("#enrollBtn").click(function(){
	let nameCheck = false;
	let nationCheck = false;
	let introCheck =false;
	
	let authorName = $('input[name=authorName]').val();
	let nationId = $('select[name=nationId]').val();
	let authorIntro = $('input[name=authorIntro]').val();
	
	let wAuthorName = $('#warn_authorName');
	let wNationId = $('#warn_nationId');
	let wAuthorIntro = $('#warn_authorIntro');
	
	if(authorName ===''){
		wAuthorName.css('display','block');
		nameCheck = false;
	}else{
		wAuthorName.css('display', 'none');
		nameCheck = true;
	}
	
	if(nationId === 'none'){
		wNationId.css('display','block');
		nationCheck = false;
	}else{
		wNationId.css('display', 'none');
		nationCheck = true;
	}
	
	if(authorIntro === ''){
		wAuthorIntro.css('display','block');
		introCheck = false;
	}else{
		wAuthorIntro.css('display', 'none');
		introCheck = true;
	}
	
	if(nameCheck && nationCheck && introCheck){
	$("#enrollForm").submit();
	}else{
		return;
	}
});
</script>
</body>
</html>