package com.kh.semi.board.recipe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.recipe.controller.RecipeControllers.UnRecipeController;

/**
 * Servlet implementation class UnRecipeServlet
 */
@WebServlet("*.un")
public class UnRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnRecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 기본 변수 세팅
		boolean flag = true;
		String viewPath = "";
		
		UnRecipeController urc = new UnRecipeController();
		
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// URI 매핑 문자열 추출
		String uri = request.getRequestURI();
		String mapping = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		
		// Controller로 분배
		switch(mapping) {
			case "" : break;
			default : System.out.println("잘못된 요청"); break;
		}
		
		// forward or sendRedirect ( flag = false로 만들면 redrect)
		if(flag) { request.getRequestDispatcher(viewPath).forward(request, response); }
		else	 { response.sendRedirect(viewPath); }
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
