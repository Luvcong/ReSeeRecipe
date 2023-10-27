package com.kh.semi.report.controller.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.report.model.service.ReportService;

/**
 * Servlet implementation class reportUpdateController
 */
@WebServlet("/jhupdate.rp")
public class reportUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportService reportService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reportUpdateController() {
        super();
        reportService = new ReportService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
