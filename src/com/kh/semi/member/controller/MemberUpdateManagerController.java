package com.kh.semi.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.member.model.vo.MemberUpdate;

/**
 * Servlet implementation class MemberUpdateManagerController
 */
@WebServlet("/hlupdatemember.ma")
public class MemberUpdateManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) POST => 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값 뽑기 member UPDATE 이름 닉네임 이메일 회원등급명 수정사유
		
		int memNo = Integer.parseInt(request.getParameter("memNo")); 
		String memberName = request.getParameter("memName");
		String memNickname = request.getParameter("memNickname");
		String memEmail = request.getParameter("memEmail");
		int memGrade = Integer.parseInt(request.getParameter("memgradeNo"));
		String memGradename = request.getParameter("memGradename");
		String memUpdateWhyCon = request.getParameter("memUpdateWhyCon");
		String memModifyDate = request.getParameter("memModifydate");
		
		// Date -> String
		//SimpleDateFormat memModifyDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date memberModifyDate = java.sql.Date.valueOf(memModifyDate);
		//Date memberModifyDate = memModifyDateFormat.parse(memModifyDate);
		
		//System.out.print(""+memberModifyDate);
		// 3) Member VO 가공 - memUpdateWhyCon 필드 추가해야 할 듯
		Member m = new Member();
		m.setMemNo(memNo);
		m.setMemName(memberName);
		m.setMemNickname(memNickname);
		m.setMemEmail(memEmail);
		m.setMemGrade(memGrade);
		m.setMemGradeName(memGradename);
		//m.setModifyDate(memModifyDate);
		//m.setMemUpdateWhyCon(memUpdateWhyCon);
		
		// VO 가공  Member Update 생성(?)
		MemberUpdate mu = new MemberUpdate();
		//mu.setMemUpdateNo();
		mu.setMemNo(memNo);
		mu.setMemUpdateCon(memUpdateWhyCon);
		
		// 4) 서비스 요청
		int result = new MemberService().updateMemInfo(m, mu);
		
		if(result > 0) {
			System.out.println("result>>" + result);
			response.sendRedirect(request.getContextPath() + "/hldetailmember.ma?mno=" + memNo);
		} else {
			request.setAttribute("errorMsg", "회원 정보 수정 실패");
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
