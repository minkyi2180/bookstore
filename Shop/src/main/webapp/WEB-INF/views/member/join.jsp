<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css1/member/join.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
 <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>

   <div class="wrapper">
      <form id="join_form" method="post">
      <div class="wrap">
         <div class="subject">
            <span>회원가입</span>
         </div>
         <div class="id_wrap">
            <div class="id_name">아이디</div>
            <div class="id_input_box">
               <input class="id_input" name="memberId">
            </div>
            <span class="id_input_re_1">사용 가능한 아이디입니다.</span>
            <span class="id_input_re_2">아이디가 이미 존재합니다.</span>
            <span class="final_id_ck">아이디를 입력해주세요.</span>
         </div>
         
         
         <div class="pw_wrap">
            <div class="pw_name">비밀번호</div>
            <div class="pw_input_box">
               <input class="pw_input" name="memberPw">
            </div>
            <span class="final_pw_ck">비밀번호를 입력해주세요.</span>
         </div>
         <div class="pwck_wrap">
            <div class="pwck_name">비밀번호 확인</div>
            <div class="pwck_input_box">
               <input class="pwck_input">
            </div>
             <span class="final_pwck_ck">비밀번호 확인을 해주세요.</span>         
             <span class="pwck_input_re_1">비밀번호가 일치합니다</span>
             <span class="pwck_input_re_2">비밀번호가 일치하지 않습니다</span>
         </div>
         <div class="user_wrap">
            <div class="user_name">이름</div>
            <div class="user_input_box">
               <input class="user_input" name="memberName">
            </div>
             <span class="final_name_ck">이름을 입력해주세요.</span>
         </div>
         <div class="mail_wrap">
            <div class="mail_name">이메일</div>
            <div class="mail_input_box">
               <input class="mail_input" name="memberMail">
            </div>
            <span class="final_mail_ck">이메일을 입력해주세요.</span>
            <span class="mail_input_box_warn"></span>
            <div class="mail_check_wrap">
               <div class="mail_check_input_box" id="mail_check_input_box_false">
               <input class="mail_check_input" disabled="disabled">
            </div>
            <div class="mail_check_button">
               <span>인증번호 전송</span>
            </div>
            <div class="clearfix"></div>
            <span id="mail_check_input_box_warn"></span>
         </div>
      </div>
      <div class="address_wrap">
         <div class="address_name">주소</div>
         <div class="address_input_1_wrap">
            <div class="address_input_1_box">
               <input class="address_input_1" name="memberAddr1" readonly="readonly">
            </div>
            <div class="address_button" onclick="execution_daum_address()">
               <span>주소 찾기</span>
            </div>
            <div class="clearfix"></div>
         </div>
         <div class="address_input_2_wrap">
            <div class="address_input_2_box">
               <input class="address_input_2" name="memberAddr2" readonly="readonly">
            </div>
         </div>
         <div class="address_input_3_wrap">
            <div class="address_input_3_box">
               <input class="address_input_3" name="memberAddr3" readonly="readonly">
            </div>
            <span class="final_addr_ck">상세 주소를 입력해주세요.</span>
         </div>
      </div>
      <div class="join_button_wrap">
         <input type="button" class="join_button" value="가입하기">
      </div>
      </div>
      </form>
   </div>
   
<script>
var code = ""; //이메일 전송 인증번호 저장을 위한 코드

//유효성 검사 통과유무 변수
var idCheck = false;
var idckCheck = false;
var pwCheck = false;
var pwckCheck = false;
var pwckcorCheck = false;
var nameCheck = false;
var mailCheck = false;
var mailnumCheck = false;
var addressCheck = false;

$(document).ready(function(){
   //회원가입 버튼 (회원가입 기능 작동)
   $(".join_button").click(function(){
      //alert("회원가입 성공");
      //$("#join_form").attr("action", "/member/join");
      //$("#join_form").submit();
      
      /*입력값 변수*/
      var id =$('.id_input').val();
      var pw =$('.pw_input').val();
      var pwck =$('.pwck_input').val();
      var name =$('.user_input').val();
      var mail =$('.mail_input').val();
      var addr =$('.address_input_3').val();
      
      /*아이디 유효성 검사*/
      if(id == ""){
         $('.final_id_ck').css('display','block');
         idCheck = false;
      }else{
         $('.final_id_ck').css('display','none');
         idCheck = true;
      }
      
      /*비밀번호 확인 유효성 검사*/
      if(pwck == ""){
         $('.final_pwck_ck').css('display','block');
         pwckCheck = false;
      }else{
         $('.final_pwck_ck').css('display','none');
         pwckCheck = true;
      }
      
      /*이름 유효성*/
      if(name==""){
         $('.final_name_ck').css('display','block');
         nameCheck = false;
      }else{
         $('.final_name_ck').css('display','none');
         nameCheck = true;
      }
      
      /*이메일 유효성 검사*/
      if(mail == ""){
         $('.final_mail_ck').css('display','block');
         mailCheck = false;
      }else{
         $('.final_mail_ck').css('display','none');
         mailCheck = true;
      }
      
      /*주소 유효성 검사*/
      if(addr == ""){
         $('.final_addr_ck').css('display','block');
         addressCheck = false;
      }else{
         $('.final_addr_ck').css('display','none');
         addressCheck = true;
      }
      
      /*최종 유효성 검사*/
      if(idCheck&&idckCheck&&pwckCheck&&pwckcorCheck&&nameCheck&&mailCheck&&mailnumCheck&&addressCheck){
         $("#join_form").attr("action", "/member/join");
         $("#join_form").submit();
      }
      return false;
   });
});

$('.id_input').on("propertychange change keyup paste input", function() {
   //alert("test");
   //console.log("keyup 테스트");
   
   var memberId = $('.id_input').val(); //.id_input에 입력되는 값
   var data = {memberId : memberId} //컨트롤에 넘길 데이터 이름
   
   $.ajax({
      type : "post",
      url : "/member/memberIdChk",
      data : data,
      success : function(result){
//         console.log("성공 여부" + result);
      if(result != 'fail'){
         $('.id_input_re_1').css("display","inline-block");
         $('.id_input_re_2').css("display","none");
         idckCheck = true;
      }else{
         $('.id_input_re_2').css("display","inline-block");
         $('.id_input_re_1').css("display","none");
         idckCheck = false;
      }
      }
   });   
});

   $(".mail_check_button").click(function(){
      var email = $(".mail_input").val(); //입력한 이메일
      var checkBox = $(".mail_check_input");
      var boxWrap = $(".mail_check_input_box");
      var warnMsg = $(".mail_input_box_warn");
      
      /*이메일 형식 유효성 검사*/
      if(mailFormCheck(email)){
    	  warnMsg.html("이메일이 전송되었습니다. 이메일을 확인해주세요");
    	  warnMsg.css("display","inline-block");
      }else{
    	  warnMsg.html("올바르지 못한 이메일 형식입니다");
    	  warnMsg.css("display","inline-block");
    	  return false;
      }
      
      $.ajax({
      type:"get",
      url:"mailCheck?email="+ email,
      success:function(data){
         //console.log("data : "+data);
         checkBox.attr("disabled", false);
         boxWrap.attr("id","mail_check_input_box_true");
         code = data;
      }
      });
   });

   $(".mail_check_input").blur(function(){
      var inputCode = $(".mail_check_input").val();
      var checkResult = $("#mail_check_input_box_warn");
      
      if(inputCode == code){
         checkResult.html("인증번호가 일치합니다");
         checkResult.attr("class","correct");
         mailnumCheck = true;
      }else{
         checkResult.html("인증번호를 다시 확인해주세요");
         checkResult.attr("class","incorrect");
         mailnumCheck = false;
      }
   });
   
   
   //다음주소연동
   function execution_daum_address(){
    new daum.Postcode({
        oncomplete: function(data) {
             var addr = ''; // 주소 변수
              var extraAddr = ''; // 참고항목 변수

              //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
              if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                  addr = data.roadAddress;
              } else { // 사용자가 지번 주소를 선택했을 경우(J)
                  addr = data.jibunAddress;
              }

              // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
              if(data.userSelectedType === 'R'){
                  // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                  // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                  if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                      extraAddr += data.bname;
                  }
                  // 건물명이 있고, 공동주택일 경우 추가한다.
                  if(data.buildingName !== '' && data.apartment === 'Y'){
                      extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                  }
                  // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                  if(extraAddr !== ''){
                      extraAddr = ' (' + extraAddr + ')';
                  }
                  // 조합된 참고항목을 해당 필드에 넣는다.
                 addr += extraAddr;
              
              } else {
                  addr += '';
              }

              // 우편번호와 주소 정보를 해당 필드에 넣는다.
            //document.getElementById('sample6_postcode').value = data.zonecode;
            //document.getElementById("sample6_address").value = addr;
            $(".address_input_1").val(data.zonecode);
            $(".address_input_2").val(addr);
              // 커서를 상세주소 필드로 이동한다.
              //document.getElementById("sample6_detailAddress").focus();
              //상세주소 입력란 disabled 속성 변경 및 커서를 상세주소 필드로 이동한다
              $(".address_input_3").attr("readonly",false);
              $(".address_input_3").focus();
        }
    }).open();
   }
   
   /*비밀번호 확인 일치 유효성 검사*/
   $('.pwck_input').on("propertychange change keyup paste input", function() {
      var pw = $('.pw_input').val();
      var pwck = $('.pwck_input').val();
      $('.final_pwck_ck').css('display','none');
      
      if(pw == pwck){
         $('.pwck_input_re_1').css('display','block');
         $('.pwck_input_re_2').css('display','none');
         pwckcorCheck= true;
      }else{
         $('.pwck_input_re_1').css('display','none');
         $('.pwck_input_re_2').css('display','block');
         pwckcorCheck= false;
      }
   });
   
   /*입력 이메일 형식 유효성 검사*/
function mailFormCheck(email){
	var form= /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	return form.test(email);
}
</script>

</body>
</html>