package com.kh.semi.coupon.model.dao.manager;

import static com.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.coupon.model.vo.Coupon;

public class CouponDao {
	
	private Properties prop = new Properties();
	
	public CouponDao() {
		String file = CouponDao.class.getResource("/sql/coupon/coupon-mapper.xml").getPath();
	
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	// CouponDao
	
	
	/**
	 * 관리자 쿠폰함 리스트 조회 요청건 처리
	 * @param conn
	 * @param pi PageInfo
	 * @return 등록되어 있는 쿠폰함 리스트 
	 * @author JH
	 * @Date : 2023. 10. 17.
	 */
	public ArrayList<Coupon> selectCouponList(Connection conn, PageInfo pi){
		
		ArrayList<Coupon> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCouponList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Coupon coupon = new Coupon();
				coupon.setCouponNo(rset.getInt("COUPON_NO"));						// 쿠폰 no
				coupon.setCouponName(rset.getString("COUPON_NAME"));				// 쿠폰 이름
				coupon.setCouponRatio(rset.getInt("COUPON_RATIO"));					// 쿠폰 할인율
				coupon.setCouponStartdate(rset.getDate("COUPON_STARTDATE"));		// 쿠폰 시작일
				coupon.setCouponEndDate(rset.getDate("COUPON_ENDDATE"));			// 쿠폰 종료일
				coupon.setIssueCouponCount(rset.getInt("ISSUE_COUPON_CNT"));		// 쿠폰 발급 수
				coupon.setUsesCouponCount(rset.getInt("USES_COUPON_CNT"));			// 쿠폰 사용 수
				coupon.setCouponAvail(rset.getString("COUPON_AVAIL"));				// 쿠폰 상태 (발급시Y / 미발급시N)
				coupon.setCouponReason(rset.getString("COUPON_REASON"));			// 쿠폰 등록 사유
				
				list.add(coupon);
				// System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}	// selectCouponList
	
	
	/**
	 * 쿠폰함 리스트 카운트 행 수 조회 요청건 처리
	 * @return DB에 저장되어 있는 쿠폰함 리스트 수
	 * @author JH
	 * @Date : 2023. 10. 17.
	 */
	public int selectCouponListCount(Connection conn) {
		
		int couponListCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCouponListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				couponListCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return couponListCount;
		
	}	// selectCouponListCount
	
	
	/**
	 * 관리자 쿠폰함 리스트 - 쿠폰 등록 요청
	 * @param conn
	 * @param coupon couponName(쿠폰이름) / startCoupon(쿠폰시작일) / endCoupon(쿠폰등록일) / couponReason(쿠폰등록사유) / couponRatio (쿠폰할인율)
	 * @return 쿠폰 등록 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 18.
	 */
	public int insertCoupon(Connection conn, Coupon coupon) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCoupon");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, coupon.getCouponName());
			pstmt.setInt(2, coupon.getCouponRatio());
			pstmt.setString(3, coupon.getCouponReason());
			pstmt.setString(4, coupon.getStartCoupon());
			pstmt.setString(5, coupon.getEndCoupon());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}	// insertCoupon
	
	
	/**
	 * 관리자 쿠폰함 리스트 - 쿠폰 삭제 요청
	 * @param conn
	 * @param categoryNo 쿠폰 번호 SEQ
	 * @return 쿠폰 삭제 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 19.
	 */
	public int deleteCoupon(Connection conn, int couponNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCoupon");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, couponNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}	// deleteCoupon
	
	
	
	
	
	
	
	
	
	
	
}	// end class
