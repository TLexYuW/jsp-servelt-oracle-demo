package lex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lex.entity.User;
import lex.util.OjdbcUtil;

public class UserDAO {
		
	 Connection connection = null;
			 
	 static {		 
		 try {
			 Class.forName(OjdbcUtil.DRIVER);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	 }
 
	private static final String INSERT_STMT = "INSERT INTO USERINFO(name, email, country) VALUES(?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE USERINFO set name = ?, email = ?, country = ? where id = ?";
	private static final String DELETE_STMT = "DELETE USERINFO where id = ?";
	private static final String FIND_BY_PK = "SELECT id, name, email, country from USERINFO WHERE id = ?";
	private static final String GET_ALL = "SELECT id, name, email, country from USERINFO";
	
	public User createUser(User user) {
		try(Connection conn = DriverManager.getConnection(OjdbcUtil.URL,OjdbcUtil.USER,OjdbcUtil.PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT_STMT)) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getCountry());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User updateUser(User user) {
		try(Connection conn = DriverManager.getConnection(OjdbcUtil.URL,OjdbcUtil.USER,OjdbcUtil.PASSWORD);
				PreparedStatement ps = conn.prepareStatement(UPDATE_STMT)) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getCountry());
			ps.setInt(4, user.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean deleteUser(Integer theId) {
		boolean flag = false;
		try(Connection conn = DriverManager.getConnection(OjdbcUtil.URL,OjdbcUtil.USER,OjdbcUtil.PASSWORD);
				PreparedStatement ps = conn.prepareStatement(DELETE_STMT)) {
			ps.setInt(1, theId);
			ps.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public User findById(Integer theId) {
		User user = new User();
		try(Connection conn = DriverManager.getConnection(OjdbcUtil.URL,OjdbcUtil.USER,OjdbcUtil.PASSWORD);
				PreparedStatement ps = conn.prepareStatement(FIND_BY_PK)) {
			ps.setInt(1, theId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setCountry(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getAll() {
		List<User> list = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(OjdbcUtil.URL,OjdbcUtil.USER,OjdbcUtil.PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ALL);) {
			ResultSet rs =  ps.executeQuery();			
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String country = rs.getString(4);
				User user = new User(id, name, email, country);
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
