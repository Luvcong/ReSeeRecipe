<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.semi.product.model.vo.Product" %>
<%
	ArrayList<Product> list1 = (ArrayList<Product>)request.getAttribute("list1");
	ArrayList<Product> list2 = (ArrayList<Product>)request.getAttribute("list2");
	ArrayList<Product> list3 = (ArrayList<Product>)request.getAttribute("list3");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품메인</title>
    <!-- swiper.js 라이브러리추가 -->
    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
    <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
    
    <style>
    
    	#content_1{
    		position:relative;
    		z-index: 0;
    	}
	    /* 이미지 영역 사이즈 조절 */
	    .swiper {
	        width: 1200px;
	        height: 500px;
	    }
	
	    /* 이미지 사이즈 조절 */
	    .swiper-slide>img {
	        width : 100%;
	        height : 100%;
	    }
	
	    /* 화살표 버튼색 변경 (기본색은 파란색) */
	    div[class^=swiper-button] {
	        color : white;
	        /* display : none; */ /* 아니면 안보이게 숨기기도 가능 */
	    }
       
        #buymain-wrap{
            width: 1200px;
            margin: auto;
        }

        /* 본문 */
        .content_best, .content_good, .content_new{
            height: 600px;
        }
        /* 상품제목 */
        .c_head{
            height: 120px;
        }
        .c_head>div{
            display: inline-block;
        }
        .c_title{
            font-size: 40px;
            margin-top: 30px;
            margin-left: 10px;
        }
        .more{
            margin-top: 90px;
            float: right;
        }
        /* 상품내용 */
        .c_product{
            display: flex;
        }
        .product1{
            flex:1;
            width:30%;
            height: 480px;
            box-sizing: border-box;
        }
        .product2{
            flex:1;
            margin: 0px 1%;
            width:30%;
            height: 480px;
            box-sizing: border-box;
        }
        .product3{
            flex:1;
            width:30%;
            height: 480px;
            box-sizing: border-box;
        }
        .p_img img{
            width: 100%;
            height: 350px;
            background-size: cover;
        }
        .p_name{
            font-size: 25px;
        }
        .p_price{
            font-size: 27px;
        }
    </style>
</head>
<body>

	<%@ include file="buyMenubar.jsp" %>
	
    <div id="buymain-wrap">
        <div id="content_1">
            <!-- Slider main container -->
            <div class="swiper">
                <!-- Additional required wrapper -->
                <div class="swiper-wrapper">
                    <!-- Slides -->
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2017/04/11/15/55/animals-2222007__480.jpg"></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2015/12/06/09/15/maple-1079235__480.jpg"></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/09/01/19/53/pocket-watch-1637396__480.jpg"></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/05/27/08/51/mobile-phone-1419275__480.jpg"></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2015/09/01/09/32/alphabet-916673__480.jpg"></div>
                </div>
            
                <!-- If we need pagination -->
                <div class="swiper-pagination"></div>
            
                <!-- If we need navigation buttons -->
                <div class="swiper-button-prev"></div>
                <div class="swiper-button-next"></div>
            
                <!-- If we need scrollbar -->
                <div class="swiper-scrollbar"></div>
            </div>
        </div>
	
	    <script>
	        // 슬라이더 동작 정의
	        const swiper = new Swiper('.swiper', {
	            autoplay : {
	                delay : 3000 // 3초마다 이미지 변경
	            },
	            loop : true, //반복 재생 여부
	            slidesPerView : 1, // 이전, 이후 사진 미리보기 갯수
	            pagination: { // 페이징 버튼 클릭 시 이미지 이동 가능
	                el: '.swiper-pagination',
	                clickable: true
	            },
	            navigation: { // 화살표 버튼 클릭 시 이미지 이동 가능
	                prevEl: '.swiper-button-prev',
	                nextEl: '.swiper-button-next'
	            }
	        }); 
	    </script>

        <div class="content_best">
            <div class="c_head">
                <div class="c_title">
                    <b>베스트상품</b>
                </div>
                <div class="more">
                    <a href="<%= contextPath %>/searchlist.po?title=베스트&cpage=1&cate=best&select=2">더보기</a>
                </div>
            </div>
                
            <div class="c_product">
                <% for(int i = 0; i < 3; i++) {%>
                	<div class="product<%= i + 1 %>">
                    <div class="p_img">
                        <a href="<%=contextPath%>/prodetail.po?pno=<%= list1.get(i).getProductNo() %>">
                            <img src="/view/image/hello.png">
                        </a>
                    </div>
                    <div class="p_name">
                        <a href="<%=contextPath%>/prodetail.po?pno=<%= list1.get(i).getProductNo() %>"><%= list1.get(i).getProductName() %></a>
                    </div>
                    <div class="p_price">
                        <a href="<%=contextPath%>/prodetail.po?pno=<%= list1.get(i).getProductNo() %>"><%= list1.get(i).getPrice() %></a>
                    </div>
                </div>
                <% } %>
                <!-- <div class="product1">
                    <div class="p_img">
                        <a href="#">
                            <img src="/view/image/hello.png">
                        </a>
                    </div>
                    <div class="p_name">
                        <a href="#">상품이름</a>
                    </div>
                    <div class="p_price">
                        <a href="#">가격</a>
                    </div>
                </div>
                <div class="product2">
                    <div class="p_img">
                        <a href="#">
                            <img src="/view/image/hello.png">
                        </a>
                    </div>
                    <div class="p_name">
                        <a href="#">상품이름</a>
                    </div>
                    <div class="p_price">
                        <a href="#">가격</a>
                    </div>
                </div>
                <div class="product3">
                    <div class="p_img">
                        <a href="#">
                            <img src="/view/image/hello.png">
                        </a>
                    </div>
                    <div class="p_name">
                        <a href="#">상품이름</a>
                    </div>
                    <div class="p_price">
                        <a href="#">가격</a>
                    </div>
                </div> -->
            </div>
        </div>

        <div class="content_good" style="background-color: #e2e2e2;">
            <div class="c_head">
                <div class="c_title">
                    	추천상품
                </div>
                <div class="more">
                    <a href="<%= contextPath %>/searchlist.po?title=추천&cpage=1&cate=good&select=2">더보기</a>
                </div>
            </div>

            <div class="c_product">
	            <% for(int i = 0; i < 3; i++) {%>
	                	<div class="product<%= i + 1 %>">
	                    <div class="p_img">
	                        <a href="<%=contextPath%>/prodetail.po?pno=<%= list2.get(i).getProductNo() %>">
	                            <img src="/view/image/hello.png">
	                        </a>
	                    </div>
	                    <div class="p_name">
	                        <a href="<%=contextPath%>/prodetail.po?pno=<%= list2.get(i).getProductNo() %>"><%= list2.get(i).getProductName() %></a>
	                    </div>
	                    <div class="p_price">
	                        <a href="<%=contextPath%>/prodetail.po?pno=<%= list2.get(i).getProductNo() %>"><%= list2.get(i).getPrice() %></a>
	                    </div>
	                </div>
	                <% } %>
                <!-- <div class="product1">
                    <div class="p_img">
                        <a href="#">
                            <img src="/view/image/hello.png">
                        </a>
                    </div>
                    <div class="p_name">
                        <a href="#">상품이름</a>
                    </div>
                    <div class="p_price">
                        <a href="#">가격</a>
                    </div>
                </div>
                <div class="product2">
                    <div class="p_img">
                        <a href="#">
                            <img src="/view/image/hello.png">
                        </a>
                    </div>
                    <div class="p_name">
                        	상품이름
                    </div>
                    <div class="p_price">
                        <a href="#">가격</a>
                    </div>
                </div>
                <div class="product3">
                    <div class="p_img">
                        <a href="#">
                            <img src="/view/image/hello.png">
                        </a>
                    </div>
                    <div class="p_name">
                        <a href="#">상품이름</a>
                    </div>
                    <div class="p_price">
                        <a href="#">가격</a>
                    </div>
                </div> -->
            </div>
        </div>

        <div class="content_new">
            <div class="c_head">
                <div class="c_title">
                    	신상품
                </div>
                <div class="more">
                    <a href="<%= contextPath %>/searchlist.po?title=신상&cpage=1&cate=new&select=2">더보기</a>
                </div>
            </div>

            <div class="c_product">
            	<% for(int i = 0; i < 3; i++) {%>
		                <div class="product<%= i + 1 %>">
		                    <div class="p_img">
		                        <a href="<%=contextPath%>/prodetail.po?pno=<%= list3.get(i).getProductNo() %>">
		                            <img src="/view/image/hello.png">
		                        </a>
		                    </div>
		                    <div class="p_name">
		                        <a href="<%=contextPath%>/prodetail.po?pno=<%= list3.get(i).getProductNo() %>"><%= list3.get(i).getProductName() %></a>
		                    </div>
		                    <div class="p_price">
		                        <a href="<%=contextPath%>/prodetail.po?pno=<%= list3.get(i).getProductNo() %>"><%= list3.get(i).getPrice() %></a>
		                    </div>
		                </div>
	                <% } %>
            
                <!-- <div class="product1">
                    <div class="p_img">
                        <a href="#">
                            <img src="/view/image/hello.png">
                        </a>
                    </div>
                    <div class="p_name">
                        <a href="#">상품이름</a>
                    </div>
                    <div class="p_price">
                        <a href="#">가격</a>
                    </div>
                </div>
                <div class="product2">
                    <div class="p_img">
                        <a href="#">
                            <img src="/view/image/hello.png">
                        </a>
                    </div>
                    <div class="p_name">
                        <a href="#">상품이름</a>
                    </div>
                    <div class="p_price">
                        <a href="#">가격</a>
                    </div>
                </div>
                <div class="product3">
                    <div class="p_img">
                        <a href="#">
                            <img src="/view/image/hello.png">
                        </a>
                    </div>
                    <div class="p_name">
                        <a href="#">상품이름</a>
                    </div>
                    <div class="p_price">
                        <a href="#">가격</a>
                    </div>
                </div> -->
            </div>
        </div>
    </div>
    
    <%@ include file="buyFooter.jsp" %>
</body>
</html>