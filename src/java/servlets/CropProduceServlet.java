package servlets;

import db.DBConnection;
import models.bean.CropProduce;
import models.bean.Crop;
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

@WebServlet("/CropProduceServlet")
public class CropProduceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer farmerId = (Integer) session.getAttribute("farmerId");

        if (farmerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<CropProduce> cropProduces = new ArrayList<>();
        List<Crop> crops = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            // Fetch only farmer's crops
            try (PreparedStatement stmt = conn.prepareStatement("SELECT crop_id, crop_name FROM crops WHERE farmer_id = ?")) {
                stmt.setInt(1, farmerId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    crops.add(new Crop(
                        rs.getInt("crop_id"),
                        farmerId,
                        rs.getString("crop_name"),
                        "", "", "", 0
                    ));
                }
            }

            // Fetch farmer's crop produce records
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM crop_produce WHERE crop_id IN (SELECT crop_id FROM crops WHERE farmer_id = ?)")) {
                stmt.setInt(1, farmerId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    cropProduces.add(new CropProduce(
                        rs.getInt("crop_produce_id"),
                        rs.getInt("crop_id"),
                        rs.getDouble("quantity"),
                        rs.getDate("crop_produce_date"),
                        rs.getString("crop_storage_location")
                    ));
                }
            }

            request.setAttribute("cropProduces", cropProduces);
            request.setAttribute("crops", crops);
            request.getRequestDispatcher("crop_produce.jsp").forward(request, response);

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

        if ("add".equals(action)) {
            int cropId = Integer.parseInt(request.getParameter("crop_id"));
            double quantity = Double.parseDouble(request.getParameter("quantity"));
            String storageLocation = request.getParameter("crop_storage_location");

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO crop_produce (crop_id, quantity, crop_produce_date, crop_storage_location) VALUES (?, ?, CURRENT_DATE, ?)")
            ) {
                stmt.setInt(1, cropId);
                stmt.setDouble(2, quantity);
                stmt.setString(3, storageLocation);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new ServletException("Database error!", e);
            }
        }

        response.sendRedirect("CropProduceServlet");
    }
}
