package com.kh.semi.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.notice.model.service.NoticeService;
import com.kh.semi.notice.model.vo.Notice;
import com.kh.semi.notice.model.vo.NoticePic;
import com.kh.semi.tag.model.vo.Tag;

/**
 * Servlet implementation class NoticeUpdateFormManagerController
 */
@WebServlet("/hlupdatenoticeForm.ma")
public class NoticeUpdateFormManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeUpdateFormManagerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int ManageNoticeNo = Integer.parseInt(request.getParameter("mnno"));

		// 3) Service 호출 해당 공지사항 번호로 공지사항 정보SELECT
		if (ManageNoticeNo > 0) {
			Notice n = new NoticeService().selectNoticeInfo(ManageNoticeNo);
			NoticePic np = new NoticeService().selectNoticePic(ManageNoticeNo);
			ArrayList<Tag> tag = new NoticeService().selectNoticeTag(ManageNoticeNo);
			request.setAttribute("n", n);
			request.setAttribute("np", np);
			request.setAttribute("tag", tag);
			request.setAttribute("mnno", ManageNoticeNo);
			
			request.getRequestDispatcher("views/notice/noticeUpdateFormManager.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "공지사항 수정 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
