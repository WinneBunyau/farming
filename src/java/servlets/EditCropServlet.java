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

@WebServlet("/EditCropServlet")
public class EditCropServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cropId = Integer.parseInt(request.getParameter("crop_id"));

        try (Connection conn = DBConnection.getConnection()) {
            // Fetch crop details
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM crops WHERE crop_id = ?");
            stmt.setInt(1, cropId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Crop crop = new Crop(
                    rs.getInt("crop_id"),
                    rs.getInt("farmer_id"),
                    rs.getString("crop_name"),
                    String.valueOf(rs.getInt("crop_type_id")), // Convert to String for dropdown compatibility
                    rs.getString("planting_date"),
                    rs.getString("harvest_date"),
                    rs.getInt("yield")
                );
                request.setAttribute("crop", crop);
            }

            // Fetch all crop types for dropdown
            List<CropType> cropTypes = new ArrayList<>();
            stmt = conn.prepareStatement("SELECT * FROM crop_type");
            rs = stmt.executeQuery();
            while (rs.next()) {
                cropTypes.add(new CropType(
                    rs.getInt("crop_type_id"),
                    rs.getString("crop_type_name")
                ));
            }
            request.setAttribute("cropTypes", cropTypes);
            request.getRequestDispatcher("edit_crop.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Database error!", e);
        }
    }
}
