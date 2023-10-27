package com.kh.semi.board.recipe.model.vo;

public class CookSteps {
	
	/*
	COOK_STEPS_NO	NUMBER
	COOK_STEPS_TITLE	VARCHAR2(100 BYTE)
	COOK_STEPS_CONTENT	VARCHAR2(1500 BYTE)
	COOK_STEPS_LEV	NUMBER
	RECIPE_NO	NUMBER
	*/
	
	private int cookStepsNo;
	private String cookStepsTitle;
	private String cookStepsContent;
	private int cookStepsLev;
	private int recipeNo;
	
	
	public CookSteps() {
		super();
	}
	public CookSteps(int cookStepsNo, String cookStepsTitle, String cookStepsContent, int cookStepsLev, int recipeNo) {
		super();
		this.cookStepsNo = cookStepsNo;
		this.cookStepsTitle = cookStepsTitle;
		this.cookStepsContent = cookStepsContent;
		this.cookStepsLev = cookStepsLev;
		this.recipeNo = recipeNo;
	}
	
	
	public int getCookStepsNo() {
		return cookStepsNo;
	}
	public void setCookStepsNo(int cookStepsNo) {
		this.cookStepsNo = cookStepsNo;
	}
	public String getCookStepsTitle() {
		return cookStepsTitle;
	}
	public void setCookStepsTitle(String cookStepsTitle) {
		this.cookStepsTitle = cookStepsTitle;
	}
	public String getCookStepsContent() {
		return cookStepsContent;
	}
	public void setCookStepsContent(String cookStepsContent) {
		this.cookStepsContent = cookStepsContent;
	}
	public int getCookStepsLev() {
		return cookStepsLev;
	}
	public void setCookStepsLev(int cookStepsLev) {
		this.cookStepsLev = cookStepsLev;
	}
	public int getRecipeNo() {
		return recipeNo;
	}
	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}
	
	
	@Override
	public String toString() {
		return "CookSteps [cookStepsNo=" + cookStepsNo + ", cookStepsTitle=" + cookStepsTitle + ", cookStepsContent="
				+ cookStepsContent + ", cookStepsLev=" + cookStepsLev + ", recipeNo=" + recipeNo + "]";
	}
	
	
}//class.end