package com.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginController
 */
@WebServlet("/yrlogin.me")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 사용자가 입력한 id와 pwd 값 뽑기
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		// 추가
		String buy = request.getParameter("buy");
		
		Member m = new Member();
		m.setMemId(memberId);
		m.setMemPwd(memberPwd);
		
		// Service 호출
		Member loginMember = new MemberService().loginMember(m);

		// 로그인 실패 시
		if(loginMember == null) {
			request.setAttribute("errorMsg", "로그인에 실패하셨습니다.");
			request.getRequestDispatcher("WEB-INF/views/member/memberLogin.jsp").forward(request, response);
		} else { // 로그인 성공 시
			//int mReward = new MemberService().memReward(loginMember.getMemNo());
			//if(mReward > -1) {
				HttpSession session = request.getSession();
				session.setAttribute("loginMember", loginMember);
				//session.setAttribute("mReward", mReward);
				if(buy.equals("buy")) {
					response.sendRedirect(request.getContextPath() + "/main.po");
				} else {
					response.sendRedirect(request.getContextPath());
				}
			//} else {
				//request.setAttribute("errorMsg", "리워드조회 실패");
				//request.getRequestDispatcher("WEB-INF/views/member/memberLogin.jsp").forward(request, response);
			//}
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
