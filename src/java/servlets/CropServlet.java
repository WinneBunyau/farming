package servlets;

import db.DBConnection;
import models.bean.Crop;
import models.bean.CropType;
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

@WebServlet("/CropServlet")
public class CropServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer farmerId = (Integer) session.getAttribute("farmerId");

        if (farmerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Crop> crops = new ArrayList<>();
        List<CropType> cropTypes = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            // Fetch all crops for the farmer
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT c.crop_id, c.crop_name, ct.crop_type_name, c.planting_date, c.harvest_date, c.yield " +
                    "FROM crops c JOIN crop_type ct ON c.crop_type_id = ct.crop_type_id WHERE c.farmer_id = ?")) {
                stmt.setInt(1, farmerId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    crops.add(new Crop(
                        rs.getInt("crop_id"),
                        farmerId,
                        rs.getString("crop_name"),
                        rs.getString("crop_type_name"),
                        rs.getString("planting_date"),
                        rs.getString("harvest_date"),
                        rs.getInt("yield")
                    ));
                }
            }

            // Fetch all crop types
            try (PreparedStatement stmt = conn.prepareStatement("SELECT crop_type_id, crop_type_name FROM crop_type");
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cropTypes.add(new CropType(
                        rs.getInt("crop_type_id"),
                        rs.getString("crop_type_name")
                    ));
                }
            }

            request.setAttribute("crops", crops);
            request.setAttribute("cropTypes", cropTypes);
            request.getRequestDispatcher("crops.jsp").forward(request, response);

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

        String action = request.getParameter("action");
        if ("update".equals(action)) {
            int cropId = Integer.parseInt(request.getParameter("crop_id"));
            String cropName = request.getParameter("crop_name");
            int cropTypeId = Integer.parseInt(request.getParameter("crop_type_id"));
            String plantingDate = request.getParameter("planting_date");
            String harvestDate = request.getParameter("harvest_date");
            int yield = Integer.parseInt(request.getParameter("yield"));

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("UPDATE crops SET crop_name=?, crop_type_id=?, planting_date=?, harvest_date=?, yield=? WHERE crop_id=?")) {
                stmt.setString(1, cropName);
                stmt.setInt(2, cropTypeId);
                stmt.setString(3, plantingDate);
                stmt.setString(4, harvestDate);
                stmt.setInt(5, yield);
                stmt.setInt(6, cropId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new ServletException("Database error!", e);
            }
        } else {
            String cropName = request.getParameter("crop_name");
            int cropTypeId = Integer.parseInt(request.getParameter("crop_type_id"));
            String plantingDate = request.getParameter("planting_date");
            String harvestDate = request.getParameter("harvest_date");
            int yield = Integer.parseInt(request.getParameter("yield"));

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO crops (farmer_id, crop_name, crop_type_id, planting_date, harvest_date, yield) VALUES (?, ?, ?, ?, ?, ?)")
            ) {
                stmt.setInt(1, farmerId);
                stmt.setString(2, cropName);
                stmt.setInt(3, cropTypeId);
                stmt.setString(4, plantingDate);
                stmt.setString(5, harvestDate);
                stmt.setInt(6, yield);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new ServletException("Database error!", e);
            }
        }
        response.sendRedirect("CropServlet");
    }
}
