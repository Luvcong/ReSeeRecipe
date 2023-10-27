<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,
			     java.util.HashMap,
			     com.kh.semi.board.recipe.model.vo.Recipe,
			     com.kh.semi.board.recipe.model.vo.RecipePic,
			     com.kh.semi.board.recipe.model.vo.Ingredient,
			     com.kh.semi.board.recipe.model.vo.CookSteps,
			     com.kh.semi.board.recipe.model.vo.RecipeTag" %>
<%
	// 변수 세팅
	HashMap<String, Object> recipeDetailMap = null;
	
	Recipe recipe = null;
	ArrayList<RecipePic> reciepPicList = null;
	ArrayList<Ingredient> ingredientList = null;
	ArrayList<CookSteps> cookStepsList = null;
	ArrayList<RecipeTag> recipeTagList = null;
	
	if(request.getAttribute("recipeDetailMap") != null) {
		
		recipeDetailMap = (HashMap<String, Object>)request.getAttribute("recipeDetailMap");
		
		recipe = (Recipe)recipeDetailMap.get("recipe");
		reciepPicList = (ArrayList<RecipePic>)recipeDetailMap.get("reciepPicList");
		ingredientList = (ArrayList<Ingredient>)recipeDetailMap.get("ingredientList");
		cookStepsList = (ArrayList<CookSteps>)recipeDetailMap.get("cookStepsList");
		if(recipeDetailMap.get("recipeTagList") != null) {
			recipeTagList = (ArrayList<RecipeTag>)recipeDetailMap.get("recipeTagList");
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 디테일뷰</title>

</head>


<style>
	/**********************************************************
	wrap div + wrap내부 div 세팅
	**********************************************************/
	div {
		box-sizing: border-box;
	}

	/* 전체 div wrap */
	#recipeDetailWrap {
		width: 1200px;
		box-sizing: border-box;
		margin: auto;
	}

	/* > > 상단바wrap div 세팅 */
	#recipeDetailBarWrap {
		width: 100%;
		height: 80px;
		margin: auto;
		box-sizing: border-box;
		/*지울부분*/
		background-color: aqua;
	}

	/* > > 디테일뷰 내용 wrap div 세팅 */
	#recipeDetailViewWrap {
	width: 100%;
	height: 2150px;
	}

	/*******************************************/


	/* > >... 상단바 내부 div요소 default 세팅 */
	#recipeDetailBarWrap div {
		height: 100%;
		float: left;
	}

	/**********************************************************
		상단바 영역 (#recipeDetailWrap)
	**********************************************************/
	/* 기본아이콘, 카테고리 글씨, 조회수, 좋아요, 북마크, 신고 아이콘 기본 세팅 */
	#recipeDetailBarImg  {
		width: 10%;
		padding: 18px 0px 0px 30px;
	}
	#recipeDetailBarCategory {
		width: 62%;
	}
	#recipeDetailBarView, #recipeDetailBarHeart, #recipeDetailBookmark, #recipeDetailReport {
		width: 7%;
		padding: 18px 0px 0px 17px;
	}
	#recipeDetailBarView i, #recipeDetailBarHeart i, #recipeDetailBookmark i, #recipeDetailReport i	{
		font-size: 45px;
	}

	#recipeDetailBarView i, #recipeDetailBarHeart i, #recipeDetailBookmark i, #recipeDetailReport i:hover {
		cursor: pointer;
	}

	/* 1. 카테고리바 기본 아이콘 컬러링 */
	#recipeDetailBarImg i {
		font-size: 45px;
		color: rgb(255, 145, 77);
	}

	/* 2. 카테고리 부분 글씨 세팅 */
	#recipeDetailBarCategory p {
		font-size: 30px;
		font-weight: 1000;
		margin: 0px;
		margin-top: 12px;
		margin-left: 30px;
		display: inline-block;
	}


	/* 3. 조회수 컬러링 세팅 */
	#recipeDetailBarView i {
		color: rgb(255, 145, 77);
	}
	
	/* 4. 좋아요 컬러링 세팅 */
	#recipeDetailBarHeart i {
		color: rgb(168, 19, 19);
	}

	/* 5. 북마크 컬러링 세팅 */
	#recipeDetailBookmark i {
		color: rgb(7, 81, 13);
		
	}
	
	/* 6. 신고 컬러링 세팅 */
	#recipeDetailReport i {
		color: rgb(48, 36, 36);
		
	}
	/*******************************************/



	/**********************************************************
		입력양식 내용 영역 (#recipeDetailViewWrap내부)
	**********************************************************/
	/* 1. 입력폼 내부 상단 div (썸네일 + 제목 + 재료입력) */
	#DetailCookStepsBasicInfo {
		width: 100%;
		height: 35%;
	}

	/* 좌우 큰 div */
	#DetailCookStepsBasicInfo .detail-view-cook-steps-input-content {
		width: 50%;
		height: 100%;
		float: left;
		text-align: center;
	}


	/* 2. 좌측 div 내부 */
	/* 셰프이름 + 해시태그 div */
	#detailViewChefHashtag {
		width: 100%;
		height: 21%;
	}

	/* 셰프이름 */
	#detailViewChefNameDate {
		height: 60%;
	}
	
	#detailViewChefName, #detailViewChefDate {
		padding-top: 30px;
	}
	
	#detailViewChefName {
		width: 80%;
		height: 100%;
		float: left;
		font-size: 25px;
		font-weight: 1000;
		color: rgb(132, 137, 143);
	}
	
	#detailViewChefDate {
		width: 20%;
		height: 100%;
		float: left;
		font-size: 10px;
		color: rgb(132, 137, 143);
	}

	/* 해시태그 */
	#detailViewTagDiv1 {
		width: 100%;
		height: 40%;
	}

	#detailViewTagDiv2 {
		width: 90%;
		height: 100%;
		margin: auto;
		border-radius: 50px;
		border-color: rgb(255, 145, 77);
		background-color: #17a2b8;
	}

	.detailTagList {
		display: inline-block;
		margin: auto;
		font-size: 17px;
		margin-top: 15px;
	}

	/* 썸네일 div */
	#detailViewThumbnailImg {
		width: 100%;
		height: 79%;
		background-color: rgb(255, 222, 89);
	}

	/* 썸네일 이미지 */
	#detailViewThumbnailImg img {
		width: 570px;
		height: 570px;
		padding-top: 20px;
	}

	/* 3. 우측 div 내부 */
	/* 레시피 제목 */
	#detailViewRecipeTitle1 {
		height: 21%;
		padding-top: 50px;
	}

	#detailViewRecipeTitle1 p {
		font-size: 30px;
		font-weight: 1000;
	}

	/* 재료 타이틀 */
	#detailViewIngredientTitle {
		height: 79%;
	}

	#detailIngredientInner1 {
		width: 100%;
		height: 10%;
		padding-top: 12px;
	}

	#detailIngredientInner1 h3 {
		font-weight: 800;
	}

	/* 재료 콘텐트 (필요한 재료 목록들 띄우는 부분) */
	#detailIngredientInner2 {
		height: 90%;
	}
	/*********************************************************************************************************************************/
	/*********************************************************************************************************************************/
	/*********************************************************************************************************************************/
	/*********************************************************************************************************************************/
	/*********************************************************************************************************************************/




	





	/* > >... 입력폼 내부 중단 div (과정사진 + 제목 + 내용) */
	#cookingInstructionContainer {
		width: 100%;
		height: 60%;
	}

	/* > >... 입력폼 내부 하단 div (submit + reset 버튼) */
	#cook-steps-buttons {
		width: 100%;
		height: 5%;
	}


	

	

	/* select요소 */
	#recipe-enroll-bar-inner {
		width: 45%;
		position: relative;
	}
	


	#enroll-bar-inner-blank1 {
		width: 55%;
	}

	/* 3. 임시저장 버튼 세팅 */
	#unrecipe-modal-request-div {
		width:15%;
	}

	#unrecipe-modal-request-div > button {
		width: 100%;
		height: 100%;
		font-size: 55px;
		text-align: center;
		color: rgb(255, 145, 77);
		appearance: none;
		border: none;
        background-color: transparent;
	
	}



	/* --- 좌측 --- */






	


	

	

	
	#cook-steps-hashtag option {
		font-size: 25px;
	}
	
	#cook-steps-hashtag option:disabled {
		display: none;
	}
	
	


	#cook-steps-title > div > textarea {
		width: 420px;
		height: 105px;
		resize: none;
		text-align: center;
		border-radius: 10px;
		padding : 10px 100px 10px 50px;
		margin: auto;
		position: absolute;
		top: 100px;
		right: 90px;
	}

	#cook-steps-title > div > textarea::placeholder {
		padding-top: 30px;
		text-align: center;
	}

	#cook-steps-title > #title-bytes-span {
		display: inline-block;
		font-size: 2px;
		z-index: 1;
		position: absolute;
		top: 172px;
		right: 100px;
	}




	#cook-steps-ingredient-title div {
		height: 100%;
		float: left;
	}


	
	#write-ingredient-input {
		width: 62%;
		height: 100%;
	}
	
	#amount-ingredient-input {
		width: 18%;
		height: 100%;
	}
	
	#measurement-ingredient-selection {
		width: 20%;
		height: 100%;
	}
	
	#ingredient-title-div1 input {
		font-size: 2px;
		text-align: center;
		padding: 2px;
	}

	#ingredient-title-div1 select {
		text-align: center;
		padding: 0px;
		padding-left: 30px;
	}
	
	#ingredient-title-div1 input, #ingredient-title-div1 select {
		width: 100%;
		height: 100%;
	}

	#ingredient-title-div2 {
		width: 10%;
		padding-top: 7px;
		padding-right: 10px;
	}

	#ingredient-title-btn {
		font-size: 40px;
		padding: 0px;
		margin-top: 2px;
		appearance: none;
		border: none;
		background-color: transparent;
		color: rgb(89, 164, 255);
		float: right;
	}
	
	#ingredient-title-btn:hover {
		color: rgb(59, 134, 225);
	}



	#detailIngredientLeft, #detailIngredientRight {
		width: 50%;
		height: 100%;
		float: left;
	}


	#detailIngredientInner2 .ingredientContainer {
		width: 100%;
		height: 34px;
	}

	#detailIngredientInner2 .ingredientContainer div {
		height: 100%;
		float: left;
	}
	
	div[id ^= 'ingredientAreaDiv'] {
		width: 60%;
	}

	div[id ^= 'ingredientAmountDiv'] {
		width: 20%;
	}

	div[id ^= measureAreaDiv] {
		width: 20%;
	}


	#detailIngredientInner2 input, select {
		width: 100%;
		height: 100%;
		padding: 0px;
		font-size: 1px;
		text-align: center;
		appearance: none;
	}

	.ingMeasure {
		font-size: 1px;
	}
	
	#detailIngredientInner2 input::placeholder, select::placeholder {
		text-align: center;
	}

	.modify-btn {
		font-size: 20px;
		padding: 5px 5px 0px 0px;
		appearance: none;
		border: none;
		background-color: transparent;
		color: rgb(227, 113, 113);
		float: right;
	}





	/*2. 입력폼 내부 중단 div (과정사진 + 제목 + 내용) */
	/* 넘버링 + 요리과정 제목 */
	#cookingInstructionContainer > div {
		width: 50%;
		height: 33%;
		margin: auto;
		float: left;
	}

	#cookingInstructionContainer .cook-steps-inst-title {
		width: 100%;
		height: 20%;
		padding: 20px 10px 0px 15px;
	}

	#cookingInstructionContainer .inst-title-lev {
		width: 10%;
		height: 100%;
		display: inline-block;
		box-sizing: border-box;
		text-align: center;
		margin: 0px;
		padding-top: 15px;
		padding-right: 10px;
	}

	#cookingInstructionContainer .inst-title-text {
		width: 75%;
		height: 100%;
		box-sizing: border-box;
		padding: 0px;
		text-align: center;
	}

	#cookingInstructionContainer button[id^='delCookSteps'] {
		font-size: 50px;
		width: 15%;
		height: 100%;
		padding: 0px;
		box-sizing: border-box;
		float: right;
	}

	/* 요리과정 설명 */
	#cookingInstructionContainer .cook-steps-inst-content {
		width: 100%;
		height: 50%;
		padding: 10px;
	}

	#cookingInstructionContainer .cook-steps-inst-content textarea {
		width: 100%;
		height: 100%;
	}

	#cookingInstructionContainer .cook-steps-inst-content textarea::placeholder {
		text-align: center;
	}

	#instAddBtn {
		font-size: 120px;
		padding: 0px;
		margin-top: 147px;
		margin-left: 237px;
		appearance: none;
		border: none;
		background-color: transparent;
		color: rgb(89, 164, 255);
	}


	/* 3. 입력폼 내부 하단 div (submit + reset 버튼) */
	#cook-steps-buttons {

	}

	

		

	/**********지울부분***************************************/
	table td { border: 1px solid black }


</style>

</head>
<body>

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

	<div id="recipeDetailWrap">
	
		<% if(recipeDetailMap != null) { %>
			<div id="recipeDetailBarWrap">
			
				<div id="recipeDetailBarImg">
					<i class='fas fa-align-left'></i>
				</div>
	
				<div id="recipeDetailBarCategory">
					<p><%= recipe.getRecipeCategoryName() %></p>
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
								<div id = "detailViewChefName"><%= recipe.getMemNickName() %></div>
								<div id = "detailViewChefDate">
									<%= recipe.getRecipeDate() %>
								</div>
							</div>
							
							<div id="detailViewTagDiv1">
								<div id="detailViewTagDiv2">
									<% if(recipeTagList != null) { %>
										<% for(int i = 0; i < recipeTagList.size(); i++) { %>
											<div class="detailTagList">#<%= recipeTagList.get(i).getTagName() %></div>
										<% } %>
									<% } else { %>
										<div class="detailTagList">#ReSeeRecipe</div>
									<% } %>
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
									<% for (int i = 0; i < 15; i++) { %>
										<div id="ingredientContainer<%= i %>" class="ingredientContainer">
											<div id="ingredientAreaDiv<%= i %>">
												재료<%= i %>
											</div>
											<div id="ingredientAmountDiv<%= i %>">
												000
											</div>
											<div id="measureAreaDiv<%= i %>">
												g
											</div>
										</div>
									<% } %>
								</div>
								<div id="detailIngredientRight">
									<% for (int i = 15; i < 30; i++) { %>
										<div id="ingredientContainer<%= i %>" class="ingredientContainer">
											<div id="ingredientAreaDiv<%= i %>">
												재료<%= i %>
											</div>
											<div id="ingredientAmountDiv<%= i %>">
												000
											</div>
											<div id="measureAreaDiv<%= i %>">
												g
											</div>
										</div>
									<% } %>
								</div>
							</div>
						</div>
					</div>
				</div>
	
	
				<!-- 레시피 과정 입력 틀 (과정사진 + 과정제목 + 과정내용) -->
				<div id="cookingInstructionContainer">
					<% for(int i = 0; i < cookStepsList.size(); i++) { %>
						<div id="cookStepsInstInner" + i>
							<div class="cook-steps-inst-pic">
								<img src="" d>
							</div>
							<div class="cook-steps-inst-title">
								<p class="inst-title-lev"><%= cookStepsList.get(i).getCookStepsLev() %></p><!--
								--><p><%= cookStepsList.get(i).getCookStepsTitle() %></p>
							</div>
							<div class="cook-steps-inst-content">
								<p><%= cookStepsList.get(i).getCookStepsContent() %>요리과정 내용</p>
							</div>
						</div>
					<% } %>
				</div>
				<div id="cook-steps-buttons" align="center">
					<div>
						<button type="button" id="recipe-enrolling-btn" class="btn btn-primary" onclick="history.back();">뒤로가기</button>
					</div>
				</div>
			</div>
		<% } else { %>
			<div>조회 결과가 없습니다</div>
		<% } %>
		
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
    	

    	<script>
	    	var recipeNo = <%= recipe.getRecipeNo() %>;
	    	<% if(loginMember != null) { %>
		    	var replyWriterNo = <%= loginMember %>;
	    	<% } %>
	    	
	    	// 댓글 리스트 조회
			function ajaxSelectRecipeReplyList(){
				$.ajax({
					type : "POST",
					url : 'ajaxSelectRecipeReplyList.ar',
					data : { recipeNo : recipeNo },
					success : function(result){
						//console.log(result);
						let resultStr = '';
						for(let i in result) {
							resultStr += '<tr>'
									    + '<td>' + result[i].memNickName + '</td>'
									    + '<td>' + result[i].replyContent + '</td>'
									    + '<td>' + result[i].replyDate + '</td>'
									    + '<tr>'
									    + '<button onclick="ajaxModifyRecipeReply(this);">수정</button>'
									    + '<button type="button" onclick="ajaxDeleteRecipeReply(this);")>삭제</button>'
									    + '</tr>'
									    + '<tr>'
									    + '<input type="hidden" value="' + result[i].replyNo + '" name="' + result[i].replyNo + '"></td>'
									    + '</tr>';
						}
						$('#recipeReplyArea tbody').html(resultStr);
					},
					error : function() {
						console.log('조회에 실패했습니다');
					
					}
				});
			};
			
			// 댓글 수정
			function ajaxModifyRecipeReply(button){
				$.ajax({
					type : 'POST',
					url : 'ajaxModifyRecipeReply.ar',
					data : {
						recipeNo : recipeNo,
						replyNo : $(button).siblings('input').val()
					},
					success : function(result) {
						console.log('성공');
						console.log(result);
					},
					error : function() {
						alert('수정에 실패했습니다!');
					}
				});
			};
			
			// 댓글 삭제(상태 N으로 UPDATE)
			function ajaxDeleteRecipeReply(button){
				$.ajax({
					type : 'POST',
					url : 'ajaxDeleteRecipeReply.ar',
					data : {
						recipeNo : recipeNo,
						replyNo : $(button).siblings('input').val()
					},
					success : function(result){
						alert('삭제 성공!');
						console.log(result);
						
						if(result > 0) {
							ajaxSelectRecipeReplyList();
						}
					},
					error : function() {
						alert('삭제에 실패했습니다');
					}
				});
			};
			
			// 댓글 작성
    		function ajaxInsertRecipeReply(){
				$.ajax({
					type : 'POST',
					url : 'ajaxInsertRecipeReply.ar',
					data : {
						recipeNo : recipeNo,
						replyContent : $('#replyContent').val()
						},
					success : function(result){
						console.log('댓글 작성 성공!');
						console.log(result);
					},
					error : function() {
						alert('댓글 작성에 실패했습니다');
					}
				});
			};
			
			// 비로그인시 로그인 버튼
    		function recipeDetailReplylogIn(){
    			location.href = '<%=contextPath%>/yrloginForm.me';
    		};
    		
			// onload 시 댓글리스트 갱신 기능 호출
			$(function(){
				ajaxSelectRecipeReplyList();
				//setInterval(ajaxSelectRecipeReplyList, 1500); // 인터벌 잠시 off
			});
			
						
    		
    		
    		
		
    	</script>

		
		
	</div>
	
</body>
</html>