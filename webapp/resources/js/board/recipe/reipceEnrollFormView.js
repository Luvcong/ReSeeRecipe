// jsp파일 로딩 시 카테고리 접힘상태로 로딩시켜주는 함수
$(function(){
	const categoryFoldingText = $('#category-toggle-msg > h3');
	categoryFoldingText.text('카테고리 더보기');
	$('#category-toggle-menu').css('display', 'none');
});




// 임시저장 아이콘 클릭 시 모달창 설정
function unrecipeModalRequest(e) {
	console.log(document.getElementsByClassName('.testList'));
	if(document.getElementsByClassName('.testList').length < 3 ) {

		e.dataset.target = '#unrecipe-modal';
	} else {
		e.dataset.target = '#unrecipe-unavailable-modal';
	}
	alert("d");
};



$(function () {
	// 선택된 옵션을 저장하는 변수
	var selectedTagHtml = null;
	var selectedTagNo = null;
	// select 요소를 참조
	var selectElement = $("#tnNotAppl");

	// label에 넣을 Str
	var newTagLabelStr = $('label[for=tnNotAppl]').html();
	var duplicateCheckCount = 0; // 중복 체크 횟수

	// select 요소에 클릭 이벤트 리스너 추가
	selectElement.on("change", function () {
		// 중복 체크가 5회 이상이면 이벤트 제거
		if (duplicateCheckCount >= 5) {
			selectElement.off("change"); // 이벤트 제거
			selectElement.prop("disabled", true); // select 요소 비활성화
			selectElement.val("");
			return;
		}

		// 선택된 옵션의 값을 가져옴
		selectedTagHtml = selectElement.find('option:selected').html();
		selectedTagNo = selectElement.find('option:selected').val();

		// 중복 확인
		if (newTagLabelStr.indexOf(' #' + selectedTagHtml + ' ') === -1) {
			newTagLabelStr += ' #' + selectedTagHtml + ' ';
			$('label[for=tnNotAppl]').html(newTagLabelStr);
			
			// hidden 인풋 요소를 만들어 값을 설정
			console.log(duplicateCheckCount);
			var hiddenInput = $('<input type="hidden" name="tagNo' + duplicateCheckCount + '" value="' + selectedTagNo + '">');
			$('#cook-steps-hashtag').append(hiddenInput);
			
			duplicateCheckCount++;
		} else {
			alert('태그를 중복으로 추가할 수 없습니다');
		}
		// 선택 해제 "해시태그 선택" 옵션을 선택
		selectElement.val("");
	});
});



// 타이틀 글자수 바이트 수 세기
$(function(){
	$('#cook-steps-title textarea').keyup(function(e){
		var textAreaBytes = 0;
		var textArea = $(this).val();
		var numberingSpan = $('#cook-steps-title').find('span').eq(0);
		
		var patternKor = /[ㄱ-ㅎㅏ-ㅣ가-힣]/m;
		var patternBlank = /[\s]/m;
		var patternOne = /[\w~!@#%^&*()_+-=\\$\`\[\]\{\}]/m;
		
		if(textArea.length != 0){
			for(let i = 0; i < textArea.length; i++){
				if(textAreaBytes <= 60) {
					textAreaBytesBefore = textAreaBytes;
					if(patternKor.test(textArea.charAt(i))) {
						textAreaBytes += 3;
					}
					else if(patternBlank.test(textArea.charAt(i)) || patternOne.test(textArea.charAt(i))) {
						if(e.key == 'Enter') {
							textAreaBytes += 2;
						}
						else {
							textArea.replace(patternBlank, ' '); // 엔터 외에는 모두 한칸 스페이스로 변경
							textAreaBytes++;
						}
					}
					else {
						textAreaBytes += 3;
					}
					numberingSpan.text(textAreaBytes);
				}
				if(60 < textAreaBytes) { // if처리
					$(this).val(textArea.substring(0, i));
					numberingSpan.text(textAreaBytesBefore);
					alert('더 이상 입력할 수 없습니다!');
					return false;
				}
			}
		}
		else {
			numberingSpan.text(0);
		}
	});
});



// 재료 입력 디스플레이 생성
function addIngredientDisplay(){
	
	// 입력된 재료 값 받기
	var ingredientIn = document.getElementById('ingredientIn');
	var ingredientAmountIn = document.getElementById('ingredientAmountIn');
	var ingredientMeasureNoIn = document.getElementById('ingredientMeasureNoIn');
	
	
	if(ingredientIn.value == '' || ingredientAmountIn.value == '') {
		alert('재료 입력란을 모두 입력하세요');
	} else {
		// 현재 만들어진 ingredientContainer 개수 카운트 (없을때 0부터 시작)
		var count = document.getElementsByClassName('ingredientContainer').length;
		
		if(count < 30) {
			
			// 생성된 요소 띄워줄 영역
			var anIngredientContent;
			var ingredientContentLeft = document.getElementById('ingredientContentLeft');
			var ingredientContentRight = document.getElementById('ingredientContentRight');
			
			// Container 생성 + 세팅 + append하기
			if(count < 15) {
				anIngredientContent = ingredientContentLeft;
			} else {
				anIngredientContent = ingredientContentRight;
			}
			var ingredientContainer = document.createElement('div');
			ingredientContainer.id = 'ingredientContainer' + count;
			ingredientContainer.classList.add('ingredientContainer');
			anIngredientContent.appendChild(ingredientContainer);
			
			// Container 내부 3파트 전부 세팅
			// 재료칸
			var ingredientAreaDiv = document.createElement('div');
			ingredientAreaDiv.id = 'ingredientAreaDiv' + count;
			ingredientContainer.appendChild(ingredientAreaDiv);
			
			var ingredient = document.createElement('input');
			ingredient.id = 'ingredient' + count;
			ingredient.name = 'ingredient' + count; // 네임
			ingredient.classList = 'form-control';
			ingredient.placeholder = '재료입력';
			ingredient.maxLength = '15';
			ingredient.required = true;
			ingredient.value = ingredientIn.value;
			ingredientIn.value = null;
			ingredientAreaDiv.appendChild(ingredient);
			
			// 재료량 (숫자)
			var ingredientAmountDiv = document.createElement('div');
			ingredientAmountDiv.id = 'ingredientAmountDiv' + count;
			ingredientContainer.appendChild(ingredientAmountDiv);
			
			var ingAmount = document.createElement('input');
			ingAmount.id = 'ingAmount' + count;
			ingAmount.classList = 'form-control';
			ingAmount.placeholder = '재료량';
			ingAmount.maxLength = '15';
			ingAmount.required = true;
			ingAmount.value = ingredientAmountIn.value;
			ingredientAmountIn.value = null;
			ingredientAmountDiv.appendChild(ingAmount);
			
			// + 계량단위
			var measureAreaDiv = document.createElement('div');
			measureAreaDiv.id = 'measureAreaDiv' + count;
			ingredientContainer.appendChild(measureAreaDiv);
			
			var ingMeasure = document.createElement('select');
			ingMeasure.id = 'ingMeasure' + count;
			ingMeasure.classList = 'ingMeasure';
			ingMeasure.required = true;
			measureAreaDiv.appendChild(ingMeasure);
			
			const optionArr = {
				g : 'g',
				kg : 'kg',
				lb : 'lb',
				pinch : '약간',
				t : '작은술(t)',
				T : '큰술(T)',
				ml : 'ml',
				L : 'L',
				cup : '컵',
				clove : '알',
				ea : '개',
				slice : '장'
			};
			
			// 옵션 생성
			for(optVal in optionArr) {
				var option = document.createElement('option');
				option.value = optionArr[optVal];
				option.innerText = optionArr[optVal];
				ingMeasure.appendChild(option);
			};
			
			// select - option 재료량+계량단위 hidden인풋
			var ingredientAmount = document.createElement('input');
			ingredientAmount.type = 'hidden';
			ingredientAmount.name = 'ingredientAmount' + count;
			ingredientAmount.value = ingAmount.value + ingMeasure.value;
			measureAreaDiv.appendChild(ingredientAmount);

			// 삭제버튼 추가
			var delIngBtnDiv = document.createElement('div');
			delIngBtnDiv.id = 'delIngBtnDiv' + count;
			ingredientContainer.appendChild(delIngBtnDiv);
			
			var delIngBtn = document.createElement('button');
			delIngBtn.id = 'delIngBtn' + count;
			delIngBtn.type = 'button';
			delIngBtn.classList = 'fas fa-minus-square modify-btn';
			delIngBtnDiv.appendChild(delIngBtn);
		}
	}
};



$(function(){
	// 요소 넘버링 다시 해주는 함수
	function reorderingIngredients(){
		$('.ingredientContainer').each(function(index){
			var $containers = $(this);
			
			$containers.attr('id', 'ingredientContainer' + index);
			
			$containers.find('div[id^=ingredientAreaDiv]').attr('id', 'ingredientAreaDiv' + index);
			$containers.find('input[id^=ingredient]').attr('id', 'ingredient' + index).attr('name', 'ingredient' + index);
			
			$containers.find('div[id^=ingredientAmountDiv]').attr('id', 'ingredientAmountDiv' + index);
			$containers.find('input[id^=ingAmount]').attr('id', 'ingAmount' + index);
			
			$containers.find('div[id^=measureAreaDiv]').attr('id', 'measureAreaDiv' + index);
			$containers.find('select[id^=ingMeasure]').attr('id', 'ingMeasure' + index);
			$containers.find('input[name^=ingredientAmount]').attr('name', 'ingredientAmount' + index);
			
			$containers.find('div[id^=delIngBtnDiv]').attr('id', 'delIngBtnDiv' + index);
			$containers.find('button[id^=delIngBtn]').attr('id', 'delIngBtn' + index);
			
			if(index < 15) {
				$containers.appendTo($('#ingredientContentLeft'));
			} else {
				$containers.appendTo($('#ingredientContentRight'));
			}
		});
	};

	// 생성된 요소에 삭제이벤트 (& 콘테이너 id, 내부div id, input id, name)
	$('#cookStepsIngredientContent').on('click', 'button[id ^= delIngBtn]', function(){
		$(this).parents('div[id^=ingredientContainer]').remove();
		reorderingIngredients();
	});
});



var cookStepsCount = 1; // 썸네일0 + 첫번째요리과정1
// Add버튼 클릭 이벤트 핸들러를 등록
$("#instAddBtn").on("click", function () {
	if (cookStepsCount < 6) {
		// 카운트 ++ 및 clone
		cookStepsCount++;
		var $newCookingInstInner = $('#cookStepsInstInner1').clone();

		// 요소 수정 (value먼저 비우고 name, id 등 세팅)
		$newCookingInstInner.attr('id', 'newCookingInstInner' + cookStepsCount); // 전체 div
		$newCookingInstInner.find('input[name=cookStepsLev1]').val(cookStepsCount); // input hidden으로 넘기는 cookSteptsLev값
		$newCookingInstInner.find('input[name=cookStepsLev1]').attr('name', 'cookStepsLev' + cookStepsCount);
		$newCookingInstInner.find('p[class=inst-title-lev]').html(cookStepsCount); // 화면에 띄워주는 cookStepsLev p요소
		$newCookingInstInner.find('input[name=cookStepsTitle1]').val(''); // 제목 인풋요소
		$newCookingInstInner.find('input[name=cookStepsTitle1]').attr('name', 'cookStepsTitle' + cookStepsCount);
		$newCookingInstInner.find('button[id=delCookSteps1]').attr('id', 'delCookSteps' + cookStepsCount); // 삭제 버튼
		$newCookingInstInner.find('img[id=recipePicImg1]').attr('src', 'https://simg.wooribank.com/img/section/bz/buss_product_noimgb.gif');  // 이미지 영역
		$newCookingInstInner.find('img[id=recipePicImg1]').attr('id', 'recipePicImg' + cookStepsCount);
		$newCookingInstInner.find('textarea[name=cookStepsContent1]').val(''); // 내용입력 textarea부분
		$newCookingInstInner.find('textarea[name=cookStepsContent1]').attr('name', 'cookStepsContent' + cookStepsCount);
		
		// Add버튼 전에 insert
		$newCookingInstInner.insertBefore('#cookStepsInstInnerEnd');
	}

	if (cookStepsCount === 6) {
		// cookStepsCount가 6일 때 클릭 이벤트 해제
		$('#instAddBtn').off('click');
		// #cookStepsInstInnerEnd 삭제
		$('#cookStepsInstInnerEnd').remove();
	}
});


// Del버튼 클릭 이벤트 핸들러를 등록
$("#cookingInstructionContainer").on("click", "button[id^=delCookSteps]", function () {
	
	// 삭제
	$(this).parents('div[id^=newCookingInstInner]').remove();
	
	var stepNumber = $(this).siblings('input[name^=cookStepsLev]').val();
	// 해당 단계 이후의 단계 번호를 조정
	for (var i = stepNumber + 1; i <= 6; i++) {
		var $nextStep = $("#cookStepsInstInner" + i);
		if ($nextStep.length > 0) {
		// 순차적으로 숫자 변경
		$nextStep.find("input[name=cookStepsLev" + i + "]").val(i - 1);
		$nextStep.find("p.inst-title-lev").text(i - 1);
		// 순차적으로 id 변경
		$nextStep.attr("id", "cookStepsInstInner" + (i - 1));
		$nextStep.find("input[name=cookStepsLev" + i + "]").attr("name", "cookStepsLev" + (i - 1));
		$nextStep.find("input[name=cookStepsTitle" + i + "]").attr("name", "cookStepsTitle" + (i - 1));
		$nextStep.find("button[id=delCookSteps" + i + "]").attr("id", "delCookSteps" + (i - 1));
		$nextStep.find("img[id=recipePicImg" + i + "]").attr("id", "recipePicImg" + (i - 1));
		$nextStep.find("textarea[name=cookStepsContent" + i + "]").attr("name", "cookStepsContent" + (i - 1));
		}
	}
	
	// cookStepsCount 감소
	cookStepsCount--;
	
	// 만약 6개의 단계가 채워지지 않았다면 추가 버튼 활성화
	if (cookStepsCount < 6) {
		$("#instAddBtn").prop("disabled", false);
	}
});



function loadRecipeImg(inputRecipePic, num) {
	var $targetRecipePic = $('#recipePicImg' + num);
	if(inputRecipePic.files.length == 1) { // 파일 첨부되면 파일 읽어들여 미리보기 띄워주기
		let reader = new FileReader();
		reader.readAsDataURL(inputRecipePic.files[0]);
		reader.onload = function(e) {
			$targetRecipePic.attr('src', e.target.result);
		};
	} else {
		const noImgStr = 'https://simg.wooribank.com/img/section/bz/buss_product_noimgb.gif';
		$targetRecipePic.attr('src', noImgStr); // 파일 없으면 0 ~ 6번 미리보기 영역에 noImg사진
	}

	// if(inputRecipePic.files.length == 1) { // 파일 첨부되면 파일 읽어들여 미리보기 띄워주기
	// 	let reader = new FileReader();
	// 	reader.readAsDataURL(inputRecipePic.files[0]);
	// 	reader.onload = function(e) {
	// 		$('#recipePicImg' + num).attr('src', e.target.result);
	// 		$('#recipePicImg' + num).attr('required', true);
	// 	};
	// } else {
	// 	const noImgStr = 'https://simg.wooribank.com/img/section/bz/buss_product_noimgb.gif';
	// 	$('#recipePicImg' + num).attr('src', noImgStr); // 파일 없으면 0 ~ 6번 미리보기 영역에 noImg사진
	// 	if(1 < num) { // 2 ~ 6번 파일의 경우는 required해제 (0썸네일, 1첫번째요리과정은 무조건 required)
	// 		$('#recipePicImg' + num).attr('required', false);
	// 	}
	// }
};



$(function(){
	// 못생긴 파일버튼을 숨기자
	$('#recipePicFileArea').hide();

	// 미리보기 영역 클릭 시 여기가 클릭되도록 => 미리보기 영역은 동적요소라 on으로 달아주기
	$('#recipe-enroll-context-wrap').on('click', 'img[id^=recipePicImg]', function(){
		var $rPicLastNum = parseInt($(this).attr('id').slice(-1));
		switch($rPicLastNum) {
			case 0 : $('#recipeNameOrigin0').click(); break;
			case 1 : $('#recipeNameOrigin1').click(); break;
			case 2 : $('#recipeNameOrigin2').click(); break;
			case 3 : $('#recipeNameOrigin3').click(); break;
			case 4 : $('#recipeNameOrigin4').click(); break;
			case 5 : $('#recipeNameOrigin5').click(); break;
			case 6 : $('#recipeNameOrigin6').click(); break;
			default : break;
		}
	})
});



// 글작성 요청
function confirmSubmit() {
	return confirm("글을 작성하시겠습니까?");
};

// 초기화 요청 confirm
function confirmReset() {
	return confirm("입력한 정보를 초기화하시겠습니까?");
};


$(function(){
	// 임시저장글 3개미만 모달창 작성요청 form태그 속성 설정 및 submit
	$('#unrecipe-enrolling-btn').click(function(){
		$('#recipe-enrolling-form').attr('action', '<%= contextPath %>/unRecipeInsert.un').submit();
	});
	
	// 임시저장글 3개이상 모달창 글삭제/작성요청 form태그 속성 설정 및 submit
	$('#unrecipe-del-enrolling-btn').click(function(){
		$('#recipe-enrolling-form').attr('action', '<%= contextPath %>/unRecipeInsertInstead.un').submit();
	})
});
