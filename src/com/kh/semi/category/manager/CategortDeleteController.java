package com.kh.semi.category.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kh.semi.board.recipe.model.service.CategoryServiceImpl;

/**
 * Servlet implementation class CategortDeleteController
 */
@WebServlet("/jhdelete.ct")
public class CategortDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryServiceImpl categoryServiceImpl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategortDeleteController() {
        super();
        categoryServiceImpl = new CategoryServiceImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 2) 전달값뽑기 - 배열
//		String[] categoryNoArr = request.getParameterValues("categoryNo");
//		String[] categoryCountArr = request.getParameterValues("categoryCount");
//		System.out.println(categoryNoArr);
//		System.out.println(categoryCountArr);
		String[] categoryNoArr = request.getParameterValues("categoryNo[]");
		String[] categoryCountArr = request.getParameterValues("categoryCount[]");
		
		// 모든 키값을 가져옴
//		Enumeration<String> keys = request.getParameterNames();
//		while(keys.hasMoreElements()) {
//			// key 조회
//			String key = keys.nextElement();
//			int keyNum = Integer.parseInt(key);
//			
//			// value 조회
//			String value = request.getParameter(key);
//			int valueNum = Integer.parseInt(value);
//			
//			int result = categoryService.deleteCategory(keyNum, valueNum);
//			if(result > 0) {
//				jArr.add(key);	// categoryNo으로 tr remove
//			}
//		}
		
		// null체크
		if(categoryNoArr.length != categoryCountArr.length) {
			return;
		}
		
		JsonArray jArr = new JsonArray();
		
		for(int i = 0; i < categoryNoArr.length; i++) {
			int key = Integer.parseInt(categoryNoArr[i]);
			int val = Integer.parseInt(categoryCountArr[i]);
			int result = categoryServiceImpl.deleteCategory(key, val);
			
			if(result > 0) {
				jArr.add(key);	// categoryNo으로 tr remove
			}
		}

		// System.out.println(categoryNo);
		// System.out.println(categoryCount);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(jArr, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
