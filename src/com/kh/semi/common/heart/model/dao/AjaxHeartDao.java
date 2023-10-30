package com.kh.semi.common.heart.model.dao;




import org.apache.ibatis.session.SqlSession;

import com.kh.semi.common.heart.model.vo.Heart;
import com.kh.semi.common.heart.model.vo.NoticeHeart;

public class AjaxHeartDao {
	
	
	
	/*************** 특정 대상 하트 카운트 조회 기능 ***************************************/
	/**
	 * 레시피 좋아요 테이블(TB_HT_RECIPE)에서 특정 레시피글 번호(PK)가 받은 총 좋아요 개수를 SELECT COUNT(*)문으로 조회.
	 * 반환값에서 결과를 int형으로 추출 후 반환.
	 * @param sqlSession : sqlSessionection객체
	 * @param htTargetNo : 조회하려는 레시피 글의 PK번호
	 * @return : 특정 레시피에 눌린 좋아요 수 총 합산
	 */
	public int htCountRecipe(SqlSession sqlSession, int htTargetNo) {
 
		return sqlSession.selectOne("heartMapper.htCountRecipe", htTargetNo);
	}
	
	
	/**
	 * 레시피 북마크 테이블(TB_HT_RECIPE)에서 특정 레시피글 번호(PK)가 받은 총 북마크 개수를 SELECT COUNT(*)문으로 조회.
	 * 반환값에서 결과를 int형으로 추출 후 반환.
	 * @param sqlSession : sqlSessionection객체
	 * @param htTargetNo : 조회하려는 레시피 글의 PK번호
	 * @return : 특정 레시피가 북마킹된 수 총 합산
	 */
	public int htCountBookmark(SqlSession sqlSession, int htTargetNo) {
 
		return sqlSession.selectOne("heartMapper.htCountBookmark", htTargetNo);
	}
	
	
	/**
	 * 공지사항 좋아요 테이블(TB_HT_NOTICE)에서 특정 공지사항글 번호(PK)가 받은 총 좋아요 개수를 SELECT COUNT(*)문으로 조회.
	 * 반환값에서 결과를 int형으로 추출 후 반환.
	 * @param sqlSession : sqlSessionection객체
	 * @param htTargetNo : 조회하려는 공지사항 글의 PK번호
	 * @return : 특정 레시피에 눌린 좋아요 수 총 합산
	 */
	public int htCountNotice(SqlSession sqlSession, int htTargetNo) {
		
		return sqlSession.selectOne("HeartMapper.htCountNotice", htTargetNo);
	}

	
	/**
	 * 쉐프 구독 테이블(TB_HT_SUBSC)에서 특정 쉐프 번호(PK)가 받은 총 구독 개수를 SELECT COUNT(*)문으로 조회.
	 * 반환값에서 결과를 int형으로 추출 후 반환.
	 * @param sqlSession : sqlSessionection객체
	 * @param htTargetNo : 조회하려는 쉐프의 PK번호
	 * @return : 특정 쉐프가 받은 구독 수 총 합산
	 */
	public int htCountSubsc(SqlSession sqlSession, int htTargetNo) {

		return sqlSession.selectOne("HeartMapper.htCountSubsc", htTargetNo);
	}
	
	
	/**
	 * 리플 좋아요 테이블(TB_HT_REPLY)에서 특정 리플 번호(PK)가 받은 총 좋아요 개수를 SELECT COUNT(*)문으로 조회.
	 * 반환값에서 결과를 int형으로 추출 후 반환.
	 * @param sqlSession : sqlSessionection객체
	 * @param htTargetNo : 조회하려는 리플의 PK번호
	 * @return : 특정 리플에 눌린 좋아요 수 총 합산
	 */
	public int htCountReply(SqlSession sqlSession, int htTargetNo) {
		
		return sqlSession.selectOne("HeartMapper.htCountReply", htTargetNo);
	}

	
	
	/*************** 좋아요 여부 체크 기능 *********************************************/
	/**
	 * 현재 로그인한 유저가 특정 레시피에 좋아요를 누른 상태인지 SELECT문으로 조회 후 좋아요 누른 여부를 boolean형으로 반환.
	 * @param sqlSession : sqlSessionection객체
	 * @param ht : Heart객체로 현재 로그인한 멤버의 회원번호(PK) 조회하려는 레시피글 번호(PK)
	 * @return : 좋아요를 누른 상태라면 true, 아니라면 false반환
	 */
	public boolean isHeartRecipe(SqlSession sqlSession, Heart heart) {

		return (sqlSession.selectOne("heartMapper.isHeartRecipe", heart) != null);
	}
	
	
	public boolean isHeartBookmark(SqlSession sqlSession, Heart heart) {
		
		return (sqlSession.selectOne("heartMapper.isHeartBookmark", heart) != null);
	}
	
	
	public boolean isHeartNotice(SqlSession sqlSession, Heart heart) {
		
		return (sqlSession.selectOne("heartMapper.isHeartNotice", heart) != null);
	}
	
	
	public boolean isHeartSubsc(SqlSession sqlSession, Heart heart) {
		
		return (sqlSession.selectOne("heartMapper.isHeartSubsc", heart) != null);
	}
	
	
	public boolean isHeartReply(SqlSession sqlSession, Heart heart) {
		
		return (sqlSession.selectOne("heartMapper.isHeartReply", heart) != null);
	}
	/****************************************************************************/
	

	
	/*************** 좋아요 추가/삭제(INSERT/DELETE) 기능 ******************************/
	public int insertHeartRecipe(SqlSession sqlSession, Heart heart) {
		
		int result = 0;
		String sql = prop.getProperty("insertHeartRecipe");
		
		try(PreparedStatement pstmt = sqlSession.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteHeartRecipe(SqlSession sqlSession, Heart heart) {
		
		int result = 0;
		String sql = prop.getProperty("deleteHeartRecipe");
		
		try(PreparedStatement pstmt = sqlSession.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int insertHeartBookmark(SqlSession sqlSession, Heart heart) {
		
		int result = 0;
		String sql = prop.getProperty("insertHeartBookmark");
		
		try(PreparedStatement pstmt = sqlSession.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteHeartBookmark(SqlSession sqlSession, Heart heart) {
		
		int result = 0;
		String sql = prop.getProperty("deleteHeartBookmark");
		
		try(PreparedStatement pstmt = sqlSession.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int insertHeartNotice(SqlSession sqlSession, Heart heart) {
		
		int result = 0;
		String sql = prop.getProperty("insertHeartNotice");
		
		try(PreparedStatement pstmt = sqlSession.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteHeartNotice(SqlSession sqlSession, Heart heart) {
		
		int result = 0;
		String sql = prop.getProperty("deleteHeartNotice");
		
		try(PreparedStatement pstmt = sqlSession.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
		
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int insertHeartSubsc(SqlSession sqlSession, Heart heart) {
		
		int result = 0;
		String sql = prop.getProperty("insertHeartSubsc");
		
		try(PreparedStatement pstmt = sqlSession.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
	
			result = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteHeartSubsc(SqlSession sqlSession, Heart heart) {
		
		int result = 0;
		String sql = prop.getProperty("deleteHeartSubsc");
		
		try(PreparedStatement pstmt = sqlSession.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
		
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int insertHeartReply(SqlSession sqlSession, Heart heart) {
		
		int result = 0;
		String sql = prop.getProperty("insertHeartReply");
	
		try(PreparedStatement pstmt = sqlSession.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
		
			result = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteHeartReply(SqlSession sqlSession, Heart heart) {
	
		int result = 0;
		String sql = prop.getProperty("deleteHeartReply");
		
		try(PreparedStatement pstmt = sqlSession.prepareStatement(sql)) {
			pstmt.setInt(1, ht.getMemNo());
			pstmt.setInt(2, ht.getHtTargetNo());
		
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/****************************************************************************/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	public int heartCountSubsc(Heart ht, SqlSession sqlSession) {
		int result = 0;
		String sql = prop.getProperty("heartCountSubsc");
		
		try(PreparedStatement pstmt = sqlSession.prepareStatement(sql)) {
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
	
	
	public int heartCountReply(Heart ht, SqlSession sqlSession) {
		int result = 0;
		String sql = prop.getProperty("heartCountReply");
		
		try(PreparedStatement pstmt = sqlSession.prepareStatement(sql)){
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
	
	public ArrayList<NoticeHeart> countnoticeHeart(SqlSession sqlSession, ArrayList<NoticeHeart> heartNoticeNo){
		NoticeHeart nh = null;
		ArrayList<NoticeHeart> noticeHeartList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("countnoticeHeart");
		//System.out.print("heartNo >> " + heartNoticeNo);
		
		 try {
			pstmt = sqlSession.prepareStatement(sql);
			
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
	
	public ArrayList<NoticeHeart> selectnoticeHeartList(SqlSession sqlSession){
		
		ArrayList<NoticeHeart> selectnoticeHeartList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectnoticeHeartList");
		
		try {
			pstmt = sqlSession.prepareStatement(sql);
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
