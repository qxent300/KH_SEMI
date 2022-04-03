package com.kh.mvc.health_nutrition.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.kh.mvc.health_nutrition.model.vo.Health_Nutrition;

public class Health_NutritionAPI {

	public static String key = "https://openapi.foodsafetykorea.go.kr/api/77f76c1944284bf28a9c/I2710/json/1/1000"; // 361


	public List<Health_Nutrition> callHealthFoodListByJSON() {
		List<Health_Nutrition> list = new ArrayList<>();

		try {

			URL url = new URL(key.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			int code = conn.getResponseCode();

			if (code < 200 || code > 300) {
				System.out.println("페이지가 잘못되었습니다.");
				return null;
			}
			InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(br);
			JSONObject result = (JSONObject) jsonObj.get("I2710");
			JSONArray officeArray = (JSONArray) result.get("row");

			for (int j = 0; j < officeArray.size(); j++) {
				JSONObject personObject = (JSONObject) officeArray.get(j);

				String PRDCT_NM = personObject.get("PRDCT_NM").toString(); // 품목명
				if(PRDCT_NM == "" || PRDCT_NM.equals("루바브뿌리추출물(제2021-17호)") == true)
					continue;
				String IFTKN_ATNT_MATR_CN = personObject.get("IFTKN_ATNT_MATR_CN").toString(); // 섭취시주의사항
				if (IFTKN_ATNT_MATR_CN.equals("(1)") == true)
					IFTKN_ATNT_MATR_CN = "";
				String PRIMARY_FNCLTY = personObject.get("PRIMARY_FNCLTY").toString(); // 주된기능성
				String DAY_INTK_LOWLIMIT = personObject.get("DAY_INTK_LOWLIMIT").toString(); // 일일섭취량 하한
				String DAY_INTK_HIGHLIMIT = personObject.get("DAY_INTK_HIGHLIMIT").toString(); // 일일섭취량 상한
				String INTK_UNIT = personObject.get("INTK_UNIT").toString(); // 단위
				String SKLL_IX_IRDNT_RAWMTRL = personObject.get("SKLL_IX_IRDNT_RAWMTRL").toString(); // 성분명

				list.add(new Health_Nutrition(PRDCT_NM, IFTKN_ATNT_MATR_CN, PRIMARY_FNCLTY, DAY_INTK_LOWLIMIT,
						DAY_INTK_HIGHLIMIT, INTK_UNIT, SKLL_IX_IRDNT_RAWMTRL));
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	
	}
}
