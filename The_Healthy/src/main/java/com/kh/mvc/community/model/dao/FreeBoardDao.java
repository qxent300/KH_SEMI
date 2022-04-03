package com.kh.mvc.community.model.dao;


import static com.kh.common.jdbc.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.util.PageInfo;
import com.kh.mvc.community.model.vo.FreeBoard;
import com.kh.mvc.community.model.vo.Reply;

public class FreeBoardDao {

	// 게시물 갯수
	public int getBoardCount(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query =  "SELECT COUNT(*) FROM FREEBOARD WHERE STATUS='Y'";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		return result;
	}
	public int getReplyCount(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*)from reply where board_NO= ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	// 게시물 정보 
	public List<FreeBoard> findAll(Connection conn, PageInfo pageInfo){
		List<FreeBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RNUM, NO, TITLE, ID, CREATE_DATE, ORIGINAL_FILENAME, READCOUNT, STATUS  "
				+ "FROM (\r\n"
				+ "    SELECT ROWNUM AS RNUM, NO, TITLE, ID, CREATE_DATE, ORIGINAL_FILENAME, READCOUNT, STATUS "
				+ "    FROM ( "
				+ "        SELECT  B.NO, B.TITLE, M.ID, B.CREATE_DATE, B.ORIGINAL_FILENAME, B.READCOUNT, B.STATUS "
				+ "        FROM FREEBOARD B JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
				+ "        WHERE B.STATUS = 'Y'  ORDER BY B.NO DESC "
				+ "    ) "
				+ ") "
				+ "WHERE RNUM BETWEEN ? and ? ";
		try {
			pstmt = conn.prepareStatement(query);
		
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FreeBoard board = new FreeBoard();
				board.setNo(rs.getInt("NO"));
				board.setRowNum(rs.getInt("RNUM"));
				board.setWriterId(rs.getString("ID"));
				board.setTitle(rs.getString("TITLE"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setReadCount(rs.getInt("READCOUNT"));
				board.setStatus(rs.getString("STATUS"));
				
				list.add(board);
			
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		return list;
	}
	//글쓰기
	public int insertBoard(Connection conn, FreeBoard board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO FREEBOARD VALUES(SEQ_BOARD_NO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getWriterNo());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getOriginalFileName());
			pstmt.setString(5, board.getRenamedFileName());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 게시글 가져오는 기능
	public FreeBoard findBoardByNo(Connection conn, int boardNo) {
		FreeBoard board = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT B.NO, B.TITLE, M.ID, B.READCOUNT, B.ORIGINAL_FILENAME, B.RENAMED_FILENAME, B.CONTENT, B.CREATE_DATE, B.MODIFY_DATE "
				+ "	FROM FREEBOARD B "
				+ "	JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
				+ "	WHERE B.STATUS = 'Y' AND B.NO = ? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new FreeBoard();
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriterId(rs.getString("ID"));
				board.setReadCount(rs.getInt("READCOUNT"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				board.setContent(rs.getString("CONTENT"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setModifyDate(rs.getDate("MODIFY_DATE"));
				board.setReplies(getRepliesByNo(conn, boardNo));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		return board;
	}
	// 조회수
	public int updateReadCount(Connection conn, FreeBoard board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE FREEBOARD SET READCOUNT=? WHERE NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			
			
			board.setReadCount(board.getReadCount() + 1);
			
			pstmt.setInt(1, board.getReadCount());
			pstmt.setInt(2, board.getNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 게시글 삭제
	public int updateStatus(Connection conn, int boardNo, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE FREEBOARD SET STATUS=? WHERE NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, boardNo);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int updateBoard(Connection conn ,FreeBoard board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE FREEBOARD SET TITLE=?,CONTENT=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?, "
				+ " MODIFY_DATE=SYSDATE WHERE NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getOriginalFileName());
			pstmt.setString(4, board.getRenamedFileName());
			pstmt.setInt(5, board.getNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	
	}
	//리플가져오기
	public List<Reply> getRepliesByNo(Connection conn , int boardNo){
		List<Reply> list = new ArrayList<Reply>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT R.NO, R.BOARD_NO, R.CONTENT, M.ID, R.CREATE_DATE, R.MODIFY_DATE "
				+ "FROM REPLY R "
				+ "JOIN MEMBER M ON(R.WRITER_NO = M.NO) "
				+ "WHERE R.STATUS='Y' AND BOARD_NO= ? "
				+ "ORDER BY R.NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setNo(rs.getInt("NO"));
				reply.setBoardNo(rs.getInt("BOARD_NO"));
				reply.setContent(rs.getString("CONTENT"));
				reply.setWriterId(rs.getString("ID"));
				reply.setCreateDate(rs.getDate("CREATE_DATE"));
				reply.setModifyDate(rs.getDate("MODIFY_DATE"));
				list.add(reply);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	//리플쓰기
	public int insertReply(Connection conn, Reply reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO REPLY VALUES(SEQ_REPLY_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, reply.getBoardNo());
			pstmt.setInt(2, reply.getWriterNo());
			pstmt.setString(3, reply.getContent());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	

	public int deleteReply(Connection conn, int replyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE REPLY WHERE NO= ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteAllReplyByUserNo(Connection conn, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE REPLY WHERE WRITER_NO= ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateStatusByWriterNo(Connection conn, int writerNo, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE FREEBOARD SET STATUS=? WHERE WRITER_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, writerNo);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
