package com.kh.semi.coupon.model.service.manager;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.common.template.Template;
import com.kh.semi.coupon.model.dao.manager.CouponDao;
import com.kh.semi.coupon.model.vo.Coupon;

public class CouponServiceImpl implements CouponService {
	
	private CouponDao couponDao;
	
	public CouponServiceImpl() {
		super();
		couponDao = new CouponDao();
	}
	
	
	// 관리자 쿠폰함 리스트 조회
	@Override
	public ArrayList<Coupon> selectCouponList(PageInfo pi) {
		
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Coupon> list = couponDao.selectCouponList(sqlSession, pi);
		
		sqlSession.close();
		
		return list;
	}	// selectCouponList
	

	// 관리자 쿠폰함 리스트 행 수 조회
	@Override
	public int selectCouponListCount() {
		
		SqlSession sqlSession = Template.getSqlSession();
		int result = couponDao.selectCouponListCount(sqlSession);
		
		sqlSession.close();
		
		return result;
	}	// selectCouponListCount
	
	
	// 관리자 쿠폰함 리스트 - 쿠폰 등록 요청
	@Override
	public int insertCoupon(Coupon coupon) {
		
		SqlSession sqlSession = Template.getSqlSession();
		int result = couponDao.insertCoupon(sqlSession, coupon);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
				
	}	// insertCoupon
	
	
	// 관리자 쿠폰함 리스트 - 쿠폰 삭제 요청
	@Override
	public int deleteCoupon(int couponNo) {
		
		SqlSession sqlSession = Template.getSqlSession();
		int result = couponDao.deleteCoupon(sqlSession, couponNo);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}	// deleteCoupon
	

}	// end class
