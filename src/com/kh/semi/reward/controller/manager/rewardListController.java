package com.kh.semi.reward.controller.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.common.template.Pagination;
import com.kh.semi.dm.model.vo.Dm;
import com.kh.semi.reward.model.service.RewardService;
import com.kh.semi.reward.model.service.RewardServiceImpl;
import com.kh.semi.reward.model.vo.Reward;

/**
 * Servlet implementation class rewardListController
 */
@WebServlet("/jhselect.rw")
public class rewardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RewardServiceImpl rewardServiceImpl;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rewardListController() {
        super();
        rewardServiceImpl = new RewardServiceImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int rewardListCount = rewardServiceImpl.selectRewardListCount();			// 현재 카테고리 총 수
		int rewardCurrentPage = Integer.parseInt(request.getParameter("page"));		// 현재 페이지
		int rewardPageLimit = 10;		// 페이징바 최대 개수 
		int rewardLimit = 10;			// 한 페이지에 보여질 게시글의 최대 개수 >> 10개로 고정
		
		PageInfo pi = Pagination.getPageInfo(rewardListCount, rewardCurrentPage, rewardPageLimit, rewardLimit);
		
		ArrayList<Reward> list = rewardServiceImpl.selectRewardList(pi);
		
		// 5) 응답화면 지정
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("WEB-INF/views/reward/manager/rewardListView.jsp").forward(request, response);
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
