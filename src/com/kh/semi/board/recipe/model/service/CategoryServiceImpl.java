package com.kh.semi.board.recipe.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.semi.board.recipe.model.dao.CategoryDao;
import com.kh.semi.board.recipe.model.vo.RecipeCategory;
import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.common.template.Template;

public class CategoryServiceImpl implements CategoryService {
	
	private CategoryDao categoryDao = new CategoryDao();

	// 카테고리 리스트 조회
	@Override
	public ArrayList<RecipeCategory> selectCategoryList(PageInfo pi) {
		
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<RecipeCategory> list = categoryDao.selectCategoryList(sqlSession, pi);
		
		sqlSession.close();
		
		return list;
	}	// selectCategoryList
	
	
	// 카테고리 리스트 행 수 조회
	@Override
	public int selectCategoryListCount() {
		
		SqlSession sqlSession = Template.getSqlSession();
		int selectCategoryListCount = categoryDao.selectCategoryListCount(sqlSession);
		
		sqlSession.close();
		
		return selectCategoryListCount;
	}	// selectCategoryListCount

	
	// 카테고리 추가 요청
	@Override
	public int insertCategory(String recipeCategoryName) {
		
		SqlSession sqlSession = Template.getSqlSession();
		int result = categoryDao.insertCategory(sqlSession, recipeCategoryName);
		// System.out.println(result);
		if(result > 0) sqlSession.commit(); 
		
		sqlSession.close();
		
		return result;
	}	// insertCategory
	
	
	// 카테고리 삭제 및 레시피 글 상태 업데이트
	@Override
	public int deleteCategory(int categoryNo, int categoryCount) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		if(categoryCount > 0) {
			int resultUpdate = categoryDao.updateRecipeStatus(sqlSession, categoryNo);
		}
		
		int resultDelete = categoryDao.deleteCategory(sqlSession, categoryNo);
		
		if(resultDelete > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return resultDelete;
	}	// deleteCategory
	
	
	// 카테고리명 변경 및 중복체크
	@Override
	public int updateCategory(HashMap<String, String> map) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int resultUpdate = 0;
		int resultCheck = categoryDao.categoryNameCheck(sqlSession, map);
		
		if(resultCheck == 0) {
			resultUpdate = categoryDao.updateCategory(sqlSession, map);
		}
		
		if(resultUpdate > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return resultUpdate;
		
	}	// updateCategory

	
	// 카테고리 키워드 검색
	@Override
	public ArrayList<RecipeCategory> searchCategoryName(String searchCategoryName) {
		
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<RecipeCategory> list = categoryDao.searchCategoryName(sqlSession, searchCategoryName);
		
		sqlSession.close();
		
		return list;
		
	}	// checkCategory
	
	
	// 카테고리 중복 일치 여부 확인
	@Override
	public int duplicateCheckCategory(String categoryNewName) {
		
		SqlSession sqlSession = Template.getSqlSession();
		int result = categoryDao.duplicateCheckCategory(sqlSession, categoryNewName);
		
		sqlSession.close();
		
		return result;
	}
	

}	// CategoryServiceImpl