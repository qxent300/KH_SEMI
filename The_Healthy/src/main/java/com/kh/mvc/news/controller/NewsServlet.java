package com.kh.mvc.news.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.news.model.service.NewsService;
import com.kh.mvc.news.model.vo.News;

@WebServlet("/community/news")
public class NewsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private NewsService service = new NewsService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<News> list = service.getNewsList();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/views/news/news.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
