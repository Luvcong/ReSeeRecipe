package com.kh.semi.reward.model.vo;

import java.sql.Date;

public class Reward {

//	REWARD_NO	NUMBER
//	REWARD_DATE	DATE
//	REWARD_REASON	VARCHAR2(500 BYTE)
//	REWARD_SCORE	NUMBER
//	MEM_NO	NUMBER
	
	private int rewardNo;			//	REWARD_NO	NUMBER
	private Date rewardDate;		//	REWARD_DATE	DATE
	private String rewardReason;	//	REWARD_REASON	VARCHAR2(500 BYTE)
	private int rewardScore;		//	REWARD_SCORE	NUMBER
	private int memNo;				//	MEM_NO	NUMBER	(관리자 리워드 리스트에 필요)
	private String memId;			//  MEM_ID			(관리자 리워드 리스트에 필요)
	private String memNickname;		//  MEM_NICKNAME	(관리자 리워드 리스트에 필요)
	private String memGradeName;	//  MEM_GRADE_NAME	(관리자 리워드 리스트에 필요)
	private int sumRewardScore;		//  누적리워드 필드 (관리자 리워드 리스트에 필요)
	private int remainRewardScore;	//  남은리워드 (회원 리워드 조회)
	private int rownum;				//  ROWNUM RNUM(조회 순서 번호)
	
	public Reward() {
		super();
	}


	public Reward(int rewardNo, Date rewardDate, String rewardReason, int rewardScore, int memNo) {
		super();
		this.rewardNo = rewardNo;
		this.rewardDate = rewardDate;
		this.rewardReason = rewardReason;
		this.rewardScore = rewardScore;
		this.memNo = memNo;
	}
	
	// 관리자 리워드 관리에 필요한 생성자
	public Reward(int rewardNo, Date rewardDate, String rewardReason, int rewardScore, int memNo, String memId,
			String memNickname, String memGradeName, int sumRewardScore) {
		super();
		this.rewardNo = rewardNo;
		this.rewardDate = rewardDate;
		this.rewardReason = rewardReason;
		this.rewardScore = rewardScore;
		this.memNo = memNo;
		this.memId = memId;
		this.memNickname = memNickname;
		this.memGradeName = memGradeName;
		this.sumRewardScore = sumRewardScore;
	}

	public int getRewardNo() {
		return rewardNo;
	}

	public void setRewardNo(int rewardNo) {
		this.rewardNo = rewardNo;
	}

	public Date getRewardDate() {
		return rewardDate;
	}

	public void setRewardDate(Date rewardDate) {
		this.rewardDate = rewardDate;
	}

	public String getRewardReason() {
		return rewardReason;
	}

	public void setRewardReason(String rewardReason) {
		this.rewardReason = rewardReason;
	}

	public int getRewardScore() {
		return rewardScore;
	}

	public void setRewardScore(int rewardScore) {
		this.rewardScore = rewardScore;
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

	public String getMemNickname() {
		return memNickname;
	}

	public void setMemNickname(String memNickname) {
		this.memNickname = memNickname;
	}

	public String getMemGradeName() {
		return memGradeName;
	}

	public void setMemGradeName(String memGradeName) {
		this.memGradeName = memGradeName;
	}
	
	public int getSumRewardScore() {
		return sumRewardScore;
	}

	public void setSumRewardScore(int sumRewardScore) {
		this.sumRewardScore = sumRewardScore;
	}

	public int getRemainRewardScore() {
		return remainRewardScore;
	}
	
	public void setRemainRewardScore(int remainRewardScore) {
		this.remainRewardScore = remainRewardScore;
	}
	
	public int getRownum() {
		return rownum;
	}
	
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	
	@Override
	public String toString() {
		return "Reward [rewardNo=" + rewardNo + ", rewardDate=" + rewardDate + ", rewardReason=" + rewardReason
				+ ", rewardScore=" + rewardScore + ", memNo=" + memNo + ", memId=" + memId + ", memNickname="
				+ memNickname + ", memGradeName=" + memGradeName + ", sumRewardScore=" + sumRewardScore + "]";
	}

	
}	// end class
