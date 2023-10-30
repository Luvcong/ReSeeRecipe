package com.kh.semi.coupon.model.dao.manager;

import static com.kh.semi.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.coupon.model.vo.Coupon;

public class CouponDao {
	
	
	/**
	 * 관리자 쿠폰함 리스트 조회 요청건 처리
	 * @param sqlSession
	 * @param pi PageInfo
	 * @return 등록되어 있는 쿠폰함 리스트 
	 * @author JH
	 * @Date : 2023. 10. 17.
	 * @Update : 2023. 10. 28.
	 */
	public ArrayList<Coupon> selectCouponList(SqlSession sqlSession, PageInfo pi){
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return (ArrayList)sqlSession.selectList("couponMapper.selectCouponList", null, rowBounds);
		
	}	// selectCouponList
	
	
	/**
	 * 쿠폰함 리스트 카운트 행 수 조회 요청건 처리
	 * @param sqlSession
	 * @return DB에 저장되어 있는 쿠폰함 리스트 수
	 * @author JH
	 * @Date : 2023. 10. 17.
	 * @Update : 2023. 10. 28.
	 */
	public int selectCouponListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("couponMapper.selectCouponListCount");
		
	}	// selectCouponListCount
	
	
	/**
	 * 관리자 쿠폰함 리스트 - 쿠폰 등록 요청
	 * @param sqlSession
	 * @param coupon couponName(쿠폰이름) / startCoupon(쿠폰시작일) / endCoupon(쿠폰등록일) / couponReason(쿠폰등록사유) / couponRatio (쿠폰할인율)
	 * @return 쿠폰 등록 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 18.
	 * @Update : 2023. 10. 28.
	 */
	public int insertCoupon(SqlSession sqlSession, Coupon coupon) {
		
		return sqlSession.insert("couponMapper.insertCoupon", coupon);
		
	}	// insertCoupon
	
	
	/**
	 * 관리자 쿠폰함 리스트 - 쿠폰 삭제 요청
	 * @param sqlSession
	 * @param categoryNo 쿠폰 번호 SEQ
	 * @return 쿠폰 삭제 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 19.
	 * @Update : 2023. 10. 28.
	 */
	public int deleteCoupon(SqlSession sqlSession, int couponNo) {
		return sqlSession.delete("couponMapper.deleteCoupon", couponNo);
		
	}	// deleteCoupon
	
	
}	// end class
