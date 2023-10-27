package com.kh.semi.board.recipe.model.vo;

public class RecipeTag {
	

	private int tagNo;
	private int tagRecipeNo;
	private String tagName;
	private String tagDate;
	
	
	public RecipeTag() {
		super();
	}
	public RecipeTag(int tagNo, int tagRecipeNo, String tagName, String tagDate) {
		super();
		this.tagNo = tagNo;
		this.tagRecipeNo = tagRecipeNo;
		this.tagName = tagName;
		this.tagDate = tagDate;
	}
	
	
	public int getTagNo() {
		return tagNo;
	}
	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}
	public int getTagRecipeNo() {
		return tagRecipeNo;
	}
	public void setTagRecipeNo(int tagRecipeNo) {
		this.tagRecipeNo = tagRecipeNo;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagDate() {
		return tagDate;
	}
	public void setTagDate(String tagDate) {
		this.tagDate = tagDate;
	}
	
	
	@Override
	public String toString() {
		return "RecipeTag [tagNo=" + tagNo + ", tagRecipeNo=" + tagRecipeNo + ", tagName=" + tagName + ", tagDate="
				+ tagDate + "]";
	}
	
	
}//class.end