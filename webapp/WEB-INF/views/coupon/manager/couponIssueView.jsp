<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.semi.coupon.model.vo.Coupon, com.kh.semi.common.model.vo.PageInfo" %>
<%
	ArrayList<Coupon> list = (ArrayList<Coupon>)request.getAttribute("list");
	String successMsg = (String)session.getAttribute("successMsg");
	String failMsg = (String)session.getAttribute("failMsg");
	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int couponListCount = pi.getListCount();
	int couponListPage = pi.getCurrentPage();
	int couponStartPage = pi.getStartPage();
	int couponEndPage = pi.getEndPage();
	int couponMaxPage = pi.getMaxPage();
%>             
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[쿠폰관리] 쿠폰함 관리</title>
</head>
<!-- sweetalert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- categoryListView / script & css -->
<link rel="stylesheet" href="resources/css/coupon/coupon_manager.css">
<link rel="shortcut icon" href="#">

</head>
<body>

	<%@ include file="../../manager/navbar.jsp" %>
	<script src="resources/js/coupon/coupon_manager.js"></script>

	<div class="rs-content">
	    <div class="header">
		    <div class="h-title p-3">   <!-- 패딩 1rem -->
		        [쿠폰 관리] 쿠폰 발급 / 내역
		    </div>
            <div class="searchTable">
            	<table>
            		<tr>
            			<td>
            			    <select name="searchDrop">
								<option value="all">전체</option>
								<option value="memId">아이디</option>
								<option value="memNickname">쿠폰사용여부</option>
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
                    조회수 <span class="selectCount"><%= pi.getListCount() %></span><span>개</span>
                </div>
                <div >
                    <button type="submit" onclick="showAddCouponModal()" class="btn btn-sm btn-warning">쿠폰등록</button>
                    <button type="button" onclick="deleteCoupon()"class="btn btn-sm btn-secondary">쿠폰삭제</button>
                </div>
            </div>
        </div>	<!-- header -->
        <div class="tableBody">
            <table id='tb-coupon' class="table table-sm table-hover">
                <thead>
                    <tr>
                        <th data-idx=0><input type="checkbox" onclick="checkAll()"></th>
                        <th data-idx=1 data-type="num">번호<div class="sort"></div></th>
                        <th data-idx=2>쿠폰이름<div class="sort"></div></th>
                        <th data-idx=3>쿠폰할인율<div class="sort"></div></th>
                        <th data-idx=4>쿠폰등록일<div class="sort"></div></th>
                        <th data-idx=5>쿠폰종료일<div class="sort"></div></th>
                        <th data-idx=6>발급 수<div class="sort"></div></th>
                        <th data-idx=7>사용 수<div class="sort"></div></th>
                        <th data-idx=8>발급상태<div class="sort"></div></th>
                        <th data-idx=9 style="display: none">쿠폰지급사유<div class="sort"></div></th>
                    </tr>
                </thead>
                <tbody>
                <% if(list == null || list.isEmpty()) { %>
   	                <tr>
	                    <td colspan="9">쿠폰 등록 내역이 없습니다</td>
	                </tr>
	            	<% } else { %>
	            		<% for(Coupon coupon : list) { %>
	                    <tr>
	                        <td><input type="checkbox" onclick="checkOnce()"></td>
	                        <td><%= coupon.getCouponNo() %></td>
	                        <td><%= coupon.getCouponName() %></td>
	                        <td><%= coupon.getCouponRatio() %>%</td>
	                        <td><%= coupon.getCouponStartdate() %></td>
	                        <td><%= coupon.getCouponEndDate() %></td>
	                        <td><%= coupon.getIssueCouponCount() %></td>
	                        <td><%= coupon.getUsesCouponCount() %></td>
	                        <td class="<%= coupon.getCouponAvail().equals("Y") ? "replied" : "waiting"%>">
	                        	사용<%= coupon.getCouponAvail().equals("Y") ? "중": "중지"  %>
	                        </td>
	                        <td style="display: none"><%= coupon.getCouponReason() %></td>
	                    </tr>
	                    <% } %>
                    <% } %>
                </tbody>
            </table>	<!-- tb-report -->
        </div>	<!-- tableBody  -->
        
        <!-- 페이징바 -->
		<div class="paging-area">
			<% if(couponListPage != 1) { %>
				<button onclick="page('<%= couponListPage -1 %>');" class="btn btn-warning">&lt;</button>
			<% } %>
			<% for(int i = couponStartPage; i <= couponEndPage; i++) { %>
				<% if(couponListPage != i) { %>
					<button onclick="page('<%= i %>');" class="btn btn-warning"><%= i %></button>
				<% } else { %>
					<button disabled class="btn btn-warning"><%= i %></button>
				<% } %>
			<% } %>
			<% if(couponListPage != couponMaxPage) { %>
				<button onclick="page('<%= couponListPage + 1 %>');" class="btn btn-warning">&gt;</button>
			<% } %>
		</div>	<!-- 페이징바 -->
   	</div>  <!-- rs-content -->
   	
	<!-- 쿠폰 등록 modal창 -->
	<div class="modal" id="addCouponForm">
		<form method="post" action="<%= contextPath %>/jhinsert.cp">
		       <div class="modal-dialog">
		           <div class="modal-content">
		               <!-- Modal Header -->
		               <div class="modal-header">
		                   <h4 class="modal-title">쿠폰 추가</h4>
		                   <button type="button" class="close" data-dismiss="modal">&times;</button>	<!-- x 닫기버튼 -->
		               </div> 
		               <!-- Modal body -->
		               <div class="modal-body">
							<table class="modal-table" border="1" id="addCouponTable">
								<tr>
									<th>쿠폰명</th>
									<td colspan="3">
										<input type="text" class="form-control form-control-sm" name="couponName">
									</td>
								</tr>
								<tr>
									<th rowspan="2">쿠폰 유효기간</th>
									<td class="btn-check-r"><button type="button" class="btn btn-warning">시작일</button></td>
									<td class="btn-check-l"><input type="date" id="startDate" class="form-control form-control-sm form-date" name="startCoupon"></td>
								</tr>
								<tr>
									<td class="btn-check-r"><button type="button" class="btn btn-warning">종료일</button></td>
									<td class="btn-check-l"><input type="date" id="endDate" class="form-control form-control-sm form-date" name="endCoupon"></td>
								</tr>	
								<tr>
									<th>쿠폰 할인율</th>
									<td colspan="4">
										<input type="number" class="form-control form-control-sm" id="couponPercent" min="0" max="100" placeholder="할인율 숫자를 입력하세요" name="couponRatio">
									</td>
								</tr>
								<tr>
									<th>쿠폰 등록사유</th>
									<td colspan="2">
										<textarea class="form-control h-25" rows="5" name="couponReason"></textarea>
									</td>
								</tr>
							</table>
		               </div>
		               <!-- Modal footer -->
		                <div class="modal-footer">
		             		<button type="submit" class="btn btn-sm btn-warning">등록하기</button>
		                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">취소</button>
		                </div>
		            </div>
		        </div>
		</form>
	 </div> <!-- 쿠폰 등록 modal창 -->
	 
  	<!-- alertMsg script : DmListController에서 사용 -->
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