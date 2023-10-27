<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.semi.board.recipe.model.vo.Recipe,
				 com.kh.semi.common.model.vo.PageInfo,
				 java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 메인</title>
	<link rel="stylesheet" href="resources/css/board/recipe/recipeMainView.css">
</head>


<body>
	<!-- 헤더, 레시피 카테고리바, 레시피 정렬바 -->
	<jsp:include page="/views/common/header.jsp" />
	<jsp:include page="/views/board/recipe_frag/recipeCategoryBar.jsp" />
	<jsp:include page="/views/board/recipe_frag/recipeSortBar.jsp" />

	<!-- 전체를 감싸는 div -->
	<div id="recipeMainViewWrap">

		<div id="recipeThumnailWrap">
			<c:if test="${ !empty requestScope.recipeList }">
				<c:forEach var="recipeList" items="${ requestScope.recipeList }">
					<div class="thumbnail-contariner">
						<div class="thumbnail-top send-detail-view">
							<img src="${pageContext.request.contextPath}${ recipeList.titleImg }">
						</div>
						<div class="thumbnail-bottom">
							<div class="thumbnail-bottom-inner1 send-detail-view">
								<span id="SendDetailViewSelectedRecipeNo">${ recipeList.recipeNo }</span>
								<p>${ recipeList.recipeTitle }</p>
							</div>
							<div class="thumbnail-bottom-inner2">
								<div class="thumbnail-left-bottom">
									<div class="thumbnail-left-bottom-chef">
										<p>${ recipeList.memNickName }</p>
									</div>
								</div>
								<div class="thumbnail-right-bottom">
									<div class="thumbnail-right-bottom-inner1">
										<p>${ recipeList.recipeCount }</p>
										<i class='fas fa-eye'></i>
									</div>
									<div class="thumbnail-right-bottom-inner2">
										<p>${ recipeList.htCount }</p>
										<i class="fa fa-heart"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
		
		
		<!-- 페이지네이션 영역 -->
		<div id="recipeMainPagationContainer">
			<c:if test="${ reqeustScope.pi.currentPage ne 1 }">
				<button id="goPrevPage" class="btn btn-sm btn-info">&lt;</button>
			</c:if>
			
			<c:forEach var="i" begin="${ pi.startPage }" end="${ pi.endPage }">
				<c:choose>
					<c:when test="${ pi.currentPage ne i }">
						<button class="btn btn-sm btn-info certainPages">${ i }</button>
					</c:when>
					<c:otherwise>
						<button disabled class="btn btn-sm btn-info">${ i }</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${ pi.currentPage ne pi.maxPage }">
				<button id="goNextPage" class="btn btn-sm info">&gt;</button>				
			</c:if>
		</div>
	</div>
	<!-- 전체를 감싸는 div -->
	
	
	<script src="resources/js/board/recipe/recipeMainView.js"></script>
	
	<!-- 푸터 -->
	<jsp:include page="/views/common/footer.jsp" />
</body>
</html>