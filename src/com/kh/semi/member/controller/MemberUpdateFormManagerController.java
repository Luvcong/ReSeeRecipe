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
 * Servlet implementation class MemberUpdateFormManagerController
 */
@WebServlet("/hlupdatemeberForm.ma")
public class MemberUpdateFormManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateFormManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 값 뽑기 - 회원번호("mno")
		int memNo = Integer.parseInt(request.getParameter("mno"));
	
		Member m = new MemberService().selectMemInfo(memNo); 
		System.out.println("m>>>>" + m);
		// 응답화면 지정
		request.setAttribute("m", m);
		request.setAttribute("memNo", memNo);
	
		request.getRequestDispatcher("views/member/memberUpdateFormManager.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
