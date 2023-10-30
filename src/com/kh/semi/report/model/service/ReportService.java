package com.kh.semi.report.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.report.model.vo.Report;

public interface ReportService {
	
	/**
	 * 관리자 신고함 리스트 조회
	 * @return 등록되어 있는 신고함 리스트
	 * @author JH
	 * @Date : 2023. 10. 14.
	 * @update : 2023. 10. 28
	 */
	public ArrayList<Report> selectReportList(PageInfo pi);
	
	
	/**
	 * 신고함 리스트 행 수 조회
	 * @return DB에 저장되어 있는 리워드 리스트 수
	 * @author JH
	 * @Date : 2023. 10. 15.
	 * @update : 2023. 10. 28
	 */
	public int selectReportListCount();
	
	
	/**
	 * 신고함 상세리스트 조회
	 * @return REPORT_NO && REPORT_CATEGORY_NO과 일치하는 값
	 * @author JH
	 * @Date : 2023. 10. 16.
	 * @update : 2023. 10. 28
	 */
	public ArrayList<Report> detailReportList(int reportNo);
	

}	// end class
