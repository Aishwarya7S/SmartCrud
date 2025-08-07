package com.smartcrud.DAO;

import com.smartcrud.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private String dburl = "jdbc:mysql://localhost:3306/smartcrud";
	private String user = "root";
	private String pwd = "db_password";
	
	private static final String INSERT_SQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?)";
	private static final String SELECT_ALL_SQL = "SELECT * FROM users";
	private static final String SELECT_BY_ID_SQL = "SELECT * FROM users WHERE id = ?";
	private static final String UPDATE_SQL = "UPDATE users SET name = ?, email = ?, country = ? WHERE id = ?";
	private static final String DELETE_SQL = "DELETE FROM users WHERE id = ?";
	
	protected Connection getConnection(){
	    Connection conn = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(dburl, user, pwd);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	// insert 
	public void insert(User user) throws SQLException {
	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

	        ps.setString(1, user.getName());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, user.getCountry());
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error inserting user", e);
	    }
	}
	
	//update
	public boolean update(User user) throws SQLException {
	    boolean rowUpdated;

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

	        ps.setString(1, user.getName());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, user.getCountry());
	        ps.setInt(4, user.getId());
	        rowUpdated = ps.executeUpdate() > 0;
	    }
	    return rowUpdated;
	}
	
	//delete
	public boolean delete(int id) throws SQLException {
	    boolean rowDeleted;

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {

	        ps.setInt(1, id);
	        rowDeleted = ps.executeUpdate() > 0;
	    }
	    return rowDeleted;
	}

	//select by id
	public User selectById(int id) throws SQLException {
	    User user = null;

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {

	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String name = rs.getString("name");
	            String email = rs.getString("email");
	            String country = rs.getString("country");
	            user = new User(id, name, email, country);
	        }
	    }
	    return user;
	}

	//select all 
	public List<User> selectAll() throws SQLException {
	    List<User> users = new ArrayList<>();

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            String email = rs.getString("email");
	            String country = rs.getString("country");
	            users.add(new User(id, name, email, country));
	        }
	    }
	    return users;
	}

}
