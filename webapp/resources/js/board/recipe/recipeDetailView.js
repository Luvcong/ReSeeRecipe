// jsp파일 로딩 시 카테고리 접힘상태로 로딩시켜주는 함수
$(function(){
	const categoryFoldingText = $('#category-toggle-msg > h3');
	categoryFoldingText.text('카테고리 더보기');
	$('#category-toggle-menu').css('display', 'none');
});

	
	


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
