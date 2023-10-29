// <!-- 페이징바 -->	
	function page(element){
		this.location.href = "/ReSeeRecipe/jhselect.rw?page=" + element;
	}

	
// <!-- 글자 Byte 수 체크 -->
	let limitByte = 500;
	let totalByte;

	function checkedByte(obj){		
		totalByte = 0;
		let message = $(obj).val();
		console.log(message);
		
		for(let i = 0; i < message.length; i++){
			var countByte = message.charCodeAt(i);
			if(countByte > 128){
				totalByte += 3;
			} else {
				totalByte++;
			}
		}
		$('#count').text(totalByte);
	}	// checkedByte
	
	
//	<!-- 리워드 지급/차감 modal창 -->
	// 리워드 지급/차감에 따른 색상 변경
	function changeColor(){
		let input = document.getElementById('reward-score');
		// console.log(input);
		
		// select 태그 색상 변경
		// reward-score 색상 변경
		// console.log(this);			// window 객체..
		// console.log(event.target);	// select 값ok
		if(event.target.value == 'plusReward'){
			input.style.color = 'dodgerblue';
		}
		else {
			input.style.color = 'red';
		}
		
		// null처리
		// 유형이 없을 경우 > confrim 버튼 disable로 변경
		let confirm = document.getElementById('nullCheck');
		// console.log(confirm);
		if(event.target.value == null || event.target.value == ""){
			confirm.setAttribute('disabled', true);
			return;
		}
		confirm.removeAttribute('disabled');
	}	// changeColor()
	
	// reward-score가 null이거나 "" 이면 disable? alert..
	function checkReward(){
		let reward = document.getElementById('reward-score');
		console.log(reward);
		// let reward = document.getElementById('reward-score');
		if(reward.value == null || reward.value == ''){
			event.preventDefault();		// submit event 막아주기
			Swal.fire('실패', '리워드 금액을 입력하세요!', 'error');
			return;
		}
	}
	
	// 리워드 지급
	function showAddRewardModal(){
		let trs = document.querySelectorAll('.tableBody tbody tr');	// .tbody의 tr요소 모두 저장
		console.log(trs);		// 값 ok
		
		let checked_tr = null;
		
		for(let tr of trs){
			let input = tr.querySelector('input');
			console.log(input);
			if(input.checked){
				checked_tr = tr;	// input.checked속성이 true이면 변수에 저장
				console.log(checked_tr);
				break;
			}
		}
		
		// null 체크
		if(checked_tr == null){
			Swal.fire('실패', '회원을 선택해주세요!', 'error');
			return;
		}
		
		let modal = document.getElementById('updateRewardForm');
		let modal_tds = modal.querySelectorAll('table tr td');
		// console.log(modal_tds);	// 값 ok

		// 회원아이디
		let memId = checked_tr.children[2].textContent;
		console.log('memId : ' + memId);	// 값 ok
		
		// 회원No
		let memNo = checked_tr.children[9].textContent;
		console.log('memNo : ' + memNo);	// 값 ok
		let hidden_input = modal.querySelector('input[name="memNo"]');
		hidden_input.value = memNo;
		console.log('hidden_input : ' + hidden_input)

		
		// 회원닉네임
		let memNickname = checked_tr.children[3].textContent;
		console.log('memNickname : ' + memNickname);	// 값 ok
		
		modal_tds[1].textContent = memId;
		modal_tds[2].textContent = memNickname;
		
		$('#updateRewardForm').modal('show');
	}	// showAddRewardModal
	
//	<!-- 체크박스  -->
	// 1) 전체 체크박스 선택 및 해제
	function checkAll(){
		
		let table = document.getElementById('tb-reward');
		let inputs = table.querySelectorAll('tr input');
		
		for(let input of inputs){
			input.checked = event.target.checked;
		}
		
		console.log(event.target);
	}	// checkAll
	
	// 2) tr 체크박스 하나라도 해제 > 헤더 체크박스 해제
	function checkOnce(){
		let table = document.getElementById('tb-reward');
		
		let hd_input = table.querySelector('th input');
		let inputs = table.querySelectorAll('tbody tr input');
		// console.log(inputs);
		
		let checked_all = true;
		
		for(let input of inputs){
			if(input.checked == false){
				checked_all = false;
				break;
			}
		}
		hd_input.checked = checked_all;
	}	// checkOnce
	
//	<!-- 컬럼 sort  -->
