package daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import configs.DBConnect;
import daos.CategoryDao;
import model.Category;

public class CategoryDAOImpl implements CategoryDao {
    @Override
    public void insert(Category category) {
        String sql = "INSERT INTO categories (cate_id, cate_name, icons) VALUES (?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, category.getCateid());
            ps.setString(2, category.getCatename());
            ps.setString(3, category.getIcon());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    @Override
    public void edit(Category category) {
        String sql = "UPDATE categories SET cate_name = ?, icons = ? WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category.getCatename());
            ps.setString(2, category.getIcon());
            ps.setInt(3, category.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM categories WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    @Override
    public Category get(int id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Category(
                        rs.getInt("id"),
                        rs.getInt("cate_id"),
                        rs.getString("cate_name"),
                        rs.getString("icons")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }


    @Override
    public Category get(String name) {
        String sql = "SELECT * FROM categories WHERE cate_name = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Category(
                		 rs.getInt("id"),
                    rs.getInt("cate_id"),
                    rs.getString("cate_name"),
                    rs.getString("icons")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category category = new Category(
                		rs.getInt("id"),
                    rs.getInt("cate_id"),
                    rs.getString("cate_name"),
                    rs.getString("icons")
                );
                categories.add(category);
                System.out.println("Tìm thấy danh mục: ID=" + category.getCateid() + ", Name=" + category.getCatename());
            }
            System.out.println("Tổng số danh mục: " + categories.size());
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách: " + e.getMessage());
            e.printStackTrace();
        }
        return categories;
    }
}