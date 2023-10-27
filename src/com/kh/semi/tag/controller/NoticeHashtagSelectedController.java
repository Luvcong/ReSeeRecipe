package com.kh.semi.tag.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.semi.notice.model.service.NoticeService;
import com.kh.semi.tag.model.vo.Tag;

/**
 * Servlet implementation class NoticeHashtagSelectedController
 */
@WebServlet("/hlnoticeselected.tg")
public class NoticeHashtagSelectedController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeHashtagSelectedController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ManagerNoticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		
		ArrayList<Tag> list = new NoticeService().selectNoticeTag(ManagerNoticeNo);
		
		// 형식 인코딩 지정
		response.setContentType("application/json; charset=UTF-8"); 
		
		// Gson객체 생성 응답 보내기
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
