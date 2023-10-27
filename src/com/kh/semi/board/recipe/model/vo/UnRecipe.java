package com.kh.semi.board.recipe.model.vo;

public class UnRecipe {
	
	
	/*
	UN_RECIPE_NO	NUMBER
	UN_RECIPE_TITLE	VARCHAR2(60 BYTE)
	UN_RECIPE_DATE	DATE
	UN_RECIPE_MODIFIED	DATE
	UN_CATEGORY_NO	NUMBER
	UN_RECIPE_WRITER_NO	NUMBER
	*/
	
	
	private int unRecipeNo;
	private String unRecipeTitle;
	private String unRecipeDate;
	private String unRecipeModified;
	private int unCategoryNo;
	private int unRecipeWriterNo;
	
	
	public UnRecipe() {
		super();
	}
	
	public UnRecipe(int unRecipeNo, String unRecipeTitle, String unRecipeDate, String unRecipeModified,
			int unCategoryNo, int unRecipeWriterNo) {
		super();
		this.unRecipeNo = unRecipeNo;
		this.unRecipeTitle = unRecipeTitle;
		this.unRecipeDate = unRecipeDate;
		this.unRecipeModified = unRecipeModified;
		this.unCategoryNo = unCategoryNo;
		this.unRecipeWriterNo = unRecipeWriterNo;
	}
	
	
	public int getUnRecipeNo() {
		return unRecipeNo;
	}
	public void setUnRecipeNo(int unRecipeNo) {
		this.unRecipeNo = unRecipeNo;
	}
	public String getUnRecipeTitle() {
		return unRecipeTitle;
	}
	public void setUnRecipeTitle(String unRecipeTitle) {
		this.unRecipeTitle = unRecipeTitle;
	}
	public String getUnRecipeDate() {
		return unRecipeDate;
	}
	public void setUnRecipeDate(String unRecipeDate) {
		this.unRecipeDate = unRecipeDate;
	}
	public String getUnRecipeModified() {
		return unRecipeModified;
	}
	public void setUnRecipeModified(String unRecipeModified) {
		this.unRecipeModified = unRecipeModified;
	}
	public int getUnCategoryNo() {
		return unCategoryNo;
	}
	public void setUnCategoryNo(int unCategoryNo) {
		this.unCategoryNo = unCategoryNo;
	}
	public int getUnRecipeWriterNo() {
		return unRecipeWriterNo;
	}
	public void setUnRecipeWriterNo(int unRecipeWriterNo) {
		this.unRecipeWriterNo = unRecipeWriterNo;
	}
	
	
	@Override
	public String toString() {
		return "UnRecipe [unRecipeNo=" + unRecipeNo + ", unRecipeTitle=" + unRecipeTitle + ", unRecipeDate="
				+ unRecipeDate + ", unRecipeModified=" + unRecipeModified + ", unCategoryNo=" + unCategoryNo
				+ ", unRecipeWriterNo=" + unRecipeWriterNo + "]";
	}
	
	
}//class.end
