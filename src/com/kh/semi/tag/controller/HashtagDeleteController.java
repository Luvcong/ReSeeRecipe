package com.kh.semi.tag.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.tag.model.service.TagService;

/**
 * Servlet implementation class HashtagDeleteController
 */
@WebServlet("/hashdelete.hs")
public class HashtagDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HashtagDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String[] arr = request.getParameterValues("tagNo");
		int[] tagNo = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
        	tagNo[i] = Integer.parseInt(arr[i]);
        }
		
		int result = new TagService().hashtagDelete(tagNo);
		
		if(result > 0) {
			request.getSession().setAttribute("successMsg", "해시태그 삭제가 완료되었습니다!");
		} else {
			request.getSession().setAttribute("failMsg", "해시태그 삭제가 실패하셨습니다. 다시 시도해주세요!");
		}
		
		response.sendRedirect(request.getContextPath() + "/hsselect.hs?cpage=1");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
