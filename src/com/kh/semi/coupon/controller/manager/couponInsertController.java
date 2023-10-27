package com.kh.semi.coupon.controller.manager;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.coupon.model.service.manager.CouponService;
import com.kh.semi.coupon.model.vo.Coupon;

/**
 * Servlet implementation class couponInsertController
 */
@WebServlet("/jhinsert.cp")
public class CouponInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CouponService couponService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CouponInsertController() {
        super();
        couponService = new CouponService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String couponName = request.getParameter("couponName");
		String startCoupon = request.getParameter("startCoupon");
		String endCoupon = request.getParameter("endCoupon");
		String couponReason = request.getParameter("couponReason");
		int couponRatio = Integer.parseInt(request.getParameter("couponRatio"));
		
		// System.out.println(startCoupon);
		// System.out.println(endCoupon);
		
		Coupon coupon = new Coupon();
		coupon.setCouponName(couponName);
		coupon.setStartCoupon(startCoupon);
		coupon.setEndCoupon(endCoupon);
		coupon.setCouponReason(couponReason);
		coupon.setCouponRatio(couponRatio);
		
		int result = couponService.insertCoupon(coupon);
		
		if(result > 0) {
			request.getSession().setAttribute("successMsg", "쿠폰 등록이 완료되었습니다!");
		} else {
			request.getSession().setAttribute("failMsg", "쿠폰 등록에 실패했습니다! 다시 시도해주세요");
		}
		response.sendRedirect(request.getContextPath() + "/jhselect.cp?page=1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
