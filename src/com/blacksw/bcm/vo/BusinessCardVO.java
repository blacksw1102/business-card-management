package com.blacksw.bcm.vo;

public class BusinessCardVO {
	private int businessCardNo;
	private String name;
	private String companyName;
	private String department;
	private String position;
	private String email;
	private String tel;
	private String phone;
	private String address;
	private String companyCI;
	private String userId;
	
	public BusinessCardVO() {
		super();
	}

	public BusinessCardVO(int businessCardNo, String name, String companyName, String department, String position,
			String email, String tel, String phone, String address, String companyCI, String userId) {
		super();
		this.businessCardNo = businessCardNo;
		this.name = name;
		this.companyName = companyName;
		this.department = department;
		this.position = position;
		this.email = email;
		this.tel = tel;
		this.phone = phone;
		this.address = address;
		this.companyCI = companyCI;
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "BusinessCardVO [businessCardNo=" + businessCardNo + ", name=" + name + ", companyName=" + companyName
				+ ", department=" + department + ", position=" + position + ", email=" + email + ", tel=" + tel
				+ ", phone=" + phone + ", address=" + address + ", companyCI=" + companyCI + ", userId=" + userId + "]";
	}

	public int getBusinessCardNo() {
		return businessCardNo;
	}

	public void setBusinessCardNo(int businessCardNo) {
		this.businessCardNo = businessCardNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyCI() {
		return companyCI;
	}

	public void setCompanyCI(String companyCI) {
		this.companyCI = companyCI;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
