package com.kh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name = "enroll", urlPatterns = "/member/enroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberService service =  new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String currentUrl = req.getParameter("currentEnrollUrl");
		Member member = new Member();

		try {
			member.setId(req.getParameter("enrollID")); 
			member.setPassword(req.getParameter("enrollPassword"));
			member.setName(req.getParameter("enrollName"));
			member.setEmail(req.getParameter("enrollEmail"));
			member.setAddress(req.getParameter("enrollAddress"));
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "회원가입 실패! (입력값에 문제가 있습니다.)");
			req.setAttribute("location", "/");
			req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
			return;
			 
		}
		
		int result = service.save(member);
		 
		if(result > 0) {
			req.setAttribute("msg", "회원가입 성공!");
			req.setAttribute("location", "/");
		}else {
			req.setAttribute("msg", "회원가입을 실패!");
			req.setAttribute("location", "/");
		}
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
		
	}
	

}
