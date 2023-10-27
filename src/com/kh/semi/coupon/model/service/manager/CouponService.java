package com.kh.semi.coupon.model.service.manager;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.coupon.model.dao.manager.CouponDao;
import com.kh.semi.coupon.model.vo.Coupon;

public class CouponService {

	private CouponDao couponDao;
	
	public CouponService() {
		super();
		couponDao = new CouponDao();
	}	// CouponService
	
	
	/**
	 * 관리자 쿠폰함 리스트 조회 요청
	 * @param pi
	 * @return 등록되어 있는 쿠폰함 리스트
	 * @author JH
	 * @Date : 2023. 10. 17.
	 */
	public ArrayList<Coupon> selectCouponList(PageInfo pi){
		
		Connection conn = getConnection();
		
		ArrayList<Coupon> list = couponDao.selectCouponList(conn, pi);
		
		close(conn);
		
		return list;
		
	}	// selectCouponList
	
	/**
	 * 관리자 쿠폰함 리스트 카운트 행 수 조회 요청
	 * @return DB에 저장되어 있는 쿠폰함 리스트 수
	 * @author JH
	 * @Date : 2023. 10. 17.
	 */
	public int selectCouponListCount(){
		
		Connection conn = getConnection();
		
		int couponListCount = couponDao.selectCouponListCount(conn);
		
		close(conn);
		
		return couponListCount; 
		
	}	// selectCouponListCount
	
	
	/**
	 * 관리자 쿠폰함 리스트 - 쿠폰 등록 요청
	 * @param coupon couponName(쿠폰이름) / startCoupon(쿠폰시작일) / endCoupon(쿠폰등록일) / couponReason(쿠폰등록사유) / couponRatio (쿠폰할인율)
	 * @return 쿠폰 등록 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 18.
	 */
	
	public int insertCoupon(Coupon coupon) {
		
		Connection conn = getConnection();
		
		int result = couponDao.insertCoupon(conn, coupon);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		
		return result;
		
	}	// insertCoupon
	
	
	/**
	 * 관리자 쿠폰함 리스트 - 쿠폰 삭제 요청
	 * @param categoryNo 쿠폰 번호 SEQ
	 * @return 쿠폰 삭제 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 19.
	 */
	public int deleteCoupon(int couponNo) {
		
		Connection conn = getConnection();
		
		int result = couponDao.deleteCoupon(conn, couponNo);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		
		return result;
	}	// deleteCoupon
	
	
	
}	// end class
