package com.kh.semi.board.recipe.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.semi.board.recipe.model.dao.RecipeDao;
import com.kh.semi.board.recipe.model.vo.CookSteps;
import com.kh.semi.board.recipe.model.vo.Ingredient;
import com.kh.semi.board.recipe.model.vo.Recipe;
import com.kh.semi.board.recipe.model.vo.RecipeCategory;
import com.kh.semi.board.recipe.model.vo.RecipePic;
import com.kh.semi.board.recipe.model.vo.RecipeTag;
import com.kh.semi.board.recipe.model.vo.Reply;
import com.kh.semi.common.model.vo.PageInfo;
import static com.kh.semi.common.template.Template.*;

public class RecipeServiceImpl implements RecipeService {
	
	/* ************************** SELECT 종류 ************************** */
	
	@Override
	public Recipe selectRecipeSingle(int recipeNo) {
		// tb_recipe정보와 유저닉네임, 카테고리 번호+이름 같이
		SqlSession sqlSession = getSqlSession();
		Recipe recipe = new RecipeDao().selectRecipeSingle(sqlSession, recipeNo);
		sqlSession.close();
		return recipe;
	}
	
	
	@Override
	public ArrayList<RecipePic> selectRecipePicSingle(int recipeNo) {
		Connection conn = getConnection();
		ArrayList<RecipePic> reciepPicList = new RecipeDao().selectRecipePicSingle(conn, recipeNo);
		close(conn);
		return reciepPicList;
	}
	
	
	public ArrayList<Ingredient> selectIngredientSingle(int recipeNo) {
		Connection conn = getConnection();
		ArrayList<Ingredient> ingredientList = new RecipeDao().selectIngredientSingle(conn, recipeNo);
		close(conn);
		return ingredientList;
	}
	
	
	public ArrayList<CookSteps> selectCookStepsSingle(int recipeNo) {
		Connection conn = getConnection();
		ArrayList<CookSteps> cookStepsList = new RecipeDao().selectCookStepsSingle(conn, recipeNo);
		close(conn);
		return cookStepsList;
	}
	
	
	@Override
	public ArrayList<RecipeTag> selectRecipeTagSingle(int recipeNo) {
		Connection conn = getConnection();
		ArrayList<RecipeTag> recipeTagList = new RecipeDao().selectRecipeTagSingle(conn, recipeNo);
		close(conn);
		return recipeTagList;
	}
	
	
	@Override
	public ArrayList<Reply> selectReplyListSingle(int recipeNo) {
		Connection conn = getConnection();
		ArrayList<Reply> replyList = new RecipeDao().selectReplyListSingle(conn, recipeNo);
		close(conn);
		return replyList;
	}
	
	
	@Override
	public ArrayList<RecipeCategory> selectRecipeCategoryList() {
		Connection conn = getConnection();
		ArrayList<RecipeCategory> cList = new RecipeDao().selectRecipeCategoryList(conn);
		close(conn);
		return cList;
	}

	
	@Override
	public int selectRecipeListCount() {
		Connection conn = getConnection();
		int listCount = new RecipeDao().selectRecipeListCount(conn);
		close(conn);
		return listCount;
	}
	
	
	@Override
	public ArrayList<Recipe> selectRecipeList(PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Recipe> recipeList = new RecipeDao().selectRecipeList(conn, pi);
		close(conn);
		return recipeList;
	}
	
	
	
	/* ************************** UPDATE 종류 ************************** */
	
	@Override
	public int deleteReqReplySingle(Reply reply) {
		Connection conn = getConnection();
		int result = doTransAction(conn, new RecipeDao().deleteReqReplySingle(conn, reply));
		return result;
	}
	
	
	
	/* ************************** INSERT 종류 ************************** */
	
	@Override
	public int insertRecipe(HashMap<String, Object> insertRecipeMap) {
		
		int returningResult = 0;
		
		int recipeResult = 0;
		int tagResult = 0;
		int picResult = 0;
		int cookStepsResult = 0;
		int ingredientResult = 0;
		
		RecipeDao rd = new RecipeDao();
		
		Connection conn = getConnection();
		
		// 레시피(TB_RECIPE) INSERT먼저
		Recipe recipe = (Recipe)insertRecipeMap.get("recipe"); /* 맵에서 뽑음 */
		recipeResult = rd.insertRecipe(conn, recipe);
		
		if(recipeResult > 0) {
			// TB_RECIPE_PIC insert
			ArrayList<RecipePic> recipePicList = (ArrayList<RecipePic>)insertRecipeMap.get("recipePicList");
			picResult = rd.insertRecipePic(conn, recipePicList);
			
			// TB_INGREDIENT insert
			ArrayList<Ingredient> ingredientList = (ArrayList<Ingredient>)insertRecipeMap.get("ingredientList");
			ingredientResult = rd.insertIngredient(conn, ingredientList);
			
			// TB_COOK_STEPS insert
			ArrayList<CookSteps> cookStepsList = (ArrayList<CookSteps>)insertRecipeMap.get("cookStepsList");
			cookStepsResult = rd.insertCookSteps(conn, cookStepsList);
			
			// TB_RECIPE_TAG insert
			ArrayList<Integer> tagNoList = (ArrayList<Integer>)insertRecipeMap.get("tagNoList");
			tagResult = rd.insertRecipeTag(conn, tagNoList);
			
			// 커넥션 닫기 전 transaction처리
			if( !(recipeResult != 1
			   || picResult * ingredientResult * cookStepsResult * tagResult != 1)) {
				returningResult = 1;
				commit(conn);
			} else {
				rollback(conn);
			}
		}
		return returningResult;
	}
	

	@Override
	public int insertReply(Reply reply) {
		Connection conn = getConnection();
		int result = doTransAction(conn, new RecipeDao().insertReply(conn, reply));
		return result;
	}

	
	
	/* ***************************** AJAX 요청 처리 ***************************** */
	
	
	/**
	 * 특정 번호 레시피(PK) 글 작성자가 동일할 시 댓글을 수정해주는 기능 
	 */
	public void ajaxModifyRecipeReply(int recipeNo) {
		//
	}
	
	/**
	 * 특정 번호 레시피(PK) 글 작성자가 동일할 시 댓글의 상태를 'N'으로 바꾸는 기능 
	 */
	public void ajaxDeleteRecipeReply(int recipeNo) {
		//
	}
	

	

	
	
	
	
	
	
	
	
	
	
	
	
}//class.end