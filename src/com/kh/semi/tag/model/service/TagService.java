package com.kh.semi.tag.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.tag.model.dao.TagDao;
import com.kh.semi.tag.model.vo.Tag;

/**
 * @author LIM
 * 등록된 해시태그 명 조회 => whitelist 배열에 추가 => 등록된 해시태그만 이용할 수 있는 기능
 */
public class TagService {
	
	public ArrayList<Tag> selectALlTagname(){
		
		Connection conn = getConnection();
		
		ArrayList<Tag> list = new TagDao().selectAllTagname(conn);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Tag> selectPHashTag(PageInfo pi){
		
		Connection conn = getConnection();
		
		ArrayList<Tag> list = new TagDao().selectPHashTag(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	public int hashTagInsert(String hstagName) {
		
		Connection conn = getConnection();
		
		int result = new TagDao().hashTagInsert(conn, hstagName);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int hashtagUpdate(int hashNo, String ChashName) {
		
		Connection conn = getConnection();
		
		int result = new TagDao().hashtagUpdate(conn, hashNo, ChashName);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int hashtagDelete(int[] tagNo) {
		
		Connection conn = getConnection();
		int result = 0;
		
		for(int i = 0; i < tagNo.length; i++) {
			result = new TagDao().hashtagDelete(conn, tagNo[i]);
		}
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	
	
	

}
