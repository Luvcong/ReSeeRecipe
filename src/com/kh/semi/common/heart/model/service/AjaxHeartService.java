package com.kh.semi.common.heart.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.common.heart.model.dao.AjaxHeartDao;
import com.kh.semi.common.heart.model.vo.Heart;
import com.kh.semi.common.heart.model.vo.NoticeHeart;

import static com.kh.semi.common.JDBCTemplate.*;

public class AjaxHeartService {
	
	
	/*************** 특정 대상 하트 카운트 조회 기능 ***************************************/
	public int htCountRecipe(int htTargetNo) {
		
		// 기본 변수 세팅
		Connection conn = getConnection();
		
		// 서비스 호출
		int result = new AjaxHeartDao().htCountRecipe(conn, htTargetNo);
		
		// 자원반납
		close(conn);

		return result;
	}
	
	
	public int htCountBookmark(int htTargetNo) {
		
		Connection conn = getConnection();
		int result = new AjaxHeartDao().htCountBookmark(conn, htTargetNo);
		close(conn);
		return result;
	}
	
	
	public int htCountNotice(int htTargetNo) {
		
		Connection conn = getConnection();
		int result = new AjaxHeartDao().htCountNotice(conn, htTargetNo);
		close(conn);
		return result;
	}
	
	
	public int htCountSubsc(int htTargetNo) {
		
		Connection conn = getConnection();
		int result = new AjaxHeartDao().htCountSubsc(conn, htTargetNo);
		close(conn);
		return result;
	}
	
	
	public int htCountReply(int htTargetNo) {
		
		Connection conn = getConnection();
		int result = new AjaxHeartDao().htCountReply(conn, htTargetNo);
		close(conn);
		return result;
	}
	/****************************************************************************/
	
	
	/*************** 하트 추가/삭제 기능 ***********************************************/
	public int htChangeRecipe(Heart ht) {
		// 기본 변수 세팅
		boolean flag = false;
		int result = 0;
		AjaxHeartDao ahd = new AjaxHeartDao();
		Connection conn = getConnection();
		
		// Dao의 좋아요여부 체크하는 메소드 호출
		flag = ahd.isHeartRecipe(conn, ht);
	
		// false(좋아요내역 없을 경우) : 좋아요 INSERT메소드 호출 result에 int결과 받음
		// true(좋아요 내역 있을 경우) : 좋아요 DELETE메소드 호출 result에 int결과 받음
		if(!flag && (ahd.insertHeartRecipe(conn, ht) > 0)) {
			result = 1;
			commit(conn);
		} else if(flag && (ahd.deleteHeartRecipe(conn, ht) > 0)) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	public int htChangeBookmark(Heart ht) {

		boolean flag = false;
		int result = 0;
		AjaxHeartDao ahd = new AjaxHeartDao();
		Connection conn = getConnection();
		
		flag = ahd.isHeartBookmark(conn, ht);
	
		if(!flag && (ahd.insertHeartBookmark(conn, ht) > 0)) {
			result = 1;
			commit(conn);
		} else if(flag && (ahd.deleteHeartBookmark(conn, ht) > 0)) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	public int htChangeNotice(Heart ht) {

		boolean flag = false;
		int result = 0;
		AjaxHeartDao ahd = new AjaxHeartDao();
		Connection conn = getConnection();
		
		flag = ahd.isHeartNotice(conn, ht);
	
		if(!flag && (ahd.insertHeartNotice(conn, ht) > 0)) {
			result = 1;
			commit(conn);
		} else if(flag && (ahd.deleteHeartNotice(conn, ht) > 0)) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	public int htChangeSubsc(Heart ht) {

		boolean flag = false;
		int result = 0;
		AjaxHeartDao ahd = new AjaxHeartDao();
		Connection conn = getConnection();
		
		flag = ahd.isHeartSubsc(conn, ht);
	
		if(!flag && (ahd.insertHeartSubsc(conn, ht) > 0)) {
			result = 1;
			commit(conn);
		} else if(flag && (ahd.deleteHeartSubsc(conn, ht) > 0)) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	public int htChangeReply(Heart ht) {

		boolean flag = false;
		int result = 0;
		AjaxHeartDao ahd = new AjaxHeartDao();
		Connection conn = getConnection();
		
		flag = ahd.isHeartReply(conn, ht);
	
		if(!flag && (ahd.insertHeartReply(conn, ht) > 0)) {
			result = 1;
			commit(conn);
		} else if(flag && (ahd.deleteHeartReply(conn, ht) > 0)) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

		
}//class.end