package com.kh.semi.myPage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.myPage.model.service.MyPageService;
import com.kh.semi.myPage.model.vo.MemberCoupon;
import com.kh.semi.reward.model.vo.Reward;

public class MyPageController {
	
	// 회원 controller 너무 많아서 마이페이지는 controller 합쳤습니다. 쓰실분 쓰세요 - yr
	
	// 회원 쿠폰 조회로 이동
	public String MemberCouponList(HttpServletRequest request, HttpServletResponse response) {
		/*
		// 1. 값 뽑기
		int memberNo = Integer.parseInt(request.getParameter("memNo"));
		String selected = request.getParameter("selected");
		System.out.println(memberNo);
		System.out.println(selected);
		
		// 2. 데이터가공

		// 3. 서비스 호출
		ArrayList<MemberCoupon> list = new MyPageService().selectMemberCouponList(memberNo, selected);
		System.out.println("일단 컨드롤러");
		System.out.println(list);
		*/
		String view = request.getContextPath();
		// 어차피 초기화를 new로 해주었기 때문에 안해도 되지만 해줌
		// if(list != null) {
			// request.setAttribute("memberCouponList", list);
			// 4. 응답화면 지정
			view = "views/myPage/memberCouponList.jsp"; //views/myPage/memberCouponList.jsp
		// } 
		return view;
	}
	
	// 회원 리워드 내역 조회
	public String MemberRewardList(HttpServletRequest request, HttpServletResponse response) {
		
		// 여기서 부터 Controller 역할
		int memberNo = Integer.parseInt(request.getParameter("memNo"));
		ArrayList<Reward> list = new MyPageService().selectMemberRewardList(memberNo);
		
		String view = request.getContextPath();
		if(list != null) {
			request.setAttribute("memberRewardList", list);
			view = "views/myPage/memberRewardList.jsp";
		} 
		return view;
	}
	
	


}
