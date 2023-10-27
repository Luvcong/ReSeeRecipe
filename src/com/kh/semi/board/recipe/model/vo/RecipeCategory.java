package com.kh.semi.board.recipe.model.vo;

public class RecipeCategory {
	/*
	RECIPE_CATEGORY_NO	NUMBER
	RECIPE_CATEGORY_NAME	VARCHAR2(20 BYTE)
	*/
	
	private int recipeCategoryNo;
	private String recipeCategoryName;
	private int recipeCategoryCount;	// 각 카테고리별 게시글 수 총 계
	
	public RecipeCategory() {
		super();
	}
	
	public RecipeCategory(int recipeCategoryNo, String recipeCategoryName, int recipeCategoryCount) {
		super();
		this.recipeCategoryNo = recipeCategoryNo;
		this.recipeCategoryName = recipeCategoryName;
		this.recipeCategoryCount = recipeCategoryCount;
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
	
	public int getRecipeCategoryCount() {
		return recipeCategoryCount;
	}


	public void setRecipeCategoryCount(int recipeCategoryCount) {
		this.recipeCategoryCount = recipeCategoryCount;
	}

	@Override
	public String toString() {
		return "RecipeCategory [recipeCategoryNo=" + recipeCategoryNo + ", recipeCategoryName=" + recipeCategoryName
				+ ", recipeCategoryCount=" + recipeCategoryCount + "]";
	}
	
}//class.end