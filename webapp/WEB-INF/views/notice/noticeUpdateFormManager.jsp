<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.semi.notice.model.vo.*,com.kh.semi.tag.model.vo.*, java.util.ArrayList" %>
<%
	
	Notice n = (Notice)request.getAttribute("n");
	NoticePic np = (NoticePic)request.getAttribute("np");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정하기</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" href="resources/css/member/memberUpdateManager.css">  
	<!-- 해시태그 Tagify 라이브러리 -->
	<!-- 소스 다운 -->
	<script src="https://unpkg.com/@yaireo/tagify"></script>
	<!-- 폴리필 (구버젼 브라우저 지원) -->
	<script src="https://unpkg.com/@yaireo/tagify/dist/tagify.polyfills.min.js"></script>
	<link href="https://unpkg.com/@yaireo/tagify/dist/tagify.css" rel="stylesheet" type="text/css" />


</head>
<body>
    <%@ include file="../manager/navbar.jsp" %>
<div class="rs-content">
    
    <h2>공지사항 수정하기</h2>
    
    
    <div class="container">
        <form enctype="multipart/form-data" class="form" action="<%=contextPath%>/hlnoticeUpdate.ma" id="HL_noticeUpdate_Form" method="post">
            
            <!-- Session의 loginMember에서 관리자 정보 hidden으로 넘겨서 notice테이블에 update -->
   
            <label for="HL_noticeUpTitle">공지사항 제목 : </label>
            <input type="text" class="form-control" placeholder="제목을 입력하세요" name="HL_noticeUpTitle" id="HL_noticeUpTitle" value="<%= n.getNoticeTitle() %>" required>
    <!--    <label for="HL_noticeWriter">ìì±ì :</label>  -->   
    <!--    <input type="password" class="form-control" placeholder="ê´ë¦¬ì | ë§¤ëì " name="HL_noticeWriter" id="HL_noticeWriter" required> -->      
            <br>
            <label for="HL_noticeUpFile">이미지</label> 
            <% if(np != null) { %>
            	<img alt="공지사항 사진" src="<%= contextPath %>/<%= np.getNoticePicPath() %>/<%= np.getNoticePicNagmeChange()%>">
            <% } %>
            <input type="file" name="HL_noticeUpFile" id="HL_noticeUpFile">
            	
              
            <br><br>
            <label for="HL_noticeUpContent">공지사항 내용</label>
            <textarea class="form-control" rows="5" name="HL_noticeUpContent" id="HL_noticeContent"><%= n.getNoticeCon() %></textarea>
            <br>
           
    <!--   <label for="HL_noticeHashtag">í´ìíê·¸</label>
            <input type="text" class="form-control" placeholder="í´ìíê·¸ë¥¼ ìë ¥íì¸ì(#ì ì¸)" name="HL_noticeHashtag" id="HL_noticeHashtag">
            <br> --> 
            <lable for="HL_noticeUpTag">해시태그</lable> <br>
            <input id="HL_noticeUpTag" name='Uptags'
  			class='some_class_name'            
 			placeholder='해시태그를 입력해주세요'     
  			data-blacklist='ㅅㅂ, ㄲㅈ, 죽어, 디저, ㅂㅅ, 시발'
  			>  
                  
			<script>
			$(document).ready(function () {

                let $hash = $('#HL_noticeUpTag').val();
                console.log($hash);

                $.ajax({
                    url : 'hlnoticeselected.tg',
                    data : {noticeNo : <%=n.getNoticeNo()%>},
                    type : 'GET',
                    dataType : 'json',
                    success : function(result){
                        console.log(result);
                        if(result != "[]"){
                        	const noticeT = $('#HL_noticeUpTag').val();
                        	for(let tag in result){
                        		noticeT += result[value];
                        	}
                        	console.log(noticeT);
                        } 
                    },
                    error : function(result) {

                    }
                });




             // 등록된 해시태그명 조회
			    $.ajax({
			      url: 'hlhashtag.tg',
			      type: 'GET',
			      dataType: 'json',
			      success: function (result) {
			    	// 태그명 "tagName" 을 추출 -> whitelist로 설정
			        var whitelist = result.map(function (item) {
			          return item.tagName;
			        });
			        initTagify(whitelist);
			        
			      },
			      error: function (error) {
			        console.error('에러 발생:', error);
			      }
			    });

			    function initTagify(whitelist) {
			      var inputElm = document.querySelector('input[name=Uptags]');
			   // 사용자가 해시태그 입력하였을 때 보여줄 해시태그 개수 5개
			      var tagify = new Tagify(inputElm, {
			        enforceWhitelist: true,
			        whitelist: whitelist,
			        dropdown: {
			          maxItems: 5,
			          enabled: 0,
			          closeOnSelect: true,
			        }
			      });

			      tagify.on('input', onInput);

			      function onInput(e) {
			        var value = e.detail.value;
			        var input = value.toLowerCase().trim();
			        var dataBlacklist = inputElm.getAttribute('data-blacklist');
			        var blacklist = dataBlacklist.split(',').map(function (item) {
			          return item.trim().toLowerCase();
			        });

			        if (blacklist.includes(input)) {
			          //tagify.removeTags();
			          console.log('입력값이 블랙리스트에 포함되어 삭제됨.');
			          tagify.replaceTag();
			        }
			      }

			      tagify.on('add', onAddTag);
			      
			      function onAddTag(e) {
					var dataInput = e.detail.data.value;
			        var hashtagList = [];
			        console.log(tagify.value);
			        console.log(tagify.value.length);
			     // 사용자가 입력한 해시태그가 whitelist(등록된 해시태그)일 경우 해시태그리스트에 추가
			        for(let i=0 ; i< tagify.value.length ; i++){
			        	hashtagList.push(tagify.value[i].value);
			        }
			        console.log(hashtagList);
			      }
			      
			    }
			    
			  });
			
			</script>
            
            <br><br>
            <button type="submit" class="btn btn-warning">수정하기</button>
            <button type="submit" class="btn btn-warning">돌아가기</button>
        </form>
    </div>
</div>
    
</body>
</html>