<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.semi.reward.model.vo.Reward" %>
<%
	ArrayList<Reward> memberRewardList = (ArrayList<Reward>)request.getAttribute("memberRewardList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리워드 내역</title>
<!-- sweetalert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<link rel="stylesheet" href="resources/css/myPage/memberRewardList.css">
<!-- <script src="resources/js/myPage/memberRewardList.js"></script> -->

</head>

<body>
    <!-- header부분 (상단 메인 메뉴바) -->
	<%@ include file="/views/common/header.jspf" %>
    <h1 id="title"><b>리워드 내역</b></h1>
    
    <div class="container">
        <div class="info-div">
            <div class="info" id="infoText">
             	※ 회원 등급은 로그인, 마이페이지 접속 시 갱신됩니다.
            </div>
            <div class="info" id="infoTotal">
                <% if(!memberRewardList.isEmpty()) {  %>
            	    내 리워드 : <%= memberRewardList.get(0).getRemainRewardScore() %> p
                <% } %>
            </div>
        </div>

        <div class="tableBody">
            <table id='tb-reward' class="table table-sm table-hover">
                
                <thead>
                    <% if(!memberRewardList.isEmpty()) { %>
                    <tr>
                        <th data-idx=0 data-type="num">번호<div class="sort"></div></th>
                        <th data-idx=1>일시<div class="sort"></div></th>
                        <th data-idx=2>내용<div class="sort"></div></th>
                        <th data-idx=3>리워드<div class="sort"></div></th>
                        <th data-idx=4>남은 리워드<div class="sort"></div></th>
                    </tr>
                </thead>
                <tbody>
                    
                    	<% for(int i = 0; i < memberRewardList.size(); i++) {%>
                    <tr>
                        <td><%= memberRewardList.get(i).getRownum() %></td>
                        <td><%= memberRewardList.get(i).getRewardDate() %></td>
                        <td><%= memberRewardList.get(i).getRewardReason() %></td>
                        <td><%= memberRewardList.get(i).getRewardScore() %></td>
                        <td><%= memberRewardList.get(i).getRemainRewardScore() %></td>
                    </tr>
                    	<% } %>
                   	<% } else { %>
                        조회된 리워드가 없습니다.
                    <% } %>
                </tbody>
            </table>	<!-- tb-category -->
        </div>	<!-- tableBody  -->
    </div>
    <!-- footer 푸터영역 -->
	<%@ include file="/views/common/footer.jspf" %>

</body>
</html>