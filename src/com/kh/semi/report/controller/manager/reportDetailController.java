package com.kh.semi.report.controller.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.report.model.service.ReportService;
import com.kh.semi.report.model.vo.Report;

/**
 * Servlet implementation class reportDetailController
 */
@WebServlet("/jhdetail.rp")
public class reportDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportService reportService;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reportDetailController() {
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
		// 2) select xx / 식별값 뽑아야함
		int reportNo = Integer.parseInt(request.getParameter("reportNo"));
		String categoryName = request.getParameter("categoryName");
		// 3) x
		// 4)
		ArrayList<Report> detailList = reportService.detailReportList(reportNo, categoryName);
		request.setAttribute("detailList", detailList);
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
