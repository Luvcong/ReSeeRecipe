package com.kh.semi.product.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.product.model.service.ProductService;
import com.kh.semi.product.model.vo.Option;

/**
 * Servlet implementation class ProductDeliveryController
 */
@WebServlet("/pdelivery.po")
public class ProductDeliveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDeliveryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		int mno = Integer.parseInt(request.getParameter("mno"));
		String ono = request.getParameter("ono"); // null
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String req = request.getParameter("request"); // 빈문자열
		int price = Integer.parseInt(request.getParameter("price"));
		int iono = -1;
		
		HashMap<String, Object> order = new HashMap<String, Object>(){{
			put("name", name);
			put("address", address);
			put("phone", phone);
			put("email", email);
			put("request", req);
			put("mno", mno);
			put("pno", pno);
			put("price", price);
		}};
		
		if(ono != null) {
			iono = Integer.parseInt(ono);
			order.put("ono", iono);
		}
		
		int result = new ProductService().orderInsert(order);
		
		//request.setAttribute("orderNo", orderNo);
		
		request.getRequestDispatcher("/views/product/product/buyOrderFinish.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
