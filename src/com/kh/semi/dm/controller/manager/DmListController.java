package com.kh.semi.dm.controller.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.common.template.Pagination;
import com.kh.semi.dm.model.service.DmService;
import com.kh.semi.dm.model.service.DmServiceImpl;
import com.kh.semi.dm.model.vo.Dm;

/**
 * Servlet implementation class DmListController
 */
@WebServlet("/jhselect.dm")
public class DmListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DmServiceImpl dmServiceImpl;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DmListController() {
        super();
        dmServiceImpl = new DmServiceImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int dmListCount = dmServiceImpl.selectDmListCount();					// 현재 카테고리 총 수
		int dmCurrentPage;			// 현재 페이지
		int dmPageLimit = 10;		// 페이징바 최대 개수 
		int dmLimit = 10;			// 한 페이지에 보여질 게시글의 최대 개수 >> 10개로 고정
		
		String pageStr = request.getParameter("page");
		
		if(pageStr == null)
			dmCurrentPage = 1;
		else
			dmCurrentPage = Integer.parseInt(pageStr);
		
		PageInfo pi = Pagination.getPageInfo(dmListCount, dmCurrentPage, dmPageLimit, dmLimit);
		
		// 4) Service요청
		ArrayList<Dm> list = dmServiceImpl.selectDmList(pi);
		int repliedCount = dmServiceImpl.repliedCount();
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.setAttribute("repliedCount", repliedCount);
		request.setAttribute("waitingCount", dmServiceImpl.getWaitingCount(list));
		
		// 5) 응답화면 지정
		request.getRequestDispatcher("WEB-INF/views/dm/manager/dmListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
