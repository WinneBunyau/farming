package servlets;

import db.DBConnection;
import models.bean.Farm;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FarmServlet")
public class FarmServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer farmerId = (Integer) session.getAttribute("farmerId");

        if (farmerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Farm> farms = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM farms WHERE is_available = TRUE");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                farms.add(new Farm(
                    rs.getInt("farm_id"),
                    rs.getString("farm_name"),
                    rs.getDouble("farm_size"),   // ✅ Fix: Correct position
                    rs.getString("farm_location"),
                    rs.getString("water_source")
                ));
            }
            request.setAttribute("farms", farms);
            request.getRequestDispatcher("farm.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error!", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer farmerId = (Integer) session.getAttribute("farmerId");
        if (farmerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int farmId = Integer.parseInt(request.getParameter("farm_id"));

        try (Connection conn = DBConnection.getConnection()) {
            // Check if the farmer has already booked this farm
            String checkQuery = "SELECT * FROM farm_bookings WHERE farmer_id = ? AND farm_id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, farmerId);
                checkStmt.setInt(2, farmId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    response.sendRedirect("farm.jsp?error=You have already booked this farm.");
                    return;
                }
            }

            // Book the farm
            String insertQuery = "INSERT INTO farm_bookings (farmer_id, farm_id, request_date) VALUES (?, ?, CURRENT_TIMESTAMP)";
            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setInt(1, farmerId);
                stmt.setInt(2, farmId);
                stmt.executeUpdate();
            }

            response.sendRedirect("FarmServlet");
        } catch (SQLException e) {
            throw new ServletException("Database error!", e);
        }
    }
}
