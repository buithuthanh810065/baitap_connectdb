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
	public User login(String username, String password) {
		User user = null;
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
		
	}
	@Override
	public boolean register(String username, String password) {
		String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            int rows = stmt.executeUpdate();
            return rows > 0; // true nếu thêm thành công
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}


}
