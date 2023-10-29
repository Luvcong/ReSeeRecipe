package com.kh.semi.reward.model.service;

import java.util.ArrayList;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.reward.model.vo.Reward;

public interface RewardService {
	
	/**
	 * 등록되어 있는 리워드 리스트 조회
	 * @return 등록되어 있는 리워드 리스트
	 * @author JH
	 * @Date : 2023. 10. 12.
	 * @Update : 2023. 10. 28
	 */
	public ArrayList<Reward> selectRewardList(PageInfo pi);

	
	/**
	 * 리워드 리스트 카운트 행 수 조회
	 * @return DB에 저장되어 있는 리워드 리스트 수
	 * @author JH
	 * @Date : 2023. 10. 12.
	 * @Update : 2023. 10. 28
	 */
	public int selectRewardListCount();
		
	
	/**
	 * 회원 리워드 포인트 지급 및 차감
	 * @param reward 회원아이디(memId), 리워드사유(rewardReason), 리워드금액(rewardScore)
	 * @param selectReward 지급(plusReward) or 차감(minusReward)
	 * @return 회원 리워드 지급 및 차감 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 13.
	 * @Update : 2023. 10. 28
	 */
	public int insertReward(Reward reward);

	
	
}	// end class
