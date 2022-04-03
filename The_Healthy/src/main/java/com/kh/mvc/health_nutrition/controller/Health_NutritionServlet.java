package com.kh.mvc.health_nutrition.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.util.PageInfo;
import com.kh.mvc.health_nutrition.model.service.Health_Nutrition_Service;
import com.kh.mvc.health_nutrition.model.vo.Health_Nutrition;

@WebServlet("/eat/nutrition")
public class Health_NutritionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Health_Nutrition_Service service = new Health_Nutrition_Service();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		int hnCount = 0;
		PageInfo pageInfo = null;
		List<Health_Nutrition> list = null;
		
		try {
			page = Integer.parseInt(req.getParameter("page"));
		} catch (Exception e) {
		}
		
		hnCount = service.getHealthFoodCount();
		pageInfo = new PageInfo(page, 10, hnCount, 9);
		list = service.getHealthFoodList(pageInfo);
		
		req.setAttribute("list", list);
	    req.setAttribute("pageInfo", pageInfo);
		req.getRequestDispatcher("/views/health_nutrition/health_nutrition.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
