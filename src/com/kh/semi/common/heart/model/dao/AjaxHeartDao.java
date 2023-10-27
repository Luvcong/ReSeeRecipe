package com.kh.semi.common.heart.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.semi.common.JDBCTemplate.*;

import com.kh.semi.common.heart.model.vo.Heart;
import com.kh.semi.common.heart.model.vo.NoticeHeart;

public class AjaxHeartDao {
	
	
	private Properties prop = new Properties();
	
	/**
	 * AjaxHeartDao의 생성자로 객체 생성 시 heart-mapper.xml를 load, 내부의 SQL구문을 사용 가능.
	 */
	public AjaxHeartDao() {
		
		String filePath = AjaxHeartDao.class.getResource("/sql/heart/heart-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	/*************** 특정 대상 하트 카운트 조회 기능 ***************************************/
	/**
	 * 레시피 좋아요 테이블(TB_HT_RECIPE)에서 특정 레시피글 번호(PK)가 받은 총 좋아요 개수를 SELECT COUNT(*)문으로 조회.
	 * 반환값에서 결과를 int형으로 추출 후 반환.
	 * @param conn : Connection객체
	 * @param htTargetNo : 조회하려는 레시피 글의 PK번호
	 * @return : 특정 레시피에 눌린 좋아요 수 총 합산
	 */
	public int htCountRecipe(Connection conn, int htTargetNo) {
		
		int result = 0;
		String sql = prop.getProperty("htCountRecipe");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, htTargetNo);
			
			try(ResultSet rset = pstmt.executeQuery()){
				if(rset.next()) {
					result = rset.getInt("COUNT(*)");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 레시피 북마크 테이블(TB_HT_RECIPE)에서 특정 레시피글 번호(PK)가 받은 총 북마크 개수를 SELECT COUNT(*)문으로 조회.
	 * 반환값에서 결과를 int형으로 추출 후 반환.
	 * @param conn : Connection객체
	 * @param htTargetNo : 조회하려는 레시피 글의 PK번호
	 * @return : 특정 레시피가 북마킹된 수 총 합산
	 */
	public int htCountBookmark(Connection conn, int htTargetNo) {
		
		int result = 0;
		String sql = prop.getProperty("htCountBookmark");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, htTargetNo);
			
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					result = rset.getInt("COUNT(*)");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 공지사항 좋아요 테이블(TB_HT_NOTICE)에서 특정 공지사항글 번호(PK)가 받은 총 좋아요 개수를 SELECT COUNT(*)문으로 조회.
	 * 반환값에서 결과를 int형으로 추출 후 반환.
	 * @param conn : Connection객체
	 * @param htTargetNo : 조회하려는 공지사항 글의 PK번호
	 * @return : 특정 레시피에 눌린 좋아요 수 총 합산
	 */
	public int htCountNotice(Connection conn, int htTargetNo) {
		
		int result = 0;
		String sql = prop.getProperty("htCountNotice");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, htTargetNo);
			
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					result = rset.getInt("COUNT(*)");
				}
			}
 		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * 쉐프 구독 테이블(TB_HT_SUBSC)에서 특정 쉐프 번호(PK)가 받은 총 구독 개수를 SELECT COUNT(*)문으로 조회.
	 * 반환값에서 결과를 int형으로 추출 후 반환.
	 * @param conn : Connection객체
	 * @param htTargetNo : 조회하려는 쉐프의 PK번호
	 * @return : 특정 쉐프가 받은 구독 수 총 합산
	 */
	public int htCountSubsc(Connection conn, int htTargetNo) {

		int result = 0;
		String sql = prop.getProperty("htCountSubsc");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, htTargetNo);
			
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					result = rset.getInt("COUNT(*)");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 리플 좋아요 테이블(TB_HT_REPLY)에서 특정 리플 번호(PK)가 받은 총 좋아요 개수를 SELECT COUNT(*)문으로 조회.
	 * 반환값에서 결과를 int형으로 추출 후 반환.
	 * @param conn : Connection객체
	 * @param htTargetNo : 조회하려는 리플의 PK번호
	 * @return : 특정 리플에 눌린 좋아요 수 총 합산
	 */
	public int htCountReply(Connection conn, int htTargetNo) {
		
		int result = 0;
		String sql = prop.getProperty("htCountReply");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, htTargetNo);
			
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					result = rset.getInt("COUNT(*)");
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
	/*************** 좋아요 여부 체크 기능 *********************************************/
	/**
	 * 현재 로그인한 유저가 특정 레시피에 좋아요를 누른 상태인지 SELECT문으로 조회 후 좋아요 누른 여부를 boolean형으로 반환.
	 * @param conn : Connection객체
	 * @param ht : Heart객체로 현재 로그인한 멤버의 회원번호(PK) 조회하려는 레시피글 번호(PK)
	 * @return : 좋아요를 누른 상태라면 true, 아니라면 false반환
	 */
	public boolean isHeartRecipe(Connection conn, Heart ht) {
		
		// 기본 변수 세팅
		boolean flag = false;
		String sql = prop.getProperty("isHeartRecipe");
		
		// TB_HT_RECIPE 테이블에 특정 멤버PK+레시피PK값을 가진 ROW가 있는지 SELECT구문 실행
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			// 만약 조회값이 존재한다면 boolean형 true값 반환
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public boolean isHeartBookmark(Connection conn, Heart ht) {
		
		// 기본 변수 세팅
		boolean flag = false;
		String sql = prop.getProperty("isHeartBookmark");
		
		// 북마크 테이블에 특정 멤버PK+레시피PK값을 가진 ROW가 있는지 SELECT구문 실행
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
	
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public boolean isHeartNotice(Connection conn, Heart ht) {
		
		boolean flag = false;
		String sql = prop.getProperty("isHeartNotice");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());

			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public boolean isHeartSubsc(Connection conn, Heart ht) {
		
		boolean flag = false;
		String sql = prop.getProperty("isHeartSubsc");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public boolean isHeartReply(Connection conn, Heart ht) {
		
		boolean result = false;
		String sql = prop.getProperty("isHeartReply");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1,ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/****************************************************************************/
	

	/*************** 좋아요 추가/삭제(INSERT/DELETE) 기능 ******************************/
	public int insertHeartRecipe(Connection conn, Heart ht) {
		
		int result = 0;
		String sql = prop.getProperty("insertHeartRecipe");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteHeartRecipe(Connection conn, Heart ht) {
		
		int result = 0;
		String sql = prop.getProperty("deleteHeartRecipe");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int insertHeartBookmark(Connection conn, Heart ht) {
		
		int result = 0;
		String sql = prop.getProperty("insertHeartBookmark");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteHeartBookmark(Connection conn, Heart ht) {
		
		int result = 0;
		String sql = prop.getProperty("deleteHeartBookmark");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int insertHeartNotice(Connection conn, Heart ht) {
		
		int result = 0;
		String sql = prop.getProperty("insertHeartNotice");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteHeartNotice(Connection conn, Heart ht) {
		
		int result = 0;
		String sql = prop.getProperty("deleteHeartNotice");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
		
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int insertHeartSubsc(Connection conn, Heart ht) {
		
		int result = 0;
		String sql = prop.getProperty("insertHeartSubsc");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
	
			result = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteHeartSubsc(Connection conn, Heart ht) {
		
		int result = 0;
		String sql = prop.getProperty("deleteHeartSubsc");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
		
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int insertHeartReply(Connection conn, Heart ht) {
		
		int result = 0;
		String sql = prop.getProperty("insertHeartReply");
	
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
		
			result = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteHeartReply(Connection conn, Heart ht) {
	
		int result = 0;
		String sql = prop.getProperty("deleteHeartReply");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
		
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/****************************************************************************/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	case "RECIPE" :
	case "BOOKMARK" :
	case "NOTICE" : result = new HeartDao().heartCountGeneralBoard(ht, conn); break;
	case "SUBSC" : result = new HeartDao().heartCountSubsc(ht, conn); break;
	case "REPLY" : result = new HeartDao().heartCountReply(ht, conn); break;
	*/
	public int heartCountGeneralBoard(Heart ht, Connection conn) {
		int result = 0;
		String sql = prop.getProperty("heartCountGeneralBoard").replace("$BASEKEY", ht.getHtKind());
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getHtTargetNo());
			
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					result = rset.getInt("COUNT(*)");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int heartCountSubsc(Heart ht, Connection conn) {
		int result = 0;
		String sql = prop.getProperty("heartCountSubsc");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getHtTargetNo());
			
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					result = rset.getInt("COUNT(*)");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int heartCountReply(Heart ht, Connection conn) {
		int result = 0;
		String sql = prop.getProperty("heartCountReply");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, ht.getHtTargetNo());
			
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					result = rset.getInt("COUNT(*)");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/****************************************************************************/
	/****************************************************************************/
	
	
	
	
	
	
	/****************************************************************************/
	
	public ArrayList<NoticeHeart> countnoticeHeart(Connection conn, ArrayList<NoticeHeart> heartNoticeNo){
		NoticeHeart nh = null;
		ArrayList<NoticeHeart> noticeHeartList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("countnoticeHeart");
		//System.out.print("heartNo >> " + heartNoticeNo);
		
		 try {
			pstmt = conn.prepareStatement(sql);
			
			for(NoticeHeart n : heartNoticeNo) {
				pstmt.setInt(1, n.getNoticeNo());
				noticeHeartList.add(n);
			}
			
			 rset = pstmt.executeQuery();
			 //ArrayList<int> count = new ArrayList();
			 while(rset.next()) {
				nh = new NoticeHeart();
				//nh.setMemNo(rset.getInt("MEM_NO"));
				//nh.setNoticeNo(rset.getInt("NOTICE_NO"));
				//nh.setNoticeHtDate(rset.getDate("HT_NOTICE_DATE"));
				nh.setNoticeHeartCount(rset.getInt("COUNT(NOTICE_NO)"));
				noticeHeartList.add(nh);
			 }
		 } catch(SQLException e) { 
			 e.printStackTrace(); 
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return noticeHeartList;
	}
	
	public ArrayList<NoticeHeart> selectnoticeHeartList(Connection conn){
		
		ArrayList<NoticeHeart> selectnoticeHeartList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectnoticeHeartList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				NoticeHeart nh = new NoticeHeart();
				nh.setMemNo(rset.getInt("MEM_NO"));
				nh.setNoticeNo(rset.getInt("NOTICE_NO"));
				nh.setNoticeHtDate(rset.getDate("HT_NOTICE_DATE"));
				
				selectnoticeHeartList.add(nh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return selectnoticeHeartList;
	}

}
