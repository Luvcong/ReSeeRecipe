package com.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semi.common.MyFileRenamePolicy;
import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;


/**
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/yrmemberUpdate.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 파일 크기 10MB
			int maxSize = 1024 * 1024 * 10;
			
			// 저장경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/profile_upfiles");
			
			// HttpServletRequest request 객체를 MultipartRequest 변환
			// MultipartRequest 객체 생성 시 파일 업로드
			// MyFileRenamePolicy()는 파일 이름 수정
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 값 가져오기
			// int memberNo = Integer.parseInt(multiRequest.getParameter("memberNo"));
			String memberName = multiRequest.getParameter("memberName");
			String memberNickname = multiRequest.getParameter("memberNickname");
			String memberEmail = multiRequest.getParameter("memberEmail");
			
			// 회원이 가진 이미지 파일
			String memberPicture = multiRequest.getParameter("memberPicture");
			
			// 회원정보변경 후 다시 회원 조회를 위한 id, pwd값
			String memberId = multiRequest.getParameter("memberId");
			String memberPwd = multiRequest.getParameter("loginMemberPwd");
			
			// 가져온 값들 객체로 Service 넘길 것
			Member m = new Member();
			// m.setMemNo(memberNo);
			m.setMemId(memberId);
			m.setMemName(memberName);
			m.setMemNickname(memberNickname);
			m.setMemEmail(memberEmail);
			
			
			// 현재 파일수정을 눌렀다면 무조건 사진이 있음. 근데 기본값은 null임
			
			// 파일을 첨부했다면
			if(multiRequest.getOriginalFileName("profileInput") != null) {
				// 파일경로 + 파일수정명을 넘겨줄거임(DB에 MEM_PICTURE컬럼에 저장)
				memberPicture = "/resources/profile_upfiles/" + multiRequest.getFilesystemName("profileInput");
			} 
			
			// Service로 수정할 회원 정보와 파일정보를 넘겨 요청
			int result = new MemberService().memberUpdate(m, memberPicture);
			
			// update 성공 시
			if(result > 0) {
				
				// 회원의 정보를 다시 SELECT해와야함 
				// => 왜냐면 loginMember에 들어있는 정보는 update후 갱신이 안되어 있음
				
				Member updatedMember = new MemberService().loginMember(memberId, memberPwd);
				
				request.getSession().setAttribute("loginMember", updatedMember);
				// 나중에 마이페이지 메인으로 바꿀 것
				// update를 한 걸 업데이트가 돼야 하니까 sendRedirect로 가야하나? => 로그인이 풀려버림
				// request.getRequestDispatcher(request.getContextPath()).forward(request, response);
				
				// 현재 blog.me를 찾을 수 없음  임시로 변경
				response.sendRedirect(request.getContextPath() + "/blog.me");
				// response.sendRedirect("views/myPage/memberPage.jsp");
				// request.getRequestDispatcher("views/myPage/memberPage.jsp").forward(request, response);
				
			} else { // 실패 시
				request.getSession().setAttribute("memberUpdateError", "변경에 실패하였습니다.");
				request.getRequestDispatcher(request.getContextPath() + "/yrmemberUpdateConfirmForm.me").forward(request, response);
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
