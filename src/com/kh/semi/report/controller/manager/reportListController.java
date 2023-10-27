package com.kh.semi.report.controller.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.report.model.service.ReportService;
import com.kh.semi.report.model.vo.Report;
import com.kh.semi.reward.model.vo.Reward;

/**
 * Servlet implementation class reportListController
 */
@WebServlet("/jhselect.rp")
public class reportListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportService reportService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reportListController() {
        super();
        reportService = new ReportService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1)
		request.setCharacterEncoding("UTF-8");
		
		int reportListCount;		// 현재 카테고리 총 수
		int reportListPage;			// 현재 페이지
		int reportPageLimit;		// 페이징바 최대 개수 
		int reportLimit;			// 한 페이지에 보여질 게시글의 최대 개수 >> 10개로 고정
		
		int reportMaxPage;			// 총 페이지 개수 == 마지막 페이지
		int reportStartPage;		// 페이징바 시작 수
		int reportEndPage;			// 페이징바 끝 수
		
		reportListCount = reportService.selectReportListCount();
		reportListPage = Integer.parseInt(request.getParameter("page"));
		// System.out.println(reportListCount);	//	저장 리워드 내역 데이터값 ok
		// System.out.println(reportListPage);	// 	현재 페이지 ok (1)
		
		reportPageLimit = 10;
		reportLimit = 10;
		reportMaxPage = (int)Math.ceil((double)reportListCount / reportLimit);
		// System.out.println(reportMaxPage);	// 현재 저장된 카테고리 수 == 105개 == maxPage 11 ok
		reportStartPage = (reportListPage - 1) / reportPageLimit * reportLimit + 1;
		reportEndPage = reportStartPage + reportPageLimit -1;
		
		if(reportEndPage > reportMaxPage) {
			reportEndPage = reportMaxPage;
		}
		
		// 3) 데이터가공 - 페이지 변수들
		PageInfo pi = new PageInfo();
		pi.setListCount(reportListCount);
		pi.setCurrentPage(reportListPage);
		pi.setPageLimit(reportPageLimit);
		pi.setBoardLimit(reportLimit);
		pi.setMaxPage(reportMaxPage);
		pi.setStartPage(reportStartPage);
		pi.setEndPage(reportEndPage);
		
		ArrayList<Report> list = reportService.selectReportList(pi);
		// 5)
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("views/report/manager/reportListView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
