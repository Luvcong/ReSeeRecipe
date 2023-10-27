package com.kh.semi.board.recipe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.recipe.controller.RecipeControllers.RecipeController;
import com.kh.semi.common.SendError;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class RecipeServletController
 */
@WebServlet("*.re")
public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 기본변수 세팅
		boolean flag = true;
		String viewPath = "";
		
		RecipeController rc = new RecipeController();
		
		// 인코딩 세팅
		request.setCharacterEncoding("UTF-8");	
		
		// uri 매핑문자열 추출
		String uri = request.getRequestURI();
		String mapping = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		System.out.println(mapping);
		
		// Controller로 분배
		switch(mapping) {
			/* 4. 카테고리 리스트 조회 */
			case "selectRecipeCategoryList" : viewPath = rc.selectRecipeCategoryList(request, response); break;
			
			/* 1. 레시피 메인 보기 (전체조회 / 최신순==레시피PK번호순) */
			case "selectRecipeList" : viewPath = rc.selectRecipeList(request, response); break;
			
			/* 2. 레시피 상세글 보기 (디테일 조회) */
			case "recipeDetail" : viewPath = rc.recipeDetail(request, response); break;
			
			/* 3_1. 글작성하기 양식 요청 recipeEnrollForm */
			case "recipeEnrollForm" : viewPath = rc.recipeEnrollForm(request, response); break;
			
			/* 3_2. 글작성양식에 입력된 값 받아 서버에 insert(작성) => 이후 redirecting, 에러의 경우 forwarding */
			case "insertRecipe" : viewPath = rc.insertRecipe(request, response);
								  flag = viewPath.contains("errorPage") ?  true : false;
								  break;
			
			/* 1_2. 레시피보기 좋아요순 selectRecipeListHt */
			//case "selectRecipeListHt" : viewPath = rc.selectRecipeListHt(request, response); break;
			
			/* 1_3. 레시피보기 조회수순 selectRecipeListVw */
			//case "selectRecipeListVw" : viewPath = rc.selectRecipeListVw(request, response); break;
			
			/* 1_4. 레시피보기 인기셰프순 selectRecipeListPo */
			//case "selectRecipeListPo" : viewPath = rc.selectRecipeListPo(request, response); break;
			
			
			/* 4. 레시피 키워드 검색하기 searchKeyWord (제목 / 작성자) */
			//case "searchKeyWord" : viewPath = rc.searchKeyWord(request, response); break;
			
			/* 예상하지 못한 매핑값으로 요청이 들어왔을 때 에러페이지로 리디렉팅 */
			default : viewPath = rc.errorDefault(request, response); flag=false; break;
		}
		
		// forward or sendRedirect ( flag = false로 만들면 redrect)
		if(flag) { request.getRequestDispatcher(viewPath).forward(request, response); }
		else 	 { response.sendRedirect(viewPath); }
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
