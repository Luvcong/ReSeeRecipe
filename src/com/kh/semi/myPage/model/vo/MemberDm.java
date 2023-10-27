package com.kh.semi.myPage.model.vo;

import java.sql.Date;

public class MemberDm {
	
	private int dmNo;
	private String receiveMem;
	private String sendMem;
	private String dmContent;
	private Date sendDate;
	private String dmStatus;
	private String dmReply;
	
	
	public MemberDm() {
		super();
	}


	public MemberDm(int dmNo, String receiveMem, String sendMem, String dmContent, Date sendDate, String dmStatus,
			String dmReply) {
		super();
		this.dmNo = dmNo;
		this.receiveMem = receiveMem;
		this.sendMem = sendMem;
		this.dmContent = dmContent;
		this.sendDate = sendDate;
		this.dmStatus = dmStatus;
		this.dmReply = dmReply;
	}


	public int getDmNo() {
		return dmNo;
	}


	public void setDmNo(int dmNo) {
		this.dmNo = dmNo;
	}


	public String getReceiveMem() {
		return receiveMem;
	}


	public void setReceiveMem(String receiveMem) {
		this.receiveMem = receiveMem;
	}


	public String getSendMem() {
		return sendMem;
	}


	public void setSendMem(String sendMem) {
		this.sendMem = sendMem;
	}


	public String getDmContent() {
		return dmContent;
	}


	public void setDmContent(String dmContent) {
		this.dmContent = dmContent;
	}


	public Date getSendDate() {
		return sendDate;
	}


	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}


	public String getDmStatus() {
		return dmStatus;
	}


	public void setDmStatus(String dmStatus) {
		this.dmStatus = dmStatus;
	}


	public String getDmReply() {
		return dmReply;
	}


	public void setDmReply(String dmReply) {
		this.dmReply = dmReply;
	}


	@Override
	public String toString() {
		return "MemberDm [dmNo=" + dmNo + ", receiveMem=" + receiveMem + ", sendMem=" + sendMem + ", dmContent="
				+ dmContent + ", sendDate=" + sendDate + ", dmStatus=" + dmStatus + ", dmReply=" + dmReply + "]";
	}
	
	

	
	
}


