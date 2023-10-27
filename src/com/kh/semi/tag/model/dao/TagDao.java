package com.kh.semi.tag.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.tag.model.vo.Tag;

public class TagDao {
	
	private Properties prop = new Properties();
	
	public TagDao() {
		
		String fileName = TagDao.class.getResource("/sql/tag/tag-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @param conn
	 * @return 등록된 해시태그명 조회한 결과
	 */
	public ArrayList<Tag> selectAllTagname(Connection conn){
		
		ArrayList<Tag> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAllTagname");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Tag t = new Tag();
				t.setTagNo(rset.getInt("TAG_NO"));
				t.setTagName(rset.getString("TAG_NAME"));
				
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	/**
	 * @param conn
	 * @param extractedValues 사용자가 입력한 해시태그명들
	 * @return 사용자가 입력한 해시태그명에 해당하는 해시태그번호들
	 */
	public ArrayList<Tag> selectTagNo(Connection conn, List<Tag> tagList) {

		ArrayList<Tag> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String sql = prop.getProperty("selectTagNo");
			pstmt = conn.prepareStatement(sql);
			for (Tag tag : tagList) {
				pstmt.setString(1, tag.getValue());

				rset = pstmt.executeQuery();

				while (rset.next()) {
					Tag t = new Tag();
					t.setTagNo(rset.getInt("TAG_NO"));
					list.add(t);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Tag> selectPHashTag(Connection conn, PageInfo pi){
		
		ArrayList<Tag> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPHashTag");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pi.getStartRow());
			pstmt.setInt(2, pi.getEndRow());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Tag t = new Tag();
				
				t.setTagNo(rset.getInt("TAG_NO"));
				t.setTagName(rset.getString("TAG_NAME"));
				t.setTagCount(rset.getInt("COUNT"));
				
				list.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int hashTagInsert(Connection conn, String hashtagName) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("hashTagInsert");
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hashtagName);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int hashtagUpdate(Connection conn, int hashNo, String ChashName) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("hashtagUpdate");
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ChashName);
			pstmt.setInt(2, hashNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int hashtagDelete(Connection conn, int tagNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("hashtagDelete");
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tagNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
