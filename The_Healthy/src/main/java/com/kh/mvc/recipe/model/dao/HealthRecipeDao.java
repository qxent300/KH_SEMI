package com.kh.mvc.recipe.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.util.PageInfo;
import com.kh.mvc.recipe.model.vo.HealthRecipe;
import com.kh.mvc.recipe.model.vo.RecipeManual;


public class HealthRecipeDao {
	public int insertHealthRecipe(Connection conn, List<HealthRecipe> list) {
		int result = 0;
		PreparedStatement pstmt = null;

		for (HealthRecipe hr : list) {
			String query = "INSERT INTO HEALTHRECIPE VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, hr.getHR_NO());
				pstmt.setString(2, hr.getRCP_NM());
				pstmt.setString(3, hr.getRCP_PAT2());
				pstmt.setString(4, hr.getRCP_WAY2());
				pstmt.setString(5, hr.getINFO_WGT());
				pstmt.setDouble(6, hr.getINFO_ENG());
				pstmt.setString(7, hr.getINFO_CAR());
				pstmt.setString(8, hr.getINFO_PRO());
				pstmt.setString(9, hr.getINFO_FAT());
				pstmt.setString(10, hr.getINFO_NA());
				pstmt.setString(11, hr.getATT_FILE_NO_MK());
				pstmt.setString(12, hr.getRCP_PARTS_DTLS());

				result = pstmt.executeUpdate();
				if (result < 0) {
					System.out.println(hr.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		}
		return result;
	}

	public int insertRecipeManual(Connection conn, List<RecipeManual> list) {
		int result = 0;
		PreparedStatement pstmt = null;

		for (RecipeManual rm : list) {
			String query = "INSERT INTO RECIPEMANUAL VALUES(SEQ_RECIPEMANUAL.NEXTVAL,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, rm.getHR_NO());
				pstmt.setInt(2, rm.getSeq());
				pstmt.setString(3, rm.getManual());
				pstmt.setString(4, rm.getManualImg());

				result = pstmt.executeUpdate();
				if (result < 0) {
					System.out.println(rm.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		}
		return result;
	}

	public int getHealthRecipeCount(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM HEALTHRECIPE";
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

	public HealthRecipe findHealthRecipeByNo(Connection conn, int no) {
		HealthRecipe hr = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM HEALTHRECIPE WHERE HR_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				hr = new HealthRecipe();
				hr.setHR_NO(rs.getInt("HR_NO"));
				hr.setRCP_NM(rs.getString("RCP_NM"));
				hr.setRCP_PAT2(rs.getString("RCP_PAT2"));
				hr.setRCP_WAY2(rs.getString("RCP_WAY2"));
				hr.setINFO_WGT(rs.getString("INFO_WGT"));
				hr.setINFO_ENG(rs.getDouble("INFO_ENG"));
				hr.setINFO_CAR(rs.getString("INFO_CAR"));
				hr.setINFO_PRO(rs.getString("INFO_PRO"));
				hr.setINFO_FAT(rs.getString("INFO_FAT"));
				hr.setINFO_NA(rs.getString("INFO_NA"));
				hr.setATT_FILE_NO_MK(rs.getString("ATT_FILE_NO_MK"));
				hr.setRCP_PARTS_DTLS(rs.getString("RCP_PARTS_DTLS"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return hr;
	}
	
//	------------------------------------------------------------------------------ update by 동식
	public HealthRecipe findHealthRecipeByName(Connection conn, String RCP_NM) {
		HealthRecipe hr = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM HEALTHRECIPE WHERE RCP_NM=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, RCP_NM);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				hr = new HealthRecipe();
				hr.setRCP_NM(rs.getString("RCP_NM"));
				hr.setRCP_PAT2(rs.getString("RCP_PAT2"));
				hr.setRCP_WAY2(rs.getString("RCP_WAY2"));
				hr.setINFO_WGT(rs.getString("INFO_WGT"));
				hr.setINFO_ENG(rs.getDouble("INFO_ENG"));
				hr.setINFO_CAR(rs.getString("INFO_CAR"));
				hr.setINFO_PRO(rs.getString("INFO_PRO"));
				hr.setINFO_FAT(rs.getString("INFO_FAT"));
				hr.setINFO_NA(rs.getString("INFO_NA"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return hr;
	}
//	------------------------------------------------------------------------------

	public List<RecipeManual> findRecipeManualByNo(Connection conn, int no) {
		List<RecipeManual> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT r.SEQ, r.manual, r.manualImg FROM HEALTHRECIPE h JOIN RECIPEMANUAL r ON(h.HR_NO = r.HR_NO) WHERE r.HR_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RecipeManual rm = new RecipeManual();
				rm.setSeq(rs.getInt("SEQ"));
				rm.setManual(rs.getString("manual"));
				rm.setManualImg(rs.getString("manualImg"));
				list.add(rm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	public List<HealthRecipe> findAll(Connection conn, PageInfo pageInfo, String RCP_PAT2, String RCP_WAY2, String search) {
		List<HealthRecipe> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RNUM, HR_NO, RCP_NM, INFO_ENG, INFO_CAR, INFO_PRO, INFO_FAT, INFO_NA, ATT_FILE_NO_MK FROM (SELECT ROWNUM AS RNUM, HR_NO, RCP_NM, INFO_ENG, INFO_CAR, INFO_PRO, INFO_FAT, INFO_NA, ATT_FILE_NO_MK FROM (SELECT * FROM HEALTHRECIPE!@ ORDER BY RCP_NM ASC)) WHERE RNUM BETWEEN ? AND ?";
		try {
			if (RCP_WAY2.equals("전체") == true && RCP_PAT2.contains("전체") == true) {
				query = query.replace("!", "");
			} else if (RCP_WAY2.equals("전체") == true) {
				query = query.replace("!", " WHERE RCP_PAT2 LIKE ?");
			} else if (RCP_PAT2.equals("전체") == true) {
				query = query.replace("!", " WHERE RCP_WAY2 LIKE ?");
			} else {
				query = query.replace("!", " WHERE RCP_WAY2 LIKE ? AND RCP_PAT2 LIKE ?");
			}

			if (search != null) {
				if (RCP_WAY2.equals("전체") == true && RCP_PAT2.contains("전체") == true) {
					query = query.replace("@", " WHERE RCP_NM LIKE ?");
				} else {
					query = query.replace("@", " AND RCP_NM LIKE ?");
				}
			} else {
				query = query.replace("@", "");
			}

			pstmt = conn.prepareStatement(query);
			int num = 1;

			if (RCP_WAY2.equals("전체") == true && RCP_PAT2.contains("전체") != true) {
				pstmt.setString(num++, RCP_PAT2);
			} else if (RCP_WAY2.equals("전체") != true && RCP_PAT2.equals("전체") == true) {
				pstmt.setString(num++, RCP_WAY2);
			} else if (RCP_WAY2.equals("전체") != true && RCP_PAT2.equals("전체") != true) {
				pstmt.setString(num++, RCP_WAY2);
				pstmt.setString(num++, RCP_PAT2);
			}

			if (search != null) {
				pstmt.setString(num++, "%" + search + "%");
			}
			
			pstmt.setInt(num++, pageInfo.getStartList());
			pstmt.setInt(num++, pageInfo.getEndList());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HealthRecipe hr = new HealthRecipe();
				hr.setRowNum(rs.getInt("RNUM"));
				hr.setHR_NO(rs.getInt("HR_NO"));
				hr.setRCP_NM(rs.getString("RCP_NM"));
				hr.setINFO_ENG(rs.getDouble("INFO_ENG"));
				hr.setINFO_CAR(rs.getString("INFO_CAR"));
				hr.setINFO_PRO(rs.getString("INFO_PRO"));
				hr.setINFO_FAT(rs.getString("INFO_FAT"));
				hr.setINFO_NA(rs.getString("INFO_NA"));
				hr.setATT_FILE_NO_MK(rs.getString("ATT_FILE_NO_MK"));
				list.add(hr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	public int getHealthRecipeCount(Connection conn, String RCP_PAT2, String RCP_WAY2, String search) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM HEALTHRECIPE!@";
		try {
			if (RCP_WAY2.equals("전체") == true && RCP_PAT2.contains("전체") == true) {
				query = query.replace("!", "");
			} else if (RCP_WAY2.equals("전체") == true) {
				query = query.replace("!", " WHERE RCP_PAT2 LIKE ?");
			} else if (RCP_PAT2.equals("전체") == true) {
				query = query.replace("!", " WHERE RCP_WAY2 LIKE ?");
			} else {
				query = query.replace("!", " WHERE RCP_WAY2 LIKE ? AND RCP_PAT2 LIKE ?");
			}

			if (search != null) {
				if (RCP_WAY2.equals("전체") == true && RCP_PAT2.contains("전체") == true) {
					query = query.replace("@", " WHERE RCP_NM LIKE ?");
				} else {
					query = query.replace("@", " AND RCP_NM LIKE ?");
				}
			} else {
				query = query.replace("@", "");
			}

			pstmt = conn.prepareStatement(query);
			int num = 1;

			if (RCP_WAY2.equals("전체") == true && RCP_PAT2.contains("전체") == true) {

			} else if (RCP_WAY2.equals("전체") == true) {
				pstmt.setString(num++, RCP_PAT2);
			} else if (RCP_PAT2.equals("전체") == true) {
				pstmt.setString(num++, RCP_WAY2);
			} else {
				pstmt.setString(num++, RCP_WAY2);
				pstmt.setString(num++, RCP_PAT2);
			}

			if (search != null) {
				pstmt.setString(num++, "%" + search + "%");
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

	public List<HealthRecipe> findAll(Connection conn, PageInfo pageInfo, String RCP_PAT2, String RCP_WAY2, int min, int max) {
		List<HealthRecipe> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RNUM, HR_NO, RCP_NM, INFO_ENG, ATT_FILE_NO_MK FROM (SELECT ROWNUM AS RNUM, HR_NO, RCP_NM, INFO_ENG, ATT_FILE_NO_MK FROM (SELECT * FROM HEALTHRECIPE WHERE! INFO_ENG BETWEEN ? AND ? ORDER BY RCP_NM ASC)) WHERE RNUM BETWEEN ? AND ?";
		try {
			if (RCP_WAY2.equals("전체") == true && RCP_PAT2.contains("전체") == true) {
				query = query.replace("!", "");
			} else if (RCP_WAY2.equals("전체") == true) {
				query = query.replace("!", " RCP_PAT2 LIKE ? AND");
			} else if (RCP_PAT2.equals("전체") == true) {
				query = query.replace("!", " RCP_WAY2 LIKE ? AND");
			} else {
				query = query.replace("!", " RCP_WAY2 LIKE ? AND RCP_PAT2 LIKE ? AND");
			}
			
			pstmt = conn.prepareStatement(query);
			int num = 1;
			
			if (RCP_WAY2.equals("전체") == true && RCP_PAT2.contains("전체") == true) {

			} else if (RCP_WAY2.equals("전체") == true) {
				pstmt.setString(num++, RCP_PAT2);
			} else if (RCP_PAT2.equals("전체") == true) {
				pstmt.setString(num++, RCP_WAY2);
			} else {
				pstmt.setString(num++, RCP_WAY2);
				pstmt.setString(num++, RCP_PAT2);
			}

			pstmt.setInt(num++, min);
			pstmt.setInt(num++, max);
			pstmt.setInt(num++, pageInfo.getStartList());
			pstmt.setInt(num++, pageInfo.getEndList());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HealthRecipe hr = new HealthRecipe();
				hr.setRowNum(rs.getInt("RNUM"));
				hr.setHR_NO(rs.getInt("HR_NO"));
				hr.setRCP_NM(rs.getString("RCP_NM"));
				hr.setINFO_ENG(rs.getDouble("INFO_ENG"));
				hr.setATT_FILE_NO_MK(rs.getString("ATT_FILE_NO_MK"));
				list.add(hr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	public int getHealthRecipeCount(Connection conn, String RCP_PAT2, String RCP_WAY2, int min, int max) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM HEALTHRECIPE WHERE! INFO_ENG BETWEEN ? AND ?";
		try {
			if (RCP_WAY2.equals("전체") == true && RCP_PAT2.contains("전체") == true) {
				query = query.replace("!", "");
			} else if (RCP_WAY2.equals("전체") == true) {
				query = query.replace("!", " RCP_PAT2 LIKE ? AND");
			} else if (RCP_PAT2.equals("전체") == true) {
				query = query.replace("!", " RCP_WAY2 LIKE ? AND");
			} else {
				query = query.replace("!", " RCP_WAY2 LIKE ? AND RCP_PAT2 LIKE ? AND");
			}
			
			pstmt = conn.prepareStatement(query);
			int num = 1;
			
			if (RCP_WAY2.equals("전체") == true && RCP_PAT2.contains("전체") != true) {
				pstmt.setString(num++, RCP_PAT2);
			} else if (RCP_WAY2.equals("전체") != true && RCP_PAT2.equals("전체") == true) {
				pstmt.setString(num++, RCP_WAY2);
			} else if(RCP_WAY2.equals("전체") != true && RCP_PAT2.equals("전체") != true) {
				pstmt.setString(num++, RCP_WAY2);
				pstmt.setString(num++, RCP_PAT2);
			}

			pstmt.setInt(num++, min);
			pstmt.setInt(num++, max);

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
