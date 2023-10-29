package com.kh.semi.board.recipe.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.semi.board.recipe.model.vo.RecipeCategory;
import com.kh.semi.common.model.vo.PageInfo;

public interface CategoryService {

	/**
	 * 카테고리 리스트 조회
	 * @param pi
	 * @return 등록되어 있는 카테고리 리스트
	 */
	ArrayList<RecipeCategory> selectCategoryList(PageInfo pi);
	
	/**
	 * 카테고리 리스트 행 수 조회
	 * @return DB에 저장되어 있는 카테고리 수
	 */
	int selectCategoryListCount();
	
	/**
	 * 카테고리 추가 요청
	 * @param recipeCategoryName - 추가 카테고리명
	 * @return 카테고리 추가 성공여부
	 */
	int insertCategory(String recipeCategoryName);
	
	/**
	 * 카테고리 삭제 및 레시피 글 상태 업데이트
	 * @param categoryNo - 카테고리 SEQ_NO 식별값
	 * @param categoryCount - 삭제 카테고리에 담겨있는 게시글 수
	 * @return 카테고리 삭제 및 레시피글 업데이트성공여부
	 */
	int deleteCategory(int categoryNo, int categoryCount);
	
	/**
	 * 카테고리명 변경 및 중복체크
	 * @param categoryName - 기존 카테고리명
	 * @param categoryUpdateName - 변경 카테고리명
	 * @return 카테고리명 변경 성공 여부 및 중복체크
	 */
	int updateCategory(HashMap<String, String> map);
	
	/**
	 * 카테고리 키워드 검색
	 * @param searchCategoryName - 카테고리 검색 키워드명
	 * @return 키워드에 해당하는 카테고리명 리스트
	 */
	ArrayList<RecipeCategory> searchCategoryName(String searchCategoryName);
	
	/**
	 * 카테고리 중복 일치 여부 확인
	 * @param addCategoryName - 변경 카테고리명
	 * @return 키워드 일치여부 (일치 == 1 / 불일치 == 0)
	 */
	int duplicateCheckCategory(String categoryNewName);
	
	
}	// end class
