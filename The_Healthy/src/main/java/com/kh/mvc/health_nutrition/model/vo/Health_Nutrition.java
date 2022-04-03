package com.kh.mvc.health_nutrition.model.vo;

import java.util.List;

public class Health_Nutrition {
	
	private int HF_NO;                      // 일련번호
	private String PRDCT_NM;               // 품목명 
	private String IFTKN_ATNT_MATR_CN;	   // 섭취시주의사항
	private String PRIMARY_FNCLTY;         // 주된기능성
	private String DAY_INTK_LOWLIMIT;      // 일일섭취량 하한
	private String DAY_INTK_HIGHLIMIT;     // 일일섭취량 상한
	private String INTK_UNIT;              // 단위
	private String SKLL_IX_IRDNT_RAWMTRL;  // 성분명
	private int rowNum;
	public Health_Nutrition() {
		super();
	}
	
	public Health_Nutrition(String pRDCT_NM, String iFTKN_ATNT_MATR_CN, String pRIMARY_FNCLTY, String dAY_INTK_LOWLIMIT,
			String dAY_INTK_HIGHLIMIT, String iNTK_UNIT, String sKLL_IX_IRDNT_RAWMTRL) {
		super();
		PRDCT_NM = pRDCT_NM;
		IFTKN_ATNT_MATR_CN = iFTKN_ATNT_MATR_CN;
		PRIMARY_FNCLTY = pRIMARY_FNCLTY;
		DAY_INTK_LOWLIMIT = dAY_INTK_LOWLIMIT;
		DAY_INTK_HIGHLIMIT = dAY_INTK_HIGHLIMIT;
		INTK_UNIT = iNTK_UNIT;
		SKLL_IX_IRDNT_RAWMTRL = sKLL_IX_IRDNT_RAWMTRL;
	}

	public Health_Nutrition(int hF_NO, String pRDCT_NM, String iFTKN_ATNT_MATR_CN, String pRIMARY_FNCLTY,
			String dAY_INTK_LOWLIMIT, String dAY_INTK_HIGHLIMIT, String iNTK_UNIT, String sKLL_IX_IRDNT_RAWMTRL,
			int rowNum) {
		super();
		HF_NO = hF_NO;
		PRDCT_NM = pRDCT_NM;
		IFTKN_ATNT_MATR_CN = iFTKN_ATNT_MATR_CN;
		PRIMARY_FNCLTY = pRIMARY_FNCLTY;
		DAY_INTK_LOWLIMIT = dAY_INTK_LOWLIMIT;
		DAY_INTK_HIGHLIMIT = dAY_INTK_HIGHLIMIT;
		INTK_UNIT = iNTK_UNIT;
		SKLL_IX_IRDNT_RAWMTRL = sKLL_IX_IRDNT_RAWMTRL;
		this.rowNum = rowNum;
	}
	public int getHF_NO() {
		return HF_NO;
	}
	public void setHF_NO(int hF_NO) {
		HF_NO = hF_NO;
	}
	public String getPRDCT_NM() {
		return PRDCT_NM;
	}
	public void setPRDCT_NM(String pRDCT_NM) {
		PRDCT_NM = pRDCT_NM;
	}
	public String getIFTKN_ATNT_MATR_CN() {
		return IFTKN_ATNT_MATR_CN;
	}
	public void setIFTKN_ATNT_MATR_CN(String iFTKN_ATNT_MATR_CN) {
		IFTKN_ATNT_MATR_CN = iFTKN_ATNT_MATR_CN;
	}
	public String getPRIMARY_FNCLTY() {
		return PRIMARY_FNCLTY;
	}
	public void setPRIMARY_FNCLTY(String pRIMARY_FNCLTY) {
		PRIMARY_FNCLTY = pRIMARY_FNCLTY;
	}
	public String getDAY_INTK_LOWLIMIT() {
		return DAY_INTK_LOWLIMIT;
	}
	public void setDAY_INTK_LOWLIMIT(String dAY_INTK_LOWLIMIT) {
		DAY_INTK_LOWLIMIT = dAY_INTK_LOWLIMIT;
	}
	public String getDAY_INTK_HIGHLIMIT() {
		return DAY_INTK_HIGHLIMIT;
	}
	public void setDAY_INTK_HIGHLIMIT(String dAY_INTK_HIGHLIMIT) {
		DAY_INTK_HIGHLIMIT = dAY_INTK_HIGHLIMIT;
	}
	public String getINTK_UNIT() {
		return INTK_UNIT;
	}
	public void setINTK_UNIT(String iNTK_UNIT) {
		INTK_UNIT = iNTK_UNIT;
	}
	public String getSKLL_IX_IRDNT_RAWMTRL() {
		return SKLL_IX_IRDNT_RAWMTRL;
	}
	public void setSKLL_IX_IRDNT_RAWMTRL(String sKLL_IX_IRDNT_RAWMTRL) {
		SKLL_IX_IRDNT_RAWMTRL = sKLL_IX_IRDNT_RAWMTRL;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	@Override
	public String toString() {
		return "HealthFood [HF_NO=" + HF_NO + ", PRDCT_NM=" + PRDCT_NM + ", IFTKN_ATNT_MATR_CN=" + IFTKN_ATNT_MATR_CN
				+ ", PRIMARY_FNCLTY=" + PRIMARY_FNCLTY + ", DAY_INTK_LOWLIMIT=" + DAY_INTK_LOWLIMIT
				+ ", DAY_INTK_HIGHLIMIT=" + DAY_INTK_HIGHLIMIT + ", INTK_UNIT=" + INTK_UNIT + ", SKLL_IX_IRDNT_RAWMTRL="
				+ SKLL_IX_IRDNT_RAWMTRL + ", rowNum=" + rowNum + "]";
	}

	public List<Health_Nutrition> callHealthFoodListByJSON() {
		return null;
	}
	
	
}