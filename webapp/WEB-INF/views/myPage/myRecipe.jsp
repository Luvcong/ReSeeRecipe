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
		#myRecipe{
        border:1px solid black;
        width:900px;
        height:900px;
        }
        .myRecipeList{
        bolder:1px solid black;
        width:250px;
        height:250px;
        float: left;
        margin-left:30px;
        margin-top:30px;
        text-align: center;
        }
        
        a{
        text-decoration:none;
        color:black;
        
        }
        
        #recipePic1{
        border:1px solid black;
        width:180px;
        height:180px;
        margin-top:15px;
        margin-left:35px;
        margin-bottom: 10px;
        }
        #recipePic2{
        border:1px solid black;
        width:180px;
        height:180px;
        margin-top:15px;
        margin-left:35px;
        margin-bottom: 10px;
        }
        #recipePic3{
        border:1px solid black;
        width:180px;
        height:180px;
        margin-top:15px;
        margin-left:35px;
        margin-bottom: 10px;
        }
        #recipePic4{
        border:1px solid black;
        width:180px;
        height:180px;
        margin-top:15px;
        margin-left:35px;
        margin-bottom: 10px;
        }
        #recipePic5{
        border:1px solid black;
        width:180px;
        height:180px;
        margin-top:15px;
        margin-left:35px;
        margin-bottom: 10px;
        }
        #recipePic6{
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
		<div id="myRecipe">
        <h3>내 레시피</h3>
        <hr>
        	<div id="myRecipeList1" class="myRecipeList">
        	<div id="recipePic1"></div>
        	<div id="recipeTitle1"><a href="#" id="title">제목</a></div>
        	
        </div>
            <div id="myRecipeList2" class="myRecipeList">
        	<div id="recipePic2"></div>
        	<div id="recipeTitle2"><a href="#" id="title">제목</a></div>
        </div>
            <div id="myRecipeList3" class="myRecipeList">
        	<div id="recipePic3"></div>
        	<div id="recipeTitle3"><a href="#" id="title">제목</a></div>
        	</div>
            <div id="myRecipeList4" class="myRecipeList">
        	<div id="recipePic4"></div>
        	<div id="recipeTitle4"><a href="#" id="title">제목</a></div>
        	</div>
            <div id="myRecipeList5" class="myRecipeList">
        	<div id="recipePic5"></div>
        	<div id="recipeTitle5"><a href="#" id="title">제목</a></div>
        	</div>
            <div id="myRecipeList6" class="myRecipeList">
        	<div id="recipePic6"></div>
        	<div id="recipeTitle6"><a href="#" id="title">제목</a></div>
        </div>
		</div>
		</div>



<%@ include file="/views/common/footer.jspf" %>

</body>
</html>