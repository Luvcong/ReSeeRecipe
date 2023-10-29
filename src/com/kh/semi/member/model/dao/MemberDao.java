package com.kh.semi.member.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.member.model.vo.MemberUpdate;

public class MemberDao {
	
	// Properties 객체 생성
	private Properties prop = new Properties();
	
	// 기본생성자 호출 시 member-mapper.xml파일 읽어오기
	/*
	public MemberDao() {
		
		String file = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	// 로그인시 로그인 한 유저 DB정보 가져오기
	public Member loginMember(SqlSession sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.loginMember", m);
	}
	
	// 리워드 조회
	public int memReward(SqlSession sqlSession, int memNo) {
		return sqlSession.selectOne("memberMapper.selectMemReward", memNo);
	}
	
	
	// 회원가입

	public int insertMember(SqlSession sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertMember", m);
	}
	
	// 중복체크
	public int checkDupl(SqlSession sqlSession, HashMap map) {
		return sqlSession.selectOne("memberMapper.checkDupl", map);
	}
	
	// 아이디 찾기
	public Member searchMemberId(SqlSession sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.searchMemberId", m);
	}
	
	// 비밀번호 찾기
	public int searchMemberPwd(SqlSession sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.searchMemberPwd", m);
	}
	
	// 회원 비밀번호 재설정
	public int updateMemberPwd(SqlSession sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMemberPwd", m);
	}
	
	// 회원 정보 변경
	public int updateMember(SqlSession sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMember", m);
	}
	
	// 회원 삭제(탈퇴)
	public int deleteMember(SqlSession sqlSession, int memberNo) {
		return sqlSession.delete("memberMapper.deleteMember", memberNo);
	}

	
	/**
	 * @param conn
	 * @return 회원 전체 수
	 */
	public int selectMemlistCount(Connection conn) {
		
		int memlistCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMemlistCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				memlistCount = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return memlistCount;
	}
	
	
	/**
	 * @param conn
	 * @param pi 페이징처리 변수들
	 * @return 회원 전체 목록 + 페이징
	 */
	public ArrayList<Member> selectMemberAll(Connection conn, PageInfo pi){
		
		ArrayList<Member> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemNo(rset.getInt("MEM_NO"));
				m.setMemName(rset.getString("MEM_NAME"));
				m.setMemId(rset.getString("MEM_ID"));
				m.setMemNickname(rset.getString("MEM_NICKNAME"));
				m.setMemEmail(rset.getString("MEM_EMAIL"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				//m.setMemReward(rset.getInt("MEM_REWARD"));
				m.setMemGrade(rset.getInt("MEM_GRADE"));
				m.setMemGradeName(rset.getString("MEM_GRADE_NAME"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	/**
	 * @param conn
	 * @param memNo 선택한 회원번호
	 * @return 해당 회원의 정보
	 */
	public Member selectMemInfo(Connection conn, int memNo) {
		
		//ArrayList<Member> list = new ArrayList();
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member();
				m.setMemNo(rset.getInt("MEM_NO"));
				m.setMemName(rset.getString("MEM_NAME"));
				m.setMemId(rset.getString("MEM_ID"));
				m.setMemNickname(rset.getString("MEM_NICKNAME"));
				m.setMemEmail(rset.getString("MEM_EMAIL"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setModifyDate(rset.getDate("MODIFY_DATE"));
				m.setMemGrade(rset.getInt("MEM_GRADE"));
				m.setMemGradeName(rset.getString("MEM_GRADE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}
	
	/**
	 * @param conn
	 * @param m 수정할 회원 정보
	 * @return 회원정보 수정 수행 결과(성공 1 실패 0)
	 */
	public int updateMemInfo(Connection conn, Member m) {
		
		int result1 = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMemInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemName());
			pstmt.setString(2, m.getMemNickname());
			pstmt.setString(3, m.getMemEmail());
			//pstmt.setInt(4, m.getMemGrade());
			pstmt.setString(4, m.getMemGradeName());
			pstmt.setInt(5, m.getMemNo());
			
			result1 = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result1;
	}
	
	/**
	 * @param conn 
	 * @param mu 회원정보수정 사유
	 * @return 처음 회원정보 수정 시 회원정보수정테이블에 INSERT 한 수행 결과
	 */
	public int insertMemUpdate(Connection conn, MemberUpdate mu) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMemUpdate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mu.getMemNo());
			pstmt.setString(2,mu.getMemUpdateCon());
			//pstmt.setInt(3, mu.getMemNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/**
	 * @param conn
	 * @param mu 회원정보 수정 사유
	 * @return 회원정보 수정이 처음이 아닐 시 회원수정테이블에 UPDATE 수행 결과
	 */
	public int updateMemUpdate(Connection conn, MemberUpdate mu) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMemUpdate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mu.getMemUpdateCon());
			pstmt.setInt(2, mu.getMemNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/**
	 * @param conn
	 * @param mno 삭제할 회원번호
	 * @return 회원 삭제 수행(UPDATE STATUS = 'N') 결과
	 */
	public int deleteMember(Connection conn, int mno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/**
	 * @param conn
	 * @param memSearchoption 회원통합조회 옵션
	 * @param memSearchcon 조회할 값
	 * @return 통합조회 결과
	 */
	public ArrayList<Member> totalsearchMember(Connection conn, String memSearchoption, String memSearchcon) {
		
		ArrayList<Member> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("totalsearchMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memSearchcon);
			pstmt.setString(2, memSearchcon);
			pstmt.setString(3, memSearchcon);
			pstmt.setString(4, memSearchcon);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemNo(rset.getInt("MEM_NO"));
				m.setMemId(rset.getString("MEM_ID"));
				m.setMemName(rset.getString("MEM_NAME"));
				m.setMemNickname(rset.getString("MEM_NICKNAME"));
				m.setMemEmail(rset.getString("MEM_EMAIL"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setMemGradeName(rset.getString("MEM_GRADE_NAME"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	/**
	 * @param conn
	 * @param memSearchoption 회원검색옵션(회원ID, 회원이름, 회원닉네임)
	 * @param memSearchcon 회원검색내용
	 * @return 회원검색 결과
	 */
	public ArrayList<Member> searchMember(Connection conn, String memSearchoption, String memSearchcon){
		
		ArrayList<Member> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//String memop = "MEM_ID";
		System.out.println(memSearchoption);
		/*
		if(memSearchoption.equals("회원ID")) {
			memop += "MEM_ID";
		} 
		else if(memSearchoption.equals("이름")) {
			memop += "MEM_NAME";
		} else if(memSearchoption.equals("닉네임")) {
			memop += "MEM_NICKNAME";
		} 
		*/
		/*else {
			totalsearchMember(conn, memSearchoption, memSearchcon);
		}*/
		
		
		String sql = "SELECT"
						   + "MEM_NO"
						   + ",MEM_ID"
						   + ",MEM_NAME"
						   + ",MEM_NICKNAME"
						   + ",MEM_EMAIL"
						   + ",ENROLL_DATE"
						   + ",MEM_GRADE_NAME"
					  + "FROM"
						   + "TB_MEMBER"
					  + "JOIN"
						   + "TB_MEMBER_GRADE"
					  + "ON"
						   + "(MEM_GRADE = MEM_GRADE_NO)"
					  + "WHERE"
						   + " + memSearchoption + " +  "= ?"
					  + "AND"
					  	   + "MEM_STATUS = 'Y'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memSearchcon);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemNo(rset.getInt("MEM_NO"));
				m.setMemId(rset.getString("MEM_ID"));
				m.setMemName(rset.getString("MEM_NAME"));
				m.setMemNickname(rset.getString("MEM_NICKNAME"));
				m.setMemEmail(rset.getString("MEM_EMAIL"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setMemGradeName(rset.getString("MEM_GRADE_NAME"));
				
				
				list.add(m);
				System.out.println("회원정보조회리스트add>>"+  list);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		System.out.println("회원정보조회리스트리턴>"+  list);
		return list;
	}

	
	public ArrayList<Member> searchmemId(Connection conn, String memSearchcon){
		
		ArrayList<Member> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchmemId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memSearchcon);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemNo(rset.getInt("MEM_NO"));
				m.setMemId(rset.getString("MEM_ID"));
				m.setMemName(rset.getString("MEM_NAME"));
				m.setMemNickname(rset.getString("MEM_NICKNAME"));
				m.setMemEmail(rset.getString("MEM_EMAIL"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setMemGradeName(rset.getString("MEM_GRADE_NAME"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
}
