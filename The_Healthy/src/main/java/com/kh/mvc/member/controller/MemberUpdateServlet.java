package com.kh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = memberService.findMemberById(req.getParameter("userId"));
		
		member.setEmail(req.getParameter("userEmail"));
		member.setAddress(req.getParameter("userAddress"));
		
		int result = memberService.save(member);
		
		if (result > 0) {
			req.setAttribute("msg", "회원 정보를 수정하였습니다.");
			req.setAttribute("location", "/");
			req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
			
			return;
		}
		
		req.setAttribute("msg", "회원 정보 수정에 실패하였습니다.");
		req.setAttribute("location", "/");
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}

}
