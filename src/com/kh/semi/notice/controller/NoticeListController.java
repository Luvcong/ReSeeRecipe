package com.kh.semi.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.heart.model.service.HeartService;
import com.kh.semi.common.heart.model.vo.NoticeHeart;
import com.kh.semi.notice.model.service.NoticeService;
import com.kh.semi.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/hllist.no")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeListController() {
		super();
		// TODO Auto-generated constructor
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) 인코디생략
		// 2) 값뽑기
		// 3) vo가공
		// 4) Service호출 select해오기
		ArrayList<Notice> list = new NoticeService().selectList();
		ArrayList<NoticeHeart> noticeNolist = new ArrayList<>(list.size());
		//System.out.print("22222??"+ nh); 
		ArrayList<NoticeHeart> selectnoticeHeartList = new HeartService().selectnoticeHeartList();
		NoticeHeart nh = new NoticeHeart();
		for(NoticeHeart n : selectnoticeHeartList) {
			nh.setNoticeNo(n.getNoticeNo());
			nh.setMemNo(n.getMemNo());
			nh.setNoticeHtDate(n.getNoticeHtDate());
			//System.out.print("??"+ nh.getNoticeNo()); 
		} 
		ArrayList<NoticeHeart> noticeHeartList = new HeartService().countnoticeHeart(selectnoticeHeartList);
		//System.out.print("22222??"+ selectnoticeHeartList); 
		
		request.setAttribute("list", list);
		request.setAttribute("noticeHeartList", noticeHeartList);
		// 5) 응답할 뷰 지정
		RequestDispatcher view = request.getRequestDispatcher("/views/notice/noticeListView.jsp");
		view.forward(request, response);
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
