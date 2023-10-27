<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.semi.notice.model.vo.*, com.kh.semi.tag.model.vo.*, java.util.ArrayList"%>
<%
    Notice n = (Notice)request.getAttribute("n");
	NoticePic np = (NoticePic)request.getAttribute("np");
	ArrayList<Tag> tag = (ArrayList<Tag>)request.getAttribute("tag");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="resources/css/member/memberDetailManager.css">  
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <%@ include file="../manager/navbar.jsp" %>
    <div class="rs-content">
        
        <h2>공지사항 상세보기</h2>
        <br><br>
        <div class="container">
            <div class="from-control">
                <table class="table">
                    <tr>
                        <th>공지사항 번호</th>
                        <td><%=n.getNoticeNo() %></td>
                    </tr>
                    <tr>
                        <th>공지사항 제목</th>
                        <td><%=n.getNoticeTitle() %></td>
                    </tr>    
                    <tr>
                        <th>작성자</th>
                        <td><%=n.getNoticeWriterName() %></td>
                    </tr>        
                    <tr>
                        <th>공지사항 내용</th>
                        <td><%=n.getNoticeCon() %></td>
                    </tr>      
                    <tr>
                        <th>이미지</th>
                        <td>
                    <% if(np != null) { %>
                    <!--  첨부 파일이 있을 경우 -->
                        <img src="<%= contextPath %>/<%= np.getNoticePicPath() %>/<%= np.getNoticePicNagmeChange() %>" alt="공지사항 이미지" id="HL_noticeImg" width="150" height="150">
                    	<a href="<%= contextPath %>/<%= np.getNoticePicPath() %>/<%= np.getNoticePicNamgeOrigin() %>" download="<%= np.getNoticePicNamgeOrigin() %>"><%= np.getNoticePicNamgeOrigin() %></a>
                    <% } else { %>
                    	첨부 파일 없어요 ~
                    <% } %>
                    	</td>
                    </tr> 
                    <tr>
                        <th>해시태그</th>
                        <td colspan="3">
                    <% if(tag.isEmpty()) { %>
                    <!-- 해시태그가 없을 때 -->         
                        	해시태그가 존재하지 않습니다.
                    <% } else { %>
                    	<!-- 해시태그가 있을 때 -->
                    	<% for(Tag t : tag) { %>
                    	<span>#<%= t.getTagName() %></span>
                    	<% } %>
                    <% } %>
                    	</td>
                    </tr>        
                    <tr>
                        <th>공지사항 작성일</th>
                        <td><%=n.getNoticeDate() %></td>
                    </tr>        
                </table>
                <br><br>
                <div id="noticedetailbtn">
                    <a href="<%=contextPath %>/hlupdatenoticeForm.ma?mnno=<%=n.getNoticeNo() %>" class="w3-button w3-round w3-yellow" id="noticeupdatebtn">수정하기</a>
                 <!-- <button type="submit" id="memberupdatebtn" class="w3-button w3-round w3-yellow">수정하기</button>-->
                    <button type="button" class="w3-button w3-round w3-yellow" onclick="history.back();">목록으로</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>