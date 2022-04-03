package com.kh.mvc.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.community.model.service.FreeBoardService;
import com.kh.mvc.community.model.vo.FreeBoard;

@WebServlet("/community/boardview")
public class BoardViewServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private FreeBoardService service = new FreeBoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		FreeBoard board = service.findBoardByNo(boardNo, true);
		
		
		if(board == null) {
			req.getRequestDispatcher(req.getContextPath()).forward(req, resp);
			return;
		}
		
		req.setAttribute("board", board);
		req.getRequestDispatcher("/views/community/board_view.jsp").forward(req, resp);
		
	}

}
