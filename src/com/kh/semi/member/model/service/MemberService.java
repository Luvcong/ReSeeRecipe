package com.kh.semi.member.model.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.member.model.dao.MemberDao;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.member.model.vo.MemberUpdate;

public class MemberService {
	
	public Member loginMember(String memberId, String memberPwd) {
		
		Connection conn = getConnection();
		
		Member m = new MemberDao().loginMember(conn, memberId, memberPwd);
		
		close(conn);
		
		return m;
	}
	
	public int memReward(int memNo) {
		
		Connection conn = getConnection();
		
		int mReward = new MemberDao().memReward(conn, memNo);
		
		close(conn);
		
		return mReward;
	}
	
	// 회원가입
	/**
	 * @param m : 회원가입하는 Member객체
	 * @return : 회원insert 성공 여부에 따른 결과값(성공1 또는 실패0)
	 */
	public int insertMember(Member m) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().insertMember(conn, m);
		
		// 회원가입 성공 / 실패
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	// 회원가입 시 nickname 중복체크
	/**
	 * @param checkNickname : 회원가입에 쓸 사용자 nickname 입력값
	 * @return : 이미 존재하는 닉네임 1 또는 사용가능 0
	 */
	public int nicknameCheck(String checkNickname) {
		Connection conn = getConnection();
		
		int count = new MemberDao().nicknameCheck(conn, checkNickname);
		
		close(conn);
		
		return count;
	}
	
	
	// 회원가입 시 id 중복체크
	/**
	 * @param checkId : 회원가입에 쓸 사용자 id 입력값
	 * @return : 이미 존재하는 아이디 1 또는 사용가능 0
	 */
	public int idCheck(String checkId) {
		Connection conn = getConnection();
		
		int count = new MemberDao().idCheck(conn, checkId);
		
		close(conn);
		
		return count;
	}
	
	// 회원가입 시 email 중복체크
	/**
	 * @param checkEmail : 회원가입에 쓸 사용자 email 입력값
	 * @return : 이미 존재하는 아이디 1 또는 사용가능 0
	 */
	public int emailCheck(String checkEmail) {
		Connection conn = getConnection();
		
		int count = new MemberDao().emailCheck(conn, checkEmail);
		
		close(conn);
		
		return count;
	}
	
	// 아이디 찾기
	/**
	 * @param memberName : 아이디를 찾고자 하는 사용자 이름 입력값
	 * @param memberEmail : 아이디를 찾고자 하는 사용자 이메일 입력값
	 * @return : 조회된 회원
	 */
	public Member searchMemberId(String memberName, String memberEmail) {
		
		Connection conn = getConnection();
		
		Member searchMember = new MemberDao().searchMemberId(conn, memberName, memberEmail);
		
		close(conn);
		
		return searchMember;
	}
	
	// 비밀번호 찾기
	/**
	 * @param memberId : 비밀번호를 찾고자 하는 사용자 아이디 입력값
	 * @param memberEmail : 비밀번호를 찾고자 하는 사용자 이메일 입력값
	 * @return : 조회된 결과가 있다면 1 반환
	 */
	public int searchMemberPwd(String memberId, String memberEmail) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().searchMemberPwd(conn, memberId, memberEmail);
		
		close(conn);
		
		return result;
	}
	
	// 회원 비밀번호 재설정
	public int updateMemberPwd(String memberId, String memberPwd) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().updateMemberPwd(conn, memberId, memberPwd);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	// 회원 정보 변경 시 비밀번호 확인
	public String memberUpdateConfirm(int memberNo) {
		
		Connection conn = getConnection();
		
		String checkPwd = new MemberDao().memberUpdateConfirm(conn, memberNo);
		
		close(conn);
		
		return checkPwd;
	}
	
	// 회원 정보 변경
	public int memberUpdate(Member m, String memberPicture) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().memberUpdate(conn, m, memberPicture);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	// 회원 삭제(탈퇴)
	public int memberDelete(int memberNo) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().memberDelete(conn, memberNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
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
	public int deleteMember(int mno) {
		
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
