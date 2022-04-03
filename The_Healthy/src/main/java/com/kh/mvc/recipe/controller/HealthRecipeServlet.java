package com.kh.mvc.recipe.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.util.PageInfo;
import com.kh.mvc.recipe.model.service.HealthRecipeService;
import com.kh.mvc.recipe.model.vo.HealthRecipe;

@WebServlet("/health/recipe")
public class HealthRecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private HealthRecipeService service = new HealthRecipeService();
	
	private Map<String, String> RCP_PAT = new HashMap<String, String>();
	private Map<String, String> RCP_WAY = new HashMap<String, String>();
	
	{
		RCP_PAT.put("kindall", "전체");
		RCP_PAT.put("rice", "밥");
		RCP_PAT.put("stew", "국&찌개");
		RCP_PAT.put("side", "반찬");
		RCP_PAT.put("alacarte", "일품");
		RCP_PAT.put("dessert", "후식");
		RCP_PAT.put("kindetc", "기타");
		
		RCP_WAY.put("howtoall", "전체");
		RCP_WAY.put("boil", "끓이기");
		RCP_WAY.put("roast", "굽기");
		RCP_WAY.put("fry", "튀기기");
		RCP_WAY.put("steam", "찌기");
		RCP_WAY.put("stir", "볶기");
		RCP_WAY.put("howtoetc", "기타");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		List<HealthRecipe> list = null;
		String category = "";
		String search = "";
		
		if (req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		if (req.getParameter("category") != null) {
			category = req.getParameter("category");
		}
		
		if (req.getParameter("search") != null) {
			search = req.getParameter("search");
		}

		pageListCategorySearch(page, list, req, category, search);

		req.getRequestDispatcher("/views/recipe/health_recipe.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private void pageListCategorySearch(int page, List<HealthRecipe> list, HttpServletRequest req, String category, String search) {
		int boardCount = 0;
		PageInfo pageInfo = null;
		String RCP_PAT2 = "전체";	// 요리 종류
		String RCP_WAY2 = "전체";	// 조리 방법

		if (category.equals("") == false) {
			if (RCP_PAT.containsKey(category) == true) {
				RCP_PAT2 = RCP_PAT.get(category);
			}
			
			if (RCP_WAY.containsKey(category) == true) {
				
				RCP_WAY2 = RCP_WAY.get(category);
			}
		}
		
		if (search.equals("") == false) {
			boardCount = service.getHealthRecipeCount(RCP_PAT2, RCP_WAY2, search);
			pageInfo = new PageInfo(page, 10, boardCount, 9);
			list = service.getHealthRecipeList(pageInfo, RCP_PAT2, RCP_WAY2, search);
		} else {
			boardCount = service.getHealthRecipeCount(RCP_PAT2, RCP_WAY2, null);
			pageInfo = new PageInfo(page, 10, boardCount, 9);
			list = service.getHealthRecipeList(pageInfo, RCP_PAT2, RCP_WAY2, null);
		}

		req.setAttribute("list", list);
		req.setAttribute("pageInfo", pageInfo);
		req.setAttribute("category", category);
		req.setAttribute("search", search);
	}

}
