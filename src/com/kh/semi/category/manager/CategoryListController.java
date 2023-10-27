package com.kh.semi.category.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.recipe.model.service.CategoryService;
import com.kh.semi.board.recipe.model.vo.RecipeCategory;
import com.kh.semi.common.model.vo.PageInfo;

/**
 * Servlet implementation class CategoryListController
 */
@WebServlet("/jhselect.ct")
public class CategoryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryService categoryService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryListController() {
        super();
        categoryService = new CategoryService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) get - 인코딩x
		// 2) 전달값 뽑기 - select문으로 없음 // 페이징처리를 위해 값 뽑기 진행
		int categoryListCount;		// 현재 카테고리 총 수
		int categoryListPage;		// 현재 페이지
		int categoryPageLimit;		// 페이징바 최대 개수 
		int categoryLimit;			// 한 페이지에 보여질 게시글의 최대 개수 >> 10개로 고정
		
		int categoryMaxPage;		// 총 페이지 개수 == 마지막 페이지
		int categoryStartPage;		// 페이징바 시작 수
		int categoryEndPage;		// 페이징바 끝 수
		
		categoryListCount = categoryService.selectCategoryListCount();
		categoryListPage = Integer.parseInt(request.getParameter("page"));
		// System.out.println(categoryListCount);	//	저장 카테고리 수 데이터값 ok
		// System.out.println(categoryListPage);	// 	현재 페이지 ok (1)
		
		categoryPageLimit = 10;
		categoryLimit = 10;
		categoryMaxPage = (int)Math.ceil((double)categoryListCount / categoryLimit);
		// System.out.println(categoryMaxPage);	// 현재 저장된 카테고리 수 == 63개 == maxPage 7 ok
		categoryStartPage = (categoryListPage - 1) / categoryPageLimit * categoryLimit + 1;
		categoryEndPage = categoryStartPage + categoryPageLimit -1;
		
		if(categoryEndPage > categoryMaxPage) {
			categoryEndPage = categoryMaxPage;
		}
		
		// 3) 데이터가공 - 페이지 변수들
		PageInfo pi = new PageInfo();
		pi.setListCount(categoryListCount);
		pi.setCurrentPage(categoryListPage);
		pi.setPageLimit(categoryPageLimit);
		pi.setBoardLimit(categoryLimit);
		pi.setMaxPage(categoryMaxPage);
		pi.setStartPage(categoryStartPage);
		pi.setEndPage(categoryEndPage);
		
		// 4) service 호출
		ArrayList<RecipeCategory> list = categoryService.selectCategoryList(pi);
		// 5) 응답화면 지정 (ajax사용)
		// 5-1) 인코딩과 형식 지정해주기
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("views/category/manager/categoryListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
