package com.kh.semi.category.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.recipe.model.service.CategoryServiceImpl;
import com.kh.semi.board.recipe.model.vo.RecipeCategory;
import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.common.template.Pagination;

/**
 * Servlet implementation class CategoryListController
 */
@WebServlet("/jhselect.ct")
public class CategoryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryServiceImpl categoryServiceImpl;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryListController() {
        super();
        categoryServiceImpl = new CategoryServiceImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int categoryListCount = categoryServiceImpl.selectCategoryListCount();	// 현재 카테고리 총 수
		int categoryListPage = Integer.parseInt(request.getParameter("page"));
		// System.out.println(categoryListCount);
		// System.out.println(categoryListPage);
		int categoryPageLimit = 10;		// 페이징바 최대 개수 
		int categoryLimit = 10;			// 한 페이지에 보여질 게시글의 최대 개수 >> 10개로 고정
		
		// 3) 데이터가공 - 페이지 변수들
		PageInfo pi = Pagination.getPageInfo(categoryListCount, categoryListPage, categoryPageLimit, categoryLimit);
		
		// 4) service 호출
		ArrayList<RecipeCategory> list = categoryServiceImpl.selectCategoryList(pi);
		// 5) 응답화면 지정 (ajax사용)
		// 5-1) 인코딩과 형식 지정해주기
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("WEB-INF/views/category/manager/categoryListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
