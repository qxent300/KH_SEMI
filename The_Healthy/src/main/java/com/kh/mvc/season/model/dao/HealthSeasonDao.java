package com.kh.mvc.season.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.util.PageInfo;
import com.kh.mvc.season.model.vo.HealthSeason;

public class HealthSeasonDao {
	public int insertSeasonFood(Connection conn, List<HealthSeason> list) {
		int result = 0;
		PreparedStatement pstmt = null;

		for (HealthSeason hs : list) {
			String query = "INSERT INTO HEALTHSEASON VALUES(SEQ_HEALTHSEASON.NEXTVAL,?,?,?,?,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, hs.getPRDLST_NM());
				pstmt.setString(2, hs.getM_DISTCTNS());
				pstmt.setString(3, hs.getMTC_NM());
				pstmt.setString(4, hs.getEFFECT());
				pstmt.setString(5, hs.getPURCHASE_MTH());
				pstmt.setString(6, hs.getCOOK_MTH());
				pstmt.setString(7, hs.getTRT_MTH());
				pstmt.setString(8, hs.getIMG_URL());

				result = pstmt.executeUpdate();
				if (result < 0) {
					System.out.println(hs.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		}
		return result;
	}
	
	public int getHealthSeasonCount(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM HEALTHSEASON";
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

	public HealthSeason findHealthSeasonByNo(Connection conn, int no) {
		HealthSeason hs = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM HEALTHSEASON WHERE HS_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				hs = new HealthSeason();
				hs.setHS_NO(rs.getInt("HS_NO"));
				hs.setPRDLST_NM(rs.getString("PRDLST_NM"));
				hs.setM_DISTCTNS(rs.getString("M_DISTCTNS"));
				hs.setMTC_NM(rs.getString("MTC_NM"));
				hs.setEFFECT(rs.getString("EFFECT"));
				hs.setPURCHASE_MTH(rs.getString("PURCHASE_MTH"));
				hs.setCOOK_MTH(rs.getString("COOK_MTH"));
				hs.setTRT_MTH(rs.getString("TRT_MTH"));
				hs.setIMG_URL(rs.getString("IMG_URL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return hs;
	}

	public List<HealthSeason> findAll(Connection conn, PageInfo pageInfo, String month) {
		List<HealthSeason> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RNUM, HS_NO, PRDLST_NM, IMG_URL FROM (SELECT ROWNUM AS RNUM, HS_NO, PRDLST_NM, IMG_URL FROM (SELECT * FROM HEALTHSEASON! ORDER BY PRDLST_NM ASC)) WHERE RNUM BETWEEN ? AND ?";
		try {
			if(month.equals("전체") == true) {
				query = query.replace("!", "");
			} else {
				query = query.replace("!", " WHERE M_DISTCTNS LIKE ?");
			}
			
			pstmt = conn.prepareStatement(query);
			int num = 1;
			
			if(month.equals("전체") != true) {
				pstmt.setString(num++, month);
			}
			
			pstmt.setInt(num++, pageInfo.getStartList());
			pstmt.setInt(num++, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HealthSeason sf = new HealthSeason();
				sf.setRowNum(rs.getInt("RNUM"));
				sf.setHS_NO(rs.getInt("HS_NO"));
				sf.setPRDLST_NM(rs.getString("PRDLST_NM"));
				sf.setIMG_URL(rs.getString("IMG_URL"));
				list.add(sf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	public int getHealthSeasonCount(Connection conn, String month) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM HEALTHSEASON!";
		try {
			if(month.equals("전체") == true) {
				query = query.replace("!", "");
			} else {
				query = query.replace("!", " WHERE M_DISTCTNS LIKE ?");
			}
			
			pstmt = conn.prepareStatement(query);
			if(month.equals("전체") != true) {
				pstmt.setString(1, month);
			}
			
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

	public List<HealthSeason> findAll(Connection conn, String month) {
		List<HealthSeason> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT ROWNUM AS RNUM, HS_NO, PRDLST_NM, IMG_URL FROM HEALTHSEASON WHERE M_DISTCTNS = ?";
		
		try {		
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, month);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HealthSeason sf = new HealthSeason();
				sf.setRowNum(rs.getInt("RNUM"));
				sf.setHS_NO(rs.getInt("HS_NO"));
				sf.setPRDLST_NM(rs.getString("PRDLST_NM"));
				sf.setIMG_URL(rs.getString("IMG_URL"));
				list.add(sf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		
		return list;
	}
}
