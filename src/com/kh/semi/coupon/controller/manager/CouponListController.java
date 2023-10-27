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
 * Servlet implementation class CouponListController
 */
@WebServlet("/jhselect.cp")
public class CouponListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CouponService couponService;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CouponListController() {
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
		
		int couponMaxPage;			// 총 페이지 개수 == 마지막 페이지
		int couponStartPage;		// 페이징바 시작 수
		int couponEndPage;			// 페이징바 끝 수
		
		couponListCount = couponService.selectCouponListCount();
		couponListPage = Integer.parseInt(request.getParameter("page"));
		// System.out.println(couponListCount);	//	저장 리워드 내역 데이터값 ok
		// System.out.println(couponListPage);	// 	현재 페이지 ok (1)
		
		couponPageLimit = 10;
		couponLimit = 10;
		couponMaxPage = (int)Math.ceil((double)couponListCount / couponLimit);
		// System.out.println(couponMaxPage);	// 현재 저장된 카테고리 수 == 105개 == maxPage 11 ok
		couponStartPage = (couponListPage - 1) / couponPageLimit * couponLimit + 1;
		couponEndPage = couponStartPage + couponPageLimit -1;
		
		if(couponEndPage > couponMaxPage) {
			couponEndPage = couponMaxPage;
		}
		
		PageInfo pi = new PageInfo();
		pi.setListCount(couponListCount);
		pi.setCurrentPage(couponListPage);
		pi.setPageLimit(couponPageLimit);
		pi.setBoardLimit(couponLimit);
		pi.setMaxPage(couponMaxPage);
		pi.setStartPage(couponStartPage);
		pi.setEndPage(couponEndPage);

		ArrayList<Coupon> list = couponService.selectCouponList(pi);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("views/coupon/manager/couponListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
