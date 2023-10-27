package com.kh.semi.coupon.controller.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.coupon.model.service.manager.CouponService;
import com.kh.semi.coupon.model.vo.Coupon;

/**
 * Servlet implementation class CouponIssueController
 */
@WebServlet("/jhissue.cp")
public class CouponIssueController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CouponService couponService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CouponIssueController() {
        super();
        couponService = new CouponService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int couponListCount;		// 현재 카테고리 총 수
		int couponListPage;			// 현재 페이지
		int couponPageLimit;		// 페이징바 최대 개수 
		int couponLimit;			// 한 페이지에 보여질 게시글의 최대 개수 >> 10개로 고정
		
		
		couponListPage = Integer.parseInt(request.getParameter("page"));
		couponListCount = couponService.selectCouponListCount();
		couponPageLimit = 10;
		couponLimit = 10;
		
		PageInfo pi = new PageInfo(couponListCount, couponListPage, couponPageLimit, couponLimit);
		ArrayList<Coupon> list = couponService.selectCouponList(pi);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("views/coupon/manager/couponIssueView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
