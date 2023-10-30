package com.kh.semi.report.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.common.template.Template;
import com.kh.semi.report.model.dao.ReportDao;
import com.kh.semi.report.model.vo.Report;

public class ReportServiceImpl implements ReportService {
	
	private ReportDao reportDao;
	
	public ReportServiceImpl(){
		reportDao = new ReportDao();
	}
	
	
	// 관리자 신고함 리스트 조회
	@Override
	public ArrayList<Report> selectReportList(PageInfo pi) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Report> list = reportDao.selectReportList(sqlSession, pi);
		
		sqlSession.close();
		
		return list;
	}
	
	
	// 신고함 리스트 행 수 조회
	@Override
	public int selectReportListCount() {
		
		SqlSession sqlSession = Template.getSqlSession();
		int reportListCount = reportDao.selectReportListCount(sqlSession);
		
		sqlSession.close();
		
		return reportListCount;
		
	}
	
	
	// 신고함 상세리스트 조회
	@Override
	public ArrayList<Report> detailReportList(int reportNo) {
		
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Report> list = reportDao.detailReportList(sqlSession, reportNo);
		
		sqlSession.close();
		
		return list; 
	}	// detailReportList

//	
//	/**
//	 * 신고함 상세리스트 조회 요청 method
//	 * @return REPORT_NO && REPORT_CATEGORY_NO과 일치하는 값
//	 * @author JH
//	 * @Date : 2023. 10. 16.
//	 */
//	public ArrayList<Report> detailReportList(int reportNo, String categoryName){
//		
//		Connection conn = getConnection();
//		
//		ArrayList<Report> list = reportDao.datailReportList(conn, reportNo, categoryName);
//		
//		close(conn);
//		
//		return list;
//	}	// detailReportList
//	
//	
	
	
}	// end class
