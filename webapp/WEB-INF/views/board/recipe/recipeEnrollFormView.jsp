<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,
			     java.util.HashMap,
			     com.kh.semi.board.recipe.model.vo.RecipeCategory,
			     com.kh.semi.board.recipe.model.vo.UnRecipe" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 글 작성 양식</title>
	<link rel="stylesheet" href="resources/css/board/recipe/recipeEnrollFormView.css">
</head>


<body>
	
	<jsp:include page="/views/common/header.jsp" />
	<jsp:include page="/views/board/recipe_frag/recipeCategoryBar.jsp" />
	<jsp:include page="/views/board/recipe_frag/recipeSortBar.jsp" />

	<div id="recipe-enroll-form-wrap"><!-- 전체 wrap 시작 -->
		
		<!---------------------- 글작성 전체 form / memNo은 session에서 빼서 사용 ----------------------->
		<form action="insertRecipe.re" id="recipe-enrolling-form" method="post" enctype="multipart/form-data">
		<!-- <form action="insertRecipe.re" id="recipe-enrolling-form" method="post" enctype="multipart/form-data"> -->
			<!---------------------- 입력양식 상단 바 영역 ---------------------->
			<div id="recipe-enroll-top-bar-wrap">
				<!-- 카테고리 선택 영역 -->
				<div id="recipe-enroll-bar-img">
					<i class='fas fa-align-left'></i>
				</div>
				<div id="recipe-enroll-bar-menu">
					<div id="recipe-enroll-bar-inner">
						<select name="recipeCategoryNo">
								<option	value="1">한식</option>
								<option value="2">양식</option>
								<option value="3">중식</option>
								<option value="4">일식</option>
								<option value="5">아시안</option>
								<option value="6">야식</option>
								<option value="7">디저트</option>
								<option value="8">음료</option>
						</select>
					</div>
					<div id="enroll-bar-inner-blank1"></div>
				</div>

				<!-- 임시저장 아이콘 (클릭 시 모달 호출) -->
				<div id="unrecipe-modal-request-div">
					<button type="button" onclick="unrecipeModalRequest(this);"
						class='fas fa-folder' data-toggle="modal" data-target="#">
						<!--임시저장글개수-->
					</button>
				</div>

				<!-- 1. 임시작성 기본 모달 (if else는 script에서 처리) -->
				<!--  글 3개 미만 0, 1, 2개 상태 : 해시태그, 이미지유실 경고 후 임시저장글 작성 -->
				<div class="modal" id="unrecipe-modal">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">

							<div class="modal-header">
								<h4 class="modal-title">임시저장글로 저장합니다.</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<div class="modal-body">
								<h5>주의! 해시태그와 사진은 저장되지 않습니다.</h5>
								<p>그래도 임시저장글로 저장을 원하시면 저장하기 버튼을 누르세요.</p>
							</div>

							<div class="modal-footer">
								<button type="button" id="unrecipe-enrolling-btn"
									class="btn btn-primary">저장하기</button>
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">취소</button>
							</div>
						</div>
					</div>
				</div>
				<!-- 2. 임시작성 글 3개 이상 시 모달 -->
				<!-- 글 3개 이상 3, 4 . . . 상태 : 모달로 제목 목록띄우면서 '이 글을 지우고 작성' 여부 선택하도록
				 							 -> 선택한 글 지우고 이거 쓰려는게 맞는지, 해시태그와 사진은 저장안된다는 것 더블체크
				  					 		 -> 선택한 임시저장글번호 지우고 + 현재 임시저장글 작성 -->
				<div class="modal" id="unrecipe-unavailable-modal">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">

							<div class="modal-header">
								<h4 class="modal-title">임시저장은 3개까지만 가능합니다!</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<div class="modal-body">
								<h5>기존 임시저장글을 지운 후 이 글을 저장합니다.</h5>
								<p>아래 목록에서 지울 대상을 선택하세요.</p>
								<p>주의! 해시태그와 사진은 저장되지 않습니다.</p>
									<input type="radio" class="testList" name="unRecipeDelNo1" id="unRecipeDelNo1" value="1" checked><label for="unRecipeDelNo1">레시피제목1</label>
									<input type="radio" class="testList" name="unRecipeDelNo2" id="unRecipeDelNo2" value="2"><label for="unRecipeDelNo2">레시피제목2</label>
									<input type="radio" class="testList" name="unRecipeDelNo3" id="unRecipeDelNo3" value="3"><label for="unRecipeDelNo3">레시피제목3</label>
							</div>
	
							<div class="modal-content"></div>
							<div class="modal-footer">
								<button type="button" id="unrecipe-del-enrolling-btn" class="btn btn-primary">글작성</button>
								<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div><!-- 입력양식 상단 바 영역 끝 -->

		
			<!---------------------- 레시피 글 작성 내용 영역 ---------------------->
			<div id="recipe-enroll-context-wrap">
				
				<div id="cook-steps-basic-info">
					
					<div class="cook-steps-input-content">
						<div id="content-writer-hashtag">
							<div id="cook-steps-chef" class="cook-steps-inner">
								<p>${ requestScope.loginMember.memNickName }</p>
							</div>
							<div id="cook-steps-hashtag" class="cook-steps-inner">
								<label for="tnNotAppl">선택된 태그 : </label>
								<select id="tnNotAppl" class="btn btn-info">
									<option disabled selected value="">해시태그 선택</option>
									<option	value="1">한식</option>
									<option value="2">양식</option>
									<option value="3">중식</option>
									<option value="4">일식</option>
									<option value="5">아시안</option>
									<option value="6">야식</option>
									<option value="7">디저트</option>
									<option value="8">음료</option>
								</select>
							</div>
						</div>	

						<div id="content-thumbnail-image">
							<img id="recipePicImg0" src="https://simg.wooribank.com/img/section/bz/buss_product_noimgb.gif">
						</div>
					</div>
						
					<div class="cook-steps-input-content">
						<div id="cook-steps-title" class="cook-steps-inner">
							<div id="title-text-area-div">
								<textarea name="recipeTitle" cols="10" rows="2" placeholder="레시피 제목을 입력하세요" required></textarea>
							</div>
							<div id="title-bytes-span">
								<span>0</span>
								<span>/60 bytes</span>
							</div>
						</div>
						
						<!-- 입력받는 영역 -->
						<div id="cook-steps-ingredient-title" class="cook-steps-inner">
							<!-- 기본 재료 입력받는 양식 -->
							<div id="ingredient-title-div1" class="inputs-in-order">
								<div id="write-ingredient-input">
									<input type="text" id="ingredientIn" class="form-control" placeholder="재료입력" maxlength="15">
								</div>
								<div id="amount-ingredient-input">
									<input type="text" id="ingredientAmountIn" class="form-control" placeholder="재료량" maxlength="4">
								</div>
								<div id="measurement-ingredient-selection">
									<select id="ingredientMeasureNoIn" class="custom-select">
										<option value="g">g</option>
										<option value="kg">kg</option>
										<option value="lb">lb</option>
										<option value="작은술(t)">작은술(t)</option>
										<option value="큰술(T)">큰술(T)</option>
										<option value="ml">ml</option>
										<option value="L">L</option>
										<option value="컵">컵</option>
										<option value="알">알</option>
										<option value="개">개</option>
										<option value="장">장</option>
									</select>
								</div>
							</div>
							<div id="ingredient-title-div2">
								<button type="button" id="ingredient-title-btn" onclick="addIngredientDisplay();" class="fa fa-plus-square"></button>
							</div>
						</div>
						<div id="cookStepsIngredientContent" class="cook-steps-inner">
							<div id="ingredientContentLeft">
								<!--
								생성된 요소 띄워줄 영역 자바 스크립트로 입력된 재료 요소 추가
								-->
							</div>
							
							<div id="ingredientContentRight">
								<!--
									생성된 요소 띄워줄 영역 자바 스크립트로 입력된 재료 요소 추가
								-->
							</div>
						</div>
					</div>
				</div>

				<!-- 레시피 과정 입력 틀 (과정사진 + 과정제목 + 과정내용) -->
				<div id="cookingInstructionContainer">
					<div id="cookStepsInstInner1">
						<div class="cook-steps-inst-title">
							<input type="hidden" name="cookStepsLev1" value="1"><p class="inst-title-lev">1</p><!--
							--><input type="text" name="cookStepsTitle1" placeholder="요리과정 제목" class="inst-title-text"><button type="button" id="delCookSteps1" class="fas fa-minus-square modify-btn" aria-hidden="true">
							</button>
						</div>
						<div class="cook-steps-inst-pic">
							<img id="recipePicImg1" src="https://simg.wooribank.com/img/section/bz/buss_product_noimgb.gif">
						</div>
						<div class="cook-steps-inst-content">
							<textarea name="cookStepsContent1" placeholder="요리과정 설명" cols="30" rows="10" maxlength="500">
							</textarea>
						</div>
					</div>

					<div id="cookStepsInstInnerEnd">
						<button id="instAddBtn" type="button" class="fas fa-plus-circle">
						</button>
					</div>
				</div>

				<!-- 파일 관련 영역 -->
				<div id="recipePicFileArea">
					<input type="file" name="recipeNameOrigin0" id="recipeNameOrigin0" required onchange="loadRecipeImg(this, 0);">
					<input type="file" name="recipeNameOrigin1" id="recipeNameOrigin1" required onchange="loadRecipeImg(this, 1);">
					<input type="file" name="recipeNameOrigin2" id="recipeNameOrigin2" onchange="loadRecipeImg(this, 2);">
					<input type="file" name="recipeNameOrigin3" id="recipeNameOrigin3" onchange="loadRecipeImg(this, 3);">
					<input type="file" name="recipeNameOrigin4" id="recipeNameOrigin4" onchange="loadRecipeImg(this, 4);">
					<input type="file" name="recipeNameOrigin5" id="recipeNameOrigin5" onchange="loadRecipeImg(this, 5);">
					<input type="file" name="recipeNameOrigin6" id="recipeNameOrigin6" onchange="loadRecipeImg(this, 6);">
				</div>

				<!-- 레시피 작성 요청 / 초기화 버튼 (script로 요청) -->
				<div id="cook-steps-buttons" align="center">
					<div>
						<button type="submit" id="recipe-enrolling-btn" class="btn btn-primary" onclick="return confirmSubmit();">글작성</button>
					</div>
					<div>
						<button type="reset" id="recipe-resetting-btn" onclick="return confirmReset();" class="btn btn-warning">초기화</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	<script src="resources/js/board/recipe/recipeEnrollFormView.js"></script>
</body>
</html>