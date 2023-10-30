package com.kh.semi.board.recipe.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.semi.board.recipe.model.vo.CookSteps;
import com.kh.semi.board.recipe.model.vo.Ingredient;
import com.kh.semi.board.recipe.model.vo.Recipe;
import com.kh.semi.board.recipe.model.vo.RecipeCategory;
import com.kh.semi.board.recipe.model.vo.RecipePic;
import com.kh.semi.board.recipe.model.vo.RecipeTag;
import com.kh.semi.board.recipe.model.vo.Reply;

public class RecipeDao {
	
	
	/* ************************** SELECT 종류 ************************** */	
	
	/**
	 * 레시피 번호로 해당 레시피의 레시피테이블(TB_RECIPE) 모든 정보와 작성자 닉네임, 카테고리 번호와 이름을 조회하는 기능
	 */
	public Recipe selectRecipeSingle(SqlSession sqlSession, int recipeNo) {
		
		return sqlSession.selectOne("recipeMapper.selectRecipeSingle", recipeNo);
	}
	
	
	/**
	 * 레시피 번호로 해당 레시피의 사진테이블(TB_RECIPE_PIC) 모든 정보를 조회하는 기능
	 * @param conn
	 * @param recipeNo
	 * @return
	 */
	public ArrayList<RecipePic> selectRecipePicSingle(SqlSession sqlSession, int recipeNo) {
		
		return (ArrayList)sqlSession.selectList("recipeMapper.selectRecipePicSingle", recipeNo);
	}
	
	
	/**
	 * 레시피 번호로 해당 레시피의 재료테이블(TB_INGREDIENT) 모든 정보를 조회하는 기능
	 * @param conn
	 * @param recipeNo
	 * @return
	 */
	public ArrayList<Ingredient> selectIngredientSingle(SqlSession sqlSession, int recipeNo) {

		return (ArrayList)sqlSession.selectList("recipeMapper.selectIngredientSingle", recipeNo);
	}
	
	
	/**
	 * 레시피 번호로 해당 레시피의 요리 과정 테이블(TB_COOK_STEPS) 모든 정보를 조회하는 기능
	 * @param conn
	 * @param recipeNo
	 * @return
	 */
	public ArrayList<CookSteps> selectCookStepsSingle(SqlSession sqlSession, int recipeNo) {
		
		return (ArrayList)sqlSession.selectList("recipeMapper.selectCookStepsSingle", recipeNo);
	}
	
	
	/**
	 * 레시피 번호로 해당 레시피의 해시태그 테이블(TB_RECIPE_TAG) 모든 정보와 해시태그 이름, 날짜를 조회하는 기능
	 * @param conn
	 * @param recipeNo
	 * @return
	 */
	public ArrayList<RecipeTag> selectRecipeTagSingle(SqlSession sqlSession, int recipeNo) {
		
		return (ArrayList)sqlSession.selectList("recipeMapper.selectRecipeTagSingle", recipeNo);
	}
	
	
	/**
	 * 특정 번호 레시피(PK)에 달린 댓글 리스트를 조회하는 기능<br>
	 */
	public ArrayList<Reply> selectReplyListSingle(SqlSession sqlSession, int recipeNo) {

		return (ArrayList)sqlSession.selectList("recipeMapper.selectReplyListSingle", recipeNo);
	}
	/***/
	
	/**
	 * 레시피 카테고리 목록을 조회해 반환
	 * @param conn : Connection객체
	 * @return : 레시피 카테고리 목록이 담긴 ArrayList배열
	 */
	public ArrayList<RecipeCategory> selectRecipeCategoryList(SqlSession sqlSession) {
	
		return (ArrayList)sqlSession.selectList("recipeMapper.selectRecipeCategoryList");
	}
	
	
	/**
	 * 레시피 개수 조회
	 * @param conn : Connection객체
	 * @return : 글과 작성자의 STATUS가 유효한 레시피글의 총 개수
	 */
	public int selectRecipeListCount(SqlSession sqlSession) {

		return sqlSession.selectOne("recipeMapper.selectRecipeListCount");
	}
	
	
	/**
	 * 레시피 메인 보기 기능, 페이지네이션 처리 된 레시피목록을 최신순(레시피 PK번호순)으로 조회한 후<br>
	 * 목록과 PageInfo객체를 RecipeMainView로 포워딩함
	 * @param conn : Connection객체
	 * @param pi : 페이지네이션 처리를 위한 정보가 담긴 PageInfo객체<br>
	 * @return
	 */
	public ArrayList<Recipe> selectRecipeList(SqlSession sqlSession, RowBounds rowBounds) {
		
		return (ArrayList)sqlSession.selectList("recipeMapper.selectRecipeList", null, rowBounds);
	}
	
	
	
	/* ************************** UPDATE 종류 ************************** */
	
	/**
	 * 요청된 번호의 리플 상태를 N으로 바꾸는 기능
	 * @param conn
	 * @param recipeNo
	 * @return
	 */
	public int deleteReqReplySingle(SqlSession sqlSession, Reply reply) {
		
		return sqlSession.update("recipeMapper.deleteReqReplySingle", reply);
	}
	
	
	/* ************************** INSERT종류 ************************** */
	/**
	 * 레시피 제목, 작성자 이름, 레시피 카테고리 정보를 받아 레시피를 INSERT하는 기능<br>
	 * PK종류는 모두 시퀀스로 생성/참조되며 나머지 정보들에는 아래와 같이 테이블의 Default값이 들어감<br>
	 * RECIPE_DATE(SYSDATE), REICPE_MODIFIED(SYSDATE), RECIPE_STATUS('Y'), RECIPE_COUNT(0)
	 * @param conn : Connection객체
	 * @param recipe : Recipe객체<br>
	 * recipeTitle, recipeWriterNo, recipeCategoryNo필드들이 초기화 된 상태
	 * @return :
	 * INSERT구문 수행이 성공한 행의 개수
	 */
	public int insertRecipe(SqlSession sqlSession, Recipe recipe) {
		
		return sqlSession.insert("recipeMapper", recipe);
	}
	
	
	/**
	 * 레시피 사진 원본명, 수정본명, 경로, 레벨(순서넘버링) 정보가 담긴 ArrayList를 받아<br>
	 * ArrayList에 값이 존재하는 동안 반복하며 레시피 사진을 INSERT하는 기능<br>
	 * PK종류는 모두 시퀀스로 생성/참조되며 나머지 정보들에는 아래와 같이 테이블의 Default값이 들어감<br>
	 * RECIPE_PIC_DATE(SYSDATE), RECIPE_PIC_STATUS('Y')
	 * @param conn : Connection객체
	 * @param recipePicList : RecipePic리터럴의 ArrayList<br>
	 * 내부 각 객체의 recipePicNameOrigin, recipePicNameUpload, recipePicPath, recipePicLev필드들이 초기화된 상태
	 * @return :
	 * INSERT구문 수행이 성공한 행의 개수
	 */
	public int insertRecipePic(SqlSession sqlSession, ArrayList<RecipePic> recipePicList) {
		/*
		// 1로 초기화 후 executeUpdate결과를 곱함 => 하나라도 실패 시 0반환
		int result = 1;
		String sql = prop.getProperty("insertRecipePic");
		
		for(RecipePic recipePic : recipePicList) {
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, recipePic.getRecipePicNameOrigin());
				pstmt.setString(2, recipePic.getRecipePicNameUpload());
				pstmt.setString(3, recipePic.getRecipePicPath());
				pstmt.setInt(4, recipePic.getRecipePicLev());
				
				result *= pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		*/
		// 몰라...
		return sqlSession.insert("recipeMapper.insertRecipePic", recipePicList);
	}
	
	
	/**
	 * 레시피 재료, 재료량 정보가 담긴 ArrayList를 받아<br>
	 * ArrayList에 값이 존재하는 동안 반복하며 레시피 사진을 INSERT하는 기능<br>
	 * PK종류는 모두 시퀀스로 생성/참조됨<br>
	 * @param conn : Connection객체
	 * @param ingredientList : Ingredient리터럴의 ArrayList<br>
	 * 내부 각 객체의 ingredient, ingredientAmount 필드들이 초기화된 상태
	 * @return :
	 * INSERT구문 수행이 성공한 행의 개수
	 */
	public int insertIngredient(SqlSession sqlSession, ArrayList<Ingredient> ingredientList) {
		// 1로 초기화 후 executeUpdate결과를 곱함 => 하나라도 실패 시 0반환
		int result = 1;
		String sql = prop.getProperty("insertIngredient");
		
		for(Ingredient ingredient : ingredientList) {
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, ingredient.getIngredient());
				pstmt.setString(2, ingredient.getIngredientAmount());
				
				result *= pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	 
	 
	/**
	 * 요리 과정 타이틀, 과정 내용, 레벨(순서 넘버링) 정보가 담긴 ArrayList를 받아<br>
	 * ArrayList에 값이 존재하는 동안 반복하며 레시피 사진을 INSERT하는 기능<br>
	 * PK종류는 모두 시퀀스로 생성/참조되며 나머지 정보들에는 아래와 같이 테이블의 Default값이 들어감<br>
	 * COOK_STEPS_LEV(1)
	 * @param conn : Connection객체
	 * @param cookStepsList : CookSteps리터럴의 ArrayList<br>
	 * 내부 각 객체의 필드들이 초기화된 상태
	 * @return :
	 * INSERT구문 수행이 성공한 행의 개수
	 */
	public int insertCookSteps(SqlSession sqlSession, ArrayList<CookSteps> cookStepsList) {
		// 1로 초기화 후 executeUpdate결과를 곱함 => 하나라도 실패 시 0반환
		int result = 1;
		String sql = prop.getProperty("insertCookSteps");
		
		for(CookSteps cookSteps : cookStepsList) {
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, cookSteps.getCookStepsTitle());
				pstmt.setString(2, cookSteps.getCookStepsContent());
				pstmt.setInt(3, cookSteps.getCookStepsLev());
				
				result *= pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	/**
	 * 레시피 태그 번호 정보가 담긴 ArrayList를 받아<br>
	 * ArrayList에 값이 존재하는 동안 반복하며 레시피 사진을 INSERT하는 기능<br>
	 * PK종류는 모두 시퀀스로 생성/참조됨<br>
	 * @param conn : Connection객체
	 * @param tagNoList : Integer리터럴의 ArrayList<br>
	 * 내부 각 객체의 필드들이 초기화된 상태
	 * @return :
	 * INSERT구문 수행이 성공한 행의 개수
	 */
	public int insertRecipeTag(SqlSession sqlSession, ArrayList<Integer> tagNoList) {
		// 1로 초기화 후 executeUpdate결과를 곱함 => 하나라도 실패 시 0반환
		int result = 1;
		String sql = prop.getProperty("insertRecipeTag");
		
		for(int tagNo : tagNoList) {
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, tagNo);
				
				result *= pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	/**
	 * 특정 번호 레시피(PK)에 댓글을 입력하는 기능
	 * @param reply : replyContent, memNo, recipeNo필드가 초기화된 Reply객체
	 */
	public int insertReply(SqlSession sqlSession, Reply reply) {

		return sqlSession.insert("recipeMapper.insertReply", reply);
	}
	
	
	/*
	public ArrayList<UnRecipe> selectUnRecipeForModal(SqlSession sqlSession, int memNo) {
		
		ArrayList<UnRecipe> uList = new ArrayList();
		String sql = prop.getProperty("selectUnRecipeForModal");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, memNo);
			
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					UnRecipe ur = new UnRecipe();
					ur.setUnRecipeNo(rset.getInt("UN_RECIPE_NO"));
					ur.setUnRecipeTitle(rset.getString("UN_RECIPE_TITLE"));
					uList.add(ur);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}
	*/
	
	
	
	
	
	
	
	
	
}//class.end