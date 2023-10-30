// <!-- 페이징바 -->
		function page(element){
			this.location.href = "/ReSeeRecipe/jhselect.ct?page=" + element;
		}
  
//  <!-- 쪽지 글자 byte count -->
	let limitByte = 500;
	let totalByte;
	
	function checkedByte(obj){
		totalByte = 0;
		let message = $(obj).val();
	
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
  
//  <!-- 카테고리 키워드 입력 후 Enter누르면 카테고리 조회 -->
	function searchNameKeydown(){
		console.log(event.code);
		if(event.code == 'Enter'){
			searchCategory();	
		}
	}

//  <!-- 카테고리 추가 modal -->
  		function showAddCategorydModal(){
  			
  			$('#addCategoryForm').modal('show');
  		}	// showAddCategorydModal
  		
  		
   		function duplicateCheck(){
   			
   			let input = document.getElementById('addCategoryName');
   			let addCategoryName = input.value;
   			console.log(input.value)
   			if(addCategoryName == null || addCategoryName == ''){
   				Swal.fire('실패', '카테고리명을 입력하세요!', 'warning');
   				return;
   			}
   			// console.log(input);				// 값 ok
   			// console.log(input.value);		// input.value값 ok
   			// console.log(addCategoryName);	// ok
   			
  			
  			$.ajax({
  				url : 'jhduplicate.ct',
  				type : 'post',
  				data : {'addCategoryName' : addCategoryName},	// 추가하려는 카테고리명 값 보내기
  				success : function(result){
   					console.log('성공');
   					if(result == 'Y'){
   						Swal.fire('성공', '<b>[' + addCategoryName + ']</b><br><br>추가 가능한 카테고리명입니다', 'success');
   					}
   					else {
   						Swal.fire('실패', '중복된 카테고명이 존재합니다!', 'error');
   						input.value = '';
   						input.focus();
   						input.setAttribute('placeholder', '카테고리명을 다시 입력하세요');		// 중복값 있는 경우 placeholder 속성값 변경
   						console.log(addCategoryName);
   						
  					}
  				},	// success
  				error : function(result){
  					console.log('실패');
  				}	// error
  				
  			}); 	// ajax
  			
  		} // duplicateCheck
  
//  <!-- 카테고리 수정 modal -->
  		function showUpdateCategoryModal(){
  			
 			let trs = document.querySelectorAll('.table tbody tr');	// 데이터가 들어있는 행(tr) 모두 갖고와서 저장
 			let checked_tr = null									// tr체크여부 변수 생성
 			
 			for(let tr of trs){
 				let input = tr.querySelector('input');			// tr의 input 요소 저장
 					if(input.checked){							// input의 checked 속성이 true인 경우
 						checked_tr = tr;						// tr체크여부 변수에 checked == true인 행(tr)을 저장한다
 						break;									// input요소가 여러개인 경우 첫번째에 해당하는 요소만 선택하기 위해 break
 					}
 			}
 			// console.log(checked_tr);
 			
 			// 체크되어 있는 카테고리가 없을 경우 alert창 발생
 			if(checked_tr == null){
 				Swal.fire('실패', '카테고리를 선택해주세요!', 'error');
 				return;
 			}	
 			
 			let modal = document.getElementById('updateCategoryForm');
 			let modal_trs = modal.querySelectorAll('table tr');
 			
 			// 기존게시글 input value
 			let category_name = checked_tr.children[2].textContent;
 			// console.log(category_Name);	// input check 첫번째 요소 값 ok
 			let modal_input = modal.querySelector("input[name='categoryName']");
 			modal_input.value = category_name;
 			// console.log(modal_input);
 			
 			// 기존게시글 수 input value
 			let category_count = checked_tr.children[3].textContent;
 			modal_input = modal.querySelector("input[name='boardCount']");
 			modal_input.value = category_count + '개';

 			
 			// 수정버튼 클릭시 중복여부 체크
 			// 중복값이 있는 경우 alert창
 			// let origin_name = trs[0].children[2].textContent; - 없음 출력
 			
 			
			let origin_name = checked_tr.children[2].textContent;	
 			// console.log(origin_name);	- 카테고리명 ok
 			
 			console.log(checked_tr.children[2].textContent);
 			console.log(checked_tr);
 				
  			$('#updateCategoryForm').modal('show');
  			
  		}	// showUpdateCategoryModal

//  <!-- 카테고리 삭제 -->
  		function deleteCategory(){
  			let trs = document.querySelectorAll('.table tbody tr');	// 데이터가 들어있는 행(tr) 모두 갖고와서 저장
  			let checked_tr = null									// tr체크여부 변수 생성
  			
  			for(let tr of trs){
  				let input = tr.querySelector('input');			// tr의 input 요소 저장
  					if(input.checked){							// input의 checked 속성이 true인 경우
  						checked_tr = tr;						// tr체크여부 변수에 checked == true인 행(tr)을 저장한다
  						break;									// input요소가 여러개인 경우 첫번째에 해당하는 요소만 선택하기 위해 break
  					}
  			}
  			// console.log(checked_tr);
  			
  			// 체크되어 있는 카테고리가 없을 경우 alert창 발생
  			if(checked_tr == null){
  				Swal.fire('실패', '카테고리를 선택해주세요!', 'error');
  				return;
  			}
  			
  			// 체크가 되어있는 경우 confirm창 발생
			Swal.fire({
				title: "카테고리를 삭제하시겠습니까?",
				text : "※ 삭제 후 복원이 불가합니다. ",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "삭제",
				cancelButtonText: "취소"
				}).then((result) => {
					if (!result.isConfirmed) {
					  return;
					}
					
					// 카테고리 번호
					// 1) 카테고리 번호를 넘기고 > 레시피 테이블에서 해당 카테고리 NO에 일치하는 데이터만 STATUS = N && CATEGORY_NO = 0 으로 변경
					// 2) 카테고리 테이블에서 해당 카테고리를 0번으로 변경 or 카테고리 STATUS = N으로 변경
  					let table = document.getElementById('tb-category');			
					let trs = table.querySelectorAll('tbody tr');		// 데이터 행 부분
					// let categoryNo_list = [];		// categoryNo 저장위해 배열 생성
					// let categoryCount_list = [];		// categoryCount 저장위해 배열 생성 (추가)	
					let category_list = {};				// categoryNo : categoryCount 저장을 위한 객체 생성 - ** 어차피 배열로 넘어가니까 []로 각각 선언해도 될 듯? 담에 해보기..나중에..
					
					for(let tr of trs){
						let input = tr.querySelector('input');
						// console.log(input); 				// tr input 데이터 값 확인 ok
						if(input.checked == true){
							let key = (tr.children[1].textContent);
							let val = (tr.children[3].textContent);
							// console.log(key);			// categoryNo - ok
							// console.log(val);			// categoryCount - ok
							// 속성 추가시 obj[key] = value;
							category_list[key] = parseInt(val);
							// console.log(category_list);		// {categoryNo : categoryCount} ok
							
							// categoryNo_list.push(tr.children[1].textContent);		// categoryNo
							// categoryCount_list.push(tr.children[3].textContent);	// categoryCount
							// console.log(categoryNo_list);		// categoryNo 값 확인 ok
							// console.log('***********');
							// console.log(categoryCount_list);	// categoryCount 값 확인 ok
						}
					}
					
			$.ajax({
 				url : 'jhdelete.ct',
				type : 'get',
				data : {
						// 'category':category_list,
						'categoryNo' : Object.keys(category_list),
						'categoryCount' : Object.values(category_list)			// https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Object/values
				},
						// 'categoryNo' : categoryNo_list,				- 삭제			.keys(object1)
						// 'categoryCount' : categoryCount_list	// 추가 - 삭제
				dataType: 'json',
				success : function(result){
					
					Swal.fire('성공', '카테고리 삭제가 완료되었습니다!', 'success');
					
					let removeCategoryCount = 0;
					// let total = <%= pi.getListCount() %>;
					let selectCount = document.querySelector('.selectCount')
					let total = parseInt(selectCount.textContent);
					console.log(total);
					
					for(let tr of trs){
						let categoryNo = parseInt(tr.children[1].textContent);	
						if(result.includes(categoryNo)){		// categoryNo를 포함하는 문자열이 있으면 == true
							tr.remove();						// 해당 tr remove
							total--;
							// console.log(total);				// 전체 조회수에서 -- count되는지 확인ok
							
							selectCount.textContent = total;	// remove total 값 넣어주기
							
							removeCategoryCount += category_list[categoryNo];	// 해당 카테고리에 들어있는 게시글 수
							// console.log(category_list[categoryNo]);	
							// console.log(removeCategoryCount);
						}
					}
					
					// 카테고리No:0의 기존 게시글 수
					let origin_count = parseInt(trs[0].children[3].textContent);
					// console.log(origin_count);
					// 기존 게시글 수에 지워진 게시글 수만큼 ++
					trs[0].children[3].textContent = origin_count + removeCategoryCount;
				},	// success
				error : function(result){
					Swal.fire('실패', '카테고리 삭제가 실패되었습니다!', 'error');
					console.log('실패');
				},	// error
			});	// ajax
			});		// confrim	
  		}	// deleteCategory
  
//  <!-- jsp로 수정예정 -->
  		function searchCategory(){
  			
  			// let test = document.getElementById('check-table');
  			// let input_test = test.querySelector('input');
  			// console.log(test);
  			// console.log(input_test.value);
  			
  			let input = document.getElementById('searchName');
  			// console.log(input);
  			// console.log(input.value);			// 값 ok
  			let searchCategoryName = input.value;
  			console.log(searchCategoryName);			// 값 ok
  			
   			$.ajax({
  				url : 'jhcheck.ct',
  				type : 'post',
  				dataType : 'json',
  				data : {'searchCategoryName' : searchCategoryName},
   				success : function(result){
   					console.log('성공');
   					
    				let resultStr = '';
    				for(let i in result){
    					resultStr += '<tr>'
    								+ '<td><input type="checkbox"></td>'
    							   + '<td>' + result[i].recipeCategoryNo + '</td>'
    							   + '<td>' + result[i].recipeCategoryName + '</td>'
    							   + '<td>' + result[i].recipeCategoryCount + '</td>'
    							   + '</tr>'
    				}
    				
    				$('#tb-category tbody').html(resultStr);
    				
    				$('.paging-area').hide();
    				$('.list-btn').show();
    				
    				
   				},	// success
   				error : function(result){
   					console.log('실패');
   				}	// error
   				
  			}) 
  		}	// searchCategory
  		
  		
/*   	$(function(){
  		$('.table th').on('click', sortTable);	// table th 요소 클릭시 (헤더)
  		
  	})  */
  	
  	// 체크박스 전체 선택 및 해제
	let table = document.getElementById('tb-category');
	
  	function checkAll(){
		let inputs = document.querySelectorAll('tr input');
		
		for(let input of inputs){
			input.checked = event.target.checked;
		}
	}	// checkAll
	
	// 체크박스 테이블 행 1개 해제시 전체 체크박스 해제
	function checkOnce(){
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
	
	// 컬럼정렬
/* 	function sortTable(){
		
		
		
	}	// sortTable */
	
