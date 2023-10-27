<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String checkPwd = (String)session.getAttribute("checkPwd"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보변경</title>


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>  
<link rel="stylesheet" href="resources/css/member/memberUpdateConfirm.css">

</head>
<body>
    <!-- header부분 (상단 메인 메뉴바) -->
	<%@ include file="/views/common/header.jspf" %>

    <div>
        <form action="<%= contextPath %>/yrmemberUpdateConfirm.me" method="post">
            <h1 id="title"><b>회원정보변경</b></h1>
            <div class="container">
                <p>본인확인을 위한 비밀번호 입력창입니다.</p>
	            <input type="password" name="checkPwd" placeholder="비밀번호를 입력해 주세요." required>
	            <input type="hidden" name="memberNo" value="<%= loginMember.getMemNo() %>">
	            <input type="hidden" name="memberPwd" value="<%= loginMember.getMemPwd() %>">
	            <button type="submit" onclick="error();">확인</button>
            </div>
        </form>
    </div>
    
    <% if(checkPwd != null && loginMember != null && !checkPwd.equals(loginMember.getMemPwd())) { %>
    	<script>
		Swal.fire({
			icon: 'error',
			title: '비밀번호 불일치',
			text: '비밀번호가 일치하지 않습니다!'
    	})
         </script>
         
    <% //checkPwd = null; 
    } %>
    


    <!-- footer 푸터영역 -->
	<%@ include file="/views/common/footer.jspf" %>

</body>
</html>