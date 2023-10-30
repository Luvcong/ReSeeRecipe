package com.kh.semi.dm.model.service;

import java.util.ArrayList;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.dm.model.vo.Dm;

public interface DmService {
	
	/**
	 * 쪽지함 리스트 조회
	 * @param pi
	 * @return
	 * @author JH
	 * @Date : 2023. 09. 30.
	 * @Update : 2023. 10. 28.
	 */
	public ArrayList<Dm> selectDmList(PageInfo pi);
	
	
	/**
	 * 쪽지함 리스트 행 수 조회
	 * @return DM에 저장되어 있는 쪽지함 리스트 수
	 * @author JH
	 * @Date : 2023. 10. 15.
	 * @Update : 2023. 10. 28.
	 */
	public int selectDmListCount();
	

	/**
	 * 쪽지함 쪽지 답변
	 * @param dm 답변내용 / 쪽지seqNo(식별값)
	 * @return TB_DM - DM_REPLY 컬럼 INSERT 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 3.
	 * @Update : 2023. 10. 28.
	 */
	public int updateReply(Dm dm);
	
	
	/**
	 * 쪽지함 쪽지 삭제
	 * @param dmNo 쪽지를 삭제하기 위한 식별값
	 * @return 삭제 성공 여부
	 * @author JH
	 * @Date : 2023. 10. 3.
	 * @Update : 2023. 10. 28.
	 */
	public int deleteDm(int dmNo);
	
	
	/**
	 * 쪽지 미답변 개수 카운트
	 * @param list 전체 쪽지 목록
	 * @return 미답변 쪽지 개수
	 * @author JH
	 * @Date : 2023. 10. 1.
	 * @Update : 2023. 10. 28.
	 */
	public int getWaitingCount(ArrayList<Dm> list);
		
		
	/**
	 * 쪽지 답변완료 수 카운트
	 * @return 쪽지 답변완료 개수 - DM_STATUS == 'Y'
	 * @author JH
	 * @Date : 2023. 10. 16.
	 * @Update : 2023. 10. 28.
	 */
	public int repliedCount();
	

}	// end class
