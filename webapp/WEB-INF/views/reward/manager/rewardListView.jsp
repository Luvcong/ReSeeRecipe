<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.semi.reward.model.vo.Reward, com.kh.semi.common.model.vo.PageInfo" %> 
<%
	ArrayList<Reward> list = (ArrayList<Reward>)request.getAttribute("list");
	String successMsg = (String)session.getAttribute("successMsg");
	String failMsg = (String)session.getAttribute("failMsg");
	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int rewardListCount = pi.getListCount();
	int rewardListPage = pi.getCurrentPage();
	int rewardStartPage = pi.getStartPage();
	int rewardEndPage = pi.getEndPage();
	int rewardMaxPage = pi.getMaxPage();
%>         
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[리워드관리] 리워드 관리</title>

<!-- sweetalert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" href="resources/css/reward/reward_manager.css">

</head>
<body>
	
	<%@ include file="../../manager/navbar.jsp" %>
	<script src="resources/js/reward/reward_manager.js"></script>
	
	<div class="rs-content">        
        <div class="header">
            <div class="h-title p-3">   <!-- 패딩 1rem -->
                [메뉴 관리] 리워드 관리
            </div>
            
            <div class="searchTable">
            	<table>
            		<tr>
            			<td>
            			    <select name="searchDrop">
								<option value="all">전체</option>
								<option value="memId">아이디</option>
								<option value="memNickname">닉네임</option>
							</select>
            			</td>
            			<td><input id="rewardSearch" type="text" placeholder="검색명을 입력하세요" size="30">
            				<button type="submit">조회</button>
           				</td>
            		</tr>
            	</table>
            </div>
            <div class="h-content d-flex p-3">  <!-- 패딩 1rem -->
                <div class="mr-auto">	
                    조회수 <span class="waiting"><%= pi.getListCount() %></span><span>개</span>
                </div>
                <div >
                    <button onclick="showAddRewardModal()" class="btn btn-sm btn-warning">리워드 지급 / 차감</button>
                    <button onclick="showUpdateRewardModal()"class="btn btn-sm btn-warning">리워드 상세조회</button>
                </div>
            </div>
        </div>
        <div class="tableBody">
            <table id='tb-reward' class="table table-sm table-hover">
                <thead>
                    <tr>
                        <th data-idx=0><input type="checkbox" onclick="checkAll()"></th>
                        <th data-idx=1 data-type="num">번호<div class="sort"></div></th>
                        <th data-idx=2>아이디<div class="sort"></div></th>
                        <th data-idx=3>닉네임<div class="sort"></div></th>
                        <th data-idx=4>회원등급<div class="sort"></div></th>
                        <th data-idx=5>지급일<div class="sort"></div></th>
                        <th data-idx=6>지급사유<div class="sort"></div></th>
                        <th data-idx=7>지급 포인트<div class="sort"></div></th>
                        <th data-idx=8>누적 포인트<div class="sort"></div></th>
                        <th data-idx=9 style="display: none">회원번호<div class="sort"></div></th>
                        <th>
	                    </tr>
                </thead>
                <tbody>
                	<% if(list == null || list.isEmpty()) { %>
   	                <tr>
	                    <td colspan="9">회원 리워드 내역이 없습니다</td>
	                </tr>
	                <% } else { %>
	                	<% for(Reward reward : list) { %>
	                    <tr>
	                        <td><input type="checkbox" onclick="checkOnce()"></td>
	                        <td><%= reward.getRewardNo() %></td>
	                        <td><%= reward.getMemId() %></td>
	                        <td><%= reward.getMemNickname() %></td>
	                        <td><%= reward.getMemGradeName() %></td>
	                        <td><%= reward.getRewardDate() %></td>
	                        <td><%= reward.getRewardReason() %></td>
	                        <td><%= reward.getRewardScore() %></td>
	                        <td><%= reward.getSumRewardScore() %></td>
	                        <td style="display: none"><%= reward.getMemNo() %></td>
	                    </tr>
	                    <% } %>
	                <% } %>
                </tbody>
            </table>	<!-- tb-category -->
        </div>	<!-- tableBody  -->
        
    <!-- 리워드 지급/차감 modal창 -->
	<div class="modal" id="updateRewardForm">
		<form method="post" action="<%= contextPath %>/jhupdate.rw">
		       <div class="modal-dialog">
		           <div class="modal-content">
		               <!-- Modal Header -->
		               <div class="modal-header">
		                   <h4 class="modal-title">리워드 지급/차감</h4>
		                   <button type="button" class="close" data-dismiss="modal">&times;</button>	<!-- x 닫기버튼 -->
		               </div> 
		               <!-- Modal body -->
		               <div class="modal-body">
							<input type="hidden" name="memNo">
							<table class="modal-table" border="1">
								<tr>
									<th>선택</th>
									<td>
 										<select name="selectReward" onchange="changeColor()">
 											<option value="">유형 선택</option>
											<option class="reward-plus" value="plusReward">리워드 지급</option>
											<option class="reward-minus" value="minusReward">리워드 차감</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>회원 아이디</th>
									<td></td>
								</tr>
								<tr>
									<th>회원 닉네임</th>
									<td></td>
								</tr>
								<tr>
									<th>리워드 포인트</th>
									<td><input type="number" name="rewardScore" id="reward-score" min="0" placeholder="리워드 금액(숫자)을 입력하세요"></td>
								</tr>
								<tr>
									<th>리워드 사유<div style="color: rgb(78, 78, 78)"><span class="replied" id="count">0</span> / 500 byte</div></th>
									<td><textarea id="reward-textarea" name="rewardReason" onkeyup="checkedByte(this)" placeholder="&#10;&#10;&#10;지급/사유 내용을 입력하세요&#10;(최대 500byte)"></textarea></td>
								</tr>
							</table>
		               </div>
		               <!-- Modal footer -->
		                <div class="modal-footer">
		             		<button type="submit" class="btn btn-sm btn-warning" id="nullCheck" disabled onclick="checkReward()">완료</button>
		                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">취소</button>
		                </div>
		            </div>
		        </div>
		</form>
	 </div>	<!-- 리워드 지급/차감 modal창 -->
        
   	<!-- 페이징바 -->
	<div class="paging-area">
		<% if(rewardListPage != 1) { %>
			<button onclick="page('<%= rewardListPage -1 %>');" class="btn btn-warning">&lt;</button>
		<% } %>
		<% for(int i = rewardStartPage; i <= rewardEndPage; i++) { %>
			<% if(rewardListPage != i) { %>
				<button onclick="page('<%= i %>');" class="btn btn-warning"><%= i %></button>
			<% } else { %>
				<button disabled class="btn btn-warning"><%= i %></button>
			<% } %>
		<% } %>
		<% if(rewardListPage != rewardMaxPage) { %>
			<button onclick="page('<%= rewardListPage + 1 %>');" class="btn bbtn-warning">&gt;</button>
		<% } %>
	</div>	<!-- 페이징바 -->
   	</div>  <!-- rs-content -->

	
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
	

	
	
	
</body>
</html>