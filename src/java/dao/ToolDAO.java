package dao;

import db.DBConnection;
import models.bean.Tool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToolDAO {

    // ✅ Fetch available tools (Fix: Ensure availability_status is correct type)
    public List<Tool> getAvailableTools() {
        List<Tool> tools = new ArrayList<>();
        String query = "SELECT * FROM tools WHERE availability_status = 'Available'"; // Assuming it's VARCHAR

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tools.add(new Tool(
                    rs.getInt("tool_id"),
                    rs.getString("tool_name"),
                    rs.getString("tool_type"),
                    rs.getString("availability_status") // ✅ Fix: Now returns String
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tools;
    }

    // ✅ Add a new tool
    public boolean addTool(String name, String type, String availability) { // ✅ Fix: Changed `int` to `String`
        String query = "INSERT INTO tools (tool_name, tool_type, availability_status) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3, availability); // ✅ Fix: Insert `String`
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Update tool availability (e.g., when a farmer borrows a tool)
    public boolean updateToolAvailability(int toolId, String status) {
        String query = "UPDATE tools SET availability_status = ? WHERE tool_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status); // e.g., "Unavailable"
            stmt.setInt(2, toolId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
