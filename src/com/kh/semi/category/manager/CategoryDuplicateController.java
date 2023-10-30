package com.kh.semi.category.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.recipe.model.service.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryOverlapController
 */
@WebServlet("/jhduplicate.ct")
public class CategoryDuplicateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryServiceImpl categoryServiceImpl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryDuplicateController() {
        super();
        categoryServiceImpl = new CategoryServiceImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String categoryNewName = request.getParameter("addCategoryName");
		// System.out.println(addCategoryName);	// 값 ok
		int count = categoryServiceImpl.duplicateCheckCategory(categoryNewName);
		response.setContentType("text/html; charset=UTF-8");
		if(count > 0) {
			response.getWriter().print("N");
		} else {
			response.getWriter().print("Y");
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
