package com.kh.semi.dm.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.dm.model.vo.Dm;

public class DmDao {
	

	/**
	 * 쪽지함 리스트 조회
	 * @param conn
	 * @return 쪽지리스트 전체 내용
	 * @author JH
	 * @Date : 2023. 09. 30.
	 * @Update : 2023. 10. 28.
	 */
	public ArrayList<Dm> selectDmList(SqlSession sqlSession, PageInfo pi){
		
		int offset= (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return (ArrayList)sqlSession.selectList("dmMapper.selectDmList", null, rowBounds);
		
	}	// selectDmList
	
	
	/**
	 * 쪽지함 리스트 카운트 행 수 조회 요청 처리
	 * @param sqlSession
	 * @return DB에 저장되어 있는 쪽지함 리스트 수
	 * @author JH
	 * @Date : 2023. 10. 15.
	 * @Update : 2023. 10. 28.
	 */
	public int selectDmListCount(SqlSession sqlSession) {
		
		return sqlSession.selectOne("dmMapper.selectDmListCount");
	}	// selectDmListCount
	
	
	/**
	 * 쪽지함 쪽지 답변
	 * @param sqlSession
	 * @param dm 답변내용 / 쪽지seqNo(식별값)
	 * @return TB_DM - DM_REPLY 컬럼 INSERT 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 3.
	 * @Update : 2023. 10. 28.
	 */
	public int updateReply(SqlSession sqlSession, Dm dm) {
		return sqlSession.update("dmMapper.updateReply", dm);
		
	}	// updateReply
	
	
	/**
	 * 쪽지 삭제 요청을 처리해주는 method
	 * @param sqlSession
	 * @param dmNo 쪽지를 삭제하기 위한 식별값
	 * @return 삭제 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 3.
	 * @Update : 2023. 10. 28.
	 */
	public int deleteDm(SqlSession sqlSession, int dmNo) {
		return sqlSession.delete("dmMapper.deleteDm", dmNo);
	}	// deleteDm
	
	
	/**
	 * 쪽지 답변완료 수 카운트
	 * @param conn
	 * @return 쪽지 답변완료 개수 - DM_STATUS == 'Y'
	 * @author JH
	 * @Date : 2023. 10. 16.
	 * @Update : 2023. 10. 28.
	 */
	public int repliedCount(SqlSession sqlSession) {
		return sqlSession.selectOne("dmMapper.repliedCount");
	}	// repliedCount

	
	
	
	
	
	
	
}	// end class
