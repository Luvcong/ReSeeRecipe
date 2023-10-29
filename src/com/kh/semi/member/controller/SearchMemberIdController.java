package com.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class SearchMemberIdController
 */
@WebServlet("/yrsearchMemberId.me")
public class SearchMemberIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberIdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberName = request.getParameter("memberName");
		String memberEmail = request.getParameter("memberEmail");
		
		Member m = new Member();
		m.setMemName(memberName);
		m.setMemEmail(memberEmail);
		
		Member searchMember = new MemberService().searchMemberId(m);
		
		JSONObject jObj = new JSONObject();
		// 조회된 아이디가 없다면 => 문자열 null 반환 => 조회된 아이디가 없습니다 처리
		if(searchMember == null) {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("null");
		} else { // 조회된 아이디가 있다면 => jObj에 조회된 ID와 Status 객체 반환
			jObj.put("searchMemberId", searchMember.getMemId());
			jObj.put("searchMemberStatus", searchMember.getMemStatus());
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(jObj);
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
