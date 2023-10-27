package com.kh.semi.notice.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.notice.model.vo.Notice;
import com.kh.semi.notice.model.vo.NoticePic;
import com.kh.semi.tag.model.vo.Tag;

public class NoticeDao {
	
	private Properties prop = new Properties();
	
	public NoticeDao() {
		
		String fileName = NoticeDao.class.getResource("/sql/notice/notice-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Notice> selectList(Connection conn){
		
		ArrayList<Notice> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				n.setNoticeCon(rset.getString("NOTICE_CON"));
				n.setNoticeDate(rset.getDate("NOTICE_DATE"));
				n.setNoticeCount(rset.getInt("NOTICE_COUNT"));
				n.setNoticeWriter(rset.getString("NOTICE_WRITER"));
				list.add(n);
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
	 * @return 전체 공지사항 게시글 수
	 */
	public int selectNoticelistCount(Connection conn) {
		
		int noticelistCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticelistCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				noticelistCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return noticelistCount;
	}
	
	/**
	 * @param conn DB와의 연결
	 * @param pg 페이징처리에 필요한 변수들 [ StartRow(), EndRow() 포함 ]
	 * @return 공지사항 전체 목록 + 페이징
	 */
	public ArrayList<Notice> selectNoticeAll(Connection conn, PageInfo pg) {
		
		ArrayList<Notice> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectNoticeAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pg.getStartRow());
			pstmt.setInt(2, pg.getEndRow());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				n.setNoticeWriterName(rset.getString("MEM_NICKNAME"));
				n.setNoticeCount(rset.getInt("NOTICE_COUNT"));
				n.setNoticeDate(rset.getDate("NOTICE_DATE"));
				n.setNoticeHeart(rset.getInt("COUNT(HT_NOTICE_DATE)"));
				
				list.add(n);
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
	 * @param n 공지사항 제목, 공지사항 내용
	 * @return 공지사항 게시글 등록 결과
	 */
	public int insertNotice(Connection conn, Notice n) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeCon());
			pstmt.setInt(3, Integer.parseInt(n.getNoticeWriter()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/**
	 * @param conn
	 * @param np 공지사항 사진파일원본명, 사진파일수정명, 사진파일저장경로
	 * @return 공지사항 사진 등록 수행 결과
	 */
	public int insertNoticePic(Connection conn, NoticePic np) {
		
		int result = 0;
		PreparedStatement pstmt = null;	
		String sql = prop.getProperty("insertNoticePic");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("np.getNoticePicNamgeOrigin()>>" + np.getNoticePicNamgeOrigin());
			pstmt.setString(1, np.getNoticePicNamgeOrigin());
			pstmt.setString(2, np.getNoticePicNagmeChange());
			pstmt.setString(3, np.getNoticePicPath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/**
	 * @param conn
	 * @param list 공지사항 해시태그 등록
	 * @return 해시태그 등록 수행 결과
	 */
	public int insertNoticeTag(Connection conn, ArrayList<Tag> list) {
			
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("insertNoticeTag");
			pstmt = conn.prepareStatement(sql);
			
			for(Tag tag : list) {
				pstmt.setInt(1, tag.getTagNo());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public Notice selectNotice(Connection conn, int ManageNoticeNo) {
		
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ManageNoticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				n.setNoticeCon(rset.getString("NOTICE_CON"));
				n.setNoticeWriterName(rset.getString("MEM_NICKNAME"));
				n.setNoticeDate(rset.getDate("NOTICE_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} 
		return n;
	}
	
	public NoticePic selectNoticePic(Connection conn, int ManageNoticeNo) {
		
		NoticePic np = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticePic");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ManageNoticeNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				np = new NoticePic();
				np.setNoticePicNo(rset.getInt("NOTICE_PIC_NO"));
				np.setNoticePicNamgeOrigin(rset.getString("NOTICE_PIC_NAME_ORIGIN"));
				np.setNoticePicNagmeChange(rset.getString("NOTICE_PIC_NAME_CHANGE"));
				np.setNoticePicPath(rset.getString("NOTICE_PIC_PATH"));
				np.setNoticeNo(rset.getInt("NOTICE_NO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return np;
	}
	
	public ArrayList<Tag> selectNoticeTag(Connection conn, int ManageNoticeNo) {
		
		ArrayList<Tag> tag = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeTag");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ManageNoticeNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Tag t = new Tag();
				t.setTagName(rset.getString("TAG_NAME"));
				tag.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return tag;
	}
}
