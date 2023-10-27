package com.kh.semi.board.recipe.model.vo;

public class UnIngredient {

	
	/*
	UN_INGREDIENT_NO	NUMBER
	UN_INGREDIENT	VARCHAR2(45 BYTE)
	UN_INGREDIENT_AMOUNT	VARCHAR2(45 BYTE)
	UN_RECIPE_NO	NUMBER
	INGREDIENT_MEASURE_NO	NUMBER
	*/
	
	private int unIngredientNo;
	private String unIngredient;
	private String unIngredientAmount;
	private int recipeNo;
	private int ingredientMeasureNo;
	
	
	public UnIngredient() {
		super();
	}
	public UnIngredient(int unIngredientNo, String unIngredient, String unIngredientAmount, int recipeNo,
			int ingredientMeasureNo) {
		super();
		this.unIngredientNo = unIngredientNo;
		this.unIngredient = unIngredient;
		this.unIngredientAmount = unIngredientAmount;
		this.recipeNo = recipeNo;
		this.ingredientMeasureNo = ingredientMeasureNo;
	}
	
	
	public int getUnIngredientNo() {
		return unIngredientNo;
	}
	public void setUnIngredientNo(int unIngredientNo) {
		this.unIngredientNo = unIngredientNo;
	}
	public String getUnIngredient() {
		return unIngredient;
	}
	public void setUnIngredient(String unIngredient) {
		this.unIngredient = unIngredient;
	}
	public String getUnIngredientAmount() {
		return unIngredientAmount;
	}
	public void setUnIngredientAmount(String unIngredientAmount) {
		this.unIngredientAmount = unIngredientAmount;
	}
	public int getRecipeNo() {
		return recipeNo;
	}
	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}
	public int getIngredientMeasureNo() {
		return ingredientMeasureNo;
	}
	public void setIngredientMeasureNo(int ingredientMeasureNo) {
		this.ingredientMeasureNo = ingredientMeasureNo;
	}
	
	
	@Override
	public String toString() {
		return "UnIngredient [unIngredientNo=" + unIngredientNo + ", unIngredient=" + unIngredient
				+ ", unIngredientAmount=" + unIngredientAmount + ", recipeNo=" + recipeNo + ", ingredientMeasureNo="
				+ ingredientMeasureNo + "]";
	}
	
	
}//class.end
