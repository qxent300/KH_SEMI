package com.kh.mvc.community.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.util.PageInfo;
import com.kh.mvc.community.model.service.UserRecipeService;
import com.kh.mvc.community.model.vo.UserRecipe;
import com.kh.mvc.member.model.service.MemberService;

@WebServlet("/community/userRecipeList")
public class UserRecipeListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UserRecipeService userRecipeService = new UserRecipeService();
	private MemberService memberService = new MemberService();
	
	private Map<String, String> RCP_PAT = new HashMap<String, String>();
	private Map<String, String> RCP_WAY = new HashMap<String, String>();
	
	{
		RCP_PAT.put("kindall", "전체");
		RCP_PAT.put("rice", "rice");
		RCP_PAT.put("stew", "stew");
		RCP_PAT.put("side", "side");
		RCP_PAT.put("alacarte", "alacarte");
		RCP_PAT.put("dessert", "dessert");
		RCP_PAT.put("kindetc", "kindetc");
		
		RCP_WAY.put("howtoall", "전체");
		RCP_WAY.put("boil", "boil");
		RCP_WAY.put("roast", "roast");
		RCP_WAY.put("fry", "fry");
		RCP_WAY.put("steam", "steam");
		RCP_WAY.put("stir", "stir");
		RCP_WAY.put("howtoetc", "howtoetc");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		List<UserRecipe> list = null;
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

		req.getRequestDispatcher("/views/community/userRecipe_list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private void pageListCategorySearch(int page, List<UserRecipe> list, HttpServletRequest req, String category, String search) {
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
			boardCount = userRecipeService.getUserRecipeCount(RCP_PAT2, RCP_WAY2, search);
			pageInfo = new PageInfo(page, 10, boardCount, 9);
			list = userRecipeService.getUserRecipeList(pageInfo, RCP_PAT2, RCP_WAY2, search);
		} else {
			boardCount = userRecipeService.getUserRecipeCount(RCP_PAT2, RCP_WAY2, null);
			pageInfo = new PageInfo(page, 10, boardCount, 9);
			list = userRecipeService.getUserRecipeList(pageInfo, RCP_PAT2, RCP_WAY2, null);
		}
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		for (UserRecipe userRecipe : list) {
			int hrno = userRecipe.getUSER_NO();
			String writer = memberService.findWriterByNo(hrno);
			
			map.put(hrno, writer);
		}

		req.setAttribute("list", list);
		req.setAttribute("pageInfo", pageInfo);
		req.setAttribute("category", category);
		req.setAttribute("search", search);
		req.setAttribute("map", map);
	}

}
