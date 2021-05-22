package com.blacksw.bcm.util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class JdbcUtil {
 	private static Connection conn = null;
 	
 	public static Connection getConnection() {
 		try {
 			Context init = new InitialContext();
 			Context  env = (Context) init.lookup("java:comp/env");
 			DataSource  ds = (DataSource) env.lookup("jdbc/mysqlDB");
 			conn = (Connection) ds.getConnection();
 			System.out.println("DB 연결 성공");
 		} catch(Exception e) {
 			System.out.println("DB 연결 실패");
 			e.printStackTrace();
 		}
 		return conn;
 	}
 	
 	public static void close(Connection conn) {
 		try {
 			conn.close();
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 	}
 	
 	public static void close(Statement stmt){
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs){
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con){
		try {
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con){
		try {
			con.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	
}
