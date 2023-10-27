package com.kh.semi.coupon.model.vo;

import java.sql.Date;

public class Coupon {
	
//	COUPON_NO	NUMBER
//	COUPON_NAME	VARCHAR2(30 BYTE)
//	COUPON_RATIO	NUMBER
//	COUPON_AVAIL	VARCHAR2(1 BYTE)
//	COUPON_REASON	VARCHAR2(500 BYTE)
//	COUPON_STARTDATE	DATE
//	COUPON_ENDDATE	DATE
	
	private int couponNo;
	private String couponName;
	private int couponRatio;
	private String couponAvail;
	private String couponReason;
	private Date couponStartdate;
	private Date couponEndDate;
	private int issueCouponCount;	// 관리자 쿠폰함 리스트 조회시 필요 (쿠폰 발급수)
	private int usesCouponCount;	// 관리자 쿠폰함 리스트 조회시 필요 (쿠폰 사용수)
	private String startCoupon;
	private String endCoupon;
	
	public Coupon() {
		super();
	}

	public Coupon(int couponNo, String couponName, int couponRatio, String couponAvail, String couponReason,
			Date couponStartdate, Date couponEndDate, int issueCouponCount, int usesCouponCount) {
		super();
		this.couponNo = couponNo;
		this.couponName = couponName;
		this.couponRatio = couponRatio;
		this.couponAvail = couponAvail;
		this.couponReason = couponReason;
		this.couponStartdate = couponStartdate;
		this.couponEndDate = couponEndDate;
		this.issueCouponCount = issueCouponCount;
		this.usesCouponCount = usesCouponCount;
	}
	

	public Coupon(int couponNo, String couponName, int couponRatio, String couponAvail, String couponReason,
			Date couponStartdate, Date couponEndDate, int issueCouponCount, int usesCouponCount, String startCoupon,
			String endCoupon) {
		super();
		this.couponNo = couponNo;
		this.couponName = couponName;
		this.couponRatio = couponRatio;
		this.couponAvail = couponAvail;
		this.couponReason = couponReason;
		this.couponStartdate = couponStartdate;
		this.couponEndDate = couponEndDate;
		this.issueCouponCount = issueCouponCount;
		this.usesCouponCount = usesCouponCount;
		this.startCoupon = startCoupon;
		this.endCoupon = endCoupon;
	}

	public int getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public int getCouponRatio() {
		return couponRatio;
	}

	public void setCouponRatio(int couponRatio) {
		this.couponRatio = couponRatio;
	}

	public String getCouponAvail() {
		return couponAvail;
	}

	public void setCouponAvail(String couponAvail) {
		this.couponAvail = couponAvail;
	}

	public String getCouponReason() {
		return couponReason;
	}

	public void setCouponReason(String couponReason) {
		this.couponReason = couponReason;
	}

	public Date getCouponStartdate() {
		return couponStartdate;
	}

	public void setCouponStartdate(Date couponStartdate) {
		this.couponStartdate = couponStartdate;
	}

	public Date getCouponEndDate() {
		return couponEndDate;
	}

	public void setCouponEndDate(Date couponEndDate) {
		this.couponEndDate = couponEndDate;
	}

	public int getIssueCouponCount() {
		return issueCouponCount;
	}

	public void setIssueCouponCount(int issueCouponCount) {
		this.issueCouponCount = issueCouponCount;
	}

	public int getUsesCouponCount() {
		return usesCouponCount;
	}

	public void setUsesCouponCount(int usesCouponCount) {
		this.usesCouponCount = usesCouponCount;
	}
	

	public String getStartCoupon() {
		return startCoupon;
	}

	public void setStartCoupon(String startCoupon) {
		this.startCoupon = startCoupon;
	}

	public String getEndCoupon() {
		return endCoupon;
	}

	public void setEndCoupon(String endCoupon) {
		this.endCoupon = endCoupon;
	}

	@Override
	public String toString() {
		return "Coupon [couponNo=" + couponNo + ", couponName=" + couponName + ", couponRatio=" + couponRatio
				+ ", couponAvail=" + couponAvail + ", couponReason=" + couponReason + ", couponStartdate="
				+ couponStartdate + ", couponEndDate=" + couponEndDate + ", issueCouponCount=" + issueCouponCount
				+ ", usesCouponCount=" + usesCouponCount + ", startCoupon=" + startCoupon + ", endCoupon=" + endCoupon
				+ "]";
	}

	
}	// end class
