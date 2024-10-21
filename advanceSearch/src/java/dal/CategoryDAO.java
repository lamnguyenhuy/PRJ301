package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Category;

public class CategoryDAO {
    private Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to get all categories
    public List<Category> getAllCategories() throws SQLException {
        String sql = "SELECT * FROM Category";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(new Category(rs.getInt("cid"), rs.getString("cname")));
        }
        return categories;
    }
}
