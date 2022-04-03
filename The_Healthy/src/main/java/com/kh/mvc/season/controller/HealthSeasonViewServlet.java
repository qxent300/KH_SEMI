package com.kh.mvc.season.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.season.model.service.HealthSeasonService;
import com.kh.mvc.season.model.vo.HealthSeason;

@WebServlet("/eat/season/view")
public class HealthSeasonViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HealthSeasonService service = new HealthSeasonService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int hsno = Integer.parseInt(req.getParameter("hsno"));
		HealthSeason hs = service.findHealthSeasonByNo(hsno);
		
		req.setAttribute("hs", hs);
		req.getRequestDispatcher("/views/season/season_view.jsp").forward(req, resp);
	}
}
