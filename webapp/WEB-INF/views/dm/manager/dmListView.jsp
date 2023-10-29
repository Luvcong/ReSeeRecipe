<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.semi.dm.model.vo.Dm, com.kh.semi.common.model.vo.PageInfo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	int waitingCount = (int)request.getAttribute("waitingCount");
	int repliedCount = (int)request.getAttribute("repliedCount");
	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int dmListCount = pi.getListCount();
	int dmListPage = pi.getCurrentPage();
	int dmStartPage = pi.getStartPage();
	int dmEndPage = pi.getEndPage();
	int dmMaxPage = pi.getMaxPage();
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[문의관리] 쪽지함관리</title>

<!-- sweetalert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- categoryListView / script & css -->
<link rel="stylesheet" href="resources/css/dm/dm_manager.css">
<link rel="shortcut icon" href="#">

</head>
<body>
	
	<jsp:include page="../../manager/navbar.jsp" />
	<script src="resources/js/dm/dm_manager.js"></script>
	
    <div class="rs-content">        
        <div class="header">
            <div class="h-title p-3">   <!-- 패딩 1rem -->
                [문의 관리] 쪽지함 관리
            </div>
            <div class="h-content d-flex p-3">  <!-- 패딩 1rem -->
                <div class="mr-auto">
                    미답변 <span class="waiting">${ requestScope.pi.listCount - requestScope.repliedCount }</span> 개 / 답변완료 <span class="replied">${ requestScope.repliedCount }</span>개
                </div>
                <div >
                    <button class="btn btn-sm btn-warning" onclick="showDmRepliedModal()">쪽지 답변</button>
                    <button class="btn btn-sm btn-secondary" onclick="deleteDm()">쪽지 삭제</button>
                </div>
            </div>
        </div>
        <div class="tableBody">
            <table id='tb-dm' class="table table-sm table-hover">
                <thead>
                    <tr>
                        <th data-idx=0><input type="checkbox" onclick="checkAll()"></th>
                        <th data-idx=1 data-type="num">번호<div class="sort"></div></th>
                        <th data-idx=2>등록일<div class="sort"></div></th>
                        <th data-idx=3>아이디<div class="sort"></div></th>
                        <th data-idx=4>닉네임<div class="sort"></div></th>
                        <th data-idx=5>쪽지 문의내용<div class="sort"></div></th>
                        <th data-idx=6>답변여부<div class="sort"></div></th>
                        <th data-idx=7 style="display: none">답변내용<div class="sort"></div></th>
                    </tr>
                </thead>
                <tbody>
                <c:choose>
                	<c:when test="${ requestScope.list eq null || empty requestScope.list }">
   	                <tr>
	                    <td colspan="7">받은 쪽지가 없습니다</td>
	                </tr>
                	</c:when>
                	<c:otherwise>
                		<c:forEach var="dm" items="${ requestScope.list }">
	                    <tr ondblclick="onDbClickRow()">
	                        <td><input type="checkbox" onclick="checkOnce()"></td>
	                        <td>${ dm.dmNo }</td>
	                        <td>${ dm.sendDate }</td>
	                        <td>${ dm.memId }</td>
	                        <td>${ dm.memNickname }</td>
	                        <td>${ dm.dmContent }</td>
	                        <td class="dm-status-${ dm.dmStatus }">답변</td>
	                        <td style="display: none">${ dm.dmReply }</td>
	                    </tr>
                		</c:forEach>
                	</c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
        
   	   	<!-- 페이징바 -->
		<div class="paging-area">
			<c:forEach var="p" begin="${ requestScope.pi.startPage }" end="${ requestScope.pi.endPage }">
				<button onclick="location.href='${ pageContext.request.contextPath }/jhselect.dm?page=${ p }'" class="btn btn-warning">${ p }</button>
			</c:forEach>
		</div>	<!-- 페이징바 -->
    	</div>  <!-- rs-content -->

	<!-- 쪽지답변  modal창 -->
 	<div class="modal" id="dmRepliedForm">
		<form method="post" action="${ pageContext.request.contextPath }/jhupdate.dm">
	        <div class="modal-dialog modal-lg">
	            <div class="modal-content">
	                <!-- Modal Header -->
	                <div class="modal-header">
	                    <h4 class="modal-title">쪽지 상세내역</h4>
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>	<!-- x 닫기버튼 -->
	                </div> 
	                <!-- Modal body -->
	                <div class="modal-body">
							<input type="hidden" name="dmNo">
							<input type="hidden" name="page" value="${ requestScope.pi.currentPage }">
							<table class="modal-table" border="1">
								<tr>
									<th>회원 아이디</th>
									<td></td>
								</tr>
								<tr>
									<th>회원 닉네임</th>
									<td></td>
								</tr>
								<tr>
									<th>쪽지 발송시간</th>
									<td></td>
								</tr>
								<tr>
									<th class="text-primary">문의 내용</th>
									<td height="200px"></td>
								</tr>
								<tr>
									<th class="text-danger">문의 답변<div style="color: rgb(78, 78, 78)"><span class="replied" id="count">0</span> / 500 byte</div></th>
									<td><textarea id="reply-textarea" name="dmReply" onkeyup="checkedByte(this)" placeholder="&#10;&#10;&#10;답변할 내용을 입력하세요&#10;(최대 500byte)"></textarea></td>
								</tr>
							</table>
	                </div>
	                <!-- Modal footer -->
	                <div class="modal-footer">
	             		<button type="submit" class="btn btn-sm btn-warning">답변</button>
	                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">취소</button>
	                </div>
	            </div>
	        </div>
	</form>
  </div>
  
  <!-- alertMsg script : DmListController에서 사용 -->
 	<c:choose>
 		<c:when test="${ sessionScope.successMsg ne null && not empty sessionScope.successMsg }">
 			<script>
	 			Swal.fire('성공', '${ successMsg }', 'success');
 			</script>
 		</c:when>
 		<c:when test="${ sessionScope.failMsg ne null && not empty sessionScope.failMsg }">
 			<script>
 			Swal.fire('실패', '${ failMsg }', 'error');
 			</script>
 		</c:when>
 	</c:choose>
 	
 	<c:remove var="successMsg" />
 	<c:remove var="failMsg" />
  	
	

</body>
</html>