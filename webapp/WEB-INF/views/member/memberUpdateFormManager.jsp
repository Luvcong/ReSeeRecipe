<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.semi.member.model.vo.Member" %>
<%
	Member m = (Member)request.getAttribute("m");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정하기</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
   	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
	<link rel="stylesheet" href="resources/css/member/memberUpdateManager.css">  
</head>

<body>
	<%@ include file="../manager/navbar.jsp" %>
<div class="rs-content">
	<div class="rs-content">
    <h2>회원 정보 수정</h2>
    <br><br>
    <div class="container">
        <form class="form" action="<%=contextPath %>/hlupdatemember.ma" method="post" id="member-updateForm">
            
            <input type="hidden" id="invalidmemnickname" value="<%= loginMember.getMemNickname() %>">
            <input type="hidden" id="invalidmemEmail" value="<%= loginMember.getMemEmail() %>">
            <label for="memberNo">회원번호</label>
            <input type="text" class="form-control" id="memberNo" name="memNo" value="<%=m.getMemNo() %>" readonly>
            <br>
            <p class="tag">회원이름</p>
            <input type="text" class="form-control"  id="HL_memberName" name="memName" value="<%=m.getMemName() %>" required>
            <label for="HL_memberName">* 한글 2 ~ 5자로 입력 가능합니다.</label>
            <br>
            <p class="tag">회원아이디(변경불가)</p>
            <input type="text" class="form-control"  id="memberId"  name="memId" value="<%=m.getMemId()%>" readonly>
            <label for="memberId">* 영문, 숫자 5 ~ 20자로 입력 가능합니다.</label>
            <br>
            <p class="tag">회원닉네임</p>
            <input type="text" class="form-control"  id="HL_memberNickname" name="memNickname" value="<%=m.getMemNickname()%>" required>
            <label for="HL_memberNickname">* 영문, 한글, 숫자 3 ~ 8자로 입력 가능합니다. </label>
            <button type="button" class="btn btn-sm btn-primary" id="HL_invalidNick">중복확인</button>
            <br>
            <p class="tag">이메일</p>
            <input type="email" class="form-control"  id="HL_memberEmail" name="memEmail" value="<%=m.getMemEmail()%>" required>
            <label for="HL_memberEmail">* 인증받을 이메일을 입력해 주세요.</label>
            <button type="button" class="btn btn-sm btn-primary" id="invalidEmail">중복확인</button>
            <br>
            <label for="memberEnrolldate">가입일자</label>
            <input type="text" class="form-control"  id="memberEnrolldate" name="memEnrolldate" value="<%=m.getEnrollDate()%>" readonly>
            <br>
            <label for="memberModifydate">수정일자</lable>
            <input type="text" class="form-control" id="memberModifydate" name="memModifydate" value="<%=m.getModifyDate() %>" readonly>
            <br>
            <label for="membergradeNo">회원등급번호</label>
            <input type="text" class="form-control" id="membergradeNo" name="memgradeNo" value="<%= m.getMemGrade()%>" readonly>
            <br>
            <p class="tag">회원등급명</p>
            <input type="text" class="form-control"  id="membergradeName" name="memGradename" value="<%=m.getMemGradeName()%>" required>
            <label for="membergradeName">회원등급은 요린이 요리왕 요리마스터 입니다.</label>
            <br>
            <div id="memUpdateWhy">
            	<label for="memUpdateWhyCon">수정사유</label>
            	<textarea id="memUpdateWhyCon" name="memUpdateWhyCon" class="form-control"></textarea>
            </div>
            <br><br>
            <button type="submit" class="btn btn-warning">수정하기</button>
            <button type="button" class="btn btn-warning" onclick="history.back();">돌아가기</button>
        </form>
    </div> 
    </div>
</div> 
</body>

	<script src="resources/js/member/memberUpdateFormManager.js"></script>
</html>