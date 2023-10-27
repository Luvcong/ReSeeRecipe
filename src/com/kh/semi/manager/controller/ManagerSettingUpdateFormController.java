package com.kh.semi.manager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.manager.model.service.ManagerService;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class ManagerSettingUpdateFormController
 */
@WebServlet("/hladminupdateForm.ma")
public class ManagerSettingUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerSettingUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int adminNo = Integer.parseInt(request.getParameter("adno"));
		
		Member m = new ManagerService().managerSetting(adminNo);
		
		request.setAttribute("m", m);
		request.setAttribute("mp", m.getMemPicture());
		
		// 응답화면 띄우기
		request.getRequestDispatcher("views/manager/managerSettingView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
