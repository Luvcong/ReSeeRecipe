package com.kh.semi.board.recipe.model.vo;

public class RecipePic {

	/*
	RECIPE_PIC_NO	NUMBER
	RECIPE_PIC_NAME_ORIGIN	VARCHAR2(255 BYTE)
	RECIPE_PIC_NAME_UPLOAD	VARCHAR2(255 BYTE)
	RECIPE_PIC_PATH	VARCHAR2(1000 BYTE)
	RECIPE_PIC_DATE	DATE
	RECIPE_PIC_LEV	NUMBER
	RECIPE_PIC_STATUS	VARCHAR2(1 BYTE)
	REF_RNO	NUMBER
	 */
	
	// unsaved recipe 임시저장글에는 사진 저장 X
	
	private int recipePicNo;
	private String recipePicNameOrigin;
	private String recipePicNameUpload;
	private String recipePicPath;
	private String recipePicDate;
	private int recipePicLev;
	private String recipePicStatus;
	private int refRno;
	
	
	public RecipePic() {
		super();
	}
	public RecipePic(int recipePicNo, String recipePicNameOrigin, String recipePicNameUpload, String recipePicPath,
			String recipePicDate, int recipePicLev, String recipePicStatus, int refRno) {
		super();
		this.recipePicNo = recipePicNo;
		this.recipePicNameOrigin = recipePicNameOrigin;
		this.recipePicNameUpload = recipePicNameUpload;
		this.recipePicPath = recipePicPath;
		this.recipePicDate = recipePicDate;
		this.recipePicLev = recipePicLev;
		this.recipePicStatus = recipePicStatus;
		this.refRno = refRno;
	}
	
	
	public int getRecipePicNo() {
		return recipePicNo;
	}
	public void setRecipePicNo(int recipePicNo) {
		this.recipePicNo = recipePicNo;
	}
	public String getRecipePicNameOrigin() {
		return recipePicNameOrigin;
	}
	public void setRecipePicNameOrigin(String recipePicNameOrigin) {
		this.recipePicNameOrigin = recipePicNameOrigin;
	}
	public String getRecipePicNameUpload() {
		return recipePicNameUpload;
	}
	public void setRecipePicNameUpload(String recipePicNameUpload) {
		this.recipePicNameUpload = recipePicNameUpload;
	}
	public String getRecipePicPath() {
		return recipePicPath;
	}
	public void setRecipePicPath(String recipePicPath) {
		this.recipePicPath = recipePicPath;
	}
	public String getRecipePicDate() {
		return recipePicDate;
	}
	public void setRecipePicDate(String recipePicDate) {
		this.recipePicDate = recipePicDate;
	}
	public int getRecipePicLev() {
		return recipePicLev;
	}
	public void setRecipePicLev(int recipePicLev) {
		this.recipePicLev = recipePicLev;
	}
	public String getRecipePicStatus() {
		return recipePicStatus;
	}
	public void setRecipePicStatus(String recipePicStatus) {
		this.recipePicStatus = recipePicStatus;
	}
	public int getRefRno() {
		return refRno;
	}
	public void setRefRno(int refRno) {
		this.refRno = refRno;
	}
	
	
	@Override
	public String toString() {
		return "RecipePic [recipePicNo=" + recipePicNo + ", recipePicNameOrigin=" + recipePicNameOrigin
				+ ", recipePicNameUpload=" + recipePicNameUpload + ", recipePicPath=" + recipePicPath
				+ ", recipePicDate=" + recipePicDate + ", recipePicLev=" + recipePicLev + ", recipePicStatus="
				+ recipePicStatus + ", refRno=" + refRno + "]";
	}
	
	
}//class.end
