package com.kh.semi.report.model.dao;

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
import com.kh.semi.report.model.vo.Report;

public class ReportDao {
	
	private Properties prop = new Properties();
	
	public ReportDao() {
		String file = ReportDao.class.getResource("/sql/report/report-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	// ReportDao
	
	
	/**
	 * 관리자 신고함 리스트 조회 요청 처리 method
	 * @return 등록되어 있는 신고함 리스트
	 * @author JH
	 * @Date : 2023. 10. 14.
	 */
	public ArrayList<Report> selectReportList(Connection conn, PageInfo pi){
		
		ArrayList<Report> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReportList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Report report = new Report();
				report.setReportNo(rset.getInt("REPORT_NO"));
				report.setRptDate(rset.getDate("RPT_DATE"));
				report.setRptCategoryName(rset.getString("RPT_CATEGORY_NAME"));
				report.setRptContent(rset.getString("RPT_CONTENT"));
				report.setReciveReport(rset.getString("RECIVE_REPORT"));
				report.setSendReport(rset.getString("SEND_REPORT"));
				report.setRptStatus(rset.getString("RPT_STATUS"));
				report.setRecipeTitle(rset.getString("RECIPE_TITLE"));
				report.setReplyContent(rset.getString("REPLY_CONTENT"));
				
				list.add(report);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}	// selectReportList
	
	
	/**
	 * 신고함 리스트 카운트 행 수 조회 요청 처리
	 * @param conn
	 * @return DB에 저장되어 있는 리워드 리스트 수
	 * @author JH
	 * @Date : 2023. 10. 15.
	 */
	public int selectReportListCount(Connection conn) {
		
		int reportListCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReportListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				reportListCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return reportListCount;
	}	// selectReportListCount
	
	
	
	/**
	 * 신고함 상세리스트 조회 요청 처리
	 * @param conn
	 * @return REPORT_NO && REPORT_CATEGORY_NO과 일치하는 값
	 * @author JH
	 * @Date : 2023. 10. 16.
	 */
	public ArrayList<Report> datailReportList(Connection conn, int reportNo, String categoryName){
		
		ArrayList<Report> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("detailReportList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reportNo);
			pstmt.setString(2, categoryName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				Report report = new Report();
				report.setRptCategoryName(rset.getString("RPT_CATEGORY_NAME,"));
				report.setSendReport(rset.getString("SEND_REPORT"));
				report.setReciveReport(rset.getString("RECIVE_REPORT"));
				report.setRptContent(rset.getString("RPT_CONTENT"));
				
				list.add(report);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

}	// end class
