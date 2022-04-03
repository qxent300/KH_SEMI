package com.kh.mvc.community.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.util.MyFileRenamePolicy;
import com.kh.mvc.community.model.service.FreeBoardService;
import com.kh.mvc.community.model.vo.FreeBoard;
import com.kh.mvc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/community/boardwrite")
public class BoardWriteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	FreeBoardService service = new FreeBoardService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");  
			if(loginMember != null) {
				req.getRequestDispatcher("/views/community/board_write.jsp").forward(req, resp);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sendCommonPage(req,resp,"로그인 후 사용할수 있습니다.", "/community/boardlist");
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			Member loginMember = (Member) req.getSession().getAttribute("loginMember");
			String path = getServletContext().getRealPath("/resources/upload/board");
			File dir = new File(path);
			if (dir.exists() == false) {
				dir.mkdirs();
			}
			int maxSize = 104857600;
			String encoding = "UTF-8";
			MultipartRequest mr 
			= new MultipartRequest(req, path, maxSize, encoding, new MyFileRenamePolicy()); 
			String writer = mr.getParameter("writer");
			
			
			if(loginMember.getId().equals(writer) == false) {
				sendCommonPage(req, resp, "잘못된 접근입니다. (code=100)", "/community/boardlist");
				return;
			}
			FreeBoard board = new FreeBoard();
			board.setTitle(mr.getParameter("title"));
			board.setWriterId(writer);
			board.setWriterNo(loginMember.getNo());
			board.setOriginalFileName(mr.getOriginalFileName("upfile"));
			board.setRenamedFileName(mr.getFilesystemName("upfile"));
			board.setContent(mr.getParameter("content"));

			int result = service.save(board);
			if(result <= 0 ) {
				sendCommonPage(req, resp, "게시글을 저장할수 없습니다. (code=101)", "/community/boardlist");
				return;
			}
			sendCommonPage(req, resp, "게시글이 등록 되었습니다.", "/community/boardlist");
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage(req, resp, "게시글을 정상적으로 저장할수 없습니다. (code=102)", "/community/boardlist");
		}
	}
	private void sendCommonPage(HttpServletRequest req, HttpServletResponse resp, String msg, String location) throws ServletException, IOException {
		req.setAttribute("msg", msg);
		req.setAttribute("location", location);
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}

}
