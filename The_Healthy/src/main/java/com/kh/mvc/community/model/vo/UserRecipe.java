package com.kh.mvc.community.model.vo;

import java.util.List;

public class UserRecipe {
	private int HR_NO;                      
	private int USER_NO;
	private String RCP_NM;                  
	private String RCP_PAT2;               
	private String RCP_WAY2;                
	private String ATT_FILE_NO_MK;          
	private String RCP_PARTS_DTLS;         
	private List<UserRecipeManual> recipeMaual; 
	private int rowNum;
	
	public UserRecipe() {}

	public UserRecipe(int hR_NO, int uSER_NO, String rCP_NM, String rCP_PAT2, String rCP_WAY2, String aTT_FILE_NO_MK,
			String rCP_PARTS_DTLS, List<UserRecipeManual> recipeMaual, int rowNum) {
		super();
		HR_NO = hR_NO;
		USER_NO = uSER_NO;
		RCP_NM = rCP_NM;
		RCP_PAT2 = rCP_PAT2;
		RCP_WAY2 = rCP_WAY2;
		ATT_FILE_NO_MK = aTT_FILE_NO_MK;
		RCP_PARTS_DTLS = rCP_PARTS_DTLS;
		this.recipeMaual = recipeMaual;
		this.rowNum = rowNum;
	}

	public int getHR_NO() {
		return HR_NO;
	}

	public void setHR_NO(int hR_NO) {
		HR_NO = hR_NO;
	}

	public int getUSER_NO() {
		return USER_NO;
	}

	public void setUSER_NO(int uSER_NO) {
		USER_NO = uSER_NO;
	}

	public String getRCP_NM() {
		return RCP_NM;
	}

	public void setRCP_NM(String rCP_NM) {
		RCP_NM = rCP_NM;
	}

	public String getRCP_PAT2() {
		return RCP_PAT2;
	}

	public void setRCP_PAT2(String rCP_PAT2) {
		RCP_PAT2 = rCP_PAT2;
	}

	public String getRCP_WAY2() {
		return RCP_WAY2;
	}

	public void setRCP_WAY2(String rCP_WAY2) {
		RCP_WAY2 = rCP_WAY2;
	}

	public String getATT_FILE_NO_MK() {
		return ATT_FILE_NO_MK;
	}

	public void setATT_FILE_NO_MK(String aTT_FILE_NO_MK) {
		ATT_FILE_NO_MK = aTT_FILE_NO_MK;
	}

	public String getRCP_PARTS_DTLS() {
		return RCP_PARTS_DTLS;
	}

	public void setRCP_PARTS_DTLS(String rCP_PARTS_DTLS) {
		RCP_PARTS_DTLS = rCP_PARTS_DTLS;
	}

	public List<UserRecipeManual> getRecipeMaual() {
		return recipeMaual;
	}

	public void setRecipeMaual(List<UserRecipeManual> recipeMaual) {
		this.recipeMaual = recipeMaual;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	@Override
	public String toString() {
		return "UserRecipe [HR_NO=" + HR_NO + ", USER_NO=" + USER_NO + ", RCP_NM=" + RCP_NM + ", RCP_PAT2=" + RCP_PAT2
				+ ", RCP_WAY2=" + RCP_WAY2 + ", ATT_FILE_NO_MK=" + ATT_FILE_NO_MK + ", RCP_PARTS_DTLS=" + RCP_PARTS_DTLS
				+ ", recipeMaual=" + recipeMaual + ", rowNum=" + rowNum + "]";
	}

}
