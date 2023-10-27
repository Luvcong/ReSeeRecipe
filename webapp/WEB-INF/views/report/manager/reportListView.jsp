<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.semi.report.model.vo.Report, com.kh.semi.common.model.vo.PageInfo" %>    
<%
	ArrayList<Report> list = (ArrayList<Report>)request.getAttribute("list");
	String successMsg = (String)session.getAttribute("successMsg");
	String failMsg = (String)session.getAttribute("failMsg");
	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int reportListCount = pi.getListCount();
	int reportListPage = pi.getCurrentPage();
	int reportStartPage = pi.getStartPage();
	int reportEndPage = pi.getEndPage();
	int reportMaxPage = pi.getMaxPage();
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[신고함관리] 신고함 관리</title>


<!-- sweetalert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<!-- categoryListView / script & css -->

<link rel="stylesheet" href="resources/css/report/report_manager.css">

</head>
<body>

	<%@ include file="../../manager/navbar.jsp" %>
	<script src="resources/js/report/report_manager.js"></script>
	
	<div class="rs-content">
	    <div class="header">
		    <div class="h-title p-3">   <!-- 패딩 1rem -->
		        [메뉴 관리] 신고함 관리
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
                    조회수 <span class="waiting">10</span><span>개</span>
                </div>
                <div >
                    <button type="button" onclick="showDetailReportModal()" class="btn btn-sm btn-warning">상세보기</button>
                    <button type="submit" onclick="updateReport()"class="btn btn-sm btn-warning">차단하기</button>
                </div>
            </div>
        </div>	<!-- header -->
        <div class="tableBody">
            <table id='tb-report' class="table table-sm table-hover">
                <thead>
                    <tr>
                        <th data-idx=0><input type="checkbox" onclick="checkAll()"></th>
                        <th data-idx=1 data-type="num">번호<div class="sort"></div></th>
                        <th data-idx=2>신고일자<div class="sort"></div></th>
                        <th data-idx=3>구분<div class="sort"></div></th>	<!-- 댓글 or 게시글 -->
                        <th data-idx=4>신고사유<div class="sort"></div></th>
                        <th data-idx=5>신고대상자<div class="sort"></div></th>
                        <th data-idx=6>신고자<div class="sort"></div></th>
                        <th data-idx=7>차단여부<div class="sort"></div></th>
                        <th data-idx=8 style="display: none">신고글 제목<div class="sort"></div></th>
                        <th data-idx=9 style="display: none">신고댓글 내용<div class="sort"></div></th>
                    </tr>
                </thead>
                <tbody>
                	<% if(list == null || list.isEmpty()) { %>
   	                <tr>
	                    <td colspan="9">신고함 내역이 없습니다</td>
	                </tr>
	                <% } else { %>
	                	<% for(Report report : list) { %>
	                    <tr>
	                        <td><input type="checkbox" onclick="checkOnce()"></td>
	                        <td><%= report.getReportNo() %></td>
	                        <td><%= report.getRptDate() %></td>
	                        <td><%= report.getRptCategoryName() %></td>
	                        <td><%= report.getRptContent() %></td>
	                        <td><%= report.getReciveReport() %></td>
	                        <td><%= report.getSendReport()	 %></td>
	                        <td class="<%= report.getRptStatus().equals("Y") ? "replied" : "waiting" %>">
	                        	차단<%= report.getRptStatus().equals("Y") ? "완료": "대기"  %>
	                        </td>
	                        <td style="display: none"><%= report.getRecipeTitle() %></td>
	                        <td style="display: none"><%= report.getReplyContent() %></td>
	                    </tr>
	                    <% } %>
	                <% } %>
                </tbody>
            </table>	<!-- tb-report -->
        </div>	<!-- tableBody  -->
	
	<!-- 페이징바 -->
	<div class="paging-area">
		<% if(reportListPage != 1) { %>
			<button onclick="page('<%= reportListPage -1 %>');" class="btn btn-warning">&lt;</button>
		<% } %>
		<% for(int i = reportStartPage; i <= reportEndPage; i++) { %>
			<% if(reportListPage != i) { %>
				<button onclick="page('<%= i %>');" class="btn btn-warning"><%= i %></button>
			<% } else { %>
				<button disabled class="btn btn-warning"><%= i %></button>
			<% } %>
		<% } %>
		<% if(reportListPage != reportMaxPage) { %>
			<button onclick="page('<%= reportListPage + 1 %>');" class="btn bbtn-warning">&gt;</button>
		<% } %>
	</div>	<!-- 페이징바 -->
	</div>	<!-- rs-content -->
	
	<!-- 신고함 상세보기 modal창 -->
	<div class="modal" id="detailReportForm">
		<form method="post" action="<%= contextPath %>/jhdetail.rp">
		       <div class="modal-dialog modal-la">
		           <div class="modal-content">
		               <!-- Modal Header -->
		               <div class="modal-header">
		                   <h4 class="modal-title">신고글 상세보기</h4>
		                   <button type="button" class="close" data-dismiss="modal">&times;</button>	<!-- x 닫기버튼 -->
		               </div> 
		               <!-- Modal body -->
		               <div class="modal-body">
							<input type="hidden" name="reportNo">
							<input type="hidden" name="categortName">
							<table class="modal-table" border="1">
								<tr>
									<th>유형</th>
									<td></td>
								</tr>
								<tr>
									<th>신고자</th>
									<td></td>
								</tr>
								<tr>
									<th>신고대상자</th>
									<td></td>
								</tr>
								<tr>
									<th>신고글 번호</th>
									<td></td>
								</tr>
								<tr style="display:none">
									<th>신고글 제목</th>
									<td></td>
								</tr>
								<tr>
									<th>신고글 댓글</th>
									<td></td>
								</tr>
								<tr>
									<th>신고 사유</th>
									<td></td>
								</tr>
							</table>
		               </div>
		               <!-- Modal footer -->
		                <div class="modal-footer">
		             		<button type="submit" class="btn btn-sm btn-warning">차단하기</button>
		                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">취소</button>
		                </div>
		            </div>
		        </div>
		</form>
	 </div> <!-- 신고함 상세보기 modal창 -->
	 

</body>
</html>