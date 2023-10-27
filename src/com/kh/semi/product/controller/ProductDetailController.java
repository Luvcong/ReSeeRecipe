package com.kh.semi.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.product.model.service.ProductService;
import com.kh.semi.product.model.vo.Option;
import com.kh.semi.product.model.vo.Product;
import com.kh.semi.product.model.vo.ProductPicture;

/**
 * Servlet implementation class ProductDetailController
 */
@WebServlet("/prodetail.po")
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		Product p = new ProductService().selectProduct(pno);
		ArrayList<ProductPicture> list = new ProductService().selectPicture(pno);
		ArrayList<Option> list2 = new ProductService().selectOption(pno);
		
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		
		request.getRequestDispatcher("/views/product/product/buyDetailView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
