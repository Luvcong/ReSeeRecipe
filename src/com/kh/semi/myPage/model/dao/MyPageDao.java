package com.kh.semi.myPage.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.semi.myPage.model.vo.MemberCoupon;
import com.kh.semi.reward.model.vo.Reward;

public class MyPageDao {
	
	private Properties prop = new Properties();
	
	public MyPageDao() {
		
		String file = MyPageDao.class.getResource("/sql/myPage/myPage-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	// 회원 쿠폰 조회
	public ArrayList<MemberCoupon> selectMemberCouponList(Connection conn, int memberNo, String selected){
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MemberCoupon> list = new ArrayList();
		
		StringBuilder sql = new StringBuilder();
		sql.append(prop.getProperty("selectMemberCouponList"));
		
		// 각 정렬에 따라 sql을 구분함
		if("saleSort".equals(selected)) {
			sql.append("COUPON_RATIO DESC");
		} else if("limitSort".equals(selected)) {
			sql.append("COUPON_ENDDATE ASC");
		} else {
			sql.append("MEMBER_COUPON_DATE DESC");
		}
		
		// String sql = sql.toString();
		
		/*
		String sql = prop.getProperty("selectMemberCouponList");
		if("saleSort".equals(selected)) {
			sql += "COUPON_RATIO DESC";
		} else if("limitSort".equals(selected)) {
			sql += "COUPON_ENDDATE ASC";
		} else {
			sql += "MEMBER_COUPON_DATE DESC";
		}
		*/
		
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new MemberCoupon(rset.getInt("COUPON_EXPIRE"),
										  rset.getInt("COUPON_RATIO"),
										  rset.getString("COUPON_NAME")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 회원 리워드 내역 조회
	public ArrayList<Reward> selectMemberRewardList(Connection conn, int memberNo){
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Reward> list = new ArrayList();
		String sql = prop.getProperty("selectMemberRewardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				// 생성자 또 만들기 싫음
				Reward reward = new Reward();
				reward.setRownum(rset.getInt("RNUM"));
				reward.setRewardDate(rset.getDate("REWARD_DATE"));
				reward.setRewardReason(rset.getString("REWARD_REASON"));
				reward.setRewardScore(rset.getInt("REWARD_SCORE"));
				reward.setRemainRewardScore(rset.getInt("REMAIN_REWARD_SCORE"));
				
				list.add(reward);
				
				/*
				list.add(new Reward(rset.getInt("RNUM"),
									rset.getDate("REWARD_DATE"),
									rset.getString("REWARD_REASON"),
									rset.getInt("REWARD_SCORE"),
									rset.getInt("REMAIN_REWARD_SCORE")));
				*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	


}
