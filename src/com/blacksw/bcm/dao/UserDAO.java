package com.blacksw.bcm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.blacksw.bcm.util.JdbcUtil;
import com.blacksw.bcm.vo.LoginVO;
import com.blacksw.bcm.vo.UserVO;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {

	private static UserDAO instance;
	
	public static UserDAO getInstance() {
		if(instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
	
	// 로그인
	public UserVO selectOneUser(LoginVO login) {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO user = null;
		
		try {
			pstmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM user WHERE id=? AND pw=?");
			pstmt.setString(1, login.getId());
			pstmt.setString(2, login.getPw());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 로그인 성공
				user = new UserVO(rs.getString("id"), rs.getString("pw"), rs.getString("name"));
			} else {
				// 로그인 실패
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return user;
	}
	
	// 회원가입
	public int insertUser(UserVO user) {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		try {
			pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO user(id, pw, name) VALUES(?,?,?)");
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getName());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return insertCount;
	}

	
}
