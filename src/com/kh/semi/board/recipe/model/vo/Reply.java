package com.kh.semi.board.recipe.model.vo;

public class Reply {
	

	private int replyNo;
	private String replyContent;
	private String replyDate;
	private String replyModified;
	private int replyWriterNo;
	private int recipeNo;
	private String replyStatus;
	private String memNickname; // 리플 작성자 닉네임
	
	
	public Reply() {
		super();
	}
	public Reply(int replyNo, String replyContent, String replyDate, String replyModified, int replyWriterNo,
			int recipeNo, String replyStatus, String memNickname) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
		this.replyModified = replyModified;
		this.replyWriterNo = replyWriterNo;
		this.recipeNo = recipeNo;
		this.replyStatus = replyStatus;
		this.memNickname = memNickname;
	}
	
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	public String getReplyModified() {
		return replyModified;
	}
	public void setReplyModified(String replyModified) {
		this.replyModified = replyModified;
	}
	public int getReplyWriterNo() {
		return replyWriterNo;
	}
	public void setReplyWriterNo(int replyWriterNo) {
		this.replyWriterNo = replyWriterNo;
	}
	public int getRecipeNo() {
		return recipeNo;
	}
	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}
	public String getReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}
	public String getMemNickname() {
		return memNickname;
	}
	public void setMemNickname(String memNickname) {
		this.memNickname = memNickname;
	}
	
	
	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyDate=" + replyDate
				+ ", replyModified=" + replyModified + ", replyWriterNo=" + replyWriterNo + ", recipeNo=" + recipeNo
				+ ", replyStatus=" + replyStatus + ", memNickname=" + memNickname + "]";
	}
	
	
}//class.end