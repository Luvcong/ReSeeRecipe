package com.kh.semi.category.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.recipe.model.service.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryInsertController
 */
@WebServlet("/jhinsert.ct")
public class CategoryInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryServiceImpl categoryServiceImpl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryInsertController() {
        super();
        categoryServiceImpl = new CategoryServiceImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 카테고리명 20byte == 8글자
		// 2) 전달값뽑기
		String recipeCategoryName = request.getParameter("recipeCategoryName");
		// 3) 데이터가공 - xx
		// 4) service호출
		int result = categoryServiceImpl.insertCategory(recipeCategoryName);
		// 5) 응답화면 지정
		if(result > 0) {
			request.getSession().setAttribute("successMsg", "카테고리 추가가 완료되었습니다!");
		} else {
			request.getSession().setAttribute("failMsg", "Error 카테고리 등록에 실패했습니다!");
		}
		response.sendRedirect(request.getContextPath() + "/jhselect.ct?page=1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
