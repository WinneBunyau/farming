package servlets;

import db.DBConnection;
import models.bean.Farmer;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String phone = request.getParameter("phone");
        String farmLocation = request.getParameter("farm_location");

        if (!password.equals(confirmPassword)) {
            response.sendRedirect("register.jsp?error=Passwords do not match");
            return;
        }

        String hashedPassword = hashPassword(password);

        try (Connection conn = DBConnection.getConnection()) {
            // Check if email already exists
            String checkEmailQuery = "SELECT email FROM farmers WHERE email = ?";
            try (PreparedStatement checkEmailStmt = conn.prepareStatement(checkEmailQuery)) {
                checkEmailStmt.setString(1, email);
                ResultSet rs = checkEmailStmt.executeQuery();
                if (rs.next()) {
                    response.sendRedirect("register.jsp?error=Email already exists. Try a different one.");
                    return;
                }
            }

            // Insert new farmer
            String insertQuery = "INSERT INTO farmers (name, email, password, phone, farm_location) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, hashedPassword);
                ps.setString(4, phone);
                ps.setString(5, farmLocation);

                int result = ps.executeUpdate();
                if (result > 0) {
                    response.sendRedirect("login.jsp?success=Registered successfully!");
                } else {
                    response.sendRedirect("register.jsp?error=Registration failed");
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error!", e);
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
