package com.kh.semi.board.recipe.model.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.doTransAction;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.semi.board.recipe.model.dao.RecipeDao;
import com.kh.semi.board.recipe.model.vo.CookSteps;
import com.kh.semi.board.recipe.model.vo.Ingredient;
import com.kh.semi.board.recipe.model.vo.Recipe;
import com.kh.semi.board.recipe.model.vo.RecipeCategory;
import com.kh.semi.board.recipe.model.vo.RecipePic;
import com.kh.semi.board.recipe.model.vo.RecipeTag;
import com.kh.semi.board.recipe.model.vo.Reply;
import com.kh.semi.common.model.vo.PageInfo;

public class RecipeService {
	
	/* ************************** SELECT 종류 ************************** */
	/**
	 * 레시피 번호로 해당 레시피의 레시피테이블(TB_RECIPE) 모든 정보와 작성자 닉네임, 카테고리 번호와 이름을 조회하는 기능
	 * @param recipeNo
	 * @return
	 */
	public Recipe selectRecipeSingle(int recipeNo) {
		// tb_recipe정보와 유저닉네임, 카테고리 번호+이름 같이
		Connection conn = getConnection();
		Recipe recipe = new RecipeDao().selectRecipeSingle(conn, recipeNo);
		close(conn);
		return recipe;
	}
	
	
	/**
	 * 레시피 번호로 해당 레시피의 사진테이블(TB_RECIPE_PIC) 모든 정보를 조회하는 기능
	 * @param recipeNo
	 * @return
	 */
	public ArrayList<RecipePic> selectRecipePicSingle(int recipeNo) {
		Connection conn = getConnection();
		ArrayList<RecipePic> reciepPicList = new RecipeDao().selectRecipePicSingle(conn, recipeNo);
		close(conn);
		return reciepPicList;
	}
	
	
	/**
	 * 레시피 번호로 해당 레시피의 재료테이블(TB_INGREDIENT) 모든 정보를 조회하는 기능
	 * @param recipeNo
	 * @return
	 */
	public ArrayList<Ingredient> selectIngredientSingle(int recipeNo) {
		Connection conn = getConnection();
		ArrayList<Ingredient> ingredientList = new RecipeDao().selectIngredientSingle(conn, recipeNo);
		close(conn);
		return ingredientList;
	}
	
	
	/**
	 * 레시피 번호로 해당 레시피의 요리 과정 테이블(TB_COOK_STEPS) 모든 정보를 조회하는 기능
	 * @param recipeNo
	 * @return
	 */
	public ArrayList<CookSteps> selectCookStepsSingle(int recipeNo) {
		Connection conn = getConnection();
		ArrayList<CookSteps> cookStepsList = new RecipeDao().selectCookStepsSingle(conn, recipeNo);
		close(conn);
		return cookStepsList;
	}
	
	
	/**
	 * 레시피 번호로 해당 레시피의 해시태그 테이블(TB_RECIPE_TAG) 모든 정보와 해시태그 이름, 날짜를 조회하는 기능
	 * @param recipeNo
	 * @return
	 */
	public ArrayList<RecipeTag> selectRecipeTagSingle(int recipeNo) {
		Connection conn = getConnection();
		ArrayList<RecipeTag> recipeTagList = new RecipeDao().selectRecipeTagSingle(conn, recipeNo);
		close(conn);
		return recipeTagList;
	}
	
	
	/**
	 * 특정 번호 레시피(PK)에 달린 댓글 리스트를 조회하는 기능<br>
	 */
	public ArrayList<Reply> selectReplyListSingle(int recipeNo) {
		Connection conn = getConnection();
		ArrayList<Reply> replyList = new RecipeDao().selectReplyListSingle(conn, recipeNo);
		close(conn);
		return replyList;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 레시피 카테고리 목록을 조회해 반환
	 * @return : 레시피 카테고리 목록이 담긴 ArrayList배열
	 */
	public ArrayList<RecipeCategory> selectRecipeCategoryList() {
		Connection conn = getConnection();
		ArrayList<RecipeCategory> cList = new RecipeDao().selectRecipeCategoryList(conn);
		close(conn);
		return cList;
	}

	
	/**
	 * 레시피 개수 조회
	 * @return : 글과 작성자의 STATUS가 유효한 레시피글의 총 개수
	 */
	public int selectRecipeListCount() {
		Connection conn = getConnection();
		int listCount = new RecipeDao().selectRecipeListCount(conn);
		close(conn);
		return listCount;
	}
	
	
	/**
	 * 레시피 메인 보기 기능, 페이지네이션 처리 된 레시피목록을 최신순(레시피 PK번호순)으로 조회한 후<br>
	 * 목록과 PageInfo객체를 RecipeMainView로 포워딩함
	 * @param pi : 페이지네이션 처리를 위한 정보가 담긴 PageInfo객체<br>
	 * PageInfo필드 : listCount(현재 게시글 총 개수), currentPage(요청온 페이지 번호),<br>
	 * pageLimit(한 페이지에 보일 페이징 바의 최대 개수), boardLimit(한 페이지에 보일 게시글 최대 개수)<br>
	 * @return
	 * > ArrayList<Recipe> rList : 페이지네이션 처리되어 조회된 레시피 글 정보를 Recipe객체로 만든 후 ArrayList에 담음<br>
	 * 	 Recipe필드 :  recipeNo, recipeTitle, recipeCount, titleImg, memNickName, htCount<br>
	 */
	public ArrayList<Recipe> selectRecipeList(PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Recipe> recipeList = new RecipeDao().selectRecipeList(conn, pi);
		close(conn);
		return recipeList;
	}
	
	/* ************************** UPDATE 종류 ************************** */
	
	/**
	 * 댓글의 상태를 'N'으로 바꾸는 기능(삭제요청)<br>
	 * 삭제요청에 성공할 시 int형 숫자 1, 실패 시 0 반환
	 * @param reply
	 * @return
	 */
	public int deleteReqReplySingle(Reply reply) {
		Connection conn = getConnection();
		int result = doTransAction(conn, new RecipeDao().deleteReqReplySingle(conn, reply));
		return result;
	}
	
	
	
	/* ************************** INSERT 종류 ************************** */
	
	
	/**
	 * 레시피 작성 기능
	 * @param insertRecipeMap
	 * @return
	 */
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
	
	
	/**
	 * 특정 번호 레시피(PK)에 댓글을 입력하는 기능
	 * @param reply : replyContent, memNo, recipeNo필드가 초기화된 Reply객체
	 */
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
		
	}
	
	/**
	 * 특정 번호 레시피(PK) 글 작성자가 동일할 시 댓글의 상태를 'N'으로 바꾸는 기능 
	 */
	public void ajaxDeleteRecipeReply(int recipeNo) {
		
	}
	

	

	
	
	
	
	
	
	
	
	
	
	
	
}//class.end