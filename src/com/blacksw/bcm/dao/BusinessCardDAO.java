package com.blacksw.bcm.dao;

import java.sql.Connection;

import com.blacksw.bcm.util.JdbcUtil;


public class BusinessCardDAO {

	private BusinessCardDAO instance;
	private Connection conn;
	
	public BusinessCardDAO getInstance() {
		if(instance == null) {
			instance = new BusinessCardDAO();
		}
		return instance;
	}
	
	public void setConnection() {
		conn = JdbcUtil.getConnection();
	}
	
	
	
}
