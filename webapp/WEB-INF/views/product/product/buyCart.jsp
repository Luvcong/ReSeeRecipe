<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장바구니</title>
	<style>
        #cart_wrap{
            margin: auto;
            width: 1200px;
        }
        

        .cart_top>h2{
            display: inline-block;
            margin-left: 3%;
            margin-top: 20px;
            width: 300px;
        }
        .cart_top>p{
            display: inline-block;
            margin-left: 46.5%;
            width: 300px;
        }


        #cart_tb1, #cart_tb2{
            margin: auto;
            text-align: center;
            width: 85%;
            border: 1px solid #e2e2e2;
            border-collapse: collapse;
            table-layout: fixed;
        }
        #cart_tb1 th,td, #cart_tb2 th,td{
            border: 1px solid #e2e2e2;
            overflow:hidden;
            white-space : nowrap;
            text-overflow: ellipsis;
        }
        #cart_tb1 img{
            width: 200px;
            height: 200px;
        }
    </style>
</head>
<body>

	<%@ include file="buyMenubar.jsp" %>
    
    <div id="cart_wrap">
        <div class="cart_top">
            <h2>장바구니</h2>
            <p><b>장바구니 -</b> 주문결제 - 주문완료</p>
        </div>
        <br>            
        <button class="btn btn-secondary" style="margin-bottom: 10px; margin-left: 90px;">선택삭제</button>
        <table class="table" id="cart_tb1">
            <thead class="thead-light">
                <tr>
                    <th width="40"><input type="checkbox"></th>
                    <th width="225">사진</th>
                    <th>상품명</th>
                    <th>수량</th>
                    <th>금액</th>
                    <th>배송비</th>
                    <th>총액</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><input type="checkbox"></td>
                    <td><img src="../image/hello.png"></td>
                    <td>
                        귤asdfasdfasdfasdfasdfa<br>
                        한라봉
                    </td>
                    <td>1</td>
                    <td>15064</td>
                    <td>3000</td>
                    <td>348987</td>
                </tr>
            </tbody>
        </table>
        <br>
        <table class="table" id="cart_tb2">
            <thead class="thead-light">
                <tr>
                    <th>총 상품금액</th>
                    <th>총 배송비</th>
                    <th>결제예정금액</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>12319</td>
                    <td>58968</td>
                    <td>1348792837</td>
                </tr>
            </tbody>
        </table>
        <br>
        <form id="cart_form" action="">
            <button style="margin-left: 40%;" class="btn btn-secondary" type="submit">전체상품구매</button>
            <button class="btn btn-dark" type="submit">선택상품구매</button>
        </form>
    </div>
    
    <%@ include file="buyFooter.jsp" %>
</body>
</html>