//	<!-- rs-content(자식요소)를 rs-main안으로 이동시킨다 -->
	$(function(){
	    let main = document.querySelector('.rs-main');
	    let content = document.querySelector('.rs-content');
	    main.appendChild(content);
	});

    $(function(){
    	let $selectMenu = $('.nav-item').children();// a태그
    	$($selectMenu).click(function(){
    		//let $thisele = $(this);// 다음 div
    		let $prevShowList = $(this).parent();// 다음 div
    		let $showList =  $prevShowList.next();// 다음 div
    		/*
			console.log('prev >> ', $prevShowList);    		
			console.log('list >> ', $showList);    		
			console.log('this >> ', $thisele);  
			*/
    		if($showList.css('display') == 'none'){
    			$showList.css('display', 'block');
    		} else {
    			$showList.css('display', 'none');
    		}
    	});
    	//console.log($selectMenu);
/*
		$('#HL_boardMange').click(function(){
    	let $Div = $(this).find('ul');
        if($Div.css('display') == 'none'){
    		$('#HL_boardList').css('display', 'block');
        }else{
    		$('#HL_boardList').css('display', 'none');
        } */
    	
    })
    
    function goMenu(e){
		this.location.href = "/ReSeeRecipe" + e;
	}
	
	
	
    /*
    $(function(){
    	
    	$('#HL_NoticeManage').on("click", gonotice);
    });
    function gonotice() {
        $.ajax({
            type: "GET",
            url: "noticeManager.jsp",
            dataType: "text",
            error: function () {
                alert('통신실패!!');
            },
            success: function (data) {
                $('.rs-content').jsp(data); // GSON 사용해야해서 내일 할게요 - 혜림 -- 넹♥
            }

        });
    }
    */
    
    /*
    $(function(){
    	$('#HL_memberSetting').on("click", goMember);
    });
    function goMember(){
    	$.ajax({
    		type : "GET",
    		url : 'hlmembermanage.ma', //?cmpage=1
    		data : {cmpage : 1},
    		dataType : "html",
    		success : function(result){
    			//$('.rs-content').html(result);
    			console.log('회원 정보 조회 성공');
    			console.log(result);
    			//JSON.parse(result);
    			//console.log(result);
    			//selectMemberAll();
    			//$('.rs-content').text('회원번호' + result[0].memNo);
    			/* $('.rs-content').html(
    					'<'
    					'회원번호' + result[0].memNo); */
  /*  			//createMemTable(result);
    			$('.rs-content').html(result);
    			//$('.rs-content').load("${contextPath}/views/member/memberManager.jsp .rs-content");
    			//$('.rs-content').jsp(result);
    		},
    		error : function(result){
    			console.log(JSON.parse(result));
    			console.log('회원 정보 조회 실패');
    			$('.rs-content').text('조회된 회원이 없습니다');
    		}
    	
    	});
    }
    */
    
	/* 나중에 사용할 수 도 있을거 같아유 */
    function createMemTable(result){
    	 $newTable = $("<br><br><table class='table' id='memAll'><tbody id='memAllList'></tbody></table>");
    	 $('.rs-content').append($newTable);
    	 for(let i in result){
    		 let $newTbody = $("<tr>" + 
    			"<td>" + result[i].memNo + "</td>" +
    			"<td>" + result[i].memName + "</td>" +
    			"<td>" + result[i].memId + "</td>" +
    			"<td>" + result[i].memNickname + "</td>" +
    			"<td>" + result[i].memEmail + "</td>" +
    			"<td>" + result[i].enrollDate + "</td>" +
    			"<td>" + result[i].memReward + "</td>"
    			+ "</tr>");
    		$newTable.append($newTbody);
    	 }
    }
