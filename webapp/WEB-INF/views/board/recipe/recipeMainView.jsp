<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.semi.board.recipe.model.vo.Recipe,
				 com.kh.semi.common.model.vo.PageInfo,
				 java.util.ArrayList" %>
<%	
	// ArrayList로 받아온 recipeList (사진 경로 titleImg있음)
	ArrayList<Recipe> recipeList = (ArrayList<Recipe>)request.getAttribute("recipeList");
	
	// 페이지네이션 변수
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 메인</title>

<style>
	div {
		box-sizing: border-box;

	}

	/*******************	Wrapping divs 세팅 *******************/
	/* 전체 wrap */
	#recipeMainViewWrap {
		width: 1200px;
		margin: auto;
	}
	
	/* 레시피글 디스플레이 영역 wrap */
	#recipeThumnailWrap {
		width: 100%;
		height: 100%;
		padding: 35px 25px 50px 25px;
	}

	/******************* 레시피글 블록(단일 글) 세팅 *******************/
	/* 레시피 글 별 전체 container */
	#recipeMainViewWrap .thumbnail-contariner {
		width: 349px;
		height: 500px;
		display: inline-block;
		margin: 20px 15px 30px 15px;
		border: 1px solid black !important;
	}
	
	.send-detail-view:hover {
		cursor: pointer;
	}
	
	/* 상단 (썸네일 이미지) */
	#recipeMainViewWrap .thumbnail-top {
		width: 100%;
		height: 347px;
	}

	#recipeMainViewWrap .thumbnail-top img {
		width:100%;
		height: 100%;
	}

	/* 하단 (제목 + 셰프이름 + 조회수/아이콘 + 좋아요개수/아이콘) */
	#recipeMainViewWrap .thumbnail-bottom {
		width: 100%;
		height: 151px;
	}
	
	/* 쿼리스트링 넘길 RecipeNo 숨김처리 */
	#SendDetailViewSelectedRecipeNo {
		display: none;
	}

	/* 하단 top */
	#recipeMainViewWrap .thumbnail-bottom-inner1 {
		width: 100%;
		height: 65%;
	}
	
	
	/* 하단 bottom (조회수 + 좋아요수) */
	#recipeMainViewWrap .thumbnail-bottom-inner2 {
		width: 100%;
		height: 35%;
	}

	#recipeMainViewWrap .thumbnail-bottom-inner2 div {
		height: 100%;
		float: left;
	}

	/* 쉐프 이름 */
	#recipeMainViewWrap .thumbnail-left-bottom {
		width: 70%;
	}

	#recipeMainViewWrap .thumbnail-left-bottom-chef {
		width: 100%;
	}

	/* 조회수 + 좋아요 */
	#recipeMainViewWrap .thumbnail-right-bottom {
		width: 30%;
	}

	#recipeMainViewWrap .thumbnail-right-bottom div {
		width: 100%;
		height: 50%;
	}

	#recipeMainViewWrap .thumbnail-right-bottom p {
		margin: 0px;
		display: inline-block;
		padding: 3px 0px 0px 10px;
	}

	#recipeMainViewWrap .thumbnail-right-bottom i {
		font-size: 20px;
		float: right;
		padding: 5px 10px 0px 0px;
	}


	/* 조회수 */
	#recipeMainViewWrap .thumbnail-right-bottom-inner1 i {
		color: rgb(255, 145, 77);
	}

	/* 좋아요 */
	#recipeMainViewWrap .thumbnail-right-bottom-inner2 i {
		color: red;
	}
</style>

</head>
<body>
	<!-- 헤더, 레시피 카테고리바, 레시피 정렬바 -->
	<%@ include file="/views/common/header.jspf" %>
	<%@ include file="/views/board/recipe_frag/recipeCategoryBar.jspf" %>
	<%@ include file="/views/board/recipe_frag/recipeSortBar.jspf" %>
	
	<script>
		// jsp파일 로딩 시 카테고리 접힘상태로 로딩시켜주는 함수
		$(function(){
			const categoryFoldingText = $('#category-toggle-msg > h3');
			categoryFoldingText.text('카테고리 더보기');
			$('#category-toggle-menu').css('display', 'none');
		});
	</script>

	

	<!-- 전체를 감싸는 div -->
	<div id="recipeMainViewWrap">
		<!-- 레시피 글 블록 wrap -->
		<div id="recipeThumnailWrap">
			<% if(!recipeList.isEmpty()) { %>
				<!-- ***********[[ 여기가 조회된 레시피 글 만들어줘야할 부분 ]] ***************** -->
				<% for(int i = 0; i < recipeList.size(); i++) { %>
					<!-- 레시피 글 블록 (* 9개 필요) -->
					<div class="thumbnail-contariner">
						<!-- 상단 이미지부분 -->
						<div class="thumbnail-top send-detail-view">
							<img src="<%=contextPath%><%=recipeList.get(i).getTitleImg()%>">
						</div>
						
						<!-- 하단 레시피정보부분 -->
						<div class="thumbnail-bottom">
							<div class="thumbnail-bottom-inner1 send-detail-view">
								<span id="SendDetailViewSelectedRecipeNo"><%=recipeList.get(i).getRecipeNo()%></span>
								<p><%= recipeList.get(i).getRecipeTitle() %></p>
							</div>
	
							<div class="thumbnail-bottom-inner2">
								<div class="thumbnail-left-bottom">
									<div class="thumbnail-left-bottom-chef">
										<p><%= recipeList.get(i).getMemNickName() %></p>
									</div>
								</div>
	
								<div class="thumbnail-right-bottom">
									<div class="thumbnail-right-bottom-inner1">
										<!-- 조회수 -->
										<p><%= recipeList.get(i).getRecipeCount() %></p>
										<i class='fas fa-eye'></i>
									</div>
									<div class="thumbnail-right-bottom-inner2">
										<!-- 좋아요수 -->
										<p><%= recipeList.get(i).getHtCount() %></p>
										<i class="fa fa-heart"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
				<% } %>
			<% } %>
				
		</div>
		
		<script>
			$('.send-detail-view').on('click', function(){
				location.href = '<%=contextPath%>/recipeDetail.re?recipeNo=' + $('#SendDetailViewSelectedRecipeNo').text();					
			});
		
		</script>
		
		
		<!-- 페이지네이션 영역 -->
		<div id="recipeMainPagationContainer">
			<% if(currentPage != 1) { %>
				<button id="goPrevPage" class="btn btn-sm btn-info">&lt;</button>
			<% } %>
			
			<% for(int i = startPage; i <= endPage; i++) { %>
				<% if(currentPage != i) { %>
					<button class="btn btn-sm btn-info certainPages"><%=i%></button>
				<% } else { %>
					<button disabled class="btn btn-sm btn-info"><%=i%></button>
				<% } %>
				
			<% } %>
			<% if(currentPage != maxPage) { %>
				<button id="goNextPage" class="btn btn-sm info">&gt;</button>				
			<% } %>
		</div>
		
		
		
		<script>
			$('#goPrevPage').click(function(){
				
				 location.href = '<%=contextPath%>/selectRecipeList.re?currentPage=<%= currentPage - 1 %>';
			});
		
			$('.certainPages').click(function(){
				location.href = '<%=contextPath%>/selectRecipeList.re?currentPage=' + $(this).html();
			});
		
			$('#goNextPage').click(function(){
				location.href = '<%=contextPath%>/selectRecipeList.re?currentPage=<%= currentPage + 1 %>';
			});
		</script>
		
		
		
	</div>
	<!-- 전체를 감싸는 div -->
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<!-- 푸터 -->
	<%@ include file="/views/common/footer.jspf" %>
</body>
</html>