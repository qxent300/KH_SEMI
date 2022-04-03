package com.kh.mvc.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.community.model.service.UserRecipeService;
import com.kh.mvc.community.model.vo.UserRecipe;
import com.kh.mvc.member.model.service.MemberService;

@WebServlet("/community/userRecipeView")
public class UserRecipeViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UserRecipeService userRecipeService = new UserRecipeService();
	private MemberService memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int hrno = Integer.parseInt(req.getParameter("hrno"));
		int userno = Integer.parseInt(req.getParameter("userno"));
		
		UserRecipe userRecipe = userRecipeService.findUserRecipeByNo(hrno);
		String writer = memberService.findWriterByNo(userno);
		
		req.setAttribute("userRecipe", userRecipe);
		req.setAttribute("writer", writer);
		
		req.getRequestDispatcher("/views/community/userRecipe_view.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
