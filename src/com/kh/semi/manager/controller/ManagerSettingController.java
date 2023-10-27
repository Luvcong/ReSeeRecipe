  package com.kh.semi.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semi.common.MyFileRenamePolicy;
import com.kh.semi.manager.model.service.ManagerService;
import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ManagerSettingController
 */
@WebServlet("/hlsettingmanager.ma") 
public class ManagerSettingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerSettingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/** 관리자 정보 설정 서블릿
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		// -- session에서 뽑기! ㄱ 는 컨트롤러를 한번더 만들어야함
		// 2) 값뽑기 - 관리자 식별 번호 input 태그 hidden의 value
		int adminNo = Integer.parseInt(request.getParameter("adno"));
		
		
		
		Member m = new ManagerService().managerSetting(adminNo);
		System.out.println("m>>" + m);
		System.out.println();
		request.getSession().setAttribute("m", m);
		request.setAttribute("mp", m.getMemPicture());
		
		// 응답화면 띄우기
		request.getRequestDispatcher("/views/manager/managerSettingInfoView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
