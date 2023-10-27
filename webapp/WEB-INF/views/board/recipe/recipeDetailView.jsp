<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,
			     java.util.HashMap,
			     com.kh.semi.board.recipe.model.vo.Recipe,
			     com.kh.semi.board.recipe.model.vo.RecipePic,
			     com.kh.semi.board.recipe.model.vo.Ingredient,
			     com.kh.semi.board.recipe.model.vo.CookSteps,
			     com.kh.semi.board.recipe.model.vo.RecipeTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 디테일뷰</title>
	<link rel="stylesheet" href="resources/css/board/recipe/recipeDetailView.css">
</head>




</head>
<body>

	<jsp:include page="/views/common/header.jsp" />
	<jsp:include page="/views/board/recipe_frag/recipeCategoryBar.jsp" />
	<jsp:include page="/views/board/recipe_frag/recipeSortBar.jsp" />
	
	<div id="recipeDetailWrap">
		<c:choose>
			<c:when test="${ requestScope.recipeDetailMap ne null }">
				<div id="recipeDetailBarWrap">
				
					<div id="recipeDetailBarImg">
						<i class='fas fa-align-left'></i>
					</div>
		
					<div id="recipeDetailBarCategory">
						<p>${ requestScope.recipeDetailMap.recipe.recipeCategoryName }</p>
					</div>
		
					<div id="recipeDetailBarView">
						<i class='fas fa-eye'></i>
					</div>
		
					<div id="recipeDetailBarHeart">
						<div>
							<button onclick="ajaxModifyRecipeReply(this);">글 수정하기</button>
						</div>
						<!-- 나중에 if문 처리 필요 (내 글일 때if(loginMember.memNo() == recipe.getRecipeWriter())는 위의 div / 내 글 아닐 때는 아래 하트) -->
						<i class="far fa-heart change-heart-bookmark-icons"></i>
						<!--<i class="fa fa-heart change-heart-bookmark-icons"></i>하트눌렀을때-->
					</div>
					<div id="recipeDetailBookmark">
						<div>
							<button onclick="ajaxDeleteRecipeReply();">글 삭제하기</button>
						</div>
						<!-- 나중에 if문 처리 필요 (내 글일 때if(loginMember.memNo() == recipe.getRecipeWriter())는 위의 div / 내 글 아닐 때는 아래 하트) -->
						<i class="far fa-bookmark change-heart-bookmark-icons"></i>
						<!--<i class="fas fa-bookmark change-heart-bookmark-icons"></i>북마크했을때-->
					</div>
		
					<div id="recipeDetailReport">
						<i class="	fas fa-exclamation-circle"></i>
					</div>
				</div>
						
				<div id="recipeDetailViewWrap">
					
					<div id="DetailCookStepsBasicInfo">
						<div class="detail-view-cook-steps-input-content">
							<div id="detailViewChefHashtag">
								<div id="detailViewChefNameDate">
									<div id = "detailViewChefName">${ requestScope.recipeDetailMap.recipe.memNickName }</div>
									<div id = "detailViewChefDate">
										${ requestScope.recipeDetailMap.recipe.recipeDate }
									</div>
								</div>
								
								<div id="detailViewTagDiv1">
									<div id="detailViewTagDiv2">
										<c:choose>
											<c:when test="${ requestScope.recipeDetailMap.recipeTagList ne null }">
												<c:forEach var="recipeTagList" items="${ requestScope.recipeDetailMap.recipeTagList }">
													<div class="detailTagList">#${ recipeTagList.tagName }</div>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<div class="detailTagList">#ReSeeRecipe</div>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
							<div id="detailViewThumbnailImg">
								<img src="https://simg.wooribank.com/img/section/bz/buss_product_noimgb.gif">
							</div>
						</div>
						
						
						<div class="detail-view-cook-steps-input-content">
							<div id="detailViewRecipeTitle1">
								<p>레시피 제목</p>
							</div>
							<div id="detailViewIngredientTitle">
								<div id="detailIngredientInner1">
									<h3>재료</h3>
								</div>
								<div id="detailIngredientInner2">
									<div id="detailIngredientLeft">
									<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@여기부터 id/class/스타일 정리 -->
										<c:forEach var="i" begin="0" end="14">
											<div id="ingredientContainer${ i }" class="ingredientContainer">
												<div id="ingredientAreaDiv${ i }">
													재료${ i }
												</div>
												<div id="ingredientAmountDiv${ i }">
													000
												</div>
												<div id="measureAreaDiv${ i }">
													g
												</div>
											</div>
										</c:forEach>
									</div>
									<div id="detailIngredientRight">
										<c:forEach var="i" begin="15" end="29">
											<div id="ingredientContainer${ i }" class="ingredientContainer">
												<div id="ingredientAreaDiv${ i }">
													재료${ i }
												</div>
												<div id="ingredientAmountDiv${ i }">
													000
												</div>
												<div id="measureAreaDiv${ i }">
													g
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
		
					<!-- 레시피 과정 입력 틀 (과정사진 + 과정제목 + 과정내용) -->
					<div id="cookingInstructionContainer">
						<c:forEach var="cookStepsList" items="${ requestScope.recipeDetailMap.cookStepsList }" varStatus="status">
							<div id="cookStepsInstInner${ status.index }">
								<div class="cook-steps-inst-pic">
									<img src="">
								</div>
								<div class="cook-steps-inst-title">
									<p class="inst-title-lev">${ cookStepsList.cookStepsLev }</p><!--
									--><p>${ cookStepsList.cookStepsTitle }</p>
								</div>
								<div class="cook-steps-inst-content">
									<p>${ cookStepsList.cookStepsContent }요리과정 내용</p>
								</div>
							</div>
						</c:forEach>
					</div>
					<div id="cook-steps-buttons" align="center">
						<div>
							<button type="button" id="recipe-enrolling-btn" class="btn btn-primary" onclick="history.back();">뒤로가기</button>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div>조회 결과가 없습니다</div>
			</c:otherwise>
		</c:choose>
		<div id="recipeReplyArea">
		   	<table border="1" align="center">
		   		<thead> <!-- 댓글 작성 영역 -->
		   			<tr>
		   				<th>댓글작성</th> <!-- 우리는 replyWriter에 NN걸렸고하니까 로그인 사용자만 댓글작성 가능하게 할 것 -->
		   				<!-- if문 필요 로그인 사용자만if(loginUser != null) 영역 띄워주기 -->
		   					<td colspan="3">
		   						<textarea id="replyContent" cols="50" rows="3" style="resize:none"></textarea>
		   					</td>
		   					<td><button onclick="ajaxInsertRecipeReply();">댓글등록</button></td>
		   				<!-- 여기까지 로그인 사용자만 -->
		   					<td>
		   						<textarea readonly cols="50" rows="3" style="resize:none">로그인 후 이용 가능한 서비스입니다</textarea>
		   					</td>
		   					<td>
		   						<button onclick="recipeDetailReplylogIn();">로그인 하러 가기</button><!-- 버튼 보여만 줌 기능 x -->
		   					</td>
		   				<!-- 그 외의 경우 -->
		   			</tr>
		   		</thead>
		   		<tbody>
		   			<!-- 댓글이 뿌려질 영역 -->
		   		</tbody>
		   	</table>
		   	<br><br><br><br><br>
    		</div>
	</div>
</body>
</html>