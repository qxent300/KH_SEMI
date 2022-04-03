package com.kh.mvc.community.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.community.model.service.FreeBoardService;
import com.kh.mvc.community.model.vo.FreeBoard;
import com.kh.mvc.member.model.vo.Member;

@WebServlet("/community/boardDelete")
public class BoardDeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	FreeBoardService service = new FreeBoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				FreeBoard board = service.findBoardByNo(boardNo, false);
				int result = service.delete(boardNo);
				System.out.println(boardNo);
				if(result > 0) {
					deleteFile(board);
					req.setAttribute("msg", "게시글 삭제 성공!");
				}else {
					req.setAttribute("msg", "게시글 삭제 실패하였습니다");
				}
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("msg", "게시글 삭제 실패하였습니다!!!!");
			}
			
			req.setAttribute("location", "/community/boardlist");
			req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
		}
		private void deleteFile(FreeBoard board) {
			try {
				String path = getServletContext().getRealPath("/resources/upload/board");
				File deleteFile = new File(path, board.getRenamedFileName());
				deleteFile.delete();
			} catch (Exception e) {
				}
			}
			
	

}
