package com.kh.semi.coupon.controller.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.common.template.Pagination;
import com.kh.semi.coupon.model.service.manager.CouponService;
import com.kh.semi.coupon.model.service.manager.CouponServiceImpl;
import com.kh.semi.coupon.model.vo.Coupon;

/**
 * Servlet implementation class CouponListController
 */
@WebServlet("/jhselect.cp")
public class CouponListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CouponServiceImpl couponServiceImpl;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CouponListController() {
        super();
        couponServiceImpl = new CouponServiceImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int couponListCount = couponServiceImpl.selectCouponListCount();				// 현재 카테고리 총 수
		int couponCurrentPage = Integer.parseInt(request.getParameter("page"));			// 현재 페이지
		int couponPageLimit = 10;		// 페이징바 최대 개수 
		int couponLimit = 10;			// 한 페이지에 보여질 게시글의 최대 개수 >> 10개로 고정
		
		// System.out.println(couponListCount);	//	저장 리워드 내역 데이터값 ok
		// System.out.println(couponCurrentPage);	// 	현재 페이지 ok (1)
		
		PageInfo pi = Pagination.getPageInfo(couponListCount, couponCurrentPage, couponPageLimit, couponLimit);
		ArrayList<Coupon> list = couponServiceImpl.selectCouponList(pi);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("WEB-INF/views/coupon/manager/couponListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
