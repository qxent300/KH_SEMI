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

@WebServlet("/community/boardupdate")
public class BoardUpdateServlet extends HttpServlet{
	private FreeBoardService service = new FreeBoardService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		FreeBoard board = service.findBoardByNo(boardNo, false);
		
		req.setAttribute("board", board);
		req.getRequestDispatcher("/views/community/board_update.jsp").forward(req, resp);
		
		if(loginMember != null) {
			return;
		}
		sendCommonPage(req,resp,"로그인 후 사용할수 있습니다.", "/");

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			String path = getServletContext().getRealPath("/resources/upload/board");
			int maxSize = 104857600; // 100MB
			String encoding = "UTF-8";
			MultipartRequest mr 
			= new MultipartRequest(req, path, maxSize, encoding, new MyFileRenamePolicy()); 
			String writer = mr.getParameter("writer");
			if(loginMember.getId().equals(writer) == false) {
				sendCommonPage(req, resp, "잘못된 접근입니다. (code=100)", "/community/boardlist");
				return;
			}
			FreeBoard board = new FreeBoard();
			board.setNo(Integer.parseInt(mr.getParameter("boardNo")));
			board.setTitle(mr.getParameter("title"));
			board.setWriterId(writer);
			board.setWriterNo(loginMember.getNo());
			board.setContent(mr.getParameter("content"));
				
			
			String originFileName = mr.getParameter("originFileName");
			String renameFileName = mr.getParameter("renameFileName");
			
			String originReloadFile = mr.getOriginalFileName("upfile");
			String renameReloadFile = mr.getFilesystemName("upfile");
			
			if(originReloadFile !=null && originReloadFile.length() > 0) {
				board.setOriginalFileName(originReloadFile);
				board.setRenamedFileName(renameReloadFile); 
				try {
					File deleteFile = new File(path, renameFileName);
					deleteFile.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				if(originFileName == null) {
					originFileName = "";
					renameFileName = "";
				}
				board.setOriginalFileName(originFileName);
				board.setRenamedFileName(renameFileName); 
			}
			
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