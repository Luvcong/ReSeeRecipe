package com.kh.semi.board.recipe.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.board.recipe.model.dao.CategoryDao;
import com.kh.semi.board.recipe.model.vo.RecipeCategory;
import com.kh.semi.common.model.vo.PageInfo;

public class CategoryService {
	
	private CategoryDao categoryDao;
	
	public CategoryService() {
		super();
		categoryDao = new CategoryDao();
	}
	
	/**
	 * 등록되어 있는 카테고리 리스트 조회를 요청하는 method
	 * @return 등록되어 있는 카테고리 리스트
	 * @author JH
	 * @Date : 2023. 10. 6.
	 */
	public ArrayList<RecipeCategory> selectCategoryList(PageInfo pi){
		
		Connection conn = getConnection();
		
		ArrayList<RecipeCategory> list = categoryDao.selectCategoryList(conn, pi);
		
		close(conn);
		
		return list;
		
	}	// selectCategoryList
	
	
	
	/**
	 * 카테고리 추가를 요청하는 method
	 * @param recipeCategoryName - 추가 카테고리명
	 * @return 카테고리 추가 성공여부
	 * @author JH
	 * @Date : 2023. 10. 6.
	 */
	public int insertCategory(String recipeCategoryName) {
		
		Connection conn = getConnection();
		
		int result = categoryDao.insertCategory(conn, recipeCategoryName);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}	// insertCategory
	
	
	
	/**
	 * 카테고리 삭제 및 해당 레시피글 업데이트를 요청하는 method
	 * @param categoryNo 카테고리 SEQ_NO 식별값
	 * @return 카테고리 삭제 및 레시피글 업데이트성공여부
	 * @author JH
	 * @Date : 2023. 10. 11.
	 */
	public int deleteCategory(int categoryNo, int categoryCount) {
		
		Connection conn = getConnection();
		
		if(categoryCount > 0) {
			// 사용자가 삭제 요청한 카테고리의 레시피글 상태(STATUS = N && RECIPE_CATEGORY_NO = 0) 변경
			int resultUpdate = categoryDao.updateRecipeStatus(conn, categoryNo);
		}
		
		// 사용자가 요청한 카테고리 삭제
		int resultDelete = categoryDao.deleteCategory(conn, categoryNo);
		
		// 게시글 수가 있던 없던간에 삭제된 결과에 따라 트랜잭션 처리를 진행
		// > resultDelete 기준으로 커밋	
		if(resultDelete > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return resultDelete;
	}	// deleteCategory
	
	
	
	/**
	 * 카테고리명 변경 요청 및 중복체크를 요청하는 method 
	 * @param categoryName 기존 카테고리명
	 * @param categoryUpdateName 변경 카테고리명
	 * @return 카테고리명 변경 성공 여부 및 중복체크
	 * @author JH
	 * @Date : 2023. 10. 11.
	 */
	public int updateCategory(String categoryName, String categoryUpdateName) {
		
		Connection conn = getConnection();
		
		// 중복확인 method >> 0이 돌아오면 updateCategory 실행해야함 1이 돌아오면 롤백
		
		int resultUpdate = 0;
		int resultCheck = categoryDao.categoryNameCheck(conn, categoryUpdateName);
		System.out.println("resultCheck : " + resultCheck );	// 존재하는 경우 1	, 존재하지 않는데도 현재 1 출력
		
		if(resultCheck == 0) {
			resultUpdate = categoryDao.updateCategory(conn, categoryName, categoryUpdateName);
		}
		
		if(resultUpdate > 0) commit(conn);
			else rollback(conn);
		
		System.out.println("resultUpdate : " + resultUpdate );		// check : 1 / update : 0
		
		close(conn);
		
		return resultUpdate;
	}	// updateCategory
	
	
	/**
	 * 카테고리 리스트 행 카운트
	 * @return DB에 저장되어 있는 카테고리 수
	 * @author JH
	 * @Date : 2023. 10. 12.
	 */
	public int selectCategoryListCount() {
		
		Connection conn = getConnection();
		
		int categoryListCount = categoryDao.selectCategoryListCount(conn);
		
		close(conn);
		
		return categoryListCount;
	}	// selectCategoryListCount
	
	
	
	/**
	 * 카테고리명 검색시 키워드에 일치하는 리스트 요청 method
	 * @param checkCategoryName 
	 * @return 키워드에 해당하는 카테고리명 리스트
	 * @author JH
	 * @Date : 2023. 10. 13.
	 */
	public ArrayList<RecipeCategory> checkCategory(String checkCategoryName){
		
		Connection conn = getConnection();
		// System.out.println("sevice : " + checkCategoryName);
		ArrayList<RecipeCategory> list = categoryDao.checkCategory(conn, checkCategoryName);
		// System.out.println("service : " + list);
		
		close(conn);
		
		return list;
	}	// checkCategory
	
	
	/**
	 * 카테고리명 추가시 기존 카테고리명과 중복되는지 일치하는지 확인 요청
	 * @param addCategoryName
	 * @return 키워드에 해당하는지 일치여부 (일치하면 1 / 없으면 0)
	 * @author JH
	 * @Date : 2023. 10. 17.
	 */
	public int duplicateCheckCategory(String addCategoryName) {
		
		Connection conn = getConnection();
		
		int count = categoryDao.duplicateCheckCategory(conn, addCategoryName);
		
		close(conn);
		
		return count;
		
	}	// categoryNameCheck
	
	
	

}	// end class
