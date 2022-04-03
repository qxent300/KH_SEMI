package com.kh.mvc.community.model.vo;

public class UserRecipeManual {
	private int HR_NO;        
	private int seq;          
	private String manual;    
	private String manualImg; 
	
	public UserRecipeManual() {}

	public UserRecipeManual(int hR_NO) {
		super();
		HR_NO = hR_NO;
	}

	public UserRecipeManual(int hR_NO, int seq, String manual, String manualImg) {
		super();
		HR_NO = hR_NO;
		this.seq = seq;
		this.manual = manual;
		this.manualImg = manualImg;
	}

	public int getHR_NO() {
		return HR_NO;
	}

	public void setHR_NO(int hR_NO) {
		HR_NO = hR_NO;
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
		return "UserRecipeManual [HR_NO=" + HR_NO + ", seq=" + seq + ", manual=" + manual + ", manualImg=" + manualImg
				+ "]";
	}

}
