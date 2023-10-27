package com.kh.semi.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class NoticeInsertManagerController
 */
@WebServlet("/hlenrollnoticeForm.ma")
public class NoticeEnrollFormManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeEnrollFormManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Session에서 로그인회원 얻기
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		//int noticeWriter = Integer.parseInt(request.getSession().loginMember.getMemNo());
		
		// 공지사항 관리자만 작성할 수 있으므로 혹시 모르니(일반회원작성X) 조건 걸기!!
		if(loginMember != null && loginMember.getMemGrade() == 4) {
			// 공지사항 작성 폼 포워딩
			
			request.setAttribute("loginMemberNo", loginMember.getMemNo());
			request.getRequestDispatcher("views/notice/noticeEnrollForm.jsp").forward(request, response);
		} else {
			session.setAttribute("alertMsg", "※관리자만 공지사항 작성할 수 있습니다");
			response.sendRedirect(request.getContextPath());
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
