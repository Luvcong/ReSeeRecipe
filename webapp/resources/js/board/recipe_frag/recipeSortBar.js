$('.recipe-sort-by').click(function(){
	const sortBy = $(this).siblings().val();
	location.href = 'recipe/' + sortBy + '.re';
});
