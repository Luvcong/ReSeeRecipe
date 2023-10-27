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
 * Servlet implementation class AjaxSearchListSortController
 */
@WebServlet("/psort.po")
public class SearchListSortController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchListSortController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cate = request.getParameter("cate");
		String sort = request.getParameter("sort");
		String title = request.getParameter("title");
		int select = Integer.parseInt(request.getParameter("select"));
		int category = 0;
		
		switch(cate) {
		case "meat": category = 1; break;
		case "fish": category = 2; break;
		case "vegi": category = 3; break;
		case "sim": category = 4; break;
		}
		
		int listCount;
		int currentPage;
		int pageLimit;
		int productLimit;
		
		listCount = new ProductService().selectListCount();
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		pageLimit = 10;
		productLimit = 12;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, productLimit);
		
		ArrayList<Product> list = new ProductService().sortSelectProductList(pi, category, sort);
		
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
