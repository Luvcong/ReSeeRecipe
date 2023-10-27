package com.kh.semi.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.product.model.service.ProductService;
import com.kh.semi.product.model.vo.Product;

/**
 * Servlet implementation class BuyMain
 */
@WebServlet("/main.po")
public class BuyMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyMainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cate1 = "best";
		String cate2 = "good";
		String cate3 = "new";
		
		ArrayList<Product> list1 = new ProductService().selectMainList(cate1);
		ArrayList<Product> list2 = new ProductService().selectMainList(cate2);
		ArrayList<Product> list3 = new ProductService().selectMainList(cate3);
		
		request.setAttribute("list1", list1);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		
		request.getRequestDispatcher("/views/product/product/buyMain.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
