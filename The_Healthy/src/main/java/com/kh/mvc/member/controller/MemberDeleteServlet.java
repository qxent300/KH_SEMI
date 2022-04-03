package com.kh.mvc.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.community.model.service.FreeBoardService;
import com.kh.mvc.community.model.service.UserRecipeService;
import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name = "delete", urlPatterns = "/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();
	private FreeBoardService freeBoardService = new FreeBoardService();
	private UserRecipeService userRecipeService = new UserRecipeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			String userPwd = req.getParameter("userPwd");

			if (loginMember == null) {
				req.setAttribute("msg", "잘못된 접근입니다.");
				req.setAttribute("location", "/");
				req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
				
				return;
			}

			if (loginMember.getPassword().equals(userPwd) == true) {
				freeBoardService.deleteByWriterNo(loginMember.getNo());
				freeBoardService.deleteAllReplyByUserNo(loginMember.getNo());
				
				List<Integer> userRecipeNoList = userRecipeService.findUserRecipeNoByWriterNo(loginMember.getNo());
				
				for (Integer userRecipeNo : userRecipeNoList) {
					userRecipeService.deleteUserRecipe(userRecipeNo);					
				}

				int result = memberService.delete(loginMember.getNo());
				
				if (result > 0) {
					session.invalidate();
					
					req.setAttribute("msg", "회원 탈퇴에 성공하였습니다.");
					req.setAttribute("location", "/");
					req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
					
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("msg", "회원 탈퇴에 실패하였습니다. 문의 바랍니다.");
		req.setAttribute("location", "/");
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}

}
