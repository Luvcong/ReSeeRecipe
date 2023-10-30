package com.kh.semi.common;

import javax.servlet.http.HttpServletRequest;

public class SendError {
	
	private String viewPathError = "WEB-INF/views/common/errorPage.jsp";
	
	public String sendError(HttpServletRequest request, String errorStr) {
		request.setAttribute("errorMsg", errorStr);
		return viewPathError;
	}
	
}
