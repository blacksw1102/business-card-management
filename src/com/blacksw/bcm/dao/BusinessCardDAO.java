package com.blacksw.bcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

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

	// ���� ������ ����
	public int insertBusinessCard(BusinessCardVO businessCard) {
		int result = 0;
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement("INSERT INTO business_card"
					+ "(name, company_name, department, position, email, tel, phone, address, company_ci, user_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}

		return result;
	}

	// ���� ������ ��ȸ
	public BusinessCardVO selectOneBusinessCard(int businessCardNo) {
		BusinessCardVO businessCard = null;
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM business_card WHERE business_card_no = ?");
			pstmt.setInt(1, businessCardNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				businessCard = new BusinessCardVO();
				businessCard.setBusinessCardNo(businessCardNo);
				businessCard.setName(rs.getString("name"));
				businessCard.setCompanyName(rs.getString("company_name"));
				businessCard.setDepartment(rs.getString("department"));
				businessCard.setPosition(rs.getString("position"));
				businessCard.setEmail(rs.getString("email"));
				businessCard.setTel(rs.getString("tel"));
				businessCard.setPhone(rs.getString("phone"));
				businessCard.setAddress(rs.getString("address"));
				businessCard.setCompanyCI(rs.getString("company_ci"));
				businessCard.setUserId(rs.getString("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return businessCard;
	}

	// ���� ������ ����
	public int updateBusinessCard(BusinessCardVO businessCard) {
		int result = 0;
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement("UPDATE business_card SET "
					+ "name = ?, company_name = ?, department = ?, position = ?, email = ?, tel = ?, phone = ?, address = ?, company_ci = ? WHERE user_id = ? AND business_card_no = ?");
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
			pstmt.setInt(11,  businessCard.getBusinessCardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}

		return result;		
	}
	
}
