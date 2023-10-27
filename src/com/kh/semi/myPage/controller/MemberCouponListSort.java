package com.kh.semi.myPage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.semi.myPage.model.service.MyPageService;
import com.kh.semi.myPage.model.vo.MemberCoupon;

/**
 * Servlet implementation class MemberCouponListSort
 */
@WebServlet("/yrmemberCouponListSort.me")
public class MemberCouponListSort extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberCouponListSort() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 값 뽑기
		System.out.println(request.getParameter("memNo"));
		int memberNo = Integer.parseInt(request.getParameter("memNo"));
		String selected = request.getParameter("selected");
		System.out.println("컨트롤러");
		System.out.println(selected);
		
		ArrayList<MemberCoupon> list = new MyPageService().selectMemberCouponList(memberNo, selected);
		
		System.out.println(list);
		
		request.setAttribute("memberCouponList", list);
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
