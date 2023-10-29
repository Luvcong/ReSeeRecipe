//  couponListView

	// 페이지 이동
	function page(element){
		this.location.href = "/ReSeeRecipe/jhselect.cp?page=" + element;
	}	// page
	
	
	// 체크박스 전체 선택 및 해제
	
  	function checkAll(){
  		let table = document.getElementById('tb-coupon');
		let inputs = document.querySelectorAll('tr input');
		
		for(let input of inputs){
			input.checked = event.target.checked;
		}
	}	// checkAll
	
	// 체크박스 테이블 행 1개 해제시 전체 체크박스 해제
	function checkOnce(){
		let table = document.getElementById('tb-coupon');
		let hd_input = table.querySelector('th input'); // 헤더 input
		let inputs = table.querySelectorAll('td input');
		
		let all_checked = true;
		
		for(let input of inputs){
			if(input.checked == false){
				all_checked = false;
				break;
			}
		}
		
		hd_input.checked = all_checked;
	}	// checkOnce
	
	
	// 쿠폰 등록처리 함수
	function showAddCouponModal(){
		
//		const day = new Date();
//		const offset = new Date().getTimezoneOffset() * 60000;
//		const today = new Date(Date.now() - offset);
//		console.log(day);
//		console.log(offset);
//		console.log(today);
		
		let toISOStringDate = new Date().toISOString();
		// console.log(toISOStringDate);
		let startDate = document.getElementById('startDate');
		let endDate = document.getElementById('endDate');
		
		let startVal = new Date().toISOString().substring(0, 10);
		let endVal = new Date().toISOString().substring(0, 10);
		
		startDate.value = startVal;
		endDate.value = endVal;
		startDate.setAttribute("min", startVal);
		endDate.setAttribute("min", endVal);
		
		// toISOString : 국제 표준시 기준 형식으로 반환
		// https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Date/toISOString
		
		let input = document.getElementById('couponPercent');
		console.log(input);
		input.value = '';
		
		$('#addCouponForm').modal('show');
		
	}	// showAddCouponModal

	
	// 쿠폰 삭제처리 함수
	function deleteCoupon(){
		
		let trs = document.querySelectorAll('.table tr');	// 테이블의 모든 tr태그 저장
		let checked_tr = null;								// 체크확인용 변수

		for(let tr of trs){
			let input = tr.querySelector('input');
			if(input.checked){
				checked_tr = tr;
				break;
			}
		}
		
		if(checked_tr == null){
			Swal.fire('실패', '쿠폰을 선택해주세요!', 'warning');
			return;
		}
		
		Swal.fire({
			title: "쿠폰을 삭제하시겠습니까?",
			text : "※ 이미 발급된 쿠폰은 삭제되지 않습니다",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "삭제",
			cancelButtonText: "취소"
			}).then((result) => {
				if (!result.isConfirmed) {
				  return;
				}
				
				// 쿠폰 번호로 TB_COUPON 데이터 삭제 (기존에 발급된 쿠폰은 사라지지 않음)
				let table = document.getElementById('tb-coupon');
				let trs = table.querySelectorAll('tbody tr');
				let coupon_list = [];
				
				for(let tr of trs){									// 다수 삭제를 위해 반복문
					let input = tr.querySelector('input');
					if(input.checked){
						coupon_list.push(tr.children[1].textContent);	// 배열에서 값 추가시 push
					}
				}	// for
				// console.log(coupon_list);
				
				$.ajax({
					url : 'jhdelete.cp',
					type : 'get',
					data : {'coupon_list' : coupon_list},
					success : function(result){
						console.log('성공');
						Swal.fire('성공', '쿠폰 삭제가 완료되었습니다!', 'success');
						
						let selectCount = document.querySelector('.selectCount');
						console.log(selectCount);
						let total = parseInt(selectCount.textContent);
						console.log(total);
						
						for(let tr of trs){
							let coupon_list = parseInt(tr.children[1].textContent);
							if(result.includes(coupon_list)){
								tr.remove();
								total--;
								selectCount.textContent = total;
							}
						}
					},	// success
					error : function(result){
						console.log('실패');
					}	// error
					
				});	// ajax
			});
	}	// deleteCoupon
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	