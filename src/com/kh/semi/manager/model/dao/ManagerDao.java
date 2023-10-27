package com.kh.semi.manager.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.semi.member.model.vo.Member;
import static com.kh.semi.common.JDBCTemplate.*;

public class ManagerDao {
	
	// Properties 객체 생성
	private Properties prop = new Properties();
	
	// 기본생성자 호출 시 manager-mapper.xml
	public ManagerDao() {
		
		String file = ManagerDao.class.getResource("/sql/manager/manager-mapper.xml").getPath();
	
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member managerSetting(Connection conn, int adminNo) {
		
		Member m = new Member();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("managerSetting");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, adminNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m.setMemNo(rset.getInt("MEM_NO"));
				m.setMemId(rset.getString("MEM_ID"));
				m.setMemName(rset.getString("MEM_NAME"));
				m.setMemNickname(rset.getString("MEM_NICKNAME"));
				m.setMemEmail(rset.getString("MEM_EMAIL"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setModifyDate(rset.getDate("MODIFY_DATE"));
				m.setMemPicture(rset.getString("MEM_PICTURE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}
	
	public int adminUpdate(Connection conn, Member m, String adminPicture) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("adminUpdate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			// 수정할 관리자 이름, 닉네임, 이메일, 사진(경로 + 수정명)
			pstmt.setString(1, m.getMemName());
			pstmt.setString(2, m.getMemNickname());
			pstmt.setString(3, m.getMemEmail());
			pstmt.setString(4, adminPicture);
			pstmt.setInt(5, m.getMemNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	

}
