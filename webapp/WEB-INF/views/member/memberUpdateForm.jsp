<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.kh.semi.member.model.vo.Member" %>
<% String contextPath = request.getContextPath(); %>
<% Member loginMember = (Member)session.getAttribute("loginMember"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보변경</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Alert창 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <link rel="stylesheet" href="resources/css/member/memberUpdateForm.css">
    <script src="resources/js/member/memberUpdateForm.js"></script>

</head>
  <body>
  	<!-- header부분 (상단 메인 메뉴바) -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

    <form enctype="multipart/form-data" action="yrmemberUpdate.me" method="post">

      <h1 id="title"><b>회원정보변경</b></h1>
      
      <!-- 사진을 바꿀 회원의 번호를 hidden으로 같이 넘겨주기  -->
      <input type="hidden" name="memberNo" id="memberNo" value="${ sessionScope.loginMember.memNo }">
      <!-- 회원이 이미 등록된 사진이 있다면 hidden으로 같이 넘겨주기 -->
      <input type="hidden" name="memberPicture" value="${ sessionScope.loginMember.memPicture }">
      <!-- 변경에 필요한 로그인된 비밀번호 hidden으로 같이 넘겨주기 -->
      <input type="hidden" name="loginMemberPwd" value="${ sessionScope.loginMember.memPwd }">

      <!-- 사진 -->
      <div class="container">
      	<c:choose>
      	<c:when test="${ sessionScope.loginMember.memPicture ne null }" >
        	<img src="<%= contextPath %>/${ sessionScope.loginMember.memPicture }" alt="프로필사진" id="profileImg" width="150" height="150">
       	</c:when>
       	<c:otherwise>
        	<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiJ77jbjsG1bGoS5Kn6gm83uk-iiWcuMLRzw&usqp=CAU" alt="프로필사진" id="profileImg" width="150" height="150">
       	</c:otherwise>
       	</c:choose>
        <input type="file" name="profileInput" id="profileInput" onchange="loadImg(this);">
      </div>

      
      <!-- 정보 내용 변경 -->
      <div class="container">
		
		  <!-- value에 loginUser를 update된 걸 넣음 -->
        <p class="tag">이름</p>
        <input type="text" value="${ sessionScope.loginMember.memName }" name="memberName" id="memberName" maxlength="5" required>
        <label for="memberName">* 한글 2 ~ 5자로 입력 가능합니다.</label>
        
        <p class="tag">닉네임(활동명)</p>
        <input type="text" value="${ sessionScope.loginMember.memNickname }" name="memberNickname" id="memberNickname" maxlength="8" required>
        <label for="memberNickname">* 영문, 한글, 숫자 3 ~ 8자로 입력 가능합니다. </label>

        <p class="tag">아이디(변경불가)</p>
        <input type="text" value="${ sessionScope.loginMember.memId }" readonly name="memberId" id="memberId" maxlength="20" required>
        <label for="memberId">* 영문, 숫자 5 ~ 20자로 입력 가능합니다.</label>
        
        <p class="tag">이메일</p>
        <input type="text" value="${ sessionScope.loginMember.memEmail }" name="memberEmail" id="memberEmail" maxlength="50" required>
        <label for="memberEmail">* 인증받을 이메일을 입력해 주세요.</label>

        <!-- 현재 로그인된 값 비교할 수 있게 값 뽑기 -->
        <p class="compare" id="loginMemNickname">${ sessionScope.loginMember.memNickname }</p>
        <p class="compare" id="loginMemEmail">${ sessionScope.loginMember.memEmail }</p>
        
        <!-- 제출버튼!!!!!!!!!!!!!!!!!! onclick = "return validate();"-->
        <button type="submit" id="submitBtn" >변경하기</button>

        <div class="enroll-checkbox">

        <!-- 비밀번호 변경 모달창-->
        <div>
          <a data-toggle="modal" href="#agreeSiteModal">비밀번호 변경</a>

          <div class="modal" id="agreeSiteModal">
            <div class="modal-dialog">
              <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">비밀번호 변경</h4>
                    <button type="button" class="close" data-dismiss="modal" id="close">&times;</button>
                </div>

                <!-- Modal body -->
                <!-- <form action=""> -->
                  <!-- 전에 비밀번호 변경도 ajax로 했으니 이것도 ajax로 해주자 -->
                  <div class="modal-body">
                      <label for="close"></label>
                        <input type="password" placeholder="비밀번호" name="memberPwd" id="memberPwd" maxlength="20">
                        <label for="memberPwd">* 영문, 숫자, 특수문자(!@#$+^*) 포함 8 ~ 20자로 입력 가능합니다.</label>
                        
                        <input type="password" placeholder="비밀번호 확인" name="memberPwdCheck" id="memberPwdCheck" maxlength="20">
                        <label for="memberPwdCheck">* 비밀번호가 일치하지 않습니다.</label>
                  </div>
                  <!-- Modal footer -->
                  <div class="modal-footer">
                      <!-- 아이디 같이 보내주기 -->
                      <!-- <button type="submit" action="yrupdateMemberPwd.me?memberId=${ sessionScope.loginMember.memId }" method="post"></button> -->
                      <button type="button" class="btn btn-danger" id="updatePwdBtn" onclick="updateMemberPwd();" data-dismiss="modal" >비밀번호 변경</button>
                  </div>
                  
                <!-- </form> -->
              </div>
            </div>
          </div>

        </div>
        <!-- 비밀번호 변경 모달창 끝 -->

        <!-- 회원삭제버튼 -->
        <button type="button" onclick="deleteAlert();">탈퇴하기</button>            

        </div>
        
      </div>
        
    </form>
      
    <!-- footer 푸터영역 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script>
      // 필요한 변수 선언
      const profileImg = document.getElementById('profileImg');
      const profileInput = document.getElementById('profileInput');

      // 사진을 클릭하면 프로필 사진 변경
      function loadImg(inputFile){
          console.log("클릭하면");
          // 파일을 첨부했을 때
          if(inputFile.files.length == 1){
            console.log("성공");
            console.log(inputFile.files.length);
              // 파일을 읽어올 객체 생성
              let reader = new FileReader();
              // 파일의 긴 url을 읽어옴
              reader.readAsDataURL(inputFile.files[0]);
              // 파일읽기가 완료되면 익명함수 호출
              reader.onload = function(e){
                  // $('#profileImg').attr('src', e.target.result);
                  // 이벤트가 발생한곳의 result에 담겨있는 url을 파일 src값으로 넣어줌
                  console.log(profileImg);
                  profileImg.src = e.target.result;
              }
          // 취소(파일 없음)
          } else {
            console.log("실패");
            console.log(inputFile.files.length);
              // 새로 등록된 사진이 없으므로 기존의 사진 등록
              const noImg = 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiJ77jbjsG1bGoS5Kn6gm83uk-iiWcuMLRzw&usqp=CAU';
              // https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1.png
              // https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiJ77jbjsG1bGoS5Kn6gm83uk-iiWcuMLRzw&usqp=CAU
              // https://s3.orbi.kr/data/file/united2/9f61edb66efb4ff982a2675ec5e9e806.png
              profileImg.src = noImg;
          }

      };
      // 파일첨부 input요소를 숨기고 사진을 클릭했을 때 input요소 클릭
      profileInput.style.display = 'none';
      profileImg.addEventListener('click', function(){
        profileInput.click();
      });


      function deleteAlert(){

        Swal.fire({
          title: '정말로 탈퇴하시겠습니까?',
          // showDenyButton: true,
          showCancelButton: true,
          confirmButtonText: '탈퇴하기',
          // denyButtonText: `안함`,
          denyButtonText: `취소`,
        }).then((result) => {
          /* Read more about isConfirmed, isDenied below */
          if (result.isConfirmed) {
            // 탈퇴하기 버튼 클릭 시 함수 호출
            memberDelete();
          } 
        });

        // 탈퇴하기 DB ajax
        function memberDelete(){
          $.ajax({
            url : 'yrmemberDelete.me',
            data : {memberNo : $('#memberNo').val()},
            // 회원 탈퇴 성공 시
            success : function(result) {
              if(result == 'NNNNY'){
                Swal.fire({
                  title: '탈퇴 완료',
                  text: "탈퇴되었습니다.",
                  icon: 'success',
                  confirmButtonColor: '#3085d6',
                  confirmButtonText: '확인'
                  }).then((result) => {
                    // 확인버튼 클릭 시 메인회면으로 이동
                if (result.isConfirmed) {
                  $(location).attr("href", "<%= contextPath %>");
                  }
                })
              // 회원 탈퇴 실패 시
              } else{
                Swal.fire({
                icon: 'error',
                title: '탈퇴 실패',
                text: '오류가 반복되는 경우 관리자에게 문의하세요.',
                })
              }
            },
            // 중복체크 조회 실패 시
            error : function(){
              console.log('회원 탈퇴 AJAX통신 실패!');
            }
          })
        };

      }

      
    </script>



</body>
</html>