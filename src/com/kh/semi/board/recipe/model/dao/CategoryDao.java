package com.kh.semi.board.recipe.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.semi.board.recipe.model.vo.RecipeCategory;
import com.kh.semi.common.model.vo.PageInfo;

public class CategoryDao {
	
	/**
	 * 등록되어 있는 카테고리 리스트 조회를 처리해주는 method
	 * @param sqlSession
	 * @return 등록되어 있는 카테고리 리스트
	 * @author JH
	 * @Date : 2023. 10. 6.
	 * @Update : 2023. 10. 27.
	 */
	public ArrayList<RecipeCategory> selectCategoryList(SqlSession sqlSession, PageInfo pi) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return (ArrayList)sqlSession.selectList("categoryMapper.selectCategoryList", null, rowBounds);
		
	}	// selectCategoryList
	
	
	/**
	 * 카테고리 리스트 행 카운트
	 * @param sqlSession
	 * @return DB에 저장되어 있는 카테고리 수
	 * @author JH
	 * @Date : 2023. 10. 13.
	 * @Update : 2023. 10. 27.
	 */
	public int selectCategoryListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("categoryMapper.selectCategoryListCount");
		
	}	// selectCategoryListCount
	
	
	/**
	 * 카테고리 추가 요청을 처리해주는 method
	 * @param sqlSession
	 * @param recipeCategoryName - 추가 카테고리명
	 * @return 카테고리 추가 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 6.
	 * @Update : 2023. 10. 27.
	 */
	public int insertCategory(SqlSession sqlSession, String recipeCategoryName) {
		return sqlSession.insert("categoryMapper.insertCategory", recipeCategoryName);
		
	}	// insertCategory
	
	
	/**
	 * 카테고리 삭제 요청을 처리해주는 method
	 * @param sqlSession
	 * @param categoryNo 카테고리 SEQ_NO
	 * @return 카테고리 삭제 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 9.
	 * @Update : 2023. 10. 28.
	 */
	public int deleteCategory(SqlSession sqlSession,  int categoryNo) {
		return sqlSession.delete("categoryMapper.deleteCategory", categoryNo);
		
	}	// deleteCategory
	
	
	/**
	 * 카테고리 삭제 요청시 해당 카테고리에 포함되어 있는 레시피 글 카테고리유형/글상태 업데이트
	 * @param sqlSession
	 * @param categoryNo 카테고리 SEQ_NO 식별값
	 * @return 레시피글 업데이트 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 10.
	 * @Update : 2023. 10. 28.
	 * @Update : 2023. 10. 28.
	 */
	public int updateRecipeStatus(SqlSession sqlSession, int categoryNo) {
		return sqlSession.update("categoryMapper.updateRecipeStatus", categoryNo);
		
	}	// updateRecipeStatus
	
	
	/**
	 * 카테고리명 변경을 처리해주는 method
	 * @param sqlSession
	 * @param categoryName 기존 카테고리명
	 * @param categoryUpdateName 변경 카테고리명
	 * @return 카테고리명 변경 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 11.
	 * @Update : 2023. 10. 28.
	 */
	public int updateCategory(SqlSession sqlSession, HashMap<String, String> map) {
		return sqlSession.update("categoryMapper.updateCateogry", map);
		
	}	// updateCategory
	
	
	/**
	 * 카테고리명 중복여부 체크 method
	 * @param sqlSession
	 * @param categoryName 기존 카테고리명
	 * @return 기존 카테고리명 존재 여부 (1이면 존재 / 0이면 없음)
	 * @author JH
	 * @Date : 2023. 10. 11.
	 * @Update : 2023. 10. 28.
	 */
	public int categoryNameCheck(SqlSession sqlSession, HashMap<String, String> map) {
		
		return sqlSession.selectOne("categoryMapper.categoryNameCheck", map);
	}	// categoryNameCheck

	
	/**
	 * 카테고리명 검색시 키워드에 일치하는 리스트 요청 method
	 * @param sqlSession
	 * @param checkCategoryName 사용자가 검색한 카테고리명 키워드
	 * @return 키워드에 해당하는 카테고리명 리스트
	 * @author JH
	 * @Date : 2023. 10. 13.
	 * @Update : 2023. 10. 28.
	 */
	public ArrayList<RecipeCategory> searchCategoryName(SqlSession sqlSession, String searchCategoryName){
		return (ArrayList)sqlSession.selectList("categoryMapper.searchCategory", searchCategoryName);
		
	}	// checkCategory
	
	
	/**
	 * 카테고리 추가시 중복체크 확인해주는 method
	 * @param sqlSession
	 * @param addCategoryName 카테고리 추가시 중복체크명
	 * @return 중복체크 여부
	 * @author JH
	 * @Date : 2023. 10. 17.
	 * @Update : 2023. 10. 28.
	 */
	public int duplicateCheckCategory(SqlSession sqlSession, String categoryNewName) {
		return sqlSession.selectOne("categoryMapper.categoryNameCheck", categoryNewName);
		
	}	// duplicateCheckCategory
	

}	// end class
