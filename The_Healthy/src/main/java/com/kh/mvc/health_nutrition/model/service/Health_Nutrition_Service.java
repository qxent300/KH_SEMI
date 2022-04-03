package com.kh.mvc.health_nutrition.model.service;

import static com.kh.common.jdbc.JDBCTemplate.close;
import static com.kh.common.jdbc.JDBCTemplate.commit;
import static com.kh.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.common.util.PageInfo;
import com.kh.mvc.health_nutrition.api.Health_NutritionAPI;
import com.kh.mvc.health_nutrition.model.dao.Health_Nutrition_Dao;
import com.kh.mvc.health_nutrition.model.vo.Health_Nutrition;

public class Health_Nutrition_Service {
	private Health_Nutrition_Dao dao = new Health_Nutrition_Dao();
	
	public Health_Nutrition_Service() {
		if(getHealthFoodCount() < 1) {
			Health_NutritionAPI api = new Health_NutritionAPI();
			List<Health_Nutrition> hfList = api.callHealthFoodListByJSON();
			insert(hfList);
		}
	}
	
	public int insert(List<Health_Nutrition> list) {
		Connection conn = getConnection();
		int result = 0;
		
		result = dao.insertHealthFood(conn, list);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	}
	
	public int getHealthFoodCount() {
		Connection conn = getConnection();
		int result = dao.getHealthFoodCount(conn);
		close(conn);
		return result;
	}
	
	
	public List<Health_Nutrition> getHealthFoodList(PageInfo pageinfo) {
		Connection conn = getConnection();
		List<Health_Nutrition> list = dao.findAll(conn, pageinfo);
		close(conn);
		return list;
	}
}
