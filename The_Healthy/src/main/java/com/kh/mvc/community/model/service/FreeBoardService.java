package com.kh.mvc.community.model.service;


	import static com.kh.common.jdbc.JDBCTemplate.*;

	import java.sql.Connection;
	import java.util.List;

	import com.kh.common.util.PageInfo;
import com.kh.mvc.community.model.dao.FreeBoardDao;
import com.kh.mvc.community.model.vo.FreeBoard;
import com.kh.mvc.community.model.vo.Reply;

	public class FreeBoardService {
		private FreeBoardDao dao = new FreeBoardDao();
		
		public int getBoardCount() {
			Connection conn = getConnection();
			int result = dao.getBoardCount(conn);
			close(conn);
			return result;
		}
		public int getReplyCount(int boardNo) {
			Connection conn = getConnection();
			int result = dao.getReplyCount(conn, boardNo);
			close(conn);
			return result;
		}

		public List<FreeBoard> getBoardList(PageInfo pageinfo) {
			Connection conn = getConnection();
			List<FreeBoard> list = dao.findAll(conn, pageinfo);
			close(conn);
			return list;
		}
		
		public int save(FreeBoard board) {
			Connection conn = getConnection();
			int result = 0;
			
			if(board.getNo() != 0) {
				result = dao.updateBoard(conn, board);
			}else {
				result = dao.insertBoard(conn, board);
			}
			
			if(result > 0 ) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			close(conn);
			return result;
		}
		
		public FreeBoard findBoardByNo(int no, boolean hasRead) {
			Connection conn = getConnection();
			FreeBoard board = dao.findBoardByNo(conn, no);
			
			// 조회수 증가 로직
			if(hasRead == true && board != null) {
				int result = dao.updateReadCount(conn, board);
				if(result > 0 ) {
					commit(conn);
				}else {
					rollback(conn);
				}
			}
			
			close(conn);
			return board;
		}
		
		public int delete(int no) {
			Connection conn = getConnection();
			int result = dao.updateStatus(conn, no, "N");
			
			if(result > 0 ) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			close(conn);
			return result;
		}

		public int saveReply(Reply reply) {
			Connection conn = getConnection();
			int result = dao.insertReply(conn, reply);
			
			if(result > 0 ) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			close(conn);
			return result;
		}

		public int deleteReply(int replyNo) {
			Connection conn = getConnection();
			
			int result = dao.deleteReply(conn, replyNo);
			
			if(result > 0 ) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			close(conn);
			return result;
		}
		
		public int deleteAllReplyByUserNo(int userNo) {
			Connection conn = getConnection();
			
			int result = dao.deleteAllReplyByUserNo(conn, userNo);
			
			if(result > 0 ) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			close(conn);
			
			return result;
		}
		
		public int deleteByWriterNo(int writerNo) {
			Connection conn = getConnection();
			int result = dao.updateStatusByWriterNo(conn, writerNo, "N");
			
			if(result > 0 ) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			close(conn);
			return result;
		}		

}
