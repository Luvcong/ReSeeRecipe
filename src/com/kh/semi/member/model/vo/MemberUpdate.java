package com.kh.semi.member.model.vo;

public class MemberUpdate {
	
	private int memUpdateNo; //MEM_UPDATE_NO	NUMBER 회원업데이트PK
	private int memNo; //MEM_NO	NUMBER 회원번호
	private String memUpdateCon; //MEM_UPDATE_CON	VARCHAR2(300 BYTE) 회원정보업데이트사유
	private String memUpdateStatus; //MEM_UPDATE_STATUS	VARCHAR2(1 BYTE) 회원정보업데이트상태
	
	public MemberUpdate() {
		super();
	}
	public MemberUpdate(int memUpdateNo, int memNo, String memUpdateCon, String memUpdateStatus) {
		super();
		this.memUpdateNo = memUpdateNo;
		this.memNo = memNo;
		this.memUpdateCon = memUpdateCon;
		this.memUpdateStatus = memUpdateStatus;
	}
	public int getMemUpdateNo() {
		return memUpdateNo;
	}
	public void setMemUpdateNo(int memUpdateNo) {
		this.memUpdateNo = memUpdateNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getMemUpdateCon() {
		return memUpdateCon;
	}
	public void setMemUpdateCon(String memUpdateCon) {
		this.memUpdateCon = memUpdateCon;
	}
	public String getMemUpdateStatus() {
		return memUpdateStatus;
	}
	public void setMemUpdateStatus(String memUpdateStatus) {
		this.memUpdateStatus = memUpdateStatus;
	}
	@Override
	public String toString() {
		return "MemerUpdate [memUpdateNo=" + memUpdateNo + ", memNo=" + memNo + ", memUpdateCon=" + memUpdateCon
				+ ", memUpdateStatus=" + memUpdateStatus + "]";
	}
}
