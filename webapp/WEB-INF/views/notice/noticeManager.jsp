<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.semi.notice.model.vo.*, com.kh.semi.common.model.vo.PageInfo" %>
<%
	
	//NoticePic np = (NoticePic)request.getParameter("np");
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
	PageInfo pg = (PageInfo)request.getAttribute("pg");
	
	// 페이징바 만들 때 필요한 변수 미리 세팅
	int noticelistCount = pg.getListCount();
	int noticelistPage = pg.getCurrentPage();
	int noticeStartPage = pg.getStartPage();
	int noticeEndPage = pg.getEndPage();
	int noticeMaxPage = pg.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[관리자]공지사항 관리</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


	<link rel="stylesheet" href="resources/css/notice/noticeManager.css">



</head>
<body>

	<%@ include file="../manager/navbar.jsp" %>
<div class="rs-content">
    <h2>공지사항 관리</h2>
    <div class="rs-content">
        <div class="header1">
            <div class="input-group mt-3 mb-3">
                <div class="input-group-prepend">
                <select id="noticeSearch"  value="공지사항조회"class="btn btn-warning">
                  	<option>공지사항 제목</option> 
                  	<option>작성자</option> 
                </select>
                </div> 
                <input type="text" class="form-control" placeholder="검색할 내용을 입력하세요" id="searchNotice" name="searchNotice" required>
                <div class="input-group-append">
                    <button class="btn btn-warning" type="submit" name="HL_noticeSearch" id="HL_noticeSearch">검색</button>
                </div>
            </div>
        </div>

        <div class="header2">
        	<a href="<%=contextPath %>/hlenrollnoticeForm.ma" class="w3-button w3-round w3-yellow">공지사항 작성</a>
            <button id="HL_deleteNotice" class="w3-button w3-round w3-yellow" onclick="deleteNotice();">공지사항 삭제</button>
        </div>
        <table class="table" id="noticeAll">
            <caption class="totalNotice">총 게시글 <%=pg.getListCount() %>개</caption> 
          <thead class="thead-light">
            <tr>
              <th>
                <div class="form-check">
                    <label class="form-check-label">
                    <input type="checkbox" name="noticeAllCheck">
                    </label>
                </div>
              </th>
              <th>공지사항번호</th>
              <th>공지사항 제목</th>
              <th>작성자</th>
              <th>작성일자</th>
              <th>조회수</th>
              <th>좋아요</th>
            </tr>
          </thead>
          <tbody id="noticetotalList">
           <!-- 공지사항이 있을 수도 있고 없을 수도 있음 -->
			<% if(list.isEmpty()) { %>
                <!-- 공지사항이 없을 때 -->
                <tr>
                    <td colspan="5">공지사항이 존재하지 않습니다.</td>
                </tr>
			<%} else { %>
                <!-- 공지사항이 있을 때 -->
              	<% for(Notice n : list) { %>
                    <tr>
                    	<td>
                		<div class="form-check">
                    		<label class="form-check-label">
                   			<input type="checkbox" name="noticeCheckbox" id="noticeCheckbox" value="<%= n.getNoticeNo() %>">
                   			</label>
                		</div>
             			</td>
                		<td><%= n.getNoticeNo() %></td>
                		<td><%= n.getNoticeTitle() %></td>
                		<td><%= n.getNoticeWriterName() %></td>
                		<td><%= n.getNoticeDate() %></td>
                		<td><%= n.getNoticeCount() %></td>
                		<td><%= n.getNoticeHeart() %></td>
                	</tr>
                <% } %>
			<% } %>
          </tbody>
        </table>

        <script>
            $(function(){
                $('#noticetotalList').on('dblclick', 'tr', function(){
                    const mnno = $(this).children().eq(1).text();
                    console.log(this);
                    location.href = '<%=contextPath%>/hldetailnotice.ma?mnno=' + mnno;
                });
            });
        </script>
    </div>
    <br><br><br><br>
    <div class="w3-bar">
		<% if(noticelistPage != 1) { %>
			<button onclick="page('<%=noticelistPage - 1 %>');" class="w3-button w3-yellow">&lt;</button>
		<% } %>
		
		<% for(int i = noticeStartPage; i <= noticeEndPage; i++) { %>
			<% if(noticelistPage != i) { %>
				<button onclick="page('<%=i %>');" class="w3-button w3-yellow"><%= i %></button>
    		<% } else { %>
    			<button disabled class="w3-button w3-yellow"><%= i %></button>
    		<% } %>
    	<% } %>
    	
    	<% if(noticelistPage != noticeMaxPage) { %>
    		<button onclick="page('<%=noticelistPage + 1 %>');" class="w3-button w3-yellow">&gt;</button>
    	<% } %>
    </div>	
</div>	

	<script>
		// 공지사항 리스트 페이징처리 함수
		function page(e){
			this.location.href = "<%=contextPath %>/hlnoticemanage.no?cnpage=" + e;
		}
	</script>


</body>
</html>