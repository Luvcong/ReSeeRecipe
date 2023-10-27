package com.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/yrmemberDelete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// request.setCharacterEncoding("UTF-8");
		
		// 삭제할 회원 번호
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		int result = new MemberService().memberDelete(memberNo);
		
		/*
		if(result > 0) {
			request.setAttribute("successMsg", "탈퇴되었습니다.");
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("errorMsg", "탈퇴에 실패하셨습니다. 오류가 반복되는 경우 관리자에게 문의하세요.");
			response.sendRedirect(request.getContextPath() + "/blog.me");
		}
		*/
		// ajax처리
		response.setContentType("text/html; charset=UTF-8");
		
		if(result > 0) response.getWriter().print("NNNNY");
		else response.getWriter().print("NNNNN");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
