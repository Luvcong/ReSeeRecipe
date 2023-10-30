// <!-- 페이징바 -->
	function page(element){
			this.location.href = "/ReSeeRecipe/jhselect.rp?page=" + element;
	}
	
	
//	<!-- 신고함 상세보기 Modal -->
	function showDetailReportModal(){
		
		// 테이블의 input 요소 가져와서 seq 뽑기
		let table = document.querySelector('#tb-report');
		let trs = table.querySelectorAll('tbody tr');
		console.log(trs);			// tr태그 값 ok
		
		let checked_tr = null;
		
		// trs에 있는 input요소 
		for(let tr of trs){
			let input = tr.querySelector('input');	
			// console.log(input);		// input태그 값 ok
			if(input.checked){
				checked_tr = tr;
				break;					// 선택한 요소 중 젤 첫번째것만 
			}
		}
			// console.log(checked_tr);	// 값 ok
		
		// null체크
		if(checked_tr == null){
			Swal.fire('실패', '신고글을 선택해주세요!', 'error');
			return;
		}
		
		let modal = document.getElementById('detailReportForm');
		let modal_tds = modal.querySelectorAll('table tr td');
		// console.log(modal_tds);		// 모든 td ok
		
		let report_name = checked_tr.children[3].textContent;	// 유형(댓글or게시글)
		let send_report = checked_tr.children[6].textContent;	// 신고자
		let recive_report = checked_tr.children[5].textContent;	// 신고대상자
		
		// 신고글 번호
		let report_no = checked_tr.children[1].textContent;
		
		// 신고글 or 신고댓글 내용
		let recipe_title = checked_tr.children[8].textContent;
		let reply_content = checked_tr.children[9].textContent;
		
		// 신고사유
		let report_content = checked_tr.children[4].textContent;
		
		
		modal_tds[0].textContent = report_name;
		modal_tds[1].textContent = send_report;
		modal_tds[2].textContent = recive_report;
		modal_tds[3].textContent = report_no;
		modal_tds[5].textContent = reply_content;	// 신고댓글
		modal_tds[6].textContent = report_content;	// 신고사유
		
		
		
		
		console.log(recipe_title);
		console.log(report_content);
		console.log(reply_content);
		
		
		
		$('#detailReportForm').modal('show');
	}	// showDetailReportModal
	
	
//	<!-- 신고함 차단하기 기능(RPT_STATUS == N && 해당 게시글/댓글의 STATUS == N) -->
	function updateReport(){
		
		// 테이블의 input 요소 가져와서 seq 뽑기
		let table = document.querySelector('#tb-report');
		let trs = table.querySelectorAll('tbody tr');
		console.log(trs);			// tr태그 값 ok
		
		let checked_tr = null;
		
		// trs에 있는 input요소 
		for(let tr of trs){
			let input = tr.querySelector('input');	
			// console.log(input);		// input태그 값 ok
			if(input.checked){
				checked_tr = tr;
				break;					// 선택한 요소 중 젤 첫번째것만 
			}
		}
			// console.log(checked_tr);	// 값 ok
		
		// null체크
		if(checked_tr == null){
			Swal.fire('실패', '신고글을 선택해주세요!', 'error');
			return;
		}
		
			
		// 체크가 되어있는 경우 confirm창 발생
		Swal.fire({
			title: "해당 글을 차단하시겠습니까?",
			text : "※ 차단시 블라인드 처리됩니다. ",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "차단",
			cancelButtonText: "취소"
			}).then((result) => {
				if (!result.isConfirmed) {
				  return;
				}
				
			});
	}	// updateReport
	
	
//	<!-- 체크박스 기능 -->
	function checkAll(){
		
	// 1) 헤더 체크박스 클릭시 전체 체크/해제
	let table = document.getElementById('tb-report');
	let inputs = table.querySelectorAll('tr input');
	console.log(inputs);
	
	for(let input of inputs){
			input.checked = event.target.checked;
		}
	
	console.log(event.target);
	}	// checkAll

	// 2) 테이블 행 하나가 체크해제면 전체 체크박스 해제
	function checkOnce(){
		let table = document.getElementById('tb-report');
		let hd_input = table.querySelector('th').querySelector('input');
		let inputs = table.querySelector('tbody').querySelectorAll('tr input');
		
		// let hd_input = table.querySelectorAll('th input')
		// console.log(hd_input);	// 값 ok
		// let inputs = table.querySelectorAll('tbody tr input');
		// console.log(inputs);	// 값 ok
		
		// 체크확인용 변수
		let checked_all = true;
		for(let input of inputs){
			if(input.checked == false){		// 하나라도 해제되면
				checked_all = false;		// 전체체크 변수를 false로 넣기
				console.log(checked_all);	// 체크해제시 false값
				break;
			}
		}
		console.log(checked_all);
		hd_input.checked = checked_all;
		console.log(hd_input);
	}	// checkOnce
