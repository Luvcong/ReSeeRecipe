package com.kh.semi.notice.model.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.notice.model.dao.NoticeDao;
import com.kh.semi.notice.model.vo.Notice;
import com.kh.semi.notice.model.vo.NoticePic;
import com.kh.semi.tag.model.dao.TagDao;
import com.kh.semi.tag.model.vo.Tag;

public class NoticeService {
	
	public ArrayList<Notice> selectList() {
		
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectList(conn);
		
		close(conn); 
		
		return list;
	}
	
	/**
	 * @return 조회할 공지사항 게시글 수
	 */
	public int selectNoticelistCount() {
		
		Connection conn =  getConnection();
		
		int noticelistCount = new NoticeDao().selectNoticelistCount(conn);
		
		close(conn);
		
		return noticelistCount;
	}
	
	/**
	 * @param pg 공지사항게시글 페이징
	 * @return 조회된 공지사항 게시글 목록(+페이징처리)
	 */
	public ArrayList<Notice> selectNoticeAll(PageInfo pg){
		
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeAll(conn, pg);
		
		close(conn);
		
		return list;
	}
	
	
	/**
	 * @param n 공지사항 제목, 공지사항 내용
	 * @param np 공지사항 사진원본파일명, 사진수정파일명, 사진저장경로
	 * @param extractedValues 입력한 해시태그명
	 * @return 트랜잭션 처리 결과
	 */
	public int insertNotice(Notice n, NoticePic np, List<Tag> tagList) {
		
		Connection conn = getConnection();
		
		// 반드시 수행되어야하는 구문
		// Notice 테이블에 INSERT 하나의 DAO 는 하나의 SQL문만 수행
		int result1 = new NoticeDao().insertNotice(conn, n);
		
		int result2 = 1;
		int result3 = 1;
		
	
		if(np != null) {
			result2 = new NoticeDao().insertNoticePic(conn, np);
		}
		
		if(tagList != null) {
			// 작성한 해시태그명에 해당하는 해시태그 번호 조회해오기
			ArrayList<Tag> list = new TagDao().selectTagNo(conn, tagList);
						
			// 가장 마지막 공지사항 번호 DB에서 조회해오기 -- 할 필요 없음 
			result3 = new NoticeDao().insertNoticeTag(conn, list);
						
		}

		// 3) 트랜잭션 처리
		// result1도 성공 result2도 성공 result3도 성공일 때만  commit
		// 셋 중 하나라도 실패하면 무조건 rollback
		
		if((result1 * result2 * result3) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		
		return (result1 * result2 * result3); //result1 / (result2 * result3); //(result1 * result2 * result3)
	}
	
	/** 
	 * @param ManageNoticeNo 선택한 공지사항 게시글의 번호
	 * @return 선택한 공지사항게시글의 정보
	 */
	public Notice selectNoticeInfo(int ManageNoticeNo) {
		
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn, ManageNoticeNo);
		
		close(conn);
		
		return n;
	}
	
	
	
	/**
	 * @param ManageNoticeNo 
	 * @return 
	 */
	public NoticePic selectNoticePic(int ManageNoticeNo) {
		
		Connection conn = getConnection();
		
		NoticePic np = new NoticeDao().selectNoticePic(conn, ManageNoticeNo);
		
		close(conn);
		
		return np;
	}
	
	public ArrayList<Tag> selectNoticeTag(int ManageNoticeNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Tag> tag = new NoticeDao().selectNoticeTag(conn, ManageNoticeNo);
		
		close(conn);
		
		return tag;
	}
}
