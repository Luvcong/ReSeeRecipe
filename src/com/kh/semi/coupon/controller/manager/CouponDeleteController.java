package com.kh.semi.coupon.controller.manager;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kh.semi.coupon.model.service.manager.CouponService;
import com.kh.semi.coupon.model.vo.Coupon;

/**
 * Servlet implementation class CouponDeleteController
 */
@WebServlet("/jhdelete.cp")
public class CouponDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CouponService couponService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CouponDeleteController() {
        super();
        couponService = new CouponService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1)
		// 2)
		String couponList[] = request.getParameterValues("coupon_list[]");
		System.out.println("Delete couponNo : " + Arrays.toString(couponList));
		// 3)
		
		JsonArray successList = new JsonArray();
		for(String list : couponList) {
			int couponNo = Integer.parseInt(list);
			int result = couponService.deleteCoupon(couponNo);
			if(result > 0) {
				successList.add(couponNo);
			}
		}
		
		// 4)
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(successList, response.getWriter());
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
