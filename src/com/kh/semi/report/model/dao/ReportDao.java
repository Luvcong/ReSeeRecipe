package com.kh.semi.report.model.dao;

import java.util.HashMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.report.model.vo.Report;

public class ReportDao {
	
	/**
	 * 관리자 신고함 리스트 조회 요청 처리 method
	 * @param sqlSession
	 * @return 등록되어 있는 신고함 리스트
	 * @author JH
	 * @Date : 2023. 10. 14.
	 * @update : 2023. 10. 28
	 */
	public ArrayList<Report> selectReportList(SqlSession sqlSession, PageInfo pi){
		
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return (ArrayList)sqlSession.selectList("reportMapper.selectReportList", null, rowBounds);
		
	}	// selectReportList
	
	
	/**
	 * 신고함 리스트 카운트 행 수 조회 요청 처리
	 * @param sqlSession
	 * @return DB에 저장되어 있는 리워드 리스트 수
	 * @author JH
	 * @Date : 2023. 10. 15.
	 * @update : 2023. 10. 28
	 */
	public int selectReportListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("reportMapper.selectReportListCount");
		
	}	// selectReportListCount
	
	
	/**
	 * 신고함 상세리스트 조회 요청 처리
	 * @param conn
	 * @return REPORT_NO && REPORT_CATEGORY_NO과 일치하는 값
	 * @author JH
	 * @Date : 2023. 10. 16.
	 * @Update : 2023. 10. 28.
	 */
	public ArrayList<Report> detailReportList(SqlSession sqlSession, int reportNo){
		return sqlSession.selectOne("reportMapeer.detailReportList", reportNo);
		
	}	// datailReportList

}	// end class
