package com.kh.mvc.recipe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.recipe.model.service.HealthRecipeService;
import com.kh.mvc.recipe.model.vo.HealthRecipe;

@WebServlet("/health/recipe/view")
public class HealthRecipeViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private HealthRecipeService service = new HealthRecipeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int hrno = Integer.parseInt(req.getParameter("hrno"));
		
		HealthRecipe healthRecipe = service.findHealthRecipeByNo(hrno);
		
		req.setAttribute("healthRecipe", healthRecipe);
		
		req.getRequestDispatcher("/views/recipe/health_recipe_view.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
