package com.kh.semi.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class MemberManagerController
 */
@WebServlet("/hlmembermanage.ma")
public class MemberManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) 인코딩 a태그 -> GET방식
		
		// 2) request 값 뽑을게 없음
		
		// -- 페이징 처리 --
		// 필요한 변수들
		int memlistCount; // 현재  회원 리스트의 총 회원 수
		int memlistPage; // 현재 페이지(관리자가 요청한 페이지) => request.getParameter("cpage")
		int mempageLimit; // 페이지 하단에 페이징바의 최대 개수 => 10개로 고정
		int memLimit; // 한 페이지에 보여질 회원의 최대 수 => 10개
		
		int memMaxPage; // 가장 마지막 페이지가 몇 번 페이지인지 (총 페이지의 개수)
		int memStartPage; // 페이지 하단에 보여질 페이징바의 시작 수
		int memEndPage; // 페이징 하단에 보여질 페이징바의 끝 수
		
		// * memlistCount : 총 회원 수
		memlistCount = new MemberService().selectMemlistCount();
		
		// * memlistPage : 현재페이지(관리자가 요청한 페이지)
		memlistPage = Integer.parseInt(request.getParameter("cmpage")); //1
		
		// * mempageLimit : 페이징바 최대 개수
		mempageLimit = 10;
		
		// * memLimit : 한 페이지에 보여질 회원의 최대 수
		memLimit = 10;
		
		// * memMaxPage : 가장 마지막 페이지가 몇 번 페이지인지(총 페이지의 개수)
		memMaxPage = (int)Math.ceil((double)memlistCount / memLimit); // 더 큰 쪽의 자료형으로 자료형이 바뀜 기본자료형 잘 하기!!
		
		// * memStartPage : 페이징 하단에 보여질 페이징바의 시작 수
		memStartPage = (memlistPage - 1) / mempageLimit * memLimit + 1;
		
		// * memEndPage : 페이징 하단에 보여질 페이징바의 끝 수
		memEndPage = memStartPage + mempageLimit -1;
		
		// memEndPage값을 memMaxPage값으로 변경
		if(memEndPage > memMaxPage) {
			memEndPage = memMaxPage;
		}
		
		// 여기까지 총 7개의 변수를 만들었음!
		PageInfo pi = new PageInfo();
		pi.setListCount(memlistCount);
		pi.setCurrentPage(memlistPage);
		pi.setPageLimit(mempageLimit);
		pi.setBoardLimit(memLimit);
		pi.setMaxPage(memMaxPage);
		pi.setStartPage(memStartPage);
		pi.setEndPage(memEndPage);
		
		request.setCharacterEncoding("UTF-8");
		// 3) Service 호출 전체 회원 정보 SELECT
		ArrayList<Member> list = new MemberService().selectMemberAll(pi);
		
		// -- request.attribute로 하기 포워딩
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("views/member/memberManager.jsp").forward(request, response);
		
		
		System.out.print("list"+ list);
		System.out.println("pi" + pi);
		// ------ Ajax 처리 --------
		
		// 5) 형식, 인코딩 지정
		//response.setContentType("application/json; charset=UTF-8");
		
		// JSON ArrayList 생성
		/*
		JSONArray jArr = new JSONArray();
		
		JSONObject jObj1 = new JSONObject();
		
		for(Member m : list) {
			JSONObject jObj = new JSONObject();
			jObj.put("memNo", m.getMemNo());
			jObj.put("memName", m.getMemName());
			jObj.put("memId", m.getMemId());
			jObj.put("memNickname", m.getMemNickname());
			jObj.put("memEmail", m.getMemEmail());
			jObj.put("enrollDate", m.getEnrollDate());
			jObj.put("memReward", m.getMemReward());
			
			jArr.add(jObj);
		}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jArr);
		*/
		// Gson객체 생성
		//Gson gson = new Gson();
		//new Gson().toJson(list, response.getWriter());
		//request.getRequestDispatcher("views/member/memberManager.jsp").forward(request, response);
		//response.sendRedirect(request.getContextPath() + );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
