<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.semi.board.recipe.model.vo.RecipeCategory, com.kh.semi.common.model.vo.PageInfo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
//	ArrayList<RecipeCategory> list = (ArrayList<RecipeCategory>)request.getAttribute("list");
//	String successMsg = (String)session.getAttribute("successMsg");
//	String failMsg = (String)session.getAttribute("failMsg");
	
/* 	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int categoryListCount = pi.getListCount();
	int categoryListPage = pi.getCurrentPage();
	int categoryStartPage = pi.getStartPage();
	int categoryEndPage = pi.getEndPage();
	int categoryMaxPage = pi.getMaxPage(); */
%>            
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[메뉴관리] 카테고리 관리</title>

<!-- sweetalert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- categoryListView / script & css -->
<link rel="stylesheet" href="resources/css/category/category_manager.css">
<link rel="shortcut icon" href="#">
</head>
<body>
	
	<jsp:include page="../../manager/navbar.jsp" />
	<script src="resources/js/category/category_manager.js"></script>
	
    <div class="rs-content">        
        <div class="header">
            <div class="h-title p-3">   <!-- 패딩 1rem -->
                [메뉴 관리] 카테고리 관리
            </div>
            <div class="searchTable">
            	<table id="check-table">
            		<tr>
            			<td>
            				<input class="form-control form-control-sm" id="searchName" name="searchCategoryName" onkeydown="searchNameKeydown()" type="text" placeholder="검색할 카테고리명을 입력하세요" size="30">
           				</td>
           				<td>
            				<button onclick="searchCategory()" class="btn btn-sm btn-warning">조회</button>
           				</td>
            		</tr>
            	</table>
            <!-- </form> -->
            </div>	<!-- searchTable -->
            <div class="h-content d-flex p-3">  <!-- 패딩 1rem -->
                <div class="mr-auto">
                    등록 카테고리 <span class="selectCount">${ requestScope.pi.listCount }</span><span>개</span>
                </div>
                <div >
                    <button onclick="showAddCategorydModal()" class="btn btn-sm btn-warning">카테고리 추가</button>
                    <button onclick="showUpdateCategoryModal()"class="btn btn-sm btn-warning">카테고리 수정</button>
                    <button onclick="deleteCategory()" class="btn btn-sm btn-secondary">카테고리 삭제</button>
                </div>
            </div>
        </div>	<!-- header  -->
        <div class="tableBody">
            <table id='tb-category' class="table table-sm table-hover">
                <thead>
                    <tr>
                        <th data-idx=0><input type="checkbox" onclick="checkAll()"></th>
                        <th data-idx=1 data-type="num">번호<div class="sort"></div></th>
                        <th data-idx=2>카테고리명<div class="sort"></div></th>
                        <th data-idx=3>게시글 수<div class="sort"></div></th>
                    </tr>
                </thead>
                <tbody>
                	<c:choose>
                		<c:when test="${ requestScope.list eq null || empty requestScope.list }">
   	                <tr>
	                    <td colspan="4">등록한 카테고리가 없습니다</td>
	                </tr>
                		</c:when>
                		<c:otherwise>
                			<c:forEach var="recipeCategory" items="${ requestScope.list }">
	                    <tr>
	                        <td><input type="checkbox" onclick="checkOnce()"></td>
	                        <td>${ recipeCategory.recipeCategoryNo }</td>
	                        <td>${ recipeCategory.recipeCategoryName }</td>
	                        <td>${ recipeCategory.recipeCategoryCount }</td>
	                    </tr>	
                			</c:forEach>
                		</c:otherwise>
	               	</c:choose>
                </tbody>
            </table>	<!-- tb-category -->
        </div>	<!-- tableBody  -->
        
	   	<!-- 페이징바 -->
		<div class="paging-area">
			<c:forEach var="p" begin="${ requestScope.pi.startPage }" end="${ requestScope.pi.endPage }">
				<button onclick="location.href='${ pageContext.request.contextPath }/jhselect.ct?page=${ p }';" class="btn btn-warning">${ p }</button>
			</c:forEach>
		</div>	
		
		
		<div style="display: none" class="list-btn">
			<!-- 카테고리 삭제 후 눌렀을경우 history.back은 바로 반영이 되지 않음 -->
			<!-- <button type="button" class="btn btn-sm btn-outline-info" onclick="history.back()">목록으로</button>  -->
			<button type="button" class="btn btn-sm btn-outline-warning" onclick="location.href='${ pageContext.request.contextPath }/jhselect.ct?page=1';">목록으로</button>
			<!-- ${ pageContext.request.contextPath } ==  pageContext.getRequest().getContextPath() %>
				: pageContext는 JSP의 내장객체 > 현재 JSP 페이지의 Context를 나타낸다 (서버에서 설정한 context path가 출력) -->
		</div>
	
   	</div>  <!-- rs-content -->
   	
   	
	<!-- 카테고리 추가  modal창 -->
 	<div class="modal" id="addCategoryForm">
		<form method="get" action="jhinsert.ct">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <!-- Modal Header -->
	                <div class="modal-header">
	                    <h4 class="modal-title">카테고리 추가</h4>
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>	<!-- x 닫기버튼 -->
	                </div> 
	                <!-- Modal body -->
	                <div class="modal-body">
							<input type="hidden" name="categoryListPage">
							<table class="modal-table" border="1">
								<tr>
									<th colspan="2">카테고리명</th>
								</tr>
								<tr>
									<td class="btn-check-r">
										<input type="text" name="recipeCategoryName" id="addCategoryName" placeholder="　　추가할 카테고리명을 입력하세요">
									</td>
									<td class="btn-check-l">
										<button type="button" onclick="duplicateCheck()" class="btn btn-warning">중복확인</button>
									</td>
								</tr>
							</table>
	                </div>
	                <!-- Modal footer -->
	                <div class="modal-footer">
	             		<button type="submit" class="btn btn-sm btn-warning">추가</button>
	                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">취소</button>
	                </div>
	            </div>
	        </div>
	</form>
  	</div>	<!-- 카테고리 추가 modal -->
  
	<!-- 카테고리 수정  modal창 -->
	<div class="modal" id="updateCategoryForm">
		<form method="post" action="jhupdate.ct">
			<div class="modal-dialog">
			    <div class="modal-content">
			        <!-- Modal Header -->
				     <div class="modal-header">
				         <h4 class="modal-title">카테고리 수정</h4>
				         <button type="button" class="close" data-dismiss="modal">&times;</button>	<!-- x 닫기버튼 -->
				     </div> 
				     <!-- Modal body -->
			         <div class="modal-body">
					<input type="hidden" name="dmNo">
					<table class="modal-table" border="1">
						<tr>
							<th>기존 카테고리명</th>
							<td><input type="text" name="categoryName" readonly></td>
						</tr>
						<tr>
							<th>현재 게시글 수</th>
							<td><input type="text" name="boardCount" readonly></td>
						</tr>
						<tr>
							<th>변경 카테고리명<div style="color: rgb(78, 78, 78)"><span class="replied" id="count">0</span> / 20 byte</div></th>
							<td>
								<input type="text" name="categoryUpdateName" placeholder="변경 카테고리명을 입력하세요" onkeyup="checkedByte(this)">
							</td>
						</tr>
					</table>
		          	</div>
		          	<!-- Modal footer -->
		            <div class="modal-footer">
		         		<button type="submit" class="btn btn-sm btn-warning">수정</button>
		                <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">취소</button>
		            </div>
		    	</div>	<!-- modal-content -->
		    </div>	<!-- modal-dialog  -->
		</form>
	</div>	<!-- 카테고리 수정 modal -->
  
 	<!-- alertMsg script -->
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