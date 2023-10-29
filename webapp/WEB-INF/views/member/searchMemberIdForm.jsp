<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="resources/css/member/searchMemberIdForm.css">

</head>
<body>
	<!-- header부분 (상단 메인 메뉴바) -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

    <!-- ajax로 처리할 것 
    <form action="yrsearchMemberId.me" method="post"> -->
      <h1 id="title"><b>아이디 찾기</b></h1>
      <div class="container">
          <input type="text" placeholder="이름" name="memberName" required>
          <input type="text" placeholder="이메일" name="memberEmail" required>
          <!-- 인증코드 입력창 -->
          <input type="text" placeholder="인증코드 입력" name="certificationCode" required>
          <!-- <button type="submit">확인</button> -->
          <button type="button" onclick="searchMemberId();">확인</button>
          <div id="searchMemberId"> </div>
          <button type="button" onclick="location.href='yrloginForm.me'">로그인하러 가기</button>
        </div>
      <!-- </form> -->
      
    <!-- footer 푸터영역 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

  <script src="resources/js/member/searchMemberIdForm.js"></script>

</body>
</html>