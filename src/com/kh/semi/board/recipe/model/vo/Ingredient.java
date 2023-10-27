package com.kh.semi.board.recipe.model.vo;

public class Ingredient {
	
	/*
	INGREDIENT_NO	NUMBER
	INGREDIENT	VARCHAR2(45 BYTE)
	INGREDIENT_AMOUNT	VARCHAR2(45 BYTE)
	RECIPE_NO	NUMBER
	*/
	
	private int ingredientNo;
	private String ingredient;
	private String ingredientAmount;
	private int recipeNo;
	
	
	public Ingredient() {
		super();
	}
	public Ingredient(int ingredientNo, String ingredient, String ingredientAmount, int recipeNo) {
		super();
		this.ingredientNo = ingredientNo;
		this.ingredient = ingredient;
		this.ingredientAmount = ingredientAmount;
		this.recipeNo = recipeNo;
	}
	
	
	public int getIngredientNo() {
		return ingredientNo;
	}
	public void setIngredientNo(int ingredientNo) {
		this.ingredientNo = ingredientNo;
	}
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	public String getIngredientAmount() {
		return ingredientAmount;
	}
	public void setIngredientAmount(String ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}
	public int getRecipeNo() {
		return recipeNo;
	}
	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}
	
	
	@Override
	public String toString() {
		return "Ingredient [ingredientNo=" + ingredientNo + ", ingredient=" + ingredient + ", ingredientAmount="
				+ ingredientAmount + ", recipeNo=" + recipeNo + "]";
	}
	
	
}//class.end