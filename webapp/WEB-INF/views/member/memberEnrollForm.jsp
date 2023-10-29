<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>    

    <link rel="stylesheet" href="resources/css/member/memberEnrollForm.css">
    <script src="resources/js/member/memberEnrollForm.js"></script>

</head>
  <body>
  	<!-- header부분 (상단 메인 메뉴바) -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	
	<c:if test="${ errorMsg ne null }" >
		<script>
			alert("${ requestScope.errorMsg }");
		</script>
	</c:if>
	

  
    <form action="yrenroll.me" method="post">

      <h1 id="title"><b>회원가입</b></h1>
      <div class="container">

          <input type="text" placeholder="이름" name="memberName" id="memberName" maxlength="5" required>
          <label for="memberName">* 한글 2 ~ 5자로 입력 가능합니다.</label>
          
          <input type="text" placeholder="닉네임(활동명)" name="memberNickname" id="memberNickname" maxlength="8" required>
          <!-- <button type="button" onclick="nicknameCheck();">닉네임 중복확인</button> -->
          <label for="memberNickname">* 영문, 한글, 숫자 3 ~ 8자로 입력 가능합니다. </label>
          

          <input type="text" placeholder="아이디(중복불가)" name="memberId" id="memberId" maxlength="20" required>
          <!-- <button type="button" onclick="idCheck();">아이디 중복확인</button> -->
          <label for="memberId">* 영문, 숫자 5 ~ 20자로 입력 가능합니다.</label>
          
          
          <input type="password" placeholder="비밀번호" name="memberPwd" id="memberPwd" maxlength="20" required>
          <label for="memberPwd">* 영문, 숫자, 특수문자(!@#$+^*) 포함 8 ~ 20자로 입력 가능합니다.</label>

          <input type="password" placeholder="비밀번호 확인" name="memberPwdCheck" id="memberPwdCheck" maxlength="20" required>
          <label for="memberPwdCheck">* 비밀번호가 일치하지 않습니다.</label>
          
          <input type="text" placeholder="이메일" name="memberEmail" id="memberEmail" maxlength="50" required>
          <!-- <button type="button" onclick="emailCheck();">이메일 중복확인</button> -->
          <label for="memberEmail">* 인증받을 이메일을 입력해 주세요.</label>
          

          
          <div class="enroll-checkbox">
            <div>
              <input type="checkbox" id="agreeAll" required><label for="agreeAll"><b>전체 동의</b></label>
            </div>

            <div>
              <input type="checkbox" id="agreeSite" class="agree"><label for="agreeSite">사이트 이용약관 동의(필수)</label>
              <!-- 사이트 이용약관 동의 모달창-->
              <a data-toggle="modal" href="#agreeSiteModal">보기</a>

              <div class="modal" id="agreeSiteModal">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                      <h4 class="modal-title">사이트 이용약관 동의</h4>
                      <button type="button" class="close" data-dismiss="modal" id="close">&times;</button>
                      
                    </div>
                    <!-- Modal body -->
                    <div class="modal-body">
                      <label for="close">
                      서비스 이용약관
                      </label>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer">
                      <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                  </div>
                </div>
              </div>

            </div>

            <div>
              <input type="checkbox" id="agreePerson" class="agree"><label for="agreePerson">개인정보 수집 및 이용 동의(필수)</label>
              <!-- 개인정보 수집 및 이용 동의 모달창-->
              <a data-toggle="modal" href="#agreePersonModal">보기</a>

              <div class="modal" id="agreePersonModal">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                      <h4 class="modal-title">개인정보 수집 및 이용 동의</h4>
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <!-- Modal body -->
                    <div class="modal-body">
                      서비스 이용약관
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer">
                      <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                  </div>
                </div>
              </div>

            </div>
          </div>
          <!-- 제출버튼!!!!!!!!!!!!!!!!!! onclick = "return validate();"-->
          <button type="submit" id="submitBtn" disabled>가입하기</button>
      </div>
        
    </form>
      
    <!-- footer 푸터영역 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

  </body>
</html>