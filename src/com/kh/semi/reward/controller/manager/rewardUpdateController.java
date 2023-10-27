package com.kh.semi.reward.controller.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.reward.model.service.RewardService;
import com.kh.semi.reward.model.vo.Reward;

/**
 * Servlet implementation class rewardUpdateController
 */
@WebServlet("/jhupdate.rw")
public class rewardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RewardService rewardService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rewardUpdateController() {
        super();
        rewardService = new RewardService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) POST
		request.setCharacterEncoding("UTF-8");
		// 2) 전달값
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		int rewardScore = Integer.parseInt(request.getParameter("rewardScore"));
		String rewardReason = request.getParameter("rewardReason");
		
//		<select name="selectReward">
//		<option value="plusReard">리워드 지급</option>
//		<option value="minusReard">리워드 차감</option>
		String selectReward = request.getParameter("selectReward");
		System.out.println(selectReward);
		
		int minus = 0;
		if(selectReward.equals("minusReward")) {
			minus = (rewardScore * -1);
			// System.out.println(minus);		// 값 음수 ok
			rewardScore = minus;
		}
		// System.out.println(rewardScore);	// 값 음수 ok
		
		// 3) 
		Reward reward = new Reward();
		reward.setMemNo(memNo);
		reward.setRewardScore(rewardScore);
		reward.setRewardReason(rewardReason);
		// 4)
		int result = rewardService.updateReward(reward, selectReward);
		// 5)
		if(result > 0) {
			request.getSession().setAttribute("successMsg", "리워드 업데이트가 완료되었습니다!");
		} else {
			request.getSession().setAttribute("failMsg", "Error 다시 시도해주세요!");
		}
		
		response.sendRedirect(request.getContextPath() + "/jhselect.rw?page=1");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
