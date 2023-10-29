package com.kh.semi.member.model.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.common.template.Template;
import com.kh.semi.member.model.dao.MemberDao;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.member.model.vo.MemberUpdate;


public class MemberService implements MemberServiceI{
	
	private MemberDao memberDao = new MemberDao();
	
	// 로그인
	@Override
	public Member loginMember(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		Member loginMember = memberDao.loginMember(sqlSession, m);
		
		sqlSession.close();
		
		return loginMember;
	}
	
	public int memReward(int memNo) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int mReward = new MemberDao().memReward(sqlSession, memNo);
		
		sqlSession.close();
		
		return mReward;
	}
	
	
	// 회원가입
	@Override
	public int insertMember(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = memberDao.insertMember(sqlSession, m);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}
	
	@Override
	public int checkDupl(HashMap<String, String> map) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int count = memberDao.checkDupl(sqlSession, map);
		
		return 0;
	}
	
	
	// 아이디 찾기
	@Override
	public Member searchMemberId(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		Member searchMember = memberDao.searchMemberId(sqlSession, m);
		
		sqlSession.close();
		
		return searchMember;
	}
	
	// 비밀번호 찾기
	@Override
	public int searchMemberPwd(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = memberDao.searchMemberPwd(sqlSession, m);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}
	
	// 회원 비밀번호 재설정
	@Override
	public int updateMemberPwd(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = memberDao.updateMemberPwd(sqlSession, m);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}
	
	// 회원 정보 변경
	public int updateMember(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = memberDao.updateMember(sqlSession, m);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}
	
	// 회원 삭제(탈퇴)
	public int deleteMember(int memberNo) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = memberDao.deleteMember(sqlSession, memberNo);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}

	/**
	 * @return 전체 회원 수(Paging의 listCount)
	 */
	public int selectMemlistCount() {
		
		Connection conn = getConnection();
		
		int memlistCount = new MemberDao().selectMemlistCount(conn);
		
		close(conn);
		
		return memlistCount;
	}
	
	
	/**
	 * @param pi 회원수에 따른 페이징 변수들
	 * @return 회원 전체 목록 + 페이징처리 결과
	 */
	public ArrayList<Member> selectMemberAll(PageInfo pi){
		
		Connection conn = getConnection();
		
		ArrayList<Member> list = new MemberDao().selectMemberAll(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * @param memNo 선택한 회원 번호
	 * @return 선택한 회원의 정보
	 */
	public Member selectMemInfo(int memNo){
		
		Connection conn = getConnection();
		
		Member m = new MemberDao().selectMemInfo(conn, memNo);
		
		close(conn);
		
		return m;
	}
	
	/**
	 * @param m 회원 정보 수정 값
	 * @param mu 회원 정보 수정 사유(처음 수정 시 INSERT / 두번째부터 UPDATE)
	 * @return 회원 정보 수정 수행 결과(성공 1 실패 0)
	 */
	public int updateMemInfo(Member m, MemberUpdate mu) {
		
		Connection conn = getConnection();
		
		int result1 = new MemberDao().updateMemInfo(conn, m);
		//java.sql.Date memberModifyDate = java.sql.Date.valueOf(m.getModifyDate());
		int result2 = 1; //new MemberDao().updateMemInfo(conn, mu);
		Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		if(m.getModifyDate() != null && m.getEnrollDate() == m.getModifyDate()) {
			result2 = new MemberDao().insertMemUpdate(conn, mu);
		} else if(m.getModifyDate() != null && m.getModifyDate().before(sqlDate)) {
			System.out.print("적음");
			result2 = new MemberDao().updateMemUpdate(conn, mu);
		}
		
		if((result1 * result2) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return(result1 * result2);
	}
	
	/**
	 * @param mno 삭제할 회원 번호
	 * @return 회원 상태(N) UPDATE 수행 결과(성공1 실패0)
	 */
	public int deleteMember2(int mno) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn, mno);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	/**
	 * @param memSearchoption 회원 검색 조건(회원ID, 회원이름, 회원닉네임)
	 * @param memSearchcon 사용자가 입력한 값
	 * @return 검색 수행 결과
	 */
	public ArrayList<Member> searchMember(String memSearchcon) {
		
		Connection conn = getConnection();
		
		//String memoption = memSearchoption; String memSearchoption, 

		/*
		ArrayList<Member> list = new ArrayList();
		if(memSearchoption == "회원ID") {
			list = new MemberDao().searchmemId(conn, memSearchcon);
		} else if(memSearchoption == "닉네임") {
			
		} else if(memSearchoption == "이름") {
			
		} else {
			
		}
		*/
		//ArrayList<Member> list = new ArrayList();
		/*
		if(memSearchoption == "회원조회") {
			list = new MemberDao().totalsearchMember(conn, memSearchoption, memSearchcon);
		} else {
			list = new MemberDao().searchMember(conn, memSearchoption, memSearchcon);
		}
		*/
		//ArrayList<Member> list = new MemberDao().searchMember(conn, memSearchoption, memSearchcon);
		
		ArrayList<Member> list = new MemberDao().searchmemId(conn, memSearchcon);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Member> searchMemId(String memSearchcon) {
		
		Connection conn = getConnection();
		
		ArrayList<Member> list = new MemberDao().searchmemId(conn, memSearchcon);
		
		close(conn);
		
		return list;
	}

}
