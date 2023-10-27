package com.kh.semi.member.model.vo;

import java.sql.Date;

public class Member {
	
	// VO생성 yr
	
//	MEM_NO	NUMBER
//	MEM_ID	VARCHAR2(15 BYTE)
//	MEM_PWD	VARCHAR2(20 BYTE)
//	MEM_NAME	VARCHAR2(20 BYTE)
//	MEM_NICKNAME	VARCHAR2(25 BYTE)
//	MEM_EMAIL	VARCHAR2(50 BYTE)
//	MEM_STATUS	VARCHAR2(1 BYTE)
//	ENROLL_DATE	DATE
//	MODIFY_DATE	DATE
//	DELETE_DATE	DATE
//	MEM_PICTURE	VARCHAR2(1500 BYTE)
//	MEM_GRADE	NUMBER
	
	private int memNo;
	private String memId;
	private String memPwd;
	private String memName;
	private String memNickname;
	private String memEmail;
	private String memStatus;
	private Date enrollDate;
	private Date modifyDate;
	private Date deleteDate;
	private String memPicture;
	private int memGrade;
	private String memGradeName;
	private int memReward;
	private String memUpdateWhyCon;
	private int memCouponCount;
	


	public Member() {
		super();
	}
	
	public Member(int memNo, String memId, String memPwd, String memName, String memNickname, String memEmail,
			String memStatus, Date enrollDate, Date modifyDate, Date deleteDate, String memPicture, int memGrade) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memNickname = memNickname;
		this.memEmail = memEmail;
		this.memStatus = memStatus;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.deleteDate = deleteDate;
		this.memPicture = memPicture;
		this.memGrade = memGrade;
	}
	
	// 로그인 시 사용
	public Member(int memNo, String memId, String memPwd, String memName, String memNickname, String memEmail,String memStatus, 
			Date enrollDate, Date modifyDate, Date deleteDate, String memPicture, int memGrade, String memGradeName, int memCouponCount, int memReward) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memNickname = memNickname;
		this.memEmail = memEmail;
		this.memStatus = memStatus;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.deleteDate = deleteDate;
		this.memPicture = memPicture;
		this.memGrade = memGrade;
		this.memGradeName = memGradeName;
		this.memCouponCount = memCouponCount;
		this.memReward = memReward;
	}
	
	
	public Member(int memNo, String memId, String memPwd, String memName, String memNickname, String memEmail,
			String memStatus, Date enrollDate, Date modifyDate, Date deleteDate, String memPicture, int memGrade,
			int memReward) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memNickname = memNickname;
		this.memEmail = memEmail;
		this.memStatus = memStatus;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.deleteDate = deleteDate;
		this.memPicture = memPicture;
		this.memGrade = memGrade;
		this.memReward = memReward;
	}
	
	
	
	public Member(int memNo, String memId, String memName, String memNickname, String memEmail, Date enrollDate,
			String memGradeName) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memName = memName;
		this.memNickname = memNickname;
		this.memEmail = memEmail;
		this.enrollDate = enrollDate;
		this.memGradeName = memGradeName;
	}

	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPwd() {
		return memPwd;
	}
	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemNickname() {
		return memNickname;
	}
	public void setMemNickname(String memNickname) {
		this.memNickname = memNickname;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(String memStatus) {
		this.memStatus = memStatus;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getMemPicture() {
		return memPicture;
	}
	public void setMemPicture(String memPicture) {
		this.memPicture = memPicture;
	}
	public int getMemGrade() {
		return memGrade;
	}
	public void setMemGrade(int memGrade) {
		this.memGrade = memGrade;
	}
	public int getMemReward() {
		return memReward;
	}

	public void setMemReward(int memReward) {
		this.memReward = memReward;
	}
	
	
	public String getMemGradeName() {
		return memGradeName;
	}

	public void setMemGradeName(String memGradeName) {
		this.memGradeName = memGradeName;
	}

	
	public String getMemUpdateWhyCon() {
		return memUpdateWhyCon;
	}

	public void setMemUpdateWhyCon(String memUpdateWhyCon) {
		this.memUpdateWhyCon = memUpdateWhyCon;
	}

	public int getMemCouponCount() {
		return memCouponCount;
	}
	
	public void setMemCouponCount(int memCouponCount) {
		this.memCouponCount = memCouponCount;
	}
	
	@Override
	public String toString() {
		return "Member [memNo=" + memNo + ", memId=" + memId + ", memPwd=" + memPwd + ", memName=" + memName
				+ ", memNickname=" + memNickname + ", memEmail=" + memEmail + ", memStatus=" + memStatus
				+ ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", deleteDate=" + deleteDate
				+ ", memPicture=" + memPicture + ", memGrade=" + memGrade + "]";
	}
	
	
	
}
