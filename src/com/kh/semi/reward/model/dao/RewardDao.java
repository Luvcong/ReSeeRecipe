package com.kh.semi.reward.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.reward.model.vo.Reward;

public class RewardDao {
	
	/**
	 * 등록되어 있는 리워드 리스트를 조회 처리
	 * @param sqlSession
	 * @return 등록되어 있는 리워드 리스트
	 * @author JH
	 * @Date : 2023. 10. 12.
	 * @Update : 2023. 10. 28.
	 */
	public ArrayList<Reward> selectRewardList(SqlSession sqlSession, PageInfo pi){
		
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit()); 
		
		return (ArrayList)sqlSession.selectList("rewardMapper.selectRewardList", null, rowBounds);
		
	}	// selectRewardList

	
	/**
	 * 리워드 리스트 카운트 행 수 조회 요청
	 * @param sqlSession
	 * @return DB에 저장되어 있는 리워드 리스트 수
	 * @author JH
	 * @Date : 2023. 10. 12.
	 * @Update : 2023. 10. 28.
	 */
	public int selectRewardListCount(SqlSession sqlSession) {
		
		return sqlSession.selectOne("rewardMapper.selectRewardListCount");
		
	}	// selectRewardListCount
	
	
	/**
	 * 회원 리워드 포인트를 지급 및 차감
	 * @param sqlSession
	 * @param reward 회원아이디(memId), 리워드사유(rewardReason), 리워드금액(rewardScore)
	 * @param searchReward 지급(plusReward) or 차감(minusReward)
	 * @return 회원 리워드 지급 및 차감 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 13.
	 * @Update : 2023. 10. 28.
	 */
	public int insertReward(SqlSession sqlSession, Reward reward) {
		
		return sqlSession.insert("rewardMapper.insertReward", reward);
	}	// updateReward
	
	
	
}	// end class
