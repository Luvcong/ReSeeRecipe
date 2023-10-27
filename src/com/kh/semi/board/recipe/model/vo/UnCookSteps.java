package com.kh.semi.board.recipe.model.vo;

public class UnCookSteps {
	
	/*
	UN_COOK_STEPS_NO	NUMBER
	UN_COOK_STEPS_TITLE	VARCHAR2(100 BYTE)
	UN_COOK_STEPS_CONTENT	VARCHAR2(1500 BYTE)
	UN_COOK_STEPS_LEV	NUMBER
	UN_RECIPE_NO	NUMBER
	*/
	
	private int unCookStepsNo;
	private String unCookStepsTitle;
	private String unCookStepesContent;
	private int unCookStepsLev;
	private int unRecipeNo;
	
	
	public UnCookSteps() {
		super();
	}
	public UnCookSteps(int unCookStepsNo, String unCookStepsTitle, String unCookStepesContent, int unCookStepsLev,
			int unRecipeNo) {
		super();
		this.unCookStepsNo = unCookStepsNo;
		this.unCookStepsTitle = unCookStepsTitle;
		this.unCookStepesContent = unCookStepesContent;
		this.unCookStepsLev = unCookStepsLev;
		this.unRecipeNo = unRecipeNo;
	}
	
	
	public int getUnCookStepsNo() {
		return unCookStepsNo;
	}
	public void setUnCookStepsNo(int unCookStepsNo) {
		this.unCookStepsNo = unCookStepsNo;
	}
	public String getUnCookStepsTitle() {
		return unCookStepsTitle;
	}
	public void setUnCookStepsTitle(String unCookStepsTitle) {
		this.unCookStepsTitle = unCookStepsTitle;
	}
	public String getUnCookStepesContent() {
		return unCookStepesContent;
	}
	public void setUnCookStepesContent(String unCookStepesContent) {
		this.unCookStepesContent = unCookStepesContent;
	}
	public int getUnCookStepsLev() {
		return unCookStepsLev;
	}
	public void setUnCookStepsLev(int unCookStepsLev) {
		this.unCookStepsLev = unCookStepsLev;
	}
	public int getUnRecipeNo() {
		return unRecipeNo;
	}
	public void setUnRecipeNo(int unRecipeNo) {
		this.unRecipeNo = unRecipeNo;
	}
	
	
	@Override
	public String toString() {
		return "UnCookSteps [unCookStepsNo=" + unCookStepsNo + ", unCookStepsTitle=" + unCookStepsTitle
				+ ", unCookStepesContent=" + unCookStepesContent + ", unCookStepsLev=" + unCookStepsLev
				+ ", unRecipeNo=" + unRecipeNo + "]";
	}
	
	
}//class.end
