package com.kh.semi.dm.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.common.template.Template;
import com.kh.semi.dm.model.dao.DmDao;
import com.kh.semi.dm.model.vo.Dm;

public class DmServiceImpl implements DmService {
	
	private DmDao dmDao;
	
	public DmServiceImpl() {
		dmDao = new DmDao();
	}
	
	
	// 쪽지함 리스트 조회
	@Override
	public ArrayList<Dm> selectDmList(PageInfo pi) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Dm> list = dmDao.selectDmList(sqlSession, pi);
		
		sqlSession.close();
		
		return list;
	}	// selectDmList
	
	
	// 쪽지함 리스트 행 수 조회
	@Override
	public int selectDmListCount() {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int dmListCount = dmDao.selectDmListCount(sqlSession);
		
		sqlSession.close();
		
		return dmListCount;
	}	// selectDmListCount
	
	
	// 쪽지 미답변 개수 카운트
	@Override
	public int getWaitingCount(ArrayList<Dm> list) {
		
		int result = 0;
		if(list == null || list.size() == 0)
			return result;
		
		for(Dm dm : list) {
			if(dm.getDmStatus().equals("N"))
				result++;
		}
		
		return result;
	}	// getWaitingCount
	
	
	// 쪽지함 쪽지 답변
	@Override
	public int updateReply(Dm dm) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = dmDao.updateReply(sqlSession, dm);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}	// updateReply
	
	
	// 쪽지함 쪽지 삭제
	@Override
	public int deleteDm(int dmNo) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = dmDao.deleteDm(sqlSession, dmNo);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}	// deleteDm
	

	// 쪽지 답변완료 수 카운트
	@Override
	public int repliedCount() {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int dmRepliedCount = dmDao.repliedCount(sqlSession);
		
		sqlSession.close();
		
		return dmRepliedCount;
		
	}	// repliedCount
	
	
	
}	// end class
