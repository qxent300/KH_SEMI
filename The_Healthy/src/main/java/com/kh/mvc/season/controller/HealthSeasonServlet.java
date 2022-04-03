package com.kh.mvc.season.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.util.PageInfo;
import com.kh.mvc.season.model.service.HealthSeasonService;
import com.kh.mvc.season.model.vo.HealthSeason;

@WebServlet("/eat/season")
public class HealthSeasonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HealthSeasonService service = new HealthSeasonService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		int listCount = 0;
		PageInfo pageInfo = null;
		List<HealthSeason> list = null;
		
		String M_DISTCTNS = req.getParameter("M_DISTCTNS");
		
		try {
			try {
				page = Integer.parseInt(req.getParameter("page"));
			} catch (Exception e) {}
			
			if(M_DISTCTNS == null) {
				M_DISTCTNS = "전체";
			}

			listCount = service.getHealthSeasonCount(M_DISTCTNS);
			pageInfo = new PageInfo(page, 10, listCount, 9);
			list = service.getHealthSeasonList(pageInfo, M_DISTCTNS);
			
			req.setAttribute("list", list);
			req.setAttribute("pageInfo", pageInfo);
			req.setAttribute("M_DISTCTNS", M_DISTCTNS);
			
		} catch (Exception e) {
		}
		
		req.getRequestDispatcher("/views/season/season.jsp").forward(req, resp);
	}
}
