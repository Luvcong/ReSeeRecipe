package com.kh.semi.board.recipe.model.service;

import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.close;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.board.recipe.model.dao.UnRecipeDao;
import com.kh.semi.board.recipe.model.vo.UnRecipe;

public class UnRecipeService {

	public ArrayList<UnRecipe> selectUnRecipeList(int memNo) {
		
		Connection conn = getConnection();
		ArrayList<UnRecipe> unReList = new UnRecipeDao().selectUnRecipeList(conn, memNo);
		close(conn);
		return unReList;
	}
	
	
	
	
}
