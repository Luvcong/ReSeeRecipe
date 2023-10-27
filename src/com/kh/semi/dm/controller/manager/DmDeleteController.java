package com.kh.semi.dm.controller.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kh.semi.dm.model.service.DmService;

/**
 * Servlet implementation class DmDeleteController
 */
@WebServlet("/jhdelete.dm")
public class DmDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DmService dmService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DmDeleteController() {
        super();
        dmService = new DmService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) 인코딩x
		// 2) 전달값
		String[] dmNoArr = request.getParameterValues("dmNo[]");	// ajax 배열은 [] 넣어야하는지 물어보기 -- No / View단에서 datatype-json으로 넘겨서 그런거임
		// 3) 데이터가공x
		// 4)
//		int successCount = 0;
//		for(String arr : dmNoArr) {
//			int dmNo = Integer.parseInt(arr);
//			int result = dmService.deleteDm(dmNo);
//			if(result == 1)
//				successCount++;
//		}
		
		JsonArray successList = new JsonArray();
		for(String arr : dmNoArr) {
			int dmNo = Integer.parseInt(arr);
			int result = dmService.deleteDm(dmNo);
			if(result == 1)
				successList.add(dmNo);
		}
		
		
		// 5) 성공
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(successList, response.getWriter());
//		if(successCount == dmNoArr.length) {
//			c
//			//response.getWriter().print("쪽지 삭제가 완료되었습니다!");
//			System.out.println("성공");
//		}
//		else {
//			request.getSession().setAttribute("failMsg", String.format("쪽지 삭제 실패 (총 %d 건/ %d 건 실패)", dmNoArr.length, dmNoArr.length - successCount));
//			//response.getWriter().print(String.format("쪽지 삭제 실패 (총 %d 건/ %d 건 실패)", dmNoArr.length, dmNoArr.length - successCount));
//			System.out.println("실패");			
//		}
		//response.sendRedirect(request.getContextPath() + "/jhselect.dm");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
