package com.kh.semi.notice.model.vo;

import java.sql.Date;

public class Notice {
	
	private int noticeNo; //NOTICE_NO	NUMBER
	private String noticeTitle; //NOTICE_TITLE	VARCHAR2(50 BYTE)
	private String noticeCon; //NOTICE_CON	VARCHAR2(1000 BYTE)
	private Date noticeDate; //NOTICE_DATE	DATE
	private Date noticeUpdate; //NOTICE_UPDATE	DATE
	private String noticeStatus; //NOTICE_STATUS	VARCHAR2(1 BYTE)
	private int noticeCount; //NOTICE_COUNT	VARCHAR2(255 BYTE)	--> Number
	private String noticeWriter; //NOTICE_WRITER	NUMBER
	
	private String noticeWriterName;
	private int noticeHeart;
	
	
	
	public Notice() {
		super();
	}

	public Notice(int noticeNo, String noticeTitle, String noticeCon, Date noticeDate, Date noticeUpdate,
			String noticeStatus, int noticeCount, String noticeWriter) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeCon = noticeCon;
		this.noticeDate = noticeDate;
		this.noticeUpdate = noticeUpdate;
		this.noticeStatus = noticeStatus;
		this.noticeCount = noticeCount;
		this.noticeWriter = noticeWriter;
	}
	
	
	public Notice(int noticeNo, String noticeTitle, String noticeCon, Date noticeDate, Date noticeUpdate,
			String noticeStatus, int noticeCount, String noticeWriter, String noticeWriterName, int noticeHeart) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeCon = noticeCon;
		this.noticeDate = noticeDate;
		this.noticeUpdate = noticeUpdate;
		this.noticeStatus = noticeStatus;
		this.noticeCount = noticeCount;
		this.noticeWriter = noticeWriter;
		this.noticeWriterName = noticeWriterName;
		this.noticeHeart = noticeHeart;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeCon() {
		return noticeCon;
	}

	public void setNoticeCon(String noticeCon) {
		this.noticeCon = noticeCon;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public Date getNoticeUpdate() {
		return noticeUpdate;
	}

	public void setNoticeUpdate(Date noticeUpdate) {
		this.noticeUpdate = noticeUpdate;
	}

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public int getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	

	public String getNoticeWriterName() {
		return noticeWriterName;
	}

	public void setNoticeWriterName(String noticeWriterName) {
		this.noticeWriterName = noticeWriterName;
	}

	public int getNoticeHeart() {
		return noticeHeart;
	}

	public void setNoticeHeart(int noticeHeart) {
		this.noticeHeart = noticeHeart;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeCon=" + noticeCon
				+ ", noticeDate=" + noticeDate + ", noticeUpdate=" + noticeUpdate + ", noticeStatus=" + noticeStatus
				+ ", noticeCount=" + noticeCount + ", noticeWriter=" + noticeWriter + "]";
	}
	
	
	
}
