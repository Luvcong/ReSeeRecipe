package com.kh.semi.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.product.model.service.ProductService;
import com.kh.semi.product.model.vo.Product;

/**
 * Servlet implementation class SearchList
 */
@WebServlet("/searchlist.po")
public class SearchListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String cate = request.getParameter("cate");
		int select = Integer.parseInt(request.getParameter("select"));
		String search = request.getParameter("search");
		
		int listCount; // 상품의 총 개수
		int currentPage; // 현재페이지(사용자 요청 페이지)
		int pageLimit; // 페이징바의 개수(10개)
		int productLimit; // 한 페이지에 보여질 상품의 최대 개수 => 12개
		
		listCount = new ProductService().selectListCount();
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		pageLimit = 10;
		productLimit = 12;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, productLimit);
		
		ArrayList<Product> list = new ProductService().selectProductList(pi, cate);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.setAttribute("title", title);
		request.setAttribute("select", select);
		request.setAttribute("cate", cate);
		
		request.getRequestDispatcher("views/product/product/buySearchPage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
