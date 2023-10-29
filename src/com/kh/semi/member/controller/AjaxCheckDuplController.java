package com.kh.semi.member.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.member.model.service.MemberService;

/**
 * Servlet implementation class AjaxCheckDuplController
 */
@WebServlet("/yrcheckDupl.me")
public class AjaxCheckDuplController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxCheckDuplController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String checkVal = request.getParameter("checkVal");
		String columnName = request.getParameter("columnName");
		
		HashMap<String, String> map = new HashMap();
		map.put("checkVal", checkVal);
		map.put("columnName", columnName);
		
		int count = new MemberService().checkDupl(map);
		
		response.setContentType("text/html; charset=UTF-8");
		
		if(count > 0) {
			response.getWriter().print("NNNNN");
		} else {
			response.getWriter().print("NNNNY");
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
