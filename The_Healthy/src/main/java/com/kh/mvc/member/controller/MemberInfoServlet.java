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

@WebServlet("/member/info")
public class MemberInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			
			if (loginMember != null) {
				Member member = memberService.findMemberById(loginMember.getId());
				req.setAttribute("member", member);
				req.getRequestDispatcher("/views/member/member_info.jsp").forward(req, resp);
				
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("msg", "로그인 후 사용할 수 있습니다.");
		req.setAttribute("location", "/");
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
		
		
		
	}

}
