package com.kh.semi.common.heart.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.heart.model.service.AjaxHeartServiceImpl;
import com.kh.semi.common.heart.model.vo.Heart;
import com.kh.semi.member.model.vo.Member;

public class AjaxHeartController {
	
	
	/*************** 숫자 검증 *****************************************************/
	private boolean isNumber(String htTargetNoStr) {
		// 숫자 유효성 체크 후 리턴
		boolean validation = Pattern.matches("^[0-9]*$", htTargetNoStr);
		return validation;
	}
	/****************************************************************************/
	
	
	/*************** 특정 대상 하트 카운트 조회 기능 ***************************************/
	public int htCountRecipe(HttpServletRequest request, HttpServletResponse response) {
		
		// 값 추출 + 가공
		int htTargetNo = Integer.parseInt(request.getParameter("htTargetNo"));
		System.out.println(htTargetNo);
		// Service호출
		int result = new AjaxHeartServiceImpl().htCountRecipe(htTargetNo);
		
		return result;
	}
	
	
	public int htCountBookmark(HttpServletRequest request, HttpServletResponse response) {
		
		// 값 추출 + 가공 
		int htTargetNo = Integer.parseInt(request.getParameter("htTargetNo"));
		// Service호출
		int result = new AjaxHeartServiceImpl().htCountBookmark(htTargetNo);
		return result;
	}
	
	
	public int htCountNotice(HttpServletRequest request, HttpServletResponse response) {
		
		// 값 추출 + 가공
		int htTargetNo = Integer.parseInt(request.getParameter("htTargetNo"));
		// Service호출
		int result = new AjaxHeartServiceImpl().htCountNotice(htTargetNo);
		
		return result;
	}
	
	
	public int htCountSubsc(HttpServletRequest request, HttpServletResponse response) {
		
		// 값 추출 + 가공
		int htTargetNo = Integer.parseInt(request.getParameter("htTargetNo"));
		int result = new AjaxHeartServiceImpl().htCountSubsc(htTargetNo);
		return result;
	}
	
	
	public int htCountReply(HttpServletRequest request, HttpServletResponse response) {
		
		int htTargetNo = Integer.parseInt(request.getParameter("htTargetNo"));
		int result = new AjaxHeartServiceImpl().htCountReply(htTargetNo);
		return result;
	}
	/****************************************************************************/
	
	
	/*************** 하트 추가/삭제 기능 ***********************************************/
	public int htChangeRecipe(HttpServletRequest request, HttpServletResponse response) {
		// 변수세팅
		int result = 0;
		
		// 값 추출
		int memNo = ((Member)request.getSession().getAttribute("loginMember")).getMemNo();
		String htTargetNoStr = request.getParameter("htTargetNo");
		
		// Controller단 입력값 검사
		if(isNumber(htTargetNoStr)) {
			// 자료형 검사 통과 후 ht객체에 담기
			Heart ht = new Heart(memNo, Integer.parseInt(htTargetNoStr));
			result = new AjaxHeartServiceImpl().htChangeRecipe(ht);
		}
		return result;
	}
	
	
	public int htChangeBookmark(HttpServletRequest request, HttpServletResponse response) {
		
		int result = 0;
		
		int memNo = ((Member)request.getSession().getAttribute("loginMember")).getMemNo();
		String htTargetNoStr = request.getParameter("htTargetNo");
		
		if(isNumber(htTargetNoStr)) {
			Heart ht = new Heart(memNo, Integer.parseInt(htTargetNoStr));
			result = new AjaxHeartServiceImpl().htChangeBookmark(ht);
		}
		return result;
	}
	
	
	public int htChangeNotice(HttpServletRequest request, HttpServletResponse response) {
		
		int result = 0;
		
		int memNo = ((Member)request.getSession().getAttribute("loginMember")).getMemNo();
		String htTargetNoStr = request.getParameter("htTargetNo");
		
		if(isNumber(htTargetNoStr)) {
			Heart ht = new Heart(memNo, Integer.parseInt(htTargetNoStr));
			result = new AjaxHeartServiceImpl().htChangeNotice(ht);
		}
		return result;
	}
	
	
	public int htChangeSubsc(HttpServletRequest request, HttpServletResponse response) {
		
		int result = 0;
		
		int memNo = ((Member)request.getSession().getAttribute("loginMember")).getMemNo();
		String htTargetNoStr = request.getParameter("htTargetNo");
		
		if(isNumber(htTargetNoStr)) {
			Heart ht = new Heart(memNo, Integer.parseInt(htTargetNoStr));
			result = new AjaxHeartServiceImpl().htChangeSubsc(ht);
		}
		return result;
	}
	
	
	public int htChangeReply(HttpServletRequest request, HttpServletResponse response) {
		
		int result = 0;
		
		int memNo = ((Member)request.getSession().getAttribute("loginMember")).getMemNo();
		String htTargetNoStr = request.getParameter("htTargetNo");
		
		if(isNumber(htTargetNoStr)) {
			Heart ht = new Heart(memNo, Integer.parseInt(htTargetNoStr));
			result = new AjaxHeartServiceImpl().htChangeReply(ht);
		}
		return result;
	}
	/****************************************************************************/	
	
	
}//class.end