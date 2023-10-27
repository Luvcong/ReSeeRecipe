package com.kh.semi.report.model.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.report.model.dao.ReportDao;
import com.kh.semi.report.model.vo.Report;

public class ReportService {

	private ReportDao reportDao;
	
	public ReportService() {
		super();
		reportDao = new ReportDao();
	}	// ReportService
	
	
	/**
	 * 관리자 신고함 리스트 조회 요청 method
	 * @return 등록되어 있는 신고함 리스트
	 * @author JH
	 * @Date : 2023. 10. 14.
	 */
	public ArrayList<Report> selectReportList(PageInfo pi){
		
		Connection conn = getConnection();
		
		ArrayList<Report> list = reportDao.selectReportList(conn, pi);
		
		close(conn);
		
		return list;
	}	// selectReportList
	
	
	/**
	 * 신고함 리스트 카운트 행 수 조회 요청
	 * @return DB에 저장되어 있는 리워드 리스트 수
	 * @author JH
	 * @Date : 2023. 10. 15.
	 */
	public int selectReportListCount() {
		
		Connection conn = getConnection();
		
		int rewardListCount = reportDao.selectReportListCount(conn);
		
		close(conn);
		
		return rewardListCount;
		
	}	// selectReportListCount
	
	
	
	/**
	 * 신고함 상세리스트 조회 요청 method
	 * @return REPORT_NO && REPORT_CATEGORY_NO과 일치하는 값
	 * @author JH
	 * @Date : 2023. 10. 16.
	 */
	public ArrayList<Report> detailReportList(int reportNo, String categoryName){
		
		Connection conn = getConnection();
		
		ArrayList<Report> list = reportDao.datailReportList(conn, reportNo, categoryName);
		
		close(conn);
		
		return list;
	}	// detailReportList
	
	
	
	
}	// end class
