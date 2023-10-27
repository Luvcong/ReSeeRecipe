// 카테고리메뉴 토글
$('#category-toggle-msg > h3').click(function(){
    if($(this).text() == '카테고리 접기') {
        $(this).text('카테고리 더보기');
    }
    else {
        $(this).text('카테고리 접기');
    }
    $('#category-toggle-menu').slideToggle();
});
