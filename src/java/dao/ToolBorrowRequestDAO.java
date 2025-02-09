package dao;

import db.DBConnection;
import models.bean.ToolBorrowRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToolBorrowRequestDAO {

    // ✅ Fetch pending tool borrow requests
    public List<ToolBorrowRequest> getPendingRequests() {
        List<ToolBorrowRequest> requests = new ArrayList<>();
        String query = "SELECT toolBorrowRequestID, farmer_id, toolID, requestDate, status, approveDate, adminID FROM tool_borrow_requests WHERE status = 'Pending'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                requests.add(new ToolBorrowRequest(
                    rs.getInt("toolBorrowRequestID"),
                    rs.getInt("farmer_id"),
                    rs.getInt("toolID"),
                    rs.getTimestamp("requestDate"), // ✅ Corrected: Keep as Timestamp
                    rs.getString("status"),
                    rs.getTimestamp("approveDate"), // ✅ Can be NULL
                    rs.getObject("adminID") != null ? rs.getInt("adminID") : null // ✅ Handle NULL adminID
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public boolean updateRequestStatus(int requestID, String status, int adminID) {
    String query = "UPDATE ToolBorrowRequest SET status = ?, adminID = ? WHERE requestID = ?";
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setString(1, status);
        stmt.setInt(2, adminID);
        stmt.setInt(3, requestID);
        
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    // ✅ Farmer requests a tool
    public boolean requestTool(int farmerId, int toolId) {
        String query = "INSERT INTO tool_borrow_requests (farmer_id, toolID, requestDate, status) VALUES (?, ?, CURRENT_TIMESTAMP, 'Pending')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, farmerId);
            stmt.setInt(2, toolId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
