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
        #chef{
        border:1px solid black;
        width:900px;
        height:900px;
        float: left;
        }
        .myChefList{
        bolder:1px solid black;
        width:250px;
        height:250px;
        float: left;
        margin-left:30px;
        margin-top:30px;
        text-align: center;
        }
        #chefPic1{
        border:1px solid black;
        width:180px;
        height:180px;
        margin-top:15px;
        margin-left:35px;
        margin-bottom: 10px;
        }
        #chefPic2{
        border:1px solid black;
        width:180px;
        height:180px;
        margin-top:15px;
        margin-left:35px;
        margin-bottom: 10px;
        }
        #chefPic3{
        border:1px solid black;
        width:180px;
        height:180px;
        margin-top:15px;
        margin-left:35px;
        margin-bottom: 10px;
        }
        #pageCenter{
        border: 1px solid black;
    	width:100%;
    	height: 1200px;
    	display: flex;
        justify-content: center;
        }
        a{
        text-decoration:none;
        color:black;
        
        }
</style>
</head>
<body>

<%@ include file="/views/common/header.jspf" %>
        
        
        <div id="pageCenter">
        <div id="chef">
        <h3>구독셰프</h3>
        <hr>
	        <div id="myChefList1" class="myChefList">
        	<div id="chefPic1"></div>
        	<div id="chefId1"><a href="#" id="id">셰프아이디</a></div>
        </div>
	        <div id="myChefList2" class="myChefList">
        	<div id="chefPic2"></div>
        	<div id="chefId2"><a href="#" id="id">셰프아이디</a></div>
        </div>
  	        <div id="myChefList3" class="myChefList">
        	<div id="chefPic3"></div>
        	<div id="chefId3"><a href="#" id="id">셰프아이디</a></div>
        </div>
              <div id="myChefList3" class="myChefList">
        	<div id="chefPic3"></div>
        	<div id="chefId3"><a href="#" id="id">셰프아이디</a></div>
        </div>
              <div id="myChefList3" class="myChefList">
        	<div id="chefPic3"></div>
        	<div id="chefId3"><a href="#" id="id">셰프아이디</a></div>
        </div>
              <div id="myChefList3" class="myChefList">
        	<div id="chefPic3"></div>
        	<div id="chefId3"><a href="#" id="id">셰프아이디</a></div>
        </div>
		</div>
		</div>





<%@ include file="/views/common/footer.jspf" %>

</body>
</html>