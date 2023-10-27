package com.kh.semi.member.model.dao;

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
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.member.model.vo.MemberUpdate;

public class MemberDao {
	
	// Properties 객체 생성
	private Properties prop = new Properties();
	
	// 기본생성자 호출 시 member-mapper.xml파일 읽어오기
	public MemberDao() {
		
		String file = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 로그인시 로그인 한 유저 DB정보 가져오기
	public Member loginMember(Connection conn, String memberId, String memberPwd) {
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("MEM_NO"),
							   rset.getString("MEM_ID"),
							   rset.getString("MEM_PWD"),
							   rset.getString("MEM_NAME"),
							   rset.getString("MEM_NICKNAME"),
							   rset.getString("MEM_EMAIL"),
							   rset.getString("MEM_STATUS"),
							   rset.getDate("ENROLL_DATE"),
							   rset.getDate("MODIFY_DATE"),
							   rset.getDate("DELETE_DATE"),
							   rset.getString("MEM_PICTURE"),
							   rset.getInt("MEM_GRADE_NUMBER"),
							   rset.getString("MEM_GRADE_NAME"),
							   rset.getInt("MEM_COUPON_COUNT"),
							   rset.getInt("MEM_REWARD"));
			}	

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}
	
	// 리워드 조회
	public int memReward(Connection conn, int memNo) {
		
		int mReward = -1;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("memReward");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mReward = rset.getInt("REWARD");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return mReward;
	}
	
	// 회원가입
	/**
	 * @param conn : Connection 객체
	 * @param m : 회원가입하는 Member객체
	 * @return : 회원insert 성공 여부에 따른 결과값(성공1 또는 실패0)
	 */
	public int insertMember(Connection conn, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemName());
			pstmt.setString(2, m.getMemNickname());
			pstmt.setString(3, m.getMemId());
			pstmt.setString(4, m.getMemPwd());
			pstmt.setString(5, m.getMemEmail());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 회원가입 시 nickname 중복체크
	/**
	 * @param conn : Connection 객체
	 * @param checkNickname : 회원가입에 쓸 사용자 nickname 입력값
	 * @return : 이미 존재하는 아이디 1 또는 사용가능 0
	 */
	public int nicknameCheck(Connection conn, String checkNickname) {
		
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("nicknameCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, checkNickname);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}
	
	// 회원가입시 id 중복체크
	/**
	 * @param conn : Connection 객체
	 * @param checkId : 회원가입에 쓸 사용자 id 입력값
	 * @return : 이미 존재하는 아이디 1 또는 사용가능 0
	 */
	public int idCheck(Connection conn, String checkId) {
		
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, checkId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}
	
	// 회원가입시 email 중복체크
	/**
	 * @param conn : Connection 객체
	 * @param checkEmail : 회원가입에 쓸 사용자 email 입력값
	 * @return : 이미 사용중인 이메일 1 또는 사용가능 0
	 */
	public int emailCheck(Connection conn, String checkEmail) {
		
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("emailCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, checkEmail);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}
	
	// 아이디 찾기
	/**
	 * @param conn : Connection 객체
	 * @param memberName : 아이디를 찾고자 하는 사용자 이름 입력값
	 * @param memberEmail : 아이디를 찾고자 하는 사용자 이메일 입력값
	 * @return : 조회된 회원
	 */
	public Member searchMemberId(Connection conn, String memberName, String memberEmail) {

		Member searchMember = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchMemberId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberEmail);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				searchMember = new Member(rset.getInt("MEM_NO"),
										  rset.getString("MEM_ID"),
										  rset.getString("MEM_PWD"),
										  rset.getString("MEM_NAME"),
										  rset.getString("MEM_NICKNAME"),
										  rset.getString("MEM_EMAIL"),
										  rset.getString("MEM_STATUS"),
										  rset.getDate("ENROLL_DATE"),
										  rset.getDate("MODIFY_DATE"),
										  rset.getDate("DELETE_DATE"),
										  rset.getString("MEM_PICTURE"),
										  rset.getInt("MEM_GRADE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return searchMember;
	}
	
	// 비밀번호 찾기
	/**
	 * @param conn : Connection 객체
	 * @param memberId : 비밀번호를 찾고자 하는 사용자 아이디 입력값
	 * @param memberEmail : 비밀번호를 찾고자 하는 사용자 이메일 입력값
	 * @return : 조회된 결과가 있다면 1 반환
	 */
	public int searchMemberPwd(Connection conn, String memberId, String memberEmail) {
		
		int result = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("searchMemberPwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberEmail);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	// 회원 비밀번호 재설정
	public int updateMemberPwd(Connection conn, String memberId, String memberPwd) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMemberPwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberPwd);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 회원 정보 변경 시 비밀번호 확인
	public String memberUpdateConfirm(Connection conn, int memberNo) {
		
		String checkPwd = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("memberUpdateConfirm");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				checkPwd = rset.getString("MEM_PWD");
			}
			System.out.println(checkPwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return checkPwd;
	}
	
	// 회원 정보 변경
	public int memberUpdate(Connection conn, Member m, String memberPicture) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("memberUpdate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			// 변경할 이름, 닉네임, 이메일, 사진(경로 + 수정명)
			pstmt.setString(1, m.getMemName());
			pstmt.setString(2, m.getMemNickname());
			pstmt.setString(3, m.getMemEmail());
			pstmt.setString(4, memberPicture);
			pstmt.setString(5, m.getMemId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 회원 삭제(탈퇴)
	public int memberDelete(Connection conn, int memberNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("memberDelete");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
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
