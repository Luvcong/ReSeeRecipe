<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.semi.member.model.vo.Member,
				 com.kh.semi.board.recipe.model.vo.RecipeCategory,
				 java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피카테고리바</title>
	<!-- jQuery / 부트스트랩 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

	<link rel="stylesheet" href="resources/css/board/recipe_frag/recipeCategoryBar.css">
</head>


<body>

    <div id="recipe-category-wrap">
        <div id="category-toggle-menu">
            <table id="recipe-category-table">
                <tr>
                    <td rowspan="2" id="recipe-category-logo">
                        <img src=".."> 로고 이미지<!-- 다른곳에서 include 시 깨짐 -> 인크루드 생각해서 경로 정하거나 절대경로 -->
                    </td>
                    <td><h3>한식</h3></td>
                    <td><h3>양식</h3></td>
                    <td><h3>중식</h3></td>
                    <td><h3>일식</h3></td>
                </tr>
                <tr>
                    <td><h3>아시안</h3></td>
                    <td><h3>야식</h3></td>
                    <td><h3>디저트</h3></td>
                    <td><h3>음료</h3></td>
                </tr>
            </table>
        </div>
        <div id="category-toggle-msg">
            <h3>카테고리 접기</h3>
        </div>
    </div>
    
	<script src="resources/js/board/recipe_frag/recipeCategoryBar.js"></script>
</body>
</html>