package servlet;

import db.DBConnection;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/UpdateFarmServlet")
public class UpdateFarmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int farmId = Integer.parseInt(request.getParameter("farmId"));
        String farmName = request.getParameter("farm_name");
        String farmLocation = request.getParameter("farm_location");
        BigDecimal farmSize = new BigDecimal(request.getParameter("farm_size"));
        String waterSource = request.getParameter("water_source");
        String rentDuration = request.getParameter("rent_duration");
        BigDecimal rentAmt = new BigDecimal(request.getParameter("rent_amt"));
        String status = request.getParameter("status");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE Farms SET farm_name=?, farm_location=?, farm_size=?, water_source=?, rent_duration=?, rent_amt=?, status=? WHERE farmId=?")) {
            
            ps.setString(1, farmName);
            ps.setString(2, farmLocation);
            ps.setBigDecimal(3, farmSize);
            ps.setString(4, waterSource);
            ps.setString(5, rentDuration);
            ps.setBigDecimal(6, rentAmt);
            ps.setString(7, status);
            ps.setInt(8, farmId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("adminFarmManagement.jsp");
    }
}
