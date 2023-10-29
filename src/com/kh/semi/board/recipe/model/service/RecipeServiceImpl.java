package com.kh.semi.board.recipe.model.service;

import static com.kh.semi.common.template.Template.getSqlSession;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
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

public class RecipeServiceImpl implements RecipeService {
	
	
	private RecipeDao recipeDao = new RecipeDao();
	
	
	/* ************************** SELECT 종류 ************************** */
	
	@Override
	public Recipe selectRecipeSingle(int recipeNo) {
		// tb_recipe정보와 유저닉네임, 카테고리 번호+이름 같이
		SqlSession sqlSession = getSqlSession();
		Recipe recipe = recipeDao.selectRecipeSingle(sqlSession, recipeNo);
		sqlSession.close();
		return recipe;
	}
	
	
	@Override
	public ArrayList<RecipePic> selectRecipePicSingle(int recipeNo) {
		SqlSession sqlSession = getSqlSession();
		ArrayList<RecipePic> reciepPicList = recipeDao.selectRecipePicSingle(sqlSession, recipeNo);
		sqlSession.close();
		return reciepPicList;
	}
	
	
	public ArrayList<Ingredient> selectIngredientSingle(int recipeNo) {
		SqlSession sqlSession = getSqlSession();
		ArrayList<Ingredient> ingredientList = recipeDao.selectIngredientSingle(sqlSession, recipeNo);
		sqlSession.close();
		return ingredientList;
	}
	
	
	public ArrayList<CookSteps> selectCookStepsSingle(int recipeNo) {
		SqlSession sqlSession = getSqlSession();
		ArrayList<CookSteps> cookStepsList = recipeDao.selectCookStepsSingle(sqlSession, recipeNo);
		sqlSession.close();
		return cookStepsList;
	}
	
	
	@Override
	public ArrayList<RecipeTag> selectRecipeTagSingle(int recipeNo) {
		SqlSession sqlSession = getSqlSession();
		ArrayList<RecipeTag> recipeTagList = recipeDao.selectRecipeTagSingle(sqlSession, recipeNo);
		sqlSession.close();
		return recipeTagList;
	}
	
	
	@Override
	public ArrayList<Reply> selectReplyListSingle(int recipeNo) {
		SqlSession sqlSession = getSqlSession();
		ArrayList<Reply> replyList = recipeDao.selectReplyListSingle(sqlSession, recipeNo);
		sqlSession.close();
		return replyList;
	}
	
	
	@Override
	public ArrayList<RecipeCategory> selectRecipeCategoryList() {
		SqlSession sqlSession = getSqlSession();
		ArrayList<RecipeCategory> cList = recipeDao.selectRecipeCategoryList(sqlSession);
		sqlSession.close();
		return cList;
	}

	
	@Override
	public int selectRecipeListCount() {
		SqlSession sqlSession = getSqlSession();
		int listCount = recipeDao.selectRecipeListCount(sqlSession);
		sqlSession.close();
		return listCount;
	}
	
	
	@Override
	public ArrayList<Recipe> selectRecipeList(PageInfo pi) {
		SqlSession sqlSession = getSqlSession();
		RowBounds rowBounds = new RowBounds(
										   ((pi.getCurrentPage() - 1) * pi.getBoardLimit()),
											 pi.getBoardLimit()
										   );
		ArrayList<Recipe> recipeList = recipeDao.selectRecipeList(sqlSession, rowBounds);
		sqlSession.close();
		return recipeList;
	}
	
	
	
	/* ************************** UPDATE 종류 ************************** */
	
	@Override
	public int deleteReqReplySingle(Reply reply) {
		SqlSession sqlSession = getSqlSession();
		int result = recipeDao.deleteReqReplySingle(sqlSession, reply);
		
		if(result > 0) {
			sqlSession.commit(); // 단일 DML
		}
		sqlSession.close();
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
		
		SqlSession sqlSession = getSqlSession();
		
		// 레시피(TB_RECIPE) INSERT먼저
		Recipe recipe = (Recipe)insertRecipeMap.get("recipe"); /* 맵에서 뽑음 */
		recipeResult = recipeDao.insertRecipe(sqlSession, recipe);
		
		if(recipeResult > 0) {
			// TB_RECIPE_PIC insert
			ArrayList<RecipePic> recipePicList = (ArrayList<RecipePic>)insertRecipeMap.get("recipePicList");
			picResult = recipeDao.insertRecipePic(sqlSession, recipePicList);
			
			// TB_INGREDIENT insert
			ArrayList<Ingredient> ingredientList = (ArrayList<Ingredient>)insertRecipeMap.get("ingredientList");
			ingredientResult = recipeDao.insertIngredient(sqlSession, ingredientList);
			
			// TB_COOK_STEPS insert
			ArrayList<CookSteps> cookStepsList = (ArrayList<CookSteps>)insertRecipeMap.get("cookStepsList");
			cookStepsResult = recipeDao.insertCookSteps(sqlSession, cookStepsList);
			
			// TB_RECIPE_TAG insert
			ArrayList<Integer> tagNoList = (ArrayList<Integer>)insertRecipeMap.get("tagNoList");
			tagResult = recipeDao.insertRecipeTag(sqlSession, tagNoList);
			
			// 커넥션 닫기 전 transaction처리
			if( !(recipeResult != 1
			   || picResult * ingredientResult * cookStepsResult * tagResult != 1)) {
				returningResult = 1;
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		}
		return returningResult;
	}
	

	@Override
	public int insertReply(Reply reply) {
		
		SqlSession sqlSession = getSqlSession();
		
		int result = recipeDao.insertReply(sqlSession, reply);
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
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