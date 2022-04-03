package com.kh.mvc.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.util.PageInfo;
import com.kh.mvc.community.model.service.FreeBoardService;
import com.kh.mvc.community.model.vo.FreeBoard;

@WebServlet("/community/boardlist")
public class BoardlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FreeBoardService service = new FreeBoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		int BoardCount = 0;
		PageInfo pageInfo = null;
		List<FreeBoard> list = null;
		
		try {
			page = Integer.parseInt(req.getParameter("page"));	
		} catch (Exception e) {}
		
		BoardCount =service.getBoardCount();
		pageInfo = new PageInfo(page, 10, BoardCount, 10);
		list = service.getBoardList(pageInfo);
		
		req.setAttribute("list", list);
		req.setAttribute("pageInfo", pageInfo);
		req.getRequestDispatcher("/views/community/board_list.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		
	}
}
