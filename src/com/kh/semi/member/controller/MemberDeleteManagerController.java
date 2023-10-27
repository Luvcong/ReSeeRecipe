package com.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kh.semi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteManagerController
 */
@WebServlet("/hldeletemember.ma")
public class MemberDeleteManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) 인코딩
		request.setCharacterEncoding("UTF-8");
		// 2) 값 뽑기
		String[] del_list = request.getParameterValues("mno[]");
		System.out.println(del_list);
	
		JsonArray memdelsuccess = new JsonArray();
		for(String jarr : del_list) {
			int mno = Integer.parseInt(jarr);
			int result = new MemberService().deleteMember(mno);
			if(result == 1) {
				memdelsuccess.add(mno);
			}
		}
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(del_list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
