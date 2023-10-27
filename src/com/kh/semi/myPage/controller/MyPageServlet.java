package com.kh.semi.myPage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.semi.myPage.controller.MyPageController;
import com.kh.semi.myPage.model.vo.MemberCoupon;
/**
 * Servlet implementation class MyPageServlet
 */
// 회원 controller 너무 많아서 마이페이지는 controller 합쳤습니다. 쓰실분 쓰세요 - yr
@WebServlet("*.mp")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 무조건 이 서블릿으로 들어옴
		String uri = request.getRequestURI();
		// System.out.println(uri);
		String mapping = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		
		MyPageController mc = new MyPageController();
		
		// 각 controller의 응답화면 url
		String view = "";
		// forward이면 flag = true;
		// redirect이면 flag = false;
		boolean flag = true;
		
		switch(mapping) {
		// 쿠폰 조회 화면으로 이동
		// case "yrmemberCouponListForm" : view = mc.MemberCouponListForm(request, response); flag = false; break;
		case "yrmemberCouponList" : view = mc.MemberCouponList(request, response);  break;
		// case "yrmemberCouponListSort" : System.out.println("으악"); flag=false; break;
		// 회원 리워드 내역 조회 화면으로 이동
		case "yrmemberRewardList" : view = mc.MemberRewardList(request, response); break;
		
		}
		// 응답화면으로 보내기
		if(flag) {
			// controller가 fowarding할거면 
			request.getRequestDispatcher(view).forward(request, response);
		} else {
			// controller가 redirecting할거면
			response.sendRedirect(view);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
