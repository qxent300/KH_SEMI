package com.kh.mvc.season.model.vo;

public class HealthSeason {
	private int HS_NO;           // 일련번호
	private String PRDLST_NM;    // 품목명
	private String M_DISTCTNS;   // 월별
	private String MTC_NM;       // 주요산지
	private String EFFECT;       // 효능
	private String PURCHASE_MTH; // 구입요령
	private String COOK_MTH;     // 조리법
	private String TRT_MTH;      // 손질요령
	private String IMG_URL;      // 이미지
	private int rowNum;
	
	public HealthSeason() {
		super();
	}

	public HealthSeason(String pRDLST_NM, String m_DISTCTNS, String mTC_NM, String eFFECT, String pURCHASE_MTH,
			String cOOK_MTH, String tRT_MTH, String iMG_URL) {
		super();
		PRDLST_NM = pRDLST_NM;
		M_DISTCTNS = m_DISTCTNS;
		MTC_NM = mTC_NM;
		EFFECT = eFFECT;
		PURCHASE_MTH = pURCHASE_MTH;
		COOK_MTH = cOOK_MTH;
		TRT_MTH = tRT_MTH;
		IMG_URL = iMG_URL;
	}

	public HealthSeason(int hS_NO, String pRDLST_NM, String m_DISTCTNS, String mTC_NM, String eFFECT, String pURCHASE_MTH,
			String cOOK_MTH, String tRT_MTH, String iMG_URL, int rowNum) {
		super();
		HS_NO = hS_NO;
		PRDLST_NM = pRDLST_NM;
		M_DISTCTNS = m_DISTCTNS;
		MTC_NM = mTC_NM;
		EFFECT = eFFECT;
		PURCHASE_MTH = pURCHASE_MTH;
		COOK_MTH = cOOK_MTH;
		TRT_MTH = tRT_MTH;
		IMG_URL = iMG_URL;
		this.rowNum = rowNum;
	}

	public int getHS_NO() {
		return HS_NO;
	}

	public void setHS_NO(int hS_NO) {
		HS_NO = hS_NO;
	}

	public String getPRDLST_NM() {
		return PRDLST_NM;
	}

	public void setPRDLST_NM(String pRDLST_NM) {
		PRDLST_NM = pRDLST_NM;
	}

	public String getM_DISTCTNS() {
		return M_DISTCTNS;
	}

	public void setM_DISTCTNS(String m_DISTCTNS) {
		M_DISTCTNS = m_DISTCTNS;
	}

	public String getMTC_NM() {
		return MTC_NM;
	}

	public void setMTC_NM(String mTC_NM) {
		MTC_NM = mTC_NM;
	}

	public String getEFFECT() {
		return EFFECT;
	}

	public void setEFFECT(String eFFECT) {
		EFFECT = eFFECT;
	}

	public String getPURCHASE_MTH() {
		return PURCHASE_MTH;
	}

	public void setPURCHASE_MTH(String pURCHASE_MTH) {
		PURCHASE_MTH = pURCHASE_MTH;
	}

	public String getCOOK_MTH() {
		return COOK_MTH;
	}

	public void setCOOK_MTH(String cOOK_MTH) {
		COOK_MTH = cOOK_MTH;
	}

	public String getTRT_MTH() {
		return TRT_MTH;
	}

	public void setTRT_MTH(String tRT_MTH) {
		TRT_MTH = tRT_MTH;
	}

	public String getIMG_URL() {
		return IMG_URL;
	}

	public void setIMG_URL(String iMG_URL) {
		IMG_URL = iMG_URL;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	@Override
	public String toString() {
		return "[SF_NO=" + HS_NO + ", PRDLST_NM=" + PRDLST_NM + ", M_DISTCTNS=" + M_DISTCTNS + ", MTC_NM="
				+ MTC_NM + ", EFFECT=" + EFFECT + ", PURCHASE_MTH=" + PURCHASE_MTH + ", COOK_MTH=" + COOK_MTH
				+ ", TRT_MTH=" + TRT_MTH + ", IMG_URL=" + IMG_URL + ", rowNum=" + rowNum + "]";
	}

}
