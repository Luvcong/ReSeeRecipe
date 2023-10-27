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
 * Servlet implementation class ProductBuyController
 */
@WebServlet("/probuy.po")
public class ProductBuyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductBuyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		int pno = Integer.parseInt(request.getParameter("pno")); 
		int ppno = Integer.parseInt(request.getParameter("ppno")); 
		String ono = request.getParameter("ono"); // 옵션번호(null일수있음)
		String buy = request.getParameter("buy"); // 선물/일반주문 구별
		Option o = null;
		
		if(ono != null) {
			o = new ProductService().oSelectOption(Integer.parseInt(ono));
			request.setAttribute("o", o);
		}
		
		Product p = new ProductService().selectProduct(pno);
		ProductPicture pp = new ProductService().selectProductPicture(ppno);
		
		request.setAttribute("buy", buy);
		request.setAttribute("pp", pp);
		request.setAttribute("p", p);
		
		request.getRequestDispatcher("/views/product/product/buyOrderDetail.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
