package com.kh.semi.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.notice.model.service.NoticeService;
import com.kh.semi.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeManageController
 */
@WebServlet("/hlnoticemanage.no")
public class NoticeManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeManageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) 인코딩
		// 2) 값뽑기
		
		// -- 페이징 처리 --
		int noticelistCount; // 현재 공지사항 리스트의 총 공지사항 게시글 수
		int noticelistPage; // 현재 페이지(관리자가 요청한 페이지) => request.getParameter("cmpage
		int noticepageLimit; // 페이지 하단에 페이징바의 최대 개수 => 10개로 고정
		int noticeLimit; // 한 페이지에 보여질 회원의 최대 수 => 10개
		
		int noticeMaxPage; // 가장 마지막 페이지가 몇 번 페이지인지(총 페이지의 개수)
		int noticeStartPage; // 페이지 하단에 보여질 페이징바의 시작 수
		int noticeEndPage; // 페이지 하단에 보여질 페이징바의 끝 수
		
		int noticeStartRow; // 시작값1 11
		int noticeEndRow; // 끝값10 20
		
		
		// * noticelistCount : 총 회원 수
		noticelistCount = new NoticeService().selectNoticelistCount();
		
		// * noticelistPage : 현재페이지(관리자가 요청한 페이지)
		noticelistPage = Integer.parseInt(request.getParameter("cnpage")); // 1
		
		// * noticepageLimit : 페이징바 최대 개수
		noticepageLimit = 10;
		
		// * noticeLimit : 한 페이지에 보여질 공지사항 게시글의 최대 수
		noticeLimit = 10;
		
		// 총페이지의 개수 | 페이징바의 시작 수 | 페이징바의 끝 수 | 현재페이지기준으로 페이징숫자 시작수 | 끝 수  => 생성자 이용해서 자동 계산
		PageInfo pi = new PageInfo(noticelistCount, noticelistPage, noticepageLimit, noticeLimit);
		
		noticeMaxPage = pi.getMaxPage();
		
		noticeStartPage = pi.getStartPage();
		
		noticeEndPage = pi.getEndPage();
		
		noticeStartRow = pi.getStartRow();
		
		noticeEndRow = pi.getEndRow();
		
		// 3) VO가공
		// 9개의 변수 새로운 생성자에 담기
		PageInfo pg = new PageInfo();
		pg.setListCount(noticelistCount);
		pg.setCurrentPage(noticelistPage);
		pg.setPageLimit(noticepageLimit);
		pg.setBoardLimit(noticeLimit);
		pg.setMaxPage(noticeMaxPage);
		pg.setStartPage(noticeStartPage);
		pg.setEndPage(noticeEndPage);
		pg.setStartRow(noticeStartRow);
		pg.setEndRow(noticeEndRow);
		
		request.setCharacterEncoding("UTF-8");
		
		// 4) 서비스 호출 -> SELECT 공지사항 리스트 조회
		ArrayList<Notice> list = new NoticeService().selectNoticeAll(pg);
		//int NoticeNo = list.get(0)
		//int cnh = new HeartService().countnoticeHeart(NoticeNo);
		//request.setAttribute("list", list);
		// 5) 응답할 뷰 지정 -- Ajax 처리 해야함 
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(list);
	
		// -- request.attribute로 하기 포워딩
		request.setAttribute("list", list);
		request.setAttribute("pg", pg);
		request.getRequestDispatcher("views/notice/noticeManager.jsp").forward(request, response);
		System.out.println("공지사항리스트" + list);
		System.out.println("공지사항 페이징" + pg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
