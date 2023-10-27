package com.kh.semi.myPage.model.vo;

import java.sql.Date;

public class MemberCoupon {
	
//	MEM_NO	NUMBER
//	COUPON_NO	NUMBER
//	MEMBER_COUPON_DATE	DATE
//	MEMBER_COUPON_STATUS	VARCHAR2(1 BYTE)
	
	private int memNo; // FK
	private int couponNo; // PK
	private Date memberCouponDate; // 지급일 (최근순)
	private String memberCouponStatus; // 사용여부
	
	private int couponExpire; // 만료일
	private int couponRatio; // 할인율(할인순)
	private String couponName; // 쿠폰이름
	private Date couponEnddate; // 만료기한(만료기한순)
	
	
	

	public MemberCoupon() {
		super();
	}
	
	public MemberCoupon(int couponExpire, int couponRatio, String couponName) {
		super();
		this.couponExpire = couponExpire;
		this.couponRatio = couponRatio;
		this.couponName = couponName;
	}

	public MemberCoupon(int memNo, int couponNo, Date memberCouponDate, String memberCouponStatus, int couponRatio,
			String couponName, Date couponEnddate) {
		super();
		this.memNo = memNo;
		this.couponNo = couponNo;
		this.memberCouponDate = memberCouponDate;
		this.memberCouponStatus = memberCouponStatus;
		this.couponRatio = couponRatio;
		this.couponName = couponName;
		this.couponEnddate = couponEnddate;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}

	public Date getMemberCouponDate() {
		return memberCouponDate;
	}

	public void setMemberCouponDate(Date memberCouponDate) {
		this.memberCouponDate = memberCouponDate;
	}

	public String getMemberCouponStatus() {
		return memberCouponStatus;
	}

	public void setMemberCouponStatus(String memberCouponStatus) {
		this.memberCouponStatus = memberCouponStatus;
	}
	
	public int getCouponExpire() {
		return couponExpire;
	}
	
	public void setCouponExpire(int couponExpire) {
		this.couponExpire = couponExpire;
	}
	
	public int getCouponRatio() {
		return couponRatio;
	}

	public void setCouponRatio(int couponRatio) {
		this.couponRatio = couponRatio;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Date getCouponEnddate() {
		return couponEnddate;
	}

	public void setCouponEnddate(Date couponEnddate) {
		this.couponEnddate = couponEnddate;
	}

	@Override
	public String toString() {
		return "MemberCoupon [memNo=" + memNo + ", couponNo=" + couponNo + ", memberCouponDate=" + memberCouponDate
				+ ", memberCouponStatus=" + memberCouponStatus + ", couponExpire=" + couponExpire + ", couponRatio="
				+ couponRatio + ", couponName=" + couponName + ", couponEnddate=" + couponEnddate + "]";
	}
	
	
	
	

}
