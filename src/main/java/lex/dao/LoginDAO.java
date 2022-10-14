package lex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lex.entity.Member;
import lex.util.OjdbcUtil;

public class LoginDAO {
	
	
	 Connection connection = null;
	 boolean status = false;
	 
	 private static final String GET_USER_ACC_PASS = "SELECT id, name, password FROM MEMBER where name = ? and password = ?";
			 
	 static {		 
		 try {
			 Class.forName(OjdbcUtil.DRIVER);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public boolean validate(Member member) {
		 try(Connection conn = DriverManager.getConnection(OjdbcUtil.URL,OjdbcUtil.USER,OjdbcUtil.PASSWORD);
					PreparedStatement ps = conn.prepareStatement(GET_USER_ACC_PASS)) {
				ps.setString(1, member.getName());
				ps.setString(2, member.getPassword());
				ResultSet rs = ps.executeQuery();
				status = rs.next();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return status;
		}
	 
}
