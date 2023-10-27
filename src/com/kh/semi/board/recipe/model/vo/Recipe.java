package com.kh.semi.board.recipe.model.vo;

public class Recipe {
	
	
	private int recipeNo;
	private String recipeTitle;
	private String recipeDate;
	private String recipeModified;
	private String recipeStatus;
	private int recipeCount;
	private int recipeWriterNo;
	private int recipeCategoryNo;
	private String recipeCategoryName;
	private String titleImg;
	private String memNickName;
	private int htCount;
	
	
	public Recipe() {
		super();
	}
	public Recipe(int recipeNo, String recipeTitle, String recipeDate, String recipeModified, String recipeStatus,
			int recipeCount, int recipeWriterNo, int recipeCategoryNo, String recipeCategoryName, String titleImg,
			String memNickName, int htCount) {
		super();
		this.recipeNo = recipeNo;
		this.recipeTitle = recipeTitle;
		this.recipeDate = recipeDate;
		this.recipeModified = recipeModified;
		this.recipeStatus = recipeStatus;
		this.recipeCount = recipeCount;
		this.recipeWriterNo = recipeWriterNo;
		this.recipeCategoryNo = recipeCategoryNo;
		this.recipeCategoryName = recipeCategoryName;
		this.titleImg = titleImg;
		this.memNickName = memNickName;
		this.htCount = htCount;
	}
	
	
	public int getRecipeNo() {
		return recipeNo;
	}
	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}
	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	public String getRecipeDate() {
		return recipeDate;
	}
	public void setRecipeDate(String recipeDate) {
		this.recipeDate = recipeDate;
	}
	public String getRecipeModified() {
		return recipeModified;
	}
	public void setRecipeModified(String recipeModified) {
		this.recipeModified = recipeModified;
	}
	public String getRecipeStatus() {
		return recipeStatus;
	}
	public void setRecipeStatus(String recipeStatus) {
		this.recipeStatus = recipeStatus;
	}
	public int getRecipeCount() {
		return recipeCount;
	}
	public void setRecipeCount(int recipeCount) {
		this.recipeCount = recipeCount;
	}
	public int getRecipeWriterNo() {
		return recipeWriterNo;
	}
	public void setRecipeWriterNo(int recipeWriterNo) {
		this.recipeWriterNo = recipeWriterNo;
	}
	public int getRecipeCategoryNo() {
		return recipeCategoryNo;
	}
	public void setRecipeCategoryNo(int recipeCategoryNo) {
		this.recipeCategoryNo = recipeCategoryNo;
	}
	public String getRecipeCategoryName() {
		return recipeCategoryName;
	}
	public void setRecipeCategoryName(String recipeCategoryName) {
		this.recipeCategoryName = recipeCategoryName;
	}
	public String getTitleImg() {
		return titleImg;
	}
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
	public String getMemNickName() {
		return memNickName;
	}
	public void setMemNickName(String memNickName) {
		this.memNickName = memNickName;
	}
	public int getHtCount() {
		return htCount;
	}
	public void setHtCount(int htCount) {
		this.htCount = htCount;
	}
	
	
	@Override
	public String toString() {
		return "Recipe [recipeNo=" + recipeNo + ", recipeTitle=" + recipeTitle + ", recipeDate=" + recipeDate
				+ ", recipeModified=" + recipeModified + ", recipeStatus=" + recipeStatus + ", recipeCount="
				+ recipeCount + ", recipeWriterNo=" + recipeWriterNo + ", recipeCategoryNo=" + recipeCategoryNo
				+ ", recipeCategoryName=" + recipeCategoryName + ", titleImg=" + titleImg + ", memNickName="
				+ memNickName + ", htCount=" + htCount + "]";
	}
	
	
}//class.end