package com.kh.mvc.community.model.service;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.common.util.PageInfo;
import com.kh.mvc.community.model.dao.UserRecipeDao;
import com.kh.mvc.community.model.vo.UserRecipe;
import com.kh.mvc.community.model.vo.UserRecipeManual;

public class UserRecipeService {
	
	private UserRecipeDao dao = new UserRecipeDao();

	public int insertUserRecipe(UserRecipe userRecipe) {
		Connection conn = getConnection();
		
		int result = dao.insertUserRecipe(conn, userRecipe);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int findUserRecipeNoByTitle(String userRecipeTitle) {
		Connection conn = getConnection();
		
		int result = dao.findUserRecipeNoByTitle(conn, userRecipeTitle);
		
		close(conn);
		
		return result;
	}
	
	public int insertUserRecipeManual(List<UserRecipeManual> manualList) {
		Connection conn = getConnection();
		
		int result = dao.insertUserRecipeManual(conn, manualList);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int getUserRecipeCount(String RCP_PAT2, String RCP_WAY2, String search) {
		Connection conn = getConnection();
		int count = dao.getUserRecipeCount(conn, RCP_PAT2, RCP_WAY2, search);
		close(conn);
		return count;
	}

	public List<UserRecipe> getUserRecipeList(PageInfo pageInfo, String RCP_PAT2, String RCP_WAY2, String search) {
		Connection conn = getConnection();
		List<UserRecipe> list = dao.findAll(conn, pageInfo, RCP_PAT2, RCP_WAY2, search);
		close(conn);
		return list;
	}

	public UserRecipe findUserRecipeByNo(int hrno) {
		Connection conn = getConnection();
		UserRecipe userRecipe = dao.findUserRecipeByNo(conn, hrno);
		userRecipe.setRecipeMaual(dao.findUserRecipeManualByNo(conn, hrno));
		close(conn);
		return userRecipe;
	}

	public int deleteUserRecipe(int userRecipeNo) {
		Connection conn = getConnection();
		int result = dao.deleteUserRecipeManual(conn, userRecipeNo);

		if (result > 0) {
			result = dao.deleteUserRecipe(conn, userRecipeNo);
			close(conn);
		}
		
		return result;
	}

	public List<Integer> findUserRecipeNoByWriterNo(int writerNo) {
		Connection conn = getConnection();
		List<Integer> result = dao.findUserRecipeNoByWriterNo(conn, writerNo);
		close(conn);
		return result;
	}
	
}
