package com.kh.mvc.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.community.model.service.FreeBoardService;
import com.kh.mvc.community.model.vo.Reply;
import com.kh.mvc.member.model.vo.Member;

@WebServlet("/community/replySave")
public class ReplySaveServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private FreeBoardService service = new FreeBoardService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Member loginMember = (Member) req.getSession().getAttribute("loginMember");
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		if(loginMember != null) {
		try {
				
			
			Reply reply = new Reply();
		
			reply.setBoardNo(boardNo);
			reply.setWriterId(req.getParameter("writer"));
			String content = req.getParameter("content");
			
			reply.setContent(req.getParameter("content"));
			reply.setWriterNo(loginMember.getNo());
			
			int result = service.saveReply(reply);
			if(result > 0) {
				req.setAttribute("msg", "댓글 저장 성공");
			} else {
				req.setAttribute("msg", "댓글 저장에 실패하였습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "댓글 저장에 실패하였습니다 error-100");
		}
		} else {
			req.setAttribute("msg", "로그인을 해주세요");
			
		}
		req.setAttribute("location", "/community/boardview?boardNo=" + boardNo);
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}
}
