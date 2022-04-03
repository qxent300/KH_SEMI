package com.kh.mvc.recipe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kh.mvc.recipe.model.service.HealthRecipeService;
import com.kh.mvc.recipe.model.vo.HealthRecipe;

@WebServlet("/recommend/recipe")
public class RecommendRecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private HealthRecipeService service = new HealthRecipeService();

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int[] randomArr = recommendRecipeNumber();
		List<HealthRecipe> list = new ArrayList<HealthRecipe>();
		
		for (int i : randomArr) {
			list.add(recommendRecipe(i));
		}
		
		JSONArray jsonArray = new JSONArray();
		
		for (HealthRecipe healthRecipe : list) {
			JSONObject object = new JSONObject();
			object.put("hrNo", healthRecipe.getHR_NO());
			object.put("rcpNm", healthRecipe.getRCP_NM());
			object.put("img", healthRecipe.getATT_FILE_NO_MK());
			
			jsonArray.add(object);
		}

		resp.setContentType("application/json;charset=UTF-8");
		resp.getWriter().append(jsonArray.toJSONString());
	}

	private int[] recommendRecipeNumber() {
		int[] result = new int[5];
		
		for (int i = 0; i < 5; i++) {
			result[i] = new Random().nextInt(970) + 1;

			for (int j = 0; j < i; j++) {
				if (result[i] == result[j]) {
					i--;
				}
			}
		}
		
		return result;
	}
	
	private HealthRecipe recommendRecipe(int i) {
		HealthRecipe result = service.findHealthRecipeByNo(i);
		
		return result;
	}
	
}
