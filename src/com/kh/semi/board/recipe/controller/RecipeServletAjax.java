package com.kh.semi.board.recipe.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

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
		
		// POST 인코딩 => 필터에서
		//request.setCharacterEncoding("UTF-8");
		
		// 문자열 추출
		String uri = request.getRequestURI();
		String mapping = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		System.out.println("ajax매핑 : " + mapping);
		
		// List / Object / int말고 올일이 딱히 없음
		int result = 0;
		List list = null;
		JSONObject jObj = null;
		
		// Servlet & Controller 따로 파는 것 보다는 차라리 중복 적은듯 + 여기서 list는 중요X 어짜피 화면에서 result로 받을거
		switch(mapping) {
			case "ajaxSelectTag" : list = rc.ajaxSelectTag(request, response); break;
			case "ajaxSelectRecipeReplyList" : list = rc.ajaxSelectRecipeReplyList(request, response); break;
			case "ajaxModifyRecipeReply" : result = rc.ajaxModifyRecipeReply(request, response); break;
			case "ajaxDeleteRecipeReply" : result = rc.ajaxDeleteRecipeReply(request, response); break;
			case "ajaxInsertRecipeReply" : result = rc.ajaxInsertRecipeReply(request, response);
			
			
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print(result);
			break;
			default : response.sendRedirect(rc.errorDefault(request, response)); break;
		}
		
		// 형식 + 인코딩 설정 / Gson 응답
		response.setContentType("application/json; charset=UTF-8");
		if(list != null) {
			new Gson().toJson(list, response.getWriter());
		} else if(jObj != null) {
			response.getWriter().print(jObj);
		} else { // 텍스트
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print(result);
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
