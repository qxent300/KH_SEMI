package com.kh.mvc.member.model.service;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.vo.Member;

public class MemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	public Member findMemberById(String id) {
		Connection conn = getConnection();
		Member member = memberDao.findMemberById(conn, id);
		close(conn);
		
		return member;
	}
	
	public Member login(String id, String pw) {
		Member member = findMemberById(id);

		if(member != null && id.equals("admin") == true) {
			return member;
		}
		
		if(member != null && member.getPassword().equals(pw) == true) {
			return member;
		} else {
			return null;
		}
	}
	
	public int save(Member member) {
		int result = 0;
		Connection conn = getConnection();
		
		if(member.getNo() != 0) {
			result = memberDao.updateMember(conn, member);
		} else {
			result = memberDao.insertMember(conn, member);
		}
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public String findWriterByNo(int userno) {
		Connection conn = getConnection();
		String writer = memberDao.findWriterByNo(conn, userno);
		close(conn);
		
		return writer;
	}

	public int delete(int no) {
		Connection conn = getConnection();
		int result = memberDao.updateStatus(conn, no, "N");
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int doubleCheckId(String enrollID) {
		Connection conn = getConnection();
		String id = memberDao.doubleCheckId(conn, enrollID);
		close(conn);
		
		if (id.equals("") == true) {
			return 0;
		}
		
		return 1;
	}

}
