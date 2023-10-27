package com.kh.semi.category.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kh.semi.board.recipe.model.service.CategoryService;
import com.kh.semi.board.recipe.model.vo.RecipeCategory;
import com.kh.semi.common.model.vo.PageInfo;

/**
 * Servlet implementation class CategoryCheckController
 */
@WebServlet("/jhcheck.ct")
public class CategoryCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryCheckController() {
        super();
        categoryService = new CategoryService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) post
		request.setCharacterEncoding("UTF-8");
		// 2) 값
		String checkCategoryName = request.getParameter("checkCategoryName");
		// 3) 가공xx
		// 4) 요청
		ArrayList<RecipeCategory> list = categoryService.checkCategory(checkCategoryName);
		// 5) 응답화면
		response.setContentType("application/json; charset=UTF-8");
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
