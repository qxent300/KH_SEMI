package com.kh.mvc.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.community.model.service.FreeBoardService;

@WebServlet("/community/replyDel")
public class ReplyDelete extends HttpServlet{
	FreeBoardService service = new FreeBoardService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			int replyNo = Integer.parseInt(req.getParameter("replyNo"));
			
			int result = service.deleteReply(replyNo);
			
			if(result > 0) {
				req.setAttribute("msg", "리플 삭제 성공!");
			}else {
				req.setAttribute("msg", "리플 삭제를 실패하였습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "리플 삭제를 실패하였습니다!!.");
		}
		
		req.setAttribute("location", "/community/boardlist");
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}


}
