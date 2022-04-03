package com.kh.mvc.member.model.vo;

import java.util.Date;

public class Member {
	private int no;
	private String id;
	private String password;
	private String role;
	private String name;
	private String email;
	private String address;
	private String status;
	private Date enrollDate;
	private Date modifyDate;
	
	public Member() {}
	
	public Member(int no, String id, String password, String role, String name, String email, String address,
			String status, Date enrollDate, Date modifyDate) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.role = role;
		this.name = name;
		this.email = email;
		this.address = address;
		this.status = status;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Member [no=" + no + ", id=" + id + ", password=" + password + ", role=" + role + ", name=" + name
				+ ", email=" + email + ", address=" + address + ", status=" + status + ", enrollDate=" + enrollDate
				+ ", modifyDate=" + modifyDate + "]";
	}

}
