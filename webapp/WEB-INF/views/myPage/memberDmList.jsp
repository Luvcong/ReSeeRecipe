<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Black+Ops+One&family=Noto+Sans+KR&family=Parisienne&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

	<style>
       a{
       text-decoration:none;
       color:black;
       }
       #post{
       	border:1px solid black;
       	width:900px;
       	height:900px;
       }
       #postHeader{
       border:1px solid black;
       }
       #postMain{
       	border:1px solid black;
       }
    </style>

</head>
<body>

<%@ include file="/views/common/header.jspf" %>
	
	<div id="post">
	<table id="postMain">
	<thead id="postHeader">
	<tr>
	<th>번호</th>
	<th>제목</th>
	<th>보낸 사람</th>
	<th>보낸 날짜</th>
	</tr>
	</thead>
	
	<tbody>
	<tr>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	</tr>
	</tbody>
	
	
	</table>
	</div>




<%@ include file="/views/common/footer.jspf" %>


</body>
</html>