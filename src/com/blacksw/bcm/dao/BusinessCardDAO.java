package com.blacksw.bcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.blacksw.bcm.util.JdbcUtil;
import com.blacksw.bcm.vo.BusinessCardVO;

public class BusinessCardDAO {

	private static BusinessCardDAO instance;
	
	public static BusinessCardDAO getInstance() {
		if(instance == null) {
			instance = new BusinessCardDAO();
		}
		return instance;
	}

	public int insertBusinessCard(BusinessCardVO businessCard) {
		int result = 0;

		try {
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO business_card(name, company_name, department, position, email, tel, phone, address, company_ci, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, businessCard.getName());
			pstmt.setString(2, businessCard.getCompanyName());
			pstmt.setString(3, businessCard.getDepartment());
			pstmt.setString(4, businessCard.getPosition());
			pstmt.setString(5, businessCard.getEmail());
			pstmt.setString(6, businessCard.getTel());
			pstmt.setString(7, businessCard.getPhone());
			pstmt.setString(8, businessCard.getAddress());
			pstmt.setString(9, businessCard.getCompanyCI());
			pstmt.setString(10, businessCard.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
}
