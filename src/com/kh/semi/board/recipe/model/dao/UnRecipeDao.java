package com.kh.semi.board.recipe.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.semi.board.recipe.model.vo.UnRecipe;

public class UnRecipeDao {
	
	private Properties prop = new Properties();
	
	public UnRecipeDao() {
		String filePath = UnRecipeDao.class.getResource("/sql/board/unrecipe-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 현재 로그인한 멤버가 저장한 임시저장글 목록 전체조회
	 * @param conn : Connection객체
	 * @param memNo : 현재 로그인한 멤버 번호(PK)
	 * @return
	 * : 조회결과에 따라 모든 필드가 초기화된 UnRecipe 객체가 담긴 ArrayList를 반환
	 */
	public ArrayList<UnRecipe> selectUnRecipeList(Connection conn, int memNo){
		ArrayList<UnRecipe> unReList = new ArrayList();
		String sql = prop.getProperty("selectUnRecipeList");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, memNo);
			
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					UnRecipe unR = new UnRecipe();
					unR.setUnRecipeNo(rset.getInt("UN_RECIPE_NO"));
					unR.setUnRecipeTitle(rset.getString("UN_RECIPE_TITLE"));
					unR.setUnRecipeDate(rset.getString("UN_RECIPE_DATE"));
					unR.setUnRecipeModified(rset.getString("UN_RECIPE_MODIFIED"));
					unR.setUnCategoryNo(rset.getInt("UN_CATEGORY_NO"));
					unR.setUnRecipeWriterNo(memNo);
					unReList.add(unR);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(unReList);
		return unReList;
	}

	
	
	
}// class.end