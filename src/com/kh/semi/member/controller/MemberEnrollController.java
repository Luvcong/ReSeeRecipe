package com.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollController
 */
@WebServlet("/yrenroll.me")
public class MemberEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. POST방식 인코딩설정
		request.setCharacterEncoding("UTF-8");
		
		// 2. request객체로부터 요청 시 전달값 뽑기(이름, 닉네임, 아이디, 비밀번호, 이메일)
		String memberName = request.getParameter("memberName");
		String memberNickname = request.getParameter("memberNickname");
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		String memberEmail = request.getParameter("memberEmail");
		
		Member m = new Member();
		m.setMemName(memberName);
		m.setMemNickname(memberNickname);
		m.setMemId(memberId);
		m.setMemPwd(memberPwd);
		m.setMemEmail(memberEmail);
		
		int result = new MemberService().insertMember(m);
		
		if(result > 0) {
			request.getRequestDispatcher("views/member/memberEnrollSuccess.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "회원가입에 실패하셨습니다.");
			request.getRequestDispatcher(request.getContextPath() + "/yrenrollForm.me").forward(request, response);
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
