package servlets;

import db.DBConnection;
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

@WebServlet("/CropTypeServlet")
public class CropTypeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CropType> cropTypes = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM crop_type");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cropTypes.add(new CropType(
                    rs.getInt("crop_type_id"),
                    rs.getString("crop_type_name")
                ));
            }
            request.setAttribute("cropTypes", cropTypes);
            request.getRequestDispatcher("crops.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error!", e);
        }
    }
}
