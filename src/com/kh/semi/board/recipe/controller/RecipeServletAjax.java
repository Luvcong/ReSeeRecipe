package com.kh.semi.board.recipe.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.kh.semi.board.recipe.controller.RecipeControllers.RecipeController;

/**
 * Servlet implementation class RecipeServletAjax
 */
@WebServlet("*.ar")
public class RecipeServletAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeServletAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 기본 변수 세팅
		RecipeController rc = new RecipeController();
		System.out.println("Ajax서블릿 도착 RecipeServletAjax");
		
		// POST 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 문자열 추출
		String uri = request.getRequestURI();
		String mapping = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		System.out.println("ajax매핑 : " + mapping);
		
		// List / Object
		List list = null;
		Object obj = null;
		
		// Controller로 분배
		switch(mapping) {
			case "ajaxSelectTag" : list = rc.ajaxSelectTag(request, response); break;
			case "ajaxModifyRecipeReply" : rc.ajaxModifyRecipeReply(request, response); break;
			case "ajaxDeleteRecipeReply" : rc.ajaxDeleteRecipeReply(request, response); break;
			case "ajaxSelectRecipeReplyList" : rc.ajaxSelectRecipeReplyList(request, response); break;
			case "ajaxInsertRecipeReply" : rc.ajaxInsertRecipeReply(request, response); break;
			default : response.sendRedirect(rc.errorDefault(request, response)); break;
		}
		//2 값 여러개 : Json (JS의 배열형태 or 객체형태)
		// 1개 오브젝트
		
		 
		// 형식 + 인코딩 설정 / Gson 응답
		if(list != null) {
			response.setContentType("application/json; charset=UTF-8");
			new Gson().toJson(list, response.getWriter());
		} else if(obj != null) {
			
		} else {
			
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
