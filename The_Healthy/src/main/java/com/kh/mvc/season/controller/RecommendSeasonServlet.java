package com.kh.mvc.season.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kh.mvc.season.model.service.HealthSeasonService;
import com.kh.mvc.season.model.vo.HealthSeason;

@WebServlet("/recommend/season")
public class RecommendSeasonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private HealthSeasonService service = new HealthSeasonService();
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<HealthSeason> list = recommendSeason();
		
		JSONArray jsonArray = new JSONArray();
		
		for (HealthSeason healthSeason : list) {
			JSONObject object = new JSONObject();
			object.put("hsNo", healthSeason.getHS_NO());
			object.put("prdlstNm", healthSeason.getPRDLST_NM());
			object.put("img", healthSeason.getIMG_URL());
			
			jsonArray.add(object);
		}

		resp.setContentType("application/json;charset=UTF-8");
		resp.getWriter().append(jsonArray.toJSONString());
	}

	private List<HealthSeason> recommendSeason() {
		List<HealthSeason> list = service.getHealthSeasonMonthList(LocalDate.now().getMonth().getValue() + "월");
		List<HealthSeason> result = new ArrayList<HealthSeason>();
		
		int[] randomArr = recommendSeasonNum(list.size());
		
		for (int i : randomArr) {
			for (HealthSeason healthSeason : list) {
				if (healthSeason.getRowNum() == i) {
					result.add(healthSeason);
					
					break;
				}
			}
		}
		
		return result;
	}

	private int[] recommendSeasonNum(int size) {
		int[] result = new int[5];

		for (int i = 0; i < 5; i++) {
			result[i] = new Random().nextInt(size) + 1;

			for (int j = 0; j < i; j++) {
				if (result[i] == result[j]) {
					i--;
				}
			}
		}
		
		return result;
	}

}
