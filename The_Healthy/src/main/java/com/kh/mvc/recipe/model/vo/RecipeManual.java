package com.kh.mvc.recipe.model.vo;

public class RecipeManual {
	private int HR_NO;        // 해당 레시피 일련번호
	private int seq;          // 조리 순서
	private String manual;    // 조리방법
	private String manualImg; // 조리 이미지

	public RecipeManual() {
		super();
	}

	public RecipeManual(int hR_No, int seq, String manual, String manualImg) {
		super();
		HR_NO = hR_No;
		this.seq = seq;
		this.manual = manual;
		this.manualImg = manualImg;
	}

	public int getHR_NO() {
		return HR_NO;
	}

	public void setHR_NO(int hR_No) {
		HR_NO = hR_No;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getManual() {
		return manual;
	}

	public void setManual(String manual) {
		this.manual = manual;
	}

	public String getManualImg() {
		return manualImg;
	}

	public void setManualImg(String manualImg) {
		this.manualImg = manualImg;
	}

	@Override
	public String toString() {
		return "(HR_NO=" + HR_NO + ", seq=" + seq + ", manual=" + manual + ", manualImg=" + manualImg + ")";
	}

}
