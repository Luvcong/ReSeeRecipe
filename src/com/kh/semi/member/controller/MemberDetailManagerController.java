package com.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

/** 회원 상세보기 서블릿
 * Servlet implementation class MemberDetailManagerController
 */
@WebServlet("/hldetailmember.ma")
public class MemberDetailManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDetailManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request.setCharacterEncoding("UTF-8");
		// 값 뽑기 - 회원번호("mno")
		int memNo = Integer.parseInt(request.getParameter("mno"));
		
		System.out.println("mno>>>" + memNo);
		
		// Service호출 회원번호로 해당 회원 정보 SELECT
		//Member m = new MemberService().selectMemInfo(memNo);
		//System.out.println("m>>>>" + m);
		// Member VO 가공
		//m.setMemNo(memNo);
		// 자바타입객체 => JSON타입 객체로 변환 JSONObject
//		JSONObject jObj = new JSONObject();
//		jObj.put("memNo", m.getMemNo());
//		jObj.put("memName", m.getMemName());
//		jObj.put("memId", m.getMemId());
//		jObj.put("memNickname", m.getMemNickname());
//		jObj.put("memEmail", m.getMemEmail());
//		jObj.put("memEnrolldate", m.getEnrollDate());
//		jObj.put("memGradename", m.getMemGradeName());
		// GSON이용 => ArrayList를 JSON타입의 데이터로 반환
		//response.setContentType("application/json; charset=UTF-8");
		//response.getWriter().print(jObj);
		//new Gson().toJson(m, response.getWriter());
		
		
		// JSP include 방식
		
		if(memNo > 0) {
			Member m = new MemberService().selectMemInfo(memNo); 
			// 응답화면 지정
			request.setAttribute("m", m);
			request.setAttribute("memNo", memNo);
		
			request.getRequestDispatcher("views/member/memberDetailManager.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "회원 상세 조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
