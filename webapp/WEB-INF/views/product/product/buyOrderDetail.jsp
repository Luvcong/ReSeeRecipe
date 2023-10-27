<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.semi.product.model.vo.Product, 
				com.kh.semi.product.model.vo.ProductPicture,
				com.kh.semi.product.model.vo.Option" %>
<%
	Product p = (Product)request.getAttribute("p");
	ProductPicture pp = (ProductPicture)request.getAttribute("pp");
	Option o = (Option)request.getAttribute("o"); // null일수있음
	String buy = (String)request.getAttribute("buy");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>구매</title>
    <style>
	    #orderdetail_wrap{
	        width: 1200px;
	        margin: auto;
	    }
    </style>
</head>
<body>

	<%@ include file="buyMenubar.jsp" %>
    
    <div id="orderdetail_wrap">
        <div>
            <h2 style="display: inline-block;">주문서 작성/결제</h2>
            <p style="display: inline-block;">장바구니 - <b>주문결제 -</b> 주문완료</p>
        </div>
        <br>
        <h3>주문상세내역</h3>
        <table class="table">
            <thead class="table-active">
                <th>사진</th>
                <th>상품명</th>
                <th>수량</th>
                <th>금액</th>
                <% if(o != null) { %>
                   	<th>옵션명/가격</th>
                <% } %>
                <th>배송비</th>
                <th>총액</th>
            </thead>
            <tbody>
                <tr>
                    <td><img src="<%= contextPath %>/<%= pp.getPicturePath() %>/<%= pp.getPictureCname() %>"></td>
                    <td>
				        <%= p.getProductName() %><br>
				        <% if(p.getProductSubname() != null) { %>
			            	<%= p.getProductSubname() %>
			            <% } %>
                    </td>
                    <td>1</td>
                    <td><%= p.getPrice() %></td>
                    <% if(o != null) { %>
                    	<td><%= o.getOptionName() %>/<%= o.getOptionPrice() %></td>
                    <% } %>
                    <td><%= p.getDilivery() %></td>
                    <% if(o != null) { %>
                    	<td><%= p.getPrice() + p.getDilivery() + o.getOptionPrice() %></td>
                    <% } else { %>
                    	<td><%= p.getPrice() + p.getDilivery() %></td>
                    <% } %>
                </tr>
            </tbody>
        </table>
        <br>
        <div>
            <h3>배송정보</h3>
            <p>배송지관리</p>
        </div>
        <form method="post">
        	<input type="hidden" value="<%= loginMember.getMemNo() %>" name="mno">
        	<input type="hidden" value="<%= p.getProductNo() %>" name="pno">
        	<input type="hidden" value="<%= pp.getPictureNo() %>" name="ppno">
        	<% if(o != null) { %>
            	<input type="hidden" value="<%= o.getOptionNo() %>" name="ono">
            	<input type="hidden" value="<%= p.getPrice() + p.getDilivery() + o.getOptionPrice() %>" name="price">
            <% } else { %>
            	<input type="hidden" value="<%= p.getPrice() + p.getDilivery() %>" name="price">
            <% } %>
	        <table class="table">
	            <tr>
	                <th class="table-active" width="300" required>받으시는분</th>
	                <td><input type="text" name="name"></td>
	            </tr>
	            <tr>
	                <th class="table-active">주소</th>
	                <td><input type="text" name="address" required></td>
	            </tr>
	            <tr>
	                <th class="table-active">휴대전화(-없이)</th>
	                <td><input type="text" name="phone" required></td>
	            </tr>
	            <tr>
	                <th class="table-active">이메일</th>
	                <td><input type="text" name="email" required></td>
	            </tr>
	            <tr>
	                <th class="table-active">배송시 남길말</th>
	                <td><input type="text" name="request"></td>
	            </tr>
	        </table>
	        <br>
	        <div>
	            <h3>주문자정보</h3>
	            <input type="checkbox">배송정보와 동일
	        </div>
	        <table class="table">
	            <tr>
	                <th class="table-active" width="300">주문하시는분</th>
	                <td><input type="text" name="oname" required></td>
	            </tr>
	            <tr>
	                <th class="table-active">휴대전화</th>
	                <td><input type="text" name="ophone" required></td>
	            </tr>
	        </table>
	        <br>
	        <% if(buy.equals("pre")) { %>
		        <div>
		            <h3>선물받을사람 정보</h3>
		        </div>
		        <table class="table">
		            <tr>
		                <th class="table-active" width="300">선물받으시는분 아이디</th>
		                <td><input type="text" name="pid" required></td>
		            </tr>
		            <tr>
		                <th class="table-active">선물시 남길말</th>
		                <td><input type="text" name="prequest" required></td>
		            </tr>
		        </table>
		        <br>
	        <% } %>
	        <h3>쿠폰</h3>
	        <button type="button" class="btn btn-secondary">추가</button>
	        <table class="table">
	            <tr>
	                <th class="table-active" width="300">쿠폰명</th>
	                <td></td>
	            </tr>
	        </table>
	        <br>
	        <h3>결제정보</h3>
	        <table class="table">
	            <tr>
	                <th class="table-active" width="300">상품 합계 금액</th>
	                <% if(o != null) { %>
                    	<td><%= p.getPrice() + o.getOptionPrice() %></td>
                    <% } else { %>
                    	<td><%= p.getPrice() %></td>
                    <% } %>
	            </tr>
	            <tr>
	                <th class="table-active">배송비</th>
	                <td><%= p.getDilivery() %></td>
	            </tr>
	            <tr>
	                <th class="table-active">쿠폰사용</th>
	                <td></td>
	            </tr>
	            <tr>
	                <th class="table-active">최종결제금액</th>
	                <% if(o != null) { %>
                    	<td><%= p.getPrice() + p.getDilivery() + o.getOptionPrice() %></td>
                    <% } else { %>
                    	<td><%= p.getPrice() + p.getDilivery() %></td>
                    <% } %>
	            </tr>
	        </table>
	        <br>
	        <% if(buy.equals("pre")) { %>
        		<button class="btn btn-secondary" formaction="<%=contextPath%>/pdelivery.po">결제하기</button>
        	<% } else { %>
        		<button class="btn btn-secondary" formaction="<%=contextPath%>/pdelivery.po">결제하기</button>
        	<% } %>
        </form>
    </div>
    <br><br>
    
    <%@ include file="buyFooter.jsp" %>
</body>
</html>