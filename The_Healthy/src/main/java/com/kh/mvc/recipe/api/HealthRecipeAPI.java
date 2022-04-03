package com.kh.mvc.recipe.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.kh.mvc.recipe.model.vo.HealthRecipe;
import com.kh.mvc.recipe.model.vo.RecipeManual;

public class HealthRecipeAPI {

	public static String URL = "https://openapi.foodsafetykorea.go.kr/api/83525b6e1a18427da93b/COOKRCP01/json"; 

	public List<HealthRecipe> callHealthRecipeListByJSON() {
		List<HealthRecipe> hrList = new ArrayList<>();
		List<String> RCP_NMFilterList = makeRCP_NMFilterList();
		int no = 1;
		
		for (int i = 0; i <= 1000; i += 500) {
			StringBuffer urlBuffer = new StringBuffer();
			urlBuffer.append(URL);
			urlBuffer.append("/" + (i + 1));
			urlBuffer.append("/" + (i + 500));

			try {
				URL url = new URL(urlBuffer.toString());
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
				JSONObject result = (JSONObject) jsonObj.get("COOKRCP01");
				JSONArray rowArray = (JSONArray) result.get("row");

				boolean bool = true;
				for (int j = 0; j < rowArray.size(); j++) {
					JSONObject recipeObject = (JSONObject) rowArray.get(j);

					String RCP_NM = recipeObject.get("RCP_NM").toString(); // 메뉴명
					if(RCP_NMFilterList.contains(RCP_NM) == true){
						continue;
					}
					String RCP_PAT2 = recipeObject.get("RCP_PAT2").toString(); // 요리종류
					String RCP_WAY2 = recipeObject.get("RCP_WAY2").toString(); // 조리방법
					String INFO_WGT = recipeObject.get("INFO_WGT").toString(); // 중량(1인분)
					Double INFO_ENG = Double.parseDouble(recipeObject.get("INFO_ENG").toString()); // 열량
					String INFO_CAR = recipeObject.get("INFO_CAR").toString(); // 탄수화물
					String INFO_PRO = recipeObject.get("INFO_PRO").toString(); // 단백질
					String INFO_FAT = recipeObject.get("INFO_FAT").toString(); // 지방
					String INFO_NA = recipeObject.get("INFO_NA").toString(); // 나트륨
					String ATT_FILE_NO_MK = recipeObject.get("ATT_FILE_NO_MK").toString(); // 이미지경로(대)
					if (ATT_FILE_NO_MK.equals("http://www.foodsafetykorea.go.kr/") == true || ATT_FILE_NO_MK == null || ATT_FILE_NO_MK == "") {
						continue;
					}
					String RCP_PARTS_DTLS = recipeObject.get("RCP_PARTS_DTLS").toString(); // 재료정보
					List<RecipeManual> rmList = new ArrayList<>();
					for (int k = 1; k <= 20; k++) {
						String MANUAL;
						String MANUAL_IMG;
						if (k < 10) {
							MANUAL = recipeObject.get("MANUAL0" + k).toString();
							MANUAL_IMG = recipeObject.get("MANUAL_IMG0" + k).toString();
						} else {
							MANUAL = recipeObject.get("MANUAL" + k).toString();
							MANUAL_IMG = recipeObject.get("MANUAL_IMG" + k).toString();
						}
						
						if ((MANUAL == null && MANUAL_IMG == null) || (MANUAL == "" && MANUAL_IMG == "") || (MANUAL.length() <= 1)) {
							break;
						}

						if (MANUAL.contains("?") == true) {
							bool = false;
							break;
						}
						
						if (MANUAL == null || MANUAL == "") {
							bool = false;
							break;
						}
							
						if (MANUAL_IMG == null || MANUAL_IMG == "") {
							bool = false;
							break;
						}

						MANUAL = MANUALFilter(MANUAL, j, k);
						rmList.add(new RecipeManual(no, k, MANUAL, MANUAL_IMG));
					}

					if (bool == true) {
						hrList.add(new HealthRecipe(no++, RCP_NM, RCP_PAT2, RCP_WAY2, INFO_WGT, INFO_ENG, INFO_CAR, INFO_PRO, INFO_FAT, INFO_NA, ATT_FILE_NO_MK, RCP_PARTS_DTLS, rmList));
					}
					bool = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hrList;
	}
	
	private String MANUALFilter(String input, int j, int k) {
		String MANUAL = input.replace("\n", "").replace("  ", " ").replace("a", "")
				.replace("(a)", "").replace("b", "").replace("(b)", "").replace("(c)", "")
				.replace("-C", "").replace("d", "").replace("(d)", "").replace("-A", "")
				.replace("-B", "").replace("-C,D", "").replace("-C, -D", "").replace("-C", "")
				.replace("-D", "").replace("D|", "").replace("()", "");
		
		if (MANUAL.lastIndexOf("c") == MANUAL.length() - 1) {
			MANUAL = MANUAL.substring(0, MANUAL.length() - 2);
		}
		if ((j == 49 && k == 5) || (j == 58 && k == 3) || (j == 422 && k == 3) || (j == 465 && k == 3)) {
			MANUAL = MANUAL.replace("c", "");
		}

		if (MANUAL.indexOf(".") == 1) {
			MANUAL = MANUAL.substring(3);
		} else if (MANUAL.indexOf(".") == 2) {
			MANUAL = MANUAL.substring(4);
		}
		
		return MANUAL;
	}
	
	private List<String> makeRCP_NMFilterList() {
		List<String> list = new ArrayList<>();
		list.add("L..A갈비구이");
		list.add("가지볶음");
		list.add("가지 탕수육");
		list.add("치즈 단호박");
		list.add("토마토채소스프와 연어소바찜");
		list.add("포니언 스프");
		list.add("콩국물소스를 곁들인 게살 냉채");
		list.add("참나물페스토 파스타");
		list.add("참나물무말이 물김치");
		list.add("자색고구마호떡");
		list.add("연두부 토마토");
		list.add("양배추말이 김치");
		list.add("소고기육전과 전복내장소스");
		list.add("버섯 리조또");
		list.add("참깨 단호박 찜");
		list.add("효종갱(曉鍾羹)");
		list.add("다시마칩");
		list.add("검은깨 두부 안심 스테이크");
		list.add("치즈 두부 튀김");
		return list;
	}

}