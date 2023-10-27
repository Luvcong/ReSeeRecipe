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
import com.kh.semi.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ManagerSettingUpdateController
 */
@WebServlet("/hladminupdate.ma")
public class ManagerSettingUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerSettingUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) POST => 인코딩
		request.setCharacterEncoding("UTF-8");

		// 2) 값 뽑기 전 => 파일이 전송될 것인가 파악
		// multipart/form-data로 잘 전송했을 때만 내용이 수정되도록 조건
		if (ServletFileUpload.isMultipartContent(request)) {

			// 1. 전송파일 용량 제한 int maxSize => 10Mbyte
			int maxSize = 1024 * 1024 * 10;

			// 2. 전송된 파일을 저장시킬 물리적인 경로 알아내기 String savePath
			String savePath = request.getSession().getServletContext().getRealPath("/resources/profile_upfiles");

			// 전달된 파일명 수정 후 서버에 업로드
			// MultipartRequest 객체를 생성함으로 서버에 파일이 업로드
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
					new MyFileRenamePolicy());

			// 3. 값 가져오기
			int adminNo = Integer.parseInt(multiRequest.getParameter("adminNo"));
			String adminName = multiRequest.getParameter("adminName");
			String adminNickname = multiRequest.getParameter("adminNickname");
			// String adminId = multiRequest.getParameter("adminId");
			String adminEmail = multiRequest.getParameter("adminEmail");
			String adminPicture = multiRequest.getParameter("adpic");
			// VO로 가공 -> Service 호출
			Member m = new Member();
			m.setMemNo(adminNo);
			m.setMemName(adminName);
			m.setMemNickname(adminNickname);
			m.setMemEmail(adminEmail);
			
			
			
			// 프로필 사진 수정을 누르면 무조건 사진 있음. 그러나 기본값 = null

			
			if (multiRequest.getOriginalFileName("adminpic") != null) {

				// 회원 프로필 사진은 회원 테이블에 존재
				//adminPicture = "/resources/profile_upfiles/" + multiRequest.getFilesystemName("adminpic");
				 adminPicture = "/resources/profile_upfiles/" + multiRequest.getFilesystemName("adminpic");
			}

			// 4) 서비스 호출 관리자 번호와 프로필파일 경로 + 수정명 DB MEM_PICTURE에 저장

			int result = new ManagerService().adminUpdate(m, adminPicture);

			// 관리자 정보 수정 성공 시
			if (result > 0) {

				// 관리자 정보 DB에서 다시 SELECT
				// => loginMember에 들어있는 정보는 update 후 갱신 필요
				Member manager = new ManagerService().managerSetting(adminNo);

				request.getSession().setAttribute("m", manager);

				// 응답화면 띄우기
				//response.sendRedirect(request.getContextPath() + "hlsettingmanager.ma?adno=" + adminNo);
				//request.getRequestDispatcher("/views/manager/navbar.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath() + "/hlsettingmanager.ma?adno=" + adminNo);
			} else {
				request.setAttribute("errorMsg", "관리자 정보 수정 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
