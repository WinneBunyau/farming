package servlets;

import db.DBConnection;
import models.bean.AnimalProduce;
import models.bean.Animal;
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

@WebServlet("/AnimalProduceServlet")
public class AnimalProduceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer farmerId = (Integer) session.getAttribute("farmerId");

        if (farmerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<AnimalProduce> animalProduces = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            // Fetch only farmer's animals
            try (PreparedStatement stmt = conn.prepareStatement("SELECT animal_id, animal_name FROM animals WHERE farmer_id = ?")) {
                stmt.setInt(1, farmerId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    animals.add(new Animal(
                        rs.getInt("animal_id"),
                        farmerId,
                        rs.getString("animal_name"),
                        0, "", 0.0
                    ));
                }
            }

            // Fetch farmer's animal produce records
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM animal_produce WHERE animal_id IN (SELECT animal_id FROM animals WHERE farmer_id = ?)")) {
                stmt.setInt(1, farmerId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    animalProduces.add(new AnimalProduce(
                        rs.getInt("animal_produce_id"),
                        rs.getInt("animal_id"),
                        rs.getDouble("quantity"),
                        rs.getDate("animal_produce_date"),
                        rs.getString("animal_storage_location")
                    ));
                }
            }

            request.setAttribute("animalProduces", animalProduces);
            request.setAttribute("animals", animals);
            request.getRequestDispatcher("animal_produce.jsp").forward(request, response);

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
            int animalId = Integer.parseInt(request.getParameter("animal_id"));
            double quantity = Double.parseDouble(request.getParameter("quantity"));
            String storageLocation = request.getParameter("animal_storage_location");

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO animal_produce (animal_id, quantity, animal_produce_date, animal_storage_location) VALUES (?, ?, CURRENT_DATE, ?)")
            ) {
                stmt.setInt(1, animalId);
                stmt.setDouble(2, quantity);
                stmt.setString(3, storageLocation);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new ServletException("Database error!", e);
            }
        }

        response.sendRedirect("AnimalProduceServlet");
    }
}
