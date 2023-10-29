package com.kh.semi.reward.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.common.template.Template;
import com.kh.semi.reward.model.dao.RewardDao;
import com.kh.semi.reward.model.vo.Reward;

public class RewardServiceImpl implements RewardService {

	private RewardDao rewardDao;
	
	public RewardServiceImpl() {
		rewardDao = new RewardDao();
	}
	
	
	// 등록되어 있는 리워드 리스트 조회
	@Override
	public ArrayList<Reward> selectRewardList(PageInfo pi) {
		
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Reward> list = rewardDao.selectRewardList(sqlSession, pi);

		sqlSession.close();
		
		return list;
	}	// selectRewardList
	
	
	// 리워드 리스트 카운트 행 수 조회
	@Override
	public int selectRewardListCount() {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int listCount = rewardDao.selectRewardListCount(sqlSession);
		
		sqlSession.close();
		
		return listCount;
	}	// selectRewardListCount
	
	
	// 회원 리워드 포인트 지급 및 차감
	@Override
	public int insertReward(Reward reward) {
		
		SqlSession sqlSession = Template.getSqlSession();
		int result = rewardDao.insertReward(sqlSession, reward);
		
		if(result > 0) sqlSession.commit();
		
		return result;
	}	// updateReward
	
	
//	/**
//	 * 회원 리워드 포인트를 지급 및 차감하는 업데이트 요청 method
//	 * @param reward 회원아이디(memId), 리워드사유(rewardReason), 리워드금액(rewardScore)
//	 * @param selectReward 지급(plusReward) or 차감(minusReward)
//	 * @return 회원 리워드 지급 및 차감 성공 여부
//	 * @author JH
//	 * @Date : 2023. 10. 13.
//	 */
//	public int updateReward(Reward reward, String selectReward) {
//		
//		Connection conn = getConnection();
//		
//		// selectReward value : plusReward  > 값을 더해주고,
//		// 					  : minusReward > 빼주어야 한다
//		
//		int result = rewardDao.updateReward(conn, reward);
//		
//		if(result > 0) commit(conn);
//			else rollback(conn);
//		
//		close(conn);
//		
//		return result;
//	}	// updateReward
//	
}	// end class
