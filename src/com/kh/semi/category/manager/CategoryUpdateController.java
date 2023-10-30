package com.kh.semi.category.manager;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.recipe.model.service.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryUpdateController
 */
@WebServlet("/jhupdate.ct")
public class CategoryUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryServiceImpl categoryServiceImpl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryUpdateController() {
        super();
        categoryServiceImpl = new CategoryServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 2) 전달값 뽑기
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("categoryName", request.getParameter("categoryName"));
		map.put("categoryNewName", request.getParameter("categoryNewName"));
		
//		String categoryName = request.getParameter("categoryName");
//		String categoryUpdateName = request.getParameter("categoryUpdateName");
		// int categoryListPage = Integer.parseInt(request.getParameter("categoryListPage"));
		
		// System.out.println("categoryName : " + categoryName);
		// System.out.println("categoryUpdateName : " + categoryUpdateName);
		// 3) 데이터가공 xx
		// 4) 서비스호출
		int result = categoryServiceImpl.updateCategory(map);
		// System.out.println("categoryUpdate result : " + result);
		// 5) 화면
		if(result > 0) {
			request.getSession().setAttribute("successMsg", "카테고리명 변경이 완료되었습니다!");
		} else {
			request.getSession().setAttribute("failMsg", "중복된 카테고리명입니다. 다시 시도해주세요!");
		}
		response.sendRedirect(request.getContextPath() + "/jhselect.ct?page=1");
		// request.getRequestDispatcher("views/board/recipe_category/manager/categoryListView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
