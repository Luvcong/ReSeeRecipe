<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.semi.tag.model.vo.Tag, com.kh.semi.common.model.vo.PageInfo" %>
<%
	ArrayList<Tag> list = (ArrayList<Tag>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	String successMsg = (String)session.getAttribute("successMsg");
	String failMsg = (String)session.getAttribute("failMsg");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>해시태그 관리</title>

    <style>
        .sort {
			display: inline-block;
			padding-left: 10px;
			color: darkgray;
		}
		.table th:hover {
			cursor: pointer;
		}
		.asc::before {
			content: "\25B2";
		}
		.desc::before {
			content: "\25BC";
		}
        
        /* 팝업창 
        .h_popup_overlay {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        display: flex;
        justify-content: center;
        align-items: center;
        visibility: hidden;
        opacity: 0;
        transition: visibility 0s, opacity 0.3s ease;
        }
        .popup-content {
        width: 750px;
        padding: 20px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        } */
        
        .modal .modal-title{
			text-align: center;
			font-weight: bold;
		}
		.modal .modal-content{
			background-color: rgb(255, 217, 64);
		    padding: 5px 20px 30px 20px;
		}
		.modal .modal-body, .modal .modal-footer{
			background-color: white;
			padding:0;
		}
		.modal .modal-footer{
			height: 50px;
			border: 1px solid gray;
			justify-content: center;
		}
		.modal-table {
			width:100%;
			text-align: center;
		}
		/* .modal-table th {
			width: 130px;
		} */
		.modal-table tr {
			height: 50px;
		}
		.modal-table tr td > label {
			font-size: 12px;
		}
		.modal input {
		    width: 100%;
		    height: 50px;
		    cursor: pointer;
		    border: none;
		    text-align: center;
		}
		
		.paging-area{
			text-align: center;
			padding: 65px;
		}
		
		.searchTable{
			padding: 0 10px;
		}
		
		#updateCategoryForm th{
			width: 170px;
			height: 80px;
		}
		#updateCategoryForm td input{
			height: 80px;
		}
		.list-btn{
			text-align: center;
		    padding-top: 30px;
		}
    </style>
</head>
<body>
    	
    	<%@ include file="../../manager/navbar.jsp" %>
    	
    <div>
        <div class="rs-content">        
            <div class="header">
                <div class="h-title p-3">   <!-- 패딩 1rem -->
                    [ 메뉴관리 ] 해시태그 관리
                </div>
                <div>
                    <form action="" method="">
                        <input id="h_search" type="text" placeholder="검색할 해시태그명을 입력해주세요" style="width: 300px; margin-left: 10px;"><button type="submit">조회</button>
                    </form>
                </div>
                <div class="h-content d-flex p-3">  <!-- 패딩 1rem -->
                    <div class="mr-auto">
                        	해시태그목록 <span class="text-danger">1</span> 개
                    </div>
                    <div >
                        <button onclick="showAddHashTagModal()" class="btn btn-sm btn-warning">해시태그 추가</button>
                    <button onclick="showUpdateHashTagModal()"class="btn btn-sm btn-warning">해시태그 수정</button>
                    <button onclick="deleteHashTag()" class="btn btn-sm btn-secondary">해시태그 삭제</button>
                    </div>
                </div>
            </div>
            <br>
            <div class="tableBody">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th><input type="checkbox"></th>
                            <th>번호</th>
                            <th>해시태그명</th>
                            <th>사용횟수</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<% if(list == null || list.isEmpty()) { %>
   	                <tr>
	                    <td colspan="4">등록한 해시태그가 없습니다</td>
	                </tr>
                    <% } else { %>
                    	<% for(Tag tag : list) { %>
	                    <tr>
	                        <td><input class="hcheck" type="checkbox"></td>
	                        <td><%= tag.getTagNo() %></td>
	                        <td><%= tag.getTagName() %></td>
	                        <td><%= tag.getTagCount() %></td>
	                    </tr>	
	                    <% } %>
					<% } %>    
                    
                    </tbody>
                </table>
            </div>
        	</div>  <!-- rs-content -->
    	</div>  <!-- rs-main -->
    	
    	

	<!-- 해시태그 추가  modal창 -->
 	<div class="modal" id="addHashTagForm">
		<form method="get" action="hsinsert.hs">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <!-- Modal Header -->
	                <div class="modal-header">
	                    <h4 class="modal-title">해시태그 추가</h4>
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>	<!-- x 닫기버튼 -->
	                </div> 
	                <!-- Modal body -->
	                <div class="modal-body">
							<table class="modal-table" border="1">
								<tr>
									<th>해시태그명</th>
								</tr>
								<tr>
									<td><input type="text" name="hstagName">
									<label for="hstagName"></label>
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
  	</div>	<!-- 해시태그 추가 modal -->
  
	<!-- 해시태그 수정  modal창 -->
	<div class="modal" id="updateHashTagForm">
		<form method="post" action="hsupdate.hs">
			<div class="modal-dialog modal-lg">
			    <div class="modal-content">
			        <!-- Modal Header -->
				     <div class="modal-header">
				         <h4 class="modal-title">해시태그 수정</h4>
				         <button type="button" class="close" data-dismiss="modal">&times;</button>	<!-- x 닫기버튼 -->
				     </div> 
				     <!-- Modal body -->
			         <div class="modal-body">
					<input type="hidden" id="uhashNo" name="tagNo" value="">
					<table class="modal-table" border="1">
						<tr>
							<th>기존 해시태그명</th>
							<td><input type="text" id="hashtagId" name="hashtagName" readonly></td>
						</tr>
						<tr>
							<th>현재 사용횟수</th>
							<td><input type="text" id="hashtagCount" name="hashtagCount" readonly></td>
						</tr>
						<tr>
							<th>변경 해시태그명<div style="color: rgb(78, 78, 78)"><span class="replied" id="count"></span> / 30 byte</div></th>
							<td>
								<input type="text" name="ChashtagName" placeholder="변경 해시태그명을 입력하세요">
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
	</div>	<!-- 해시태그 수정 modal -->
	
	<!-- 해시태그 삭제  modal창 -->
	<div class="modal" id="deleteHashTagForm">
		<form method="post" action="hashdelete.hs">
			<div class="modal-dialog modal-lg">
			    <div class="modal-content">
			        <!-- Modal Header -->
				     <div class="modal-header">
				         <h4 class="modal-title">해시태그 삭제</h4>
				         <button type="button" class="close" data-dismiss="modal">&times;</button>	<!-- x 닫기버튼 -->
				     </div> 
				     <!-- Modal body -->
			         <div class="modal-body">
			         <div id="dhashbody"></div>
						<table class="modal-table" border="1">
						<tr>
							<th>정말 삭제하시겠습니까?</th>
						</tr>
					</table>
		          	</div>
		          	<!-- Modal footer -->
		            <div class="modal-footer">
		         		<button type="submit" class="btn btn-sm btn-warning">삭제</button>
		                <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">취소</button>
		            </div>
		    	</div>	<!-- modal-content -->
		    </div>	<!-- modal-dialog  -->
		</form>
	</div>	<!-- 해시태그 수정 modal -->

	  	<!-- alertMsg script -->
	  <script>
			var successMsg = '<%= successMsg %>';
			var failMsg = '<%= failMsg %>';
			
			if(successMsg != 'null'){
				Swal.fire('성공', successMsg, 'success');	// alert대신 swal 라이브러리 사용
			}
			
			if(failMsg != 'null'){
				Swal.fire('실패', failMsg, 'error');
			}
			
			<% session.removeAttribute("successMsg"); %>
			<% session.removeAttribute("failMsg"); %>
	  </script>
	
	<script>
		function showAddHashTagModal(){
			$('#addHashTagForm').modal('show');
		}
		
		
		function showUpdateHashTagModal(){
			
			var checkbox = $("input[class=hcheck]:checked");
			
			if(!$('.hcheck').is(":checked")){
 				Swal.fire('실패', '해시태그를 선택해주세요!', 'error');
 				return;
 			} else if(checkbox.length > 1){
 				Swal.fire('실패', '해시태그를 하나만 선택해주세요!', 'error');
 				return;
 			}
			
			var col1 = "";
			var col2 = "";
			var col3 = "";
			
			checkbox.each(function(i){
				var tr = checkbox.parent().parent();
				var td = tr.children();
				
				col1 = td.eq(1).text();
				col2 = td.eq(2).text();
				col3 = td.eq(3).text();
			})
			
			$("#uhashNo").attr('value', col1);
			$("#hashtagId").val(col2);
			$("#hashtagCount").val(col3);
			
			$('#updateHashTagForm').modal('show');
		}
		
		
		function deleteHashTag(){
			
			var tdArr = new Array();
			var checkbox = $("input[class=hcheck]:checked");
			
			if(!$('.hcheck').is(":checked")){
 				Swal.fire('실패', '해시태그를 선택해주세요!', 'error');
 				return;
 			}
			
			checkbox.each(function(i){
				var tr = checkbox.parent().parent();
				var td = tr.children();
				
				var no = td.eq(1).text();
				
				tdArr.push(no);
			})
			
			for(var i = 0; i < tdArr.length; i++){
				$('#dhashbody').append('<input type="hidden" class="dhashNo" name="tagNo" value="'+ tdArr[i] +'">');
			}
			
			$('#deleteHashTagForm').modal('show');
		}
	</script>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <!-- <div id="h_popup1" class="h_popup_overlay">
        <div class="popup-content">
            <div align="center">해시태그 추가</div>
            <form action="">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>해시태그명</th>
                            <th>사용횟수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="text" name="">
                                
                            </td>
                            <td>231</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                	해당 해시태그를 추가하시겠습니까?
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="submit">확인</button>
                                <button onclick="closePopup()">취소</button>
                            </td>   
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div id="h_popup2" class="h_popup_overlay">
        <div class="popup-content">
            <div align="center">해시태그 수정</div>
            <form action="">
                <table class="table table-hover">
                    <tr>
                        <th width="150">해시태그명</th>
                        <th width="150">사용횟수</th>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="">
                        </td>
                        <td>4534</td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            	해당 해시태그를 수정하시겠습니까?
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="submit">확인</button>
                            <button onclick="closePopup()">취소</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    

    <div id="h_popup3" class="h_popup_overlay">
        <div class="popup-content">
            <div align="center">해시태그 삭제</div>
            <form action="">
                <table class="table table-hover">
                    <tr>
                        <th width="150">해시태그명</th>
                        <th width="150">사용횟수</th>
                    </tr>
                    <tr>
                        <td>#aasdf</td>
                        <td>1233</td>
                    </tr>
                    <tr>
                        <td colspan="2">
			                            해당 해시태그를 정말 삭제하시겠습니까? <br>
			                            삭제 후 복원이 불가합니다.
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="submit">확인</button>
                            <button onclick="closePopup()">취소</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

    <script>
        function openPopup1() {
            var popup = document.getElementById("h_popup1");
            popup.style.visibility = "visible";
            popup.style.opacity = "1";
        }

        function openPopup2() {
            var popup = document.getElementById("h_popup2");
            popup.style.visibility = "visible";
            popup.style.opacity = "1";
        }

        function openPopup3() {
            var popup = document.getElementById("h_popup3");
            popup.style.visibility = "visible";
            popup.style.opacity = "1";
        }

        function closePopup() {
            var popup1 = document.getElementById("h_popup1");
            var popup2 = document.getElementById("h_popup2");
            var popup3 = document.getElementById("h_popup3");
            popup1.style.visibility = "hidden";
            popup1.style.opacity = "0";
            popup2.style.visibility = "hidden";
            popup2.style.opacity = "0";
            popup3.style.visibility = "hidden";
            popup3.style.opacity = "0";
        }
    </script> -->
</body>
</html>