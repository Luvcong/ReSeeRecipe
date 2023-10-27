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
        #recipeBook{
        border:1px solid black;
        width:900px;
        height:900px;
        float: left;
        }
        .myRecipeBookList{
        bolder:1px solid black;
        width:250px;
        height:250px;
        float: left;
        margin-left:30px;
        margin-top:30px;
        text-align: center;
        }
        #recipeBookPic1{
        border:1px solid black;
        width:180px;
        height:180px;
        margin-top:15px;
        margin-left:35px;
        margin-bottom: 10px;
        }
        #recipeBookPic2{
        border:1px solid black;
        width:180px;
        height:180px;
        margin-top:15px;
        margin-left:35px;
        margin-bottom: 10px;
        }
        #recipeBookPic3{
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
		<div id="recipeBook">
        <h3>레시피북</h3>
        
        
	        <div id="myRecipeBook1" class="myRecipeBookList">
        	<div id="recipeBookPic1"></div>
        	<div id="recipeBookTitle1"><a href="#" id="recipeBookTt">레시피북 제목</a></div>
        </div>
	        <div id="myRecipeBook2" class="myRecipeBookList">
        	<div id="recipeBookPic2"></div>
        	<div id="recipeBookTitle2"><a href="#" id="recipeBookTt">레시피북 제목</a></div>
        </div>
	        <div id="myRecipeBook3" class="myRecipeBookList">
        	<div id="recipeBookPic3"></div>
        	<div id="recipeBookTitle3"><a href="#" id="recipeBookTt">레시피북 제목</a></div>
        </div>
        	        <div id="myRecipeBook3" class="myRecipeBookList">
        	<div id="recipeBookPic3"></div>
        	<div id="recipeBookTitle3"><a href="#" id="recipeBookTt">레시피북 제목</a></div>
        </div>
        	        <div id="myRecipeBook3" class="myRecipeBookList">
        	<div id="recipeBookPic3"></div>
        	<div id="recipeBookTitle3"><a href="#" id="recipeBookTt">레시피북 제목</a></div>
        </div>
        	        <div id="myRecipeBook3" class="myRecipeBookList">
        	<div id="recipeBookPic3"></div>
        	<div id="recipeBookTitle3"><a href="#" id="recipeBookTt">레시피북 제목</a></div>
        </div>
		</div>
		</div>	
		

<%@ include file="/views/common/footer.jspf" %>

</body>
</html>