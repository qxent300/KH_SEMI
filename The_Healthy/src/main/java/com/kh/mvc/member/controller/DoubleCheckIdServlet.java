package com.kh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.member.model.service.MemberService;

@WebServlet("/member/doubleCheckId")
public class DoubleCheckIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String enrollID = req.getParameter("enrollID");
		int result = service.doubleCheckId(enrollID);
		
		resp.setContentType("text/html;charset=UTF-8");
		
		if (result == 0) {
			resp.getWriter().append("사용 가능한 아이디입니다!");
		} else {
			resp.getWriter().append("이미 사용중인 아이디입니다...");
		}
	}

}
