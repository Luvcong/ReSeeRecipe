package com.kh.semi.notice.model.vo;

import java.sql.Date;

public class NoticePic {
	
	private int noticePicNo; //NOTICE_PIC_NO	NUMBER
	private String noticePicNamgeOrigin; //NOTICE_PIC_NAME_ORIGIN	VARCHAR2(255 BYTE)
	private String noticePicNagmeChange; //NOTICE_PIC_NAME_CHANGE	VARCHAR2(255 BYTE)
	private String noticePicPath; //NOTICE_PIC_PATH	VARCHAR2(1000 BYTE)
	private Date noticePicDate; //NOTICE_PIC_DATE	DATE
	private int noticePicLev; //NOTICE_PIC_LEV	NUMBER
	private String noticePicStatus; //NOTICE_PIC_STATUS	VARCHAR2(1 BYTE)
	private int noticeNo; //NOTICE_NO	NUMBER
	
	public NoticePic() {
		super();
	}

	public NoticePic(int noticePicNo, String noticePicNamgeOrigin, String noticePicNagmeChange, String noticePicPath,
			Date noticePicDate, int noticePicLev, String noticePicStatus, int noticeNo) {
		super();
		this.noticePicNo = noticePicNo;
		this.noticePicNamgeOrigin = noticePicNamgeOrigin;
		this.noticePicNagmeChange = noticePicNagmeChange;
		this.noticePicPath = noticePicPath;
		this.noticePicDate = noticePicDate;
		this.noticePicLev = noticePicLev;
		this.noticePicStatus = noticePicStatus;
		this.noticeNo = noticeNo;
	}

	public int getNoticePicNo() {
		return noticePicNo;
	}

	public void setNoticePicNo(int noticePicNo) {
		this.noticePicNo = noticePicNo;
	}

	public String getNoticePicNamgeOrigin() {
		return noticePicNamgeOrigin;
	}

	public void setNoticePicNamgeOrigin(String noticePicNamgeOrigin) {
		this.noticePicNamgeOrigin = noticePicNamgeOrigin;
	}

	public String getNoticePicNagmeChange() {
		return noticePicNagmeChange;
	}

	public void setNoticePicNagmeChange(String noticePicNagmeChange) {
		this.noticePicNagmeChange = noticePicNagmeChange;
	}

	public String getNoticePicPath() {
		return noticePicPath;
	}

	public void setNoticePicPath(String noticePicPath) {
		this.noticePicPath = noticePicPath;
	}

	public Date getNoticePicDate() {
		return noticePicDate;
	}

	public void setNoticePicDate(Date noticePicDate) {
		this.noticePicDate = noticePicDate;
	}

	public int getNoticePicLev() {
		return noticePicLev;
	}

	public void setNoticePicLev(int noticePicLev) {
		this.noticePicLev = noticePicLev;
	}

	public String getNoticePicStatus() {
		return noticePicStatus;
	}

	public void setNoticePicStatus(String noticePicStatus) {
		this.noticePicStatus = noticePicStatus;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	@Override
	public String toString() {
		return "NoticePic [noticePicNo=" + noticePicNo + ", noticePicNamgeOrigin=" + noticePicNamgeOrigin
				+ ", noticePicNagmeChange=" + noticePicNagmeChange + ", noticePicPath=" + noticePicPath
				+ ", noticePicDate=" + noticePicDate + ", noticePicLev=" + noticePicLev + ", noticePicStatus="
				+ noticePicStatus + ", noticeNo=" + noticeNo + "]";
	}
	
}
