package daos.impl;

import configs.DBConnect;
import daos.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.Cookie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

public class DAOUser implements UserDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	@Override
	public User login(String email, String password) {
		User user = null;
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
		
	}
	@Override
	public boolean register(String email, String password) {
	    if (existsEmail(email)) {
	        return false; 
	    }

	    String sql = "INSERT INTO users(email, password) VALUES(?, ?)";
	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, email);
	        ps.setString(2, password);
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	@Override
	public boolean existsEmail(String email) {
	    String sql = "SELECT 1 FROM users WHERE email = ?";
	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();
	        return rs.next(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}