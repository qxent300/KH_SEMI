package com.kh.mvc.community.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.util.PageInfo;
import com.kh.mvc.community.model.vo.UserRecipe;
import com.kh.mvc.community.model.vo.UserRecipeManual;

public class UserRecipeDao {
	
	public int insertUserRecipe(Connection conn, UserRecipe userRecipe) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO USERRECIPE VALUES(SEQ_USERRECIPE.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userRecipe.getUSER_NO());
			pstmt.setString(2, userRecipe.getRCP_NM());
			pstmt.setString(3, userRecipe.getRCP_PAT2());
			pstmt.setString(4, userRecipe.getRCP_WAY2());
			pstmt.setString(5, userRecipe.getATT_FILE_NO_MK());
			pstmt.setString(6, userRecipe.getRCP_PARTS_DTLS());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	
		return result;
	}
	
	public int findUserRecipeNoByTitle(Connection conn, String userRecipeTitle) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT HR_NO FROM USERRECIPE WHERE RCP_NM = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userRecipeTitle);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public int insertUserRecipeManual(Connection conn, List<UserRecipeManual> manualList) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO USERRECIPEMANUAL VALUES(SEQ_USERRECIPEMANUAL.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			for (UserRecipeManual userRecipeManual : manualList) {
				pstmt.setInt(1, userRecipeManual.getHR_NO());
				pstmt.setInt(2, userRecipeManual.getSeq());
				pstmt.setString(3, userRecipeManual.getManual());
				pstmt.setString(4, userRecipeManual.getManualImg());
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			conn.commit();
			pstmt.clearBatch();
			
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			result = -1;
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getUserRecipeCount(Connection conn, String RCP_PAT2, String RCP_WAY2, String search) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM USERRECIPE!@";
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

	public List<UserRecipe> findAll(Connection conn, PageInfo pageInfo, String RCP_PAT2, String RCP_WAY2, String search) {
		List<UserRecipe> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RNUM, HR_NO, USER_NO, RCP_NM, ATT_FILE_NO_MK FROM (SELECT ROWNUM AS RNUM, HR_NO, USER_NO, RCP_NM, ATT_FILE_NO_MK FROM (SELECT * FROM USERRECIPE!@ ORDER BY RCP_NM ASC)) WHERE RNUM BETWEEN ? AND ?";
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
				UserRecipe userRecipe = new UserRecipe();
				userRecipe.setRowNum(rs.getInt("RNUM"));
				userRecipe.setHR_NO(rs.getInt("HR_NO"));
				userRecipe.setUSER_NO(rs.getInt("USER_NO"));
				userRecipe.setRCP_NM(rs.getString("RCP_NM"));
				userRecipe.setATT_FILE_NO_MK(rs.getString("ATT_FILE_NO_MK"));

				list.add(userRecipe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	public UserRecipe findUserRecipeByNo(Connection conn, int hrno) {
		UserRecipe userRecipe = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM USERRECIPE WHERE HR_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, hrno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				userRecipe = new UserRecipe();
				userRecipe.setHR_NO(rs.getInt("HR_NO"));
				userRecipe.setUSER_NO(rs.getInt("USER_NO"));
				userRecipe.setRCP_NM(rs.getString("RCP_NM"));
				userRecipe.setRCP_PAT2(rs.getString("RCP_PAT2"));
				userRecipe.setRCP_WAY2(rs.getString("RCP_WAY2"));
				userRecipe.setATT_FILE_NO_MK(rs.getString("ATT_FILE_NO_MK"));
				userRecipe.setRCP_PARTS_DTLS(rs.getString("RCP_PARTS_DTLS"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return userRecipe;
	}

	public List<UserRecipeManual> findUserRecipeManualByNo(Connection conn, int hrno) {
		List<UserRecipeManual> list = new ArrayList<UserRecipeManual>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT urm.SEQ, urm.manual, urm.manualImg FROM USERRECIPE ur JOIN USERRECIPEMANUAL urm ON(ur.HR_NO = urm.HR_NO) WHERE ur.HR_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, hrno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserRecipeManual urm = new UserRecipeManual();
				urm.setSeq(rs.getInt("SEQ"));
				urm.setManual(rs.getString("manual"));
				urm.setManualImg(rs.getString("manualImg"));
				list.add(urm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}

	public int deleteUserRecipe(Connection conn, int hrno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM USERRECIPE WHERE HR_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, hrno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteUserRecipeManual(Connection conn, int userRecipeNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM USERRECIPEMANUAL WHERE HR_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userRecipeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Integer> findUserRecipeNoByWriterNo(Connection conn, int writerNo) {
		List<Integer> result = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT HR_NO FROM USERRECIPE WHERE USER_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, writerNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				result.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
}
