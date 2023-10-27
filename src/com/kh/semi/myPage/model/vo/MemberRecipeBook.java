package com.kh.semi.myPage.model.vo;

public class MemberRecipeBook {
	

	private int recipeBookNum;
	private String recipeBookTitle;
	
	
	public MemberRecipeBook() {
		super();
	}


	public MemberRecipeBook(int recipeBookNum, String recipeBookTitle) {
		super();
		this.recipeBookNum = recipeBookNum;
		this.recipeBookTitle = recipeBookTitle;
	}


	public int getRecipeBookNum() {
		return recipeBookNum;
	}


	public void setRecipeBookNum(int recipeBookNum) {
		this.recipeBookNum = recipeBookNum;
	}


	public String getRecipeBookTitle() {
		return recipeBookTitle;
	}


	public void setRecipeBookTitle(String recipeBookTitle) {
		this.recipeBookTitle = recipeBookTitle;
	}


	@Override
	public String toString() {
		return "MemberRecipeBook [recipeBookNum=" + recipeBookNum + ", recipeBookTitle=" + recipeBookTitle + "]";
	}
	
	
	
	
	
}
