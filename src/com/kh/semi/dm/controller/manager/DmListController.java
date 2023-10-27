package com.kh.semi.dm.controller.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.dm.model.service.DmService;
import com.kh.semi.dm.model.vo.Dm;

/**
 * Servlet implementation class DmListController
 */
@WebServlet("/jhselect.dm")
public class DmListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DmService dmService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DmListController() {
        super();
        dmService = new DmService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int dmListCount;		// 현재 카테고리 총 수
		int dmListPage;			// 현재 페이지
		int dmPageLimit;		// 페이징바 최대 개수 
		int dmLimit;			// 한 페이지에 보여질 게시글의 최대 개수 >> 10개로 고정
		
		int dmMaxPage;			// 총 페이지 개수 == 마지막 페이지
		int dmStartPage;		// 페이징바 시작 수
		int dmEndPage;			// 페이징바 끝 수
		
		dmListCount = dmService.selectDmListCount();
		
		String pageStr = request.getParameter("page");
		if(pageStr == null)
			dmListPage = 1;
		else
			dmListPage = Integer.parseInt(pageStr);
		// System.out.println(dmListCount);	//	저장 리워드 내역 데이터값 ok
		// System.out.println(dmListPage);	// 	현재 페이지 ok (1)
		
		dmPageLimit = 10;
		dmLimit = 10;
		dmMaxPage = (int)Math.ceil((double)dmListCount / dmLimit);
		// System.out.println(dmMaxPage);	// 현재 저장된 카테고리 수 == 105개 == maxPage 11 ok
		dmStartPage = (dmListPage - 1) / dmPageLimit * dmLimit + 1;
		dmEndPage = dmStartPage + dmPageLimit -1;
		
		if(dmEndPage > dmMaxPage) {
			dmEndPage = dmMaxPage;
		}
		
		// 3) 데이터가공 - 페이지 변수들
		PageInfo pi = new PageInfo();
		pi.setListCount(dmListCount);
		pi.setCurrentPage(dmListPage);
		pi.setPageLimit(dmPageLimit);
		pi.setBoardLimit(dmLimit);
		pi.setMaxPage(dmMaxPage);
		pi.setStartPage(dmStartPage);
		pi.setEndPage(dmEndPage);
		
		// 4) Service요청
		ArrayList<Dm> list = dmService.selectDmList(pi);
		int repliedCount = dmService.repliedCount();
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.setAttribute("repliedCount", repliedCount);
		request.setAttribute("waitingCount", dmService.getWaitingCount(list));
		
		// 5) 응답화면 지정
		request.getRequestDispatcher("views/dm/manager/dmListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
