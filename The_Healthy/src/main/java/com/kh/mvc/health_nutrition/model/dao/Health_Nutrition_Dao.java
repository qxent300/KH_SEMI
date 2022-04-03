package com.kh.mvc.health_nutrition.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.util.PageInfo;
import com.kh.mvc.health_nutrition.model.vo.Health_Nutrition;

public class Health_Nutrition_Dao {
	public int insertHealthFood(Connection conn,  List<Health_Nutrition> list) {
		int result = 0; 
		PreparedStatement pstmt = null;
		for(Health_Nutrition HF : list) {
		String query = "INSERT INTO HEALTHFOOD VALUES (SEQ_HEALTHFOOD.NEXTVAL,?,?,?,?,?,?,?)";
		
		try {
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, HF.getPRDCT_NM());
				pstmt.setString(2, HF.getIFTKN_ATNT_MATR_CN());
				pstmt.setString(3, HF.getPRIMARY_FNCLTY());
				pstmt.setString(4, HF.getDAY_INTK_LOWLIMIT());
				pstmt.setString(5, HF
						.getDAY_INTK_HIGHLIMIT());
				pstmt.setString(6, HF.getINTK_UNIT());
				pstmt.setString(7, HF.getSKLL_IX_IRDNT_RAWMTRL());
				
				result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		}
		return result;
	}
	
	public List<Health_Nutrition> findAll(Connection conn, PageInfo pageInfo) {
		List<Health_Nutrition> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RNUM, HF_NO,PRDCT_NM, IFTKN_ATNT_MATR_CN,PRIMARY_FNCLTY,DAY_INTK_LOWLIMIT,DAY_INTK_HIGHLIMIT, INTK_UNIT,SKLL_IX_IRDNT_RAWMTRL\r\n"
				+ "FROM (SELECT ROWNUM AS RNUM,HF_NO,PRDCT_NM, IFTKN_ATNT_MATR_CN,PRIMARY_FNCLTY,DAY_INTK_LOWLIMIT,DAY_INTK_HIGHLIMIT,\r\n"
				+ "INTK_UNIT,SKLL_IX_IRDNT_RAWMTRL FROM (SELECT * FROM HEALTHFOOD ORDER BY PRDCT_NM ASC)) WHERE RNUM BETWEEN ? AND ?";

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Health_Nutrition hf = new Health_Nutrition();
				hf.setHF_NO(rs.getInt("HF_NO"));
				hf.setPRDCT_NM(rs.getString("PRDCT_NM"));
				hf.setIFTKN_ATNT_MATR_CN(rs.getString("IFTKN_ATNT_MATR_CN"));
				hf.setPRIMARY_FNCLTY(rs.getString("PRIMARY_FNCLTY"));
				hf.setDAY_INTK_LOWLIMIT(rs.getString("DAY_INTK_LOWLIMIT"));
				hf.setDAY_INTK_HIGHLIMIT(rs.getString("DAY_INTK_HIGHLIMIT"));
				hf.setINTK_UNIT(rs.getString("INTK_UNIT"));
				hf.setSKLL_IX_IRDNT_RAWMTRL(rs.getString("SKLL_IX_IRDNT_RAWMTRL"));
				list.add(hf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	public int getHealthFoodCount(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM HEALTHFOOD";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}
	

}
	
	
	

