<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>해시태그 관리</title>
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

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
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>1</td>
                            <td>#ㅁㄴㅇㄹ</td>
                            <td>4</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>2</td>
                            <td>2023-09-23</td>
                            <td>5</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>3</td>
                            <td>2023-09-22</td>
                            <td>6</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>  <!-- rs-content -->
    </div>  <!-- rs-main -->

	<!-- 해시태그 추가  modal창 -->
 	<div class="modal" id="addHashTagForm">
		<form method="get" action="">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <!-- Modal Header -->
	                <div class="modal-header">
	                    <h4 class="modal-title">해시태그 추가</h4>
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>	<!-- x 닫기버튼 -->
	                </div> 
	                <!-- Modal body -->
	                <div class="modal-body">
							<input type="hidden" name="">
							<table class="modal-table" border="1">
								<tr>
									<th>해시태그명</th>
								</tr>
								<tr>
									<td><input type="text" name="r">
									<label for=""></label>
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
		<form method="post" action="">
			<div class="modal-dialog modal-lg">
			    <div class="modal-content">
			        <!-- Modal Header -->
				     <div class="modal-header">
				         <h4 class="modal-title">카테고리 수정</h4>
				         <button type="button" class="close" data-dismiss="modal">&times;</button>	<!-- x 닫기버튼 -->
				     </div> 
				     <!-- Modal body -->
			         <div class="modal-body">
					<input type="hidden" name="">
					<table class="modal-table" border="1">
						<tr>
							<th>기존 해시태그명</th>
							<td><input type="text" name="" readonly></td>
						</tr>
						<tr>
							<th>현재 사용횟수</th>
							<td><input type="text" name="" readonly></td>
						</tr>
						<tr>
							<th>변경 해시태그명<div style="color: rgb(78, 78, 78)"><span class="replied" id="count">0</span> / 20 byte</div></th>
							<td>
								<input type="text" name="" placeholder="변경 해시태그명을 입력하세요" onkeyup="checkedByte(this)">
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
	
	<script>
		function showAddHashTagModal(){
			
			$('#addHashTagForm').modal('show');
		}
		
		
		function showUpdateHashTagModal(){
			
			$('#updateHashTagForm').modal('show');
		}
		
		
		function deleteHashTag(){
			
			
		}
	</script>
</body>
</html>