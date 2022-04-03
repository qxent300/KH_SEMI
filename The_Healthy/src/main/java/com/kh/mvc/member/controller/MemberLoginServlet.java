package com.kh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name = "login", urlPatterns = "/member/login")
public class MemberLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String userPwd = req.getParameter("userPwd");
		String currentUrl = req.getParameter("currentUrl");
		
		Member loginMember = service.login(userId, userPwd);
		
		if (loginMember != null) {
			HttpSession session = req.getSession();
			session.setAttribute("loginMember", loginMember);
			
			resp.sendRedirect(currentUrl);
		} else {
			req.setAttribute("msg", "아이디 또는 비밀번호가 맞지 않습니다...!");
			req.setAttribute("location", "/");
			
			req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
		}
	}

}
