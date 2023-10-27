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
 * Servlet implementation class NoticeDetailManagerController
 */
@WebServlet("/hldetailnotice.ma")
public class NoticeDetailManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인코딩 생략
		
		// 2) 값뽑기
		int ManageNoticeNo = Integer.parseInt(request.getParameter("mnno"));
		System.out.println(ManageNoticeNo);
		
		// 3) Service 호출 해당 공지사항 번호로 공지사항 정보SELECT
		if(ManageNoticeNo > 0) {
			Notice n = new NoticeService().selectNoticeInfo(ManageNoticeNo);
			NoticePic np = new NoticeService().selectNoticePic(ManageNoticeNo);
			ArrayList<Tag> tag = new NoticeService().selectNoticeTag(ManageNoticeNo);
			request.setAttribute("n", n);
			request.setAttribute("np", np);
			request.setAttribute("tag",tag);
			request.setAttribute("mnno", ManageNoticeNo);
			
			request.getRequestDispatcher("views/notice/noticeDetailManager.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "공지사항 상세 조회 실패");
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
