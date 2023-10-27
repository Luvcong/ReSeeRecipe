package com.kh.semi.common.heart.model.vo;

/**
 * @author JY
 * 
 * Heart기능들 공통 VO
 * 특정 레시피글, 댓글, 북마크, 공지사항글, 셰프구독의 하트(좋아요)
 * 
 * memNo 	  : 하트 누른 회원 PK번호
 * htTargetNo : 하트 대상 PK번호 (RECIPE_NO, REPLY_NO 등)
 * htDate 	  : 하트 누른 날짜 (정렬기준용)
 * htCount    : 하트 개수
 */
public class Heart {

	
	private int memNo;
	private int htTargetNo;
	private String htDate;
	private String htKind;
	private String htCount;
	
	
	public Heart() {
		super();
	}
	
	public Heart(int memNo, int htTargetNo) {
		super();
		this.memNo = memNo;
		this.htTargetNo = htTargetNo;
	}

	public Heart(int memNo, int htTargetNo, String htDate, String htKind, String htCount) {
		super();
		this.memNo = memNo;
		this.htTargetNo = htTargetNo;
		this.htDate = htDate;
		this.htKind = htKind;
		this.htCount = htCount;
	}


	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getHtTargetNo() {
		return htTargetNo;
	}

	public void setHtTargetNo(int htTargetNo) {
		this.htTargetNo = htTargetNo;
	}

	public String getHtDate() {
		return htDate;
	}

	public void setHtDate(String htDate) {
		this.htDate = htDate;
	}

	public String getHtKind() {
		return htKind;
	}

	public void setHtKind(String htKind) {
		this.htKind = htKind;
	}

	public String getHtCount() {
		return htCount;
	}

	public void setHtCount(String htCount) {
		this.htCount = htCount;
	}


	@Override
	public String toString() {
		return "Heart [memNo=" + memNo + ", htTargetNo=" + htTargetNo + ", htDate=" + htDate + ", htKind=" + htKind
				+ ", htCount=" + htCount + "]";
	}
	
	
}//end