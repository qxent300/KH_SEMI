package com.kh.mvc.recipe.model.vo;

import java.util.List;

public class HealthRecipe {
	private int HR_NO;                      // 일련번호
	private String RCP_NM;                  // 메뉴명
	private String RCP_PAT2;                // 요리종류
	private String RCP_WAY2;                // 조리방법
	private String INFO_WGT;                // 중량(1인분)
	private double INFO_ENG;                // 열량
	private String INFO_CAR;                // 탄수화물
	private String INFO_PRO;                // 단백질
	private String INFO_FAT;                // 지방
	private String INFO_NA;                 // 나트륨
	private String ATT_FILE_NO_MK;          // 이미지경로(대)
	private String RCP_PARTS_DTLS;          // 재료정보
	private List<RecipeManual> recipeMaual; // 레시피 만드는 법
	private int rowNum;
	
	public HealthRecipe() {
		super();
	}

	public HealthRecipe(int hR_NO, String rCP_NM, String rCP_PAT2, String rCP_WAY2, String iNFO_WGT, double iNFO_ENG,
			String iNFO_CAR, String iNFO_PRO, String iNFO_FAT, String iNFO_NA, String aTT_FILE_NO_MK,
			String rCP_PARTS_DTLS, List<RecipeManual> recipeMaual) {
		super();
		HR_NO = hR_NO;
		RCP_NM = rCP_NM;
		RCP_PAT2 = rCP_PAT2;
		RCP_WAY2 = rCP_WAY2;
		INFO_WGT = iNFO_WGT;
		INFO_ENG = iNFO_ENG;
		INFO_CAR = iNFO_CAR;
		INFO_PRO = iNFO_PRO;
		INFO_FAT = iNFO_FAT;
		INFO_NA = iNFO_NA;
		ATT_FILE_NO_MK = aTT_FILE_NO_MK;
		RCP_PARTS_DTLS = rCP_PARTS_DTLS;
		this.recipeMaual = recipeMaual;
	}

	public HealthRecipe(int hR_NO, String rCP_NM, String rCP_PAT2, String rCP_WAY2, String iNFO_WGT, double iNFO_ENG,
			String iNFO_CAR, String iNFO_PRO, String iNFO_FAT, String iNFO_NA, String aTT_FILE_NO_MK,
			String rCP_PARTS_DTLS, List<RecipeManual> recipeMaual, int rowNum) {
		super();
		HR_NO = hR_NO;
		RCP_NM = rCP_NM;
		RCP_PAT2 = rCP_PAT2;
		RCP_WAY2 = rCP_WAY2;
		INFO_WGT = iNFO_WGT;
		INFO_ENG = iNFO_ENG;
		INFO_CAR = iNFO_CAR;
		INFO_PRO = iNFO_PRO;
		INFO_FAT = iNFO_FAT;
		INFO_NA = iNFO_NA;
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

	public String getINFO_WGT() {
		return INFO_WGT;
	}

	public void setINFO_WGT(String iNFO_WGT) {
		INFO_WGT = iNFO_WGT;
	}

	public double getINFO_ENG() {
		return INFO_ENG;
	}

	public void setINFO_ENG(double iNFO_ENG) {
		INFO_ENG = iNFO_ENG;
	}

	public String getINFO_CAR() {
		return INFO_CAR;
	}

	public void setINFO_CAR(String iNFO_CAR) {
		INFO_CAR = iNFO_CAR;
	}

	public String getINFO_PRO() {
		return INFO_PRO;
	}

	public void setINFO_PRO(String iNFO_PRO) {
		INFO_PRO = iNFO_PRO;
	}

	public String getINFO_FAT() {
		return INFO_FAT;
	}

	public void setINFO_FAT(String iNFO_FAT) {
		INFO_FAT = iNFO_FAT;
	}

	public String getINFO_NA() {
		return INFO_NA;
	}

	public void setINFO_NA(String iNFO_NA) {
		INFO_NA = iNFO_NA;
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

	public List<RecipeManual> getRecipeMaual() {
		return recipeMaual;
	}

	public void setRecipeMaual(List<RecipeManual> recipeMaual) {
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
		return "HealthyRecipe [HR_NO=" + HR_NO + ", RCP_NM=" + RCP_NM + ", RCP_PAT2=" + RCP_PAT2 + ", RCP_WAY2="
				+ RCP_WAY2 + ", INFO_WGT=" + INFO_WGT + ", INFO_ENG=" + INFO_ENG + ", INFO_CAR=" + INFO_CAR
				+ ", INFO_PRO=" + INFO_PRO + ", INFO_FAT=" + INFO_FAT + ", INFO_NA=" + INFO_NA + ", ATT_FILE_NO_MK="
				+ ATT_FILE_NO_MK + ", RCP_PARTS_DTLS=" + RCP_PARTS_DTLS + ", recipeMaual=" + recipeMaual + ", rowNum="
				+ rowNum + "]";
	}

}
