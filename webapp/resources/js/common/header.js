$(function() {
	$('#managerCheck').click(function() {
		const managerCheck = prompt('관리자 암호를 입력하세요');
		if (managerCheck == '1234') {
			//console.log(managerCheck);
			return true;
		} else {
			//window.alert('관리자 암호가 일치하지 않습니다.');
			Swal.fire("관리자 페이지 접근 실패", "관리자 암호가 일치하지 않습니다", "warning");
			return false;
		}
	});
})
