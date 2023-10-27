package com.kh.semi.report.model.vo;

import java.sql.Date;

public class Report {

//	REPORT_NO	NUMBER
//	RPT_CONTENT	VARCHAR2(500 BYTE)
//	RPT_DATE	DATE
//	RPT_TARGET	NUMBER
//	RECIVE_REPORT	NUMBER
//	SEND_REPORT	NUMBER
//	RPT_CATEGORY_NO	NUMBER
//	RPT_STATUS	VARCHAR2(1 BYTE)
	
	private int reportNo;
	private String rptContent;
	private Date rptDate;
	private int rptTarget;
	private String reciveReport;
	private String sendReport;
	private int rptCategoryNo;
	private String rptStatus;
	private String rptCategoryName;		// 관리자 신고함 리스트 조회시 필요
	private String recipeTitle;			// 관리자 신고함 리스트 조회시 필요
	private String replyContent;		// 관리자 신고함 리스트 조회시 필요
	
	public Report() {
		super();
	}
	
	public Report(int reportNo, String rptContent, Date rptDate, int rptTarget, String reciveReport, String sendReport,
			int rptCategoryNo, String rptStatus, String rptCategoryName, String recipeTitle, String replyContent) {
		super();
		this.reportNo = reportNo;
		this.rptContent = rptContent;
		this.rptDate = rptDate;
		this.rptTarget = rptTarget;
		this.reciveReport = reciveReport;
		this.sendReport = sendReport;
		this.rptCategoryNo = rptCategoryNo;
		this.rptStatus = rptStatus;
		this.rptCategoryName = rptCategoryName;
		this.recipeTitle = recipeTitle;
		this.replyContent = replyContent;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public String getRptContent() {
		return rptContent;
	}

	public void setRptContent(String rptContent) {
		this.rptContent = rptContent;
	}

	public Date getRptDate() {
		return rptDate;
	}

	public void setRptDate(Date rptDate) {
		this.rptDate = rptDate;
	}

	public int getRptTarget() {
		return rptTarget;
	}

	public void setRptTarget(int rptTarget) {
		this.rptTarget = rptTarget;
	}

	public String getReciveReport() {
		return reciveReport;
	}

	public void setReciveReport(String reciveReport) {
		this.reciveReport = reciveReport;
	}

	public String getSendReport() {
		return sendReport;
	}

	public void setSendReport(String sendReport) {
		this.sendReport = sendReport;
	}

	public int getRptCategoryNo() {
		return rptCategoryNo;
	}

	public void setRptCategoryNo(int rptCategoryNo) {
		this.rptCategoryNo = rptCategoryNo;
	}

	public String getRptStatus() {
		return rptStatus;
	}

	public void setRptStatus(String rptStatus) {
		this.rptStatus = rptStatus;
	}

	public String getRptCategoryName() {
		return rptCategoryName;
	}

	public void setRptCategoryName(String rptCategoryName) {
		this.rptCategoryName = rptCategoryName;
	}

	public String getRecipeTitle() {
		return recipeTitle;
	}

	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Override
	public String toString() {
		return "Report [reportNo=" + reportNo + ", rptContent=" + rptContent + ", rptDate=" + rptDate + ", rptTarget="
				+ rptTarget + ", reciveReport=" + reciveReport + ", sendReport=" + sendReport + ", rptCategoryNo="
				+ rptCategoryNo + ", rptStatus=" + rptStatus + ", rptCategoryName=" + rptCategoryName + ", recipeTitle="
				+ recipeTitle + ", replyContent=" + replyContent + "]";
	}

}	// end class
