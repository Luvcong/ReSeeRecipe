package com.kh.semi.reward.controller.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.dm.model.vo.Dm;
import com.kh.semi.reward.model.service.RewardService;
import com.kh.semi.reward.model.vo.Reward;

/**
 * Servlet implementation class rewardListController
 */
@WebServlet("/jhselect.rw")
public class rewardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RewardService rewardService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rewardListController() {
        super();
        rewardService = new RewardService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int rewardListCount;		// 현재 카테고리 총 수
		int rewardListPage;			// 현재 페이지
		int rewardPageLimit;		// 페이징바 최대 개수 
		int rewardLimit;			// 한 페이지에 보여질 게시글의 최대 개수 >> 10개로 고정
		
		int rewardMaxPage;			// 총 페이지 개수 == 마지막 페이지
		int rewardStartPage;		// 페이징바 시작 수
		int rewardEndPage;			// 페이징바 끝 수
		
		rewardListCount = rewardService.selectRewardListCount();
		rewardListPage = Integer.parseInt(request.getParameter("page"));
		// System.out.println(rewardListCount);	//	저장 리워드 내역 데이터값 ok
		// System.out.println(rewardListPage);	// 	현재 페이지 ok (1)
		
		rewardPageLimit = 10;
		rewardLimit = 10;
		rewardMaxPage = (int)Math.ceil((double)rewardListCount / rewardLimit);
		// System.out.println(rewardMaxPage);	// 현재 저장된 카테고리 수 == 105개 == maxPage 11 ok
		rewardStartPage = (rewardListPage - 1) / rewardPageLimit * rewardLimit + 1;
		rewardEndPage = rewardStartPage + rewardPageLimit -1;
		
		if(rewardEndPage > rewardMaxPage) {
			rewardEndPage = rewardMaxPage;
		}
		
		// 3) 데이터가공 - 페이지 변수들
		PageInfo pi = new PageInfo();
		pi.setListCount(rewardListCount);
		pi.setCurrentPage(rewardListPage);
		pi.setPageLimit(rewardPageLimit);
		pi.setBoardLimit(rewardLimit);
		pi.setMaxPage(rewardMaxPage);
		pi.setStartPage(rewardStartPage);
		pi.setEndPage(rewardEndPage);
		
		ArrayList<Reward> list = rewardService.selectRewardList(pi);
		
		// 5) 응답화면 지정
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("views/reward/manager/rewardListView.jsp").forward(request, response);
		// response.sendRedirect(request.getContextPath() + "/jhselect.rw?page=1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
