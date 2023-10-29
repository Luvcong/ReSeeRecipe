package com.kh.semi.report.controller.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.common.template.Pagination;
import com.kh.semi.report.model.service.ReportService;
import com.kh.semi.report.model.service.ReportServiceImpl;
import com.kh.semi.report.model.vo.Report;

/**
 * Servlet implementation class reportListController
 */
@WebServlet("/jhselect.rp")
public class reportListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportServiceImpl reportServiceImpl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reportListController() {
        super();
        reportServiceImpl = new ReportServiceImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1)
		request.setCharacterEncoding("UTF-8");
		
		int reportListCount = reportServiceImpl.selectReportListCount();		// 현재 카테고리 총 수
		int reportCurrentPage = Integer.parseInt(request.getParameter("page"));	// 현재 페이지
		int reportPageLimit = 10;		// 페이징바 최대 개수 
		int reportLimit = 10;			// 한 페이지에 보여질 게시글의 최대 개수 >> 10개로 고정
		
		// 3) 데이터가공 - 페이지 변수들
		PageInfo pi = Pagination.getPageInfo(reportListCount, reportCurrentPage, reportPageLimit, reportLimit);
		
		ArrayList<Report> list = reportServiceImpl.selectReportList(pi);
		// 5)
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("WEB-INF/views/report/manager/reportListView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
