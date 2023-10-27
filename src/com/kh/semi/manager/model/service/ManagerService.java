package com.kh.semi.manager.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.semi.manager.model.dao.ManagerDao;
import com.kh.semi.member.model.vo.Member;

public class ManagerService {
	
	
	/**
	 * @param adminNo 관리자 번호
	 * @return 접속된 관리자의 정보
	 */
	public Member managerSetting(int adminNo) {
		
		Connection conn =  getConnection();
		
		Member m = new ManagerDao().managerSetting(conn, adminNo);
		
		close(conn);
		
		return m;
	}
	
	/**
	 * @param m 관리자 정보(관리자 번호, 관리자 이름, 관리자 닉네임, 관리자 이메일)
	 * @param adminPicture (관리자 프로필 사진 파일이름 + 경로)
	 * @return 관리자 정보 수정 성공1 실패0
	 */
	public int adminUpdate(Member m, String adminPicture) {
		
		Connection conn = getConnection();
		
		int result = new ManagerDao().adminUpdate(conn, m, adminPicture);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
}
