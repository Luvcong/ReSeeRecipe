package com.kh.semi.report.controller.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.report.model.service.ReportServiceImpl;
import com.kh.semi.report.model.vo.Report;

/**
 * Servlet implementation class reportDetailController
 */
@WebServlet("/jhdetail.rp")
public class reportDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportServiceImpl reportServiceImpl;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reportDetailController() {
        super();
        reportServiceImpl = new ReportServiceImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int reportNo = Integer.parseInt(request.getParameter("reportNo"));
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("reportNo", Integer.parseInt(request.getParameter("reportNo")));
//		map.put("categoryName", request.getParameter("categoryName"));
		
		// 4)
		ArrayList<Report> detailList = reportServiceImpl.detailReportList(reportNo);
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
