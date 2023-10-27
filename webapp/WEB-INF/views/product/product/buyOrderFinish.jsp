<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>구매완료</title>
    <style>
        #orderfinish_wrap{
            margin: auto;
            width: 1200px;
        }
        hr{
            border: 1px outset lightgrey;
        }

        #orf_top>h2, h5{
            text-align: center;
        }

        #orf_tb{
            margin: auto;
            text-align: center;
            width: 85%;
            border: 1px solid #e2e2e2;
            border-collapse: collapse;
            table-layout: fixed;
        }
        #orf_tb th,td{
            border: 1px solid #e2e2e2;
            overflow:hidden;
            white-space : nowrap;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>

	<%@ include file="buyMenubar.jsp" %>
    
    <div id="orderfinish_wrap">
        <br><br>
        <hr>
        <div id="orf_top">
            <h2>구매가 완료되었습니다</h2>
            <h5>주문번호 - 123124</h5>
            <br>
            <button class="btn btn-secondary" style="margin-left: 42%;">마이페이지</button>
            <button class="btn btn-secondary">홈으로</button>
        </div>
        <hr>
        <div id="orf_bottom">
            <h4 style="margin-bottom: 10px; margin-left: 90px;">주문 요약 정보</h4>
            <table class="table" id="orf_tb">
                <tr>
                    <th class="table-active" width="300">주문일자</th>
                    <td></td>
                </tr>
                <tr>
                    <th class="table-active">주문상품</th>
                    <td></td>
                </tr>
                <tr>
                    <th class="table-active">주문자명</th>
                    <td></td>
                </tr>
                <tr>
                    <th class="table-active">총 결제금액</th>
                    <td></td>
                </tr>
            </table>
        </div>
    </div>
    <br><br>
    
    <%@ include file="buyFooter.jsp" %>
</body>
</html>