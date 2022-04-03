package com.kh.mvc.meal.controller;

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
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.kh.common.util.PageInfo;
import com.kh.mvc.recipe.model.service.HealthRecipeService;
import com.kh.mvc.recipe.model.vo.HealthRecipe;

@WebServlet("/health/meal")
public class HealthMealServlet extends HttpServlet {

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
		HttpSession session = req.getSession();
		
		if (req.getParameter("removeRcpNm") != null) {
			removeRecipeData(req, resp);

			return;
		}

		if (req.getParameter("totalInfoEng") != null) {		// ajax로 현재 클릭한 메뉴의 영양정보를 받아왔을 경우 session에 영양정보 저장
			saveInfoData(req, session);

			if (req.getParameter("remove") != null) {
				planMeal(req, session);
			}
		}
		
		if (req.getParameter("empty") != null && req.getParameter("empty").equals("empty") == true) {
			initializeInfoData(session);
		}

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

		req.getRequestDispatcher("/views/meal/health_meal.jsp").forward(req, resp);
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
	
	@SuppressWarnings("unchecked")
	private void removeRecipeData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String RCP_NM = req.getParameter("removeRcpNm");
		
		HealthRecipe healthRecipe = service.findHealthRecipeByName(RCP_NM);
		JSONObject object = new JSONObject();
		object.put("infoEng", healthRecipe.getINFO_ENG());
		object.put("infoCar", healthRecipe.getINFO_CAR());
		object.put("infoPro", healthRecipe.getINFO_PRO());
		object.put("infoFat", healthRecipe.getINFO_FAT());
		object.put("infoNa", healthRecipe.getINFO_NA());
		
		resp.setContentType("application/json;charset=UTF-8");
		resp.getWriter().append(object.toJSONString());
	}
	
	private void saveInfoData(HttpServletRequest req, HttpSession session) {
		session.setAttribute("totalInfoEng", req.getParameter("totalInfoEng"));	
		session.setAttribute("totalInfoCar", req.getParameter("totalInfoCar"));
		session.setAttribute("totalInfoPro", req.getParameter("totalInfoPro"));
		session.setAttribute("totalInfoFat", req.getParameter("totalInfoFat"));
		session.setAttribute("totalInfoNa", req.getParameter("totalInfoNa"));
	}
	
	private void planMeal(HttpServletRequest req, HttpSession session) {
		if (req.getParameter("remove").equals("N") == true) {								// 추가
			if (session.getAttribute("rcpNm") == null) {									// 처음으로 식단 목록에 메뉴 추가하는 경우
				String rcpNm = req.getParameter("rcpNm");
				
				session.setAttribute("rcpNm", rcpNm);
				session.setAttribute("isArray", "N");
			} else {																		// 이미 메뉴가 있는 경우
				if (session.getAttribute("rcpNm").getClass().isArray() == false) {			// 식단 메뉴가 한가지인 경우
					List<String> rcpNmList = new ArrayList<String>();
					rcpNmList.add((String) session.getAttribute("rcpNm"));
					rcpNmList.add(req.getParameter("rcpNm"));
					
					String[] rcpNm = rcpNmList.toArray(new String[2]);
					session.setAttribute("rcpNm", rcpNm);
					session.setAttribute("isArray", "Y");
				} else {																	// 식단 메뉴가 여러개인 경우
					String[] temp = (String[]) session.getAttribute("rcpNm");
					List<String> rcpNmList = new ArrayList<String>();
					
					for (int i = 0; i < temp.length; i++) {
						rcpNmList.add(temp[i]);
					}

					rcpNmList.add(req.getParameter("rcpNm"));

					String[] rcpNm = rcpNmList.toArray(new String[rcpNmList.size()]);
					session.setAttribute("rcpNm", rcpNm);
					session.setAttribute("isArray", "Y");
				}
			}
		} else {																			// 제거
			if (session.getAttribute("rcpNm").getClass().isArray() == false) {
				session.removeAttribute("rcpNm");
			} else {
				String[] temp = (String[]) session.getAttribute("rcpNm");
				List<String> rcpNmList = new ArrayList<String>();
				
				for (int i = 0; i < temp.length; i++) {
					rcpNmList.add(temp[i]);
				}

				int index = rcpNmList.indexOf(req.getParameter("rcpNm"));
				rcpNmList.remove(index);
				
				if (rcpNmList.size() == 1) {												// 삭제 후 목록에 한개만 존재할 경우
					String rcpNm = rcpNmList.get(0);
					session.setAttribute("rcpNm", rcpNm);
					session.setAttribute("isArray", "N");
				} else {
					String[] rcpNm = rcpNmList.toArray(new String[rcpNmList.size()]);
					session.setAttribute("rcpNm", rcpNm);
					session.setAttribute("isArray", "Y");
				}
			}
		}
	}
	
	private void initializeInfoData(HttpSession session) {
		session.removeAttribute("totalInfoEng");
		session.removeAttribute("totalInfoCar");
		session.removeAttribute("totalInfoPro");
		session.removeAttribute("totalInfoFat");
		session.removeAttribute("totalInfoNa");
		session.removeAttribute("rcpNm");
		session.removeAttribute("isArray");
	}

}
