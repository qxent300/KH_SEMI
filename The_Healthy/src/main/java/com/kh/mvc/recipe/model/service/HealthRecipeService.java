package com.kh.mvc.recipe.model.service;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.util.PageInfo;
import com.kh.mvc.recipe.api.HealthRecipeAPI;
import com.kh.mvc.recipe.model.dao.HealthRecipeDao;
import com.kh.mvc.recipe.model.vo.HealthRecipe;
import com.kh.mvc.recipe.model.vo.RecipeManual;

public class HealthRecipeService {
	private HealthRecipeDao dao = new HealthRecipeDao();

	public HealthRecipeService() {
		if (getHealthRecipeCount() < 1) {
			HealthRecipeAPI api = new HealthRecipeAPI();
			List<HealthRecipe> hrList = api.callHealthRecipeListByJSON();
			List<RecipeManual> rmList = new ArrayList<>();
			for (HealthRecipe hr : hrList) {
				rmList.addAll(hr.getRecipeMaual());
			}
			insert(hrList, rmList);
		}
	}

	private int insert(List<HealthRecipe> hrList, List<RecipeManual> rmList) {
		Connection conn = getConnection();
		int result = 0;

		result = dao.insertHealthRecipe(conn, hrList);
		result = dao.insertRecipeManual(conn, rmList);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	}

	private int getHealthRecipeCount() {
		Connection conn = getConnection();
		int result = dao.getHealthRecipeCount(conn);
		close(conn);
		return result;
	}

	public HealthRecipe findHealthRecipeByNo(int no) {
		Connection conn = getConnection();
		HealthRecipe hr = dao.findHealthRecipeByNo(conn, no);
		hr.setRecipeMaual(dao.findRecipeManualByNo(conn, no));
		close(conn);
		return hr;
	}

//	--------------------------------------------------------------- update by 동식
	public HealthRecipe findHealthRecipeByName(String RCP_NM) {			
		Connection conn = getConnection();
		HealthRecipe hr = dao.findHealthRecipeByName(conn, RCP_NM);
		close(conn);
		return hr;
	}
//	---------------------------------------------------------------

	public List<HealthRecipe> getHealthRecipeList(PageInfo pageinfo, String RCP_PAT2, String RCP_WAY2, String search) {
		Connection conn = getConnection();
		List<HealthRecipe> list;
		list = dao.findAll(conn, pageinfo, RCP_PAT2, RCP_WAY2, search);
		close(conn);
		return list;
	}
	
	public int getHealthRecipeCount(String RCP_PAT2, String RCP_WAY2, String search) {
		Connection conn = getConnection();
		int count = dao.getHealthRecipeCount(conn, RCP_PAT2, RCP_WAY2, search);
		close(conn);
		return count;
	}

	public List<HealthRecipe> getHealthRecipeList(PageInfo pageinfo, String RCP_PAT2, String RCP_WAY2, int min, int max) {
		Connection conn = getConnection();
		List<HealthRecipe> list;
		list = dao.findAll(conn, pageinfo, RCP_PAT2, RCP_WAY2, min, max);
		close(conn);
		return list;
	}

	public int getHealthRecipeCount(String RCP_PAT2, String RCP_WAY2, int min, int max) {
		Connection conn = getConnection();
		int count = dao.getHealthRecipeCount(conn, RCP_PAT2, RCP_WAY2, min, max);
		close(conn);
		return count;
	}
}
