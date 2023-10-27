// jsp파일 로딩 시 카테고리 접힘상태로 로딩시켜주는 함수
$(function(){
	const categoryFoldingText = $('#category-toggle-msg > h3');
	categoryFoldingText.text('카테고리 더보기');
	$('#category-toggle-menu').css('display', 'none');
});

$('.send-detail-view').on('click', function(){
	location.href = 'recipeDetail.re?recipeNo=' + $('#SendDetailViewSelectedRecipeNo').text();					
});

$('#goPrevPage').click(function(){
	
	 location.href = 'selectRecipeList.re?currentPage=<%= currentPage - 1 %>';
});

$('.certainPages').click(function(){
	location.href = 'selectRecipeList.re?currentPage=' + $(this).html();
});

$('#goNextPage').click(function(){
	location.href = 'selectRecipeList.re?currentPage=<%= currentPage + 1 %>';
});
