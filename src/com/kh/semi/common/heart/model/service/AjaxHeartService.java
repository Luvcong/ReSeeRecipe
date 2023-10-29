package com.kh.semi.common.heart.model.service;

import com.kh.semi.common.heart.model.vo.Heart;

public interface AjaxHeartService {
	
	
	/*************** 특정 대상 하트 카운트 조회 기능 ***************************************/
	int htCountRecipe(int htTargetNo);
	
	
	int htCountBookmark(int htTargetNo);
	
	
	int htCountNotice(int htTargetNo);
	
	
	int htCountSubsc(int htTargetNo);
	
	
	int htCountReply(int htTargetNo);
	
	
	/*************** 하트 추가/삭제 기능 ***********************************************/
	int htChangeRecipe(Heart ht);
		
		
	int htChangeBookmark(Heart ht);
		
		
	int htChangeNotice(Heart ht);
		
		
	int htChangeSubsc(Heart ht);
		
		
	int htChangeReply(Heart ht);

			


}
