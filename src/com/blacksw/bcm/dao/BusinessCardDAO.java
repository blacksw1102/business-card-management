package com.blacksw.bcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	// 명함 데이터 삽입
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

	// 명함 데이터 조회
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

	// 명함 데이터 수정
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

	public ArrayList<BusinessCardVO> selectBusinessCardList( int page, String keyword) {
		ArrayList<BusinessCardVO> businessCardList = new ArrayList<>();
		BusinessCardVO businessCard = null;
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 키워드를 제공받지 않았을 경우
		if(keyword == null) {
			keyword = "";
		}
		
		System.out.println(keyword);
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM business_card WHERE name LIKE '%' ? '%' ORDER BY business_card_no DESC LIMIT ?, 10");
			pstmt.setString(1, keyword);
			pstmt.setInt(2, (page - 1) * 10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				businessCard = new BusinessCardVO();
				businessCard.setBusinessCardNo(Integer.parseInt(rs.getString("business_card_no")));
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
				businessCardList.add(businessCard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return businessCardList;
	}

	public int selectBusinessCardCount() {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM business_card");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return count;
	}

	public int deleteBusinessCard(int businessCardNo, String userId) {
		int result = 0;
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement("DELETE FROM business_card WHERE business_card_no = ? AND user_id = ?");
			pstmt.setInt(1, businessCardNo);
			pstmt.setString(2, userId);
			
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
