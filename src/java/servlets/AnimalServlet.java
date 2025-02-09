package servlets;

import db.DBConnection;
import models.bean.Animal;
import models.bean.AnimalType;
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

@WebServlet("/AnimalServlet")
public class AnimalServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer farmerId = (Integer) session.getAttribute("farmerId");

        if (farmerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Animal> animals = new ArrayList<>();
        List<AnimalType> animalTypes = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            // Fetch all animals for the farmer
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT a.animal_id, a.animal_name, a.animal_type_id, a.birth_date, a.weight " +
                    "FROM animals a WHERE a.farmer_id = ?")) {
                stmt.setInt(1, farmerId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    animals.add(new Animal(
                        rs.getInt("animal_id"),
                        farmerId,
                        rs.getString("animal_name"),
                        rs.getInt("animal_type_id"),
                        rs.getString("birth_date"),
                        rs.getDouble("weight")
                    ));
                }
            }

            // Fetch all animal types
            try (PreparedStatement stmt = conn.prepareStatement("SELECT animal_type_id, animal_type_name FROM animal_type");
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    animalTypes.add(new AnimalType(
                        rs.getInt("animal_type_id"),
                        rs.getString("animal_type_name")
                    ));
                }
            }

            request.setAttribute("animals", animals);
            request.setAttribute("animalTypes", animalTypes);
            request.getRequestDispatcher("animals.jsp").forward(request, response);

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

        if ("delete".equals(action)) {
            // Handle delete logic
            int animalId = Integer.parseInt(request.getParameter("animal_id"));
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM animals WHERE animal_id=?")) {
                stmt.setInt(1, animalId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new ServletException("Database error!", e);
            }
        } else if ("add".equals(action)) {
            // Handle adding new animal
            String animalName = request.getParameter("animal_name");
            int animalTypeId = Integer.parseInt(request.getParameter("animal_type_id"));
            String birthDate = request.getParameter("birth_date");
            double weight = Double.parseDouble(request.getParameter("weight"));

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO animals (farmer_id, animal_name, animal_type_id, birth_date, weight) VALUES (?, ?, ?, ?, ?)")
            ) {
                stmt.setInt(1, farmerId);
                stmt.setString(2, animalName);
                stmt.setInt(3, animalTypeId);
                stmt.setString(4, birthDate);
                stmt.setDouble(5, weight);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new ServletException("Database error!", e);
            }
        } else if ("update_animal".equals(action)) {
            // Handle update logic
            int animalId = Integer.parseInt(request.getParameter("animal_id"));
            String animalName = request.getParameter("animal_name");
            int animalTypeId = Integer.parseInt(request.getParameter("animal_type_id"));
            String birthDate = request.getParameter("birth_date");
            double weight = Double.parseDouble(request.getParameter("weight"));

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("UPDATE animals SET animal_name=?, animal_type_id=?, birth_date=?, weight=? WHERE animal_id=?")) {
                stmt.setString(1, animalName);
                stmt.setInt(2, animalTypeId);
                stmt.setString(3, birthDate);
                stmt.setDouble(4, weight);
                stmt.setInt(5, animalId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new ServletException("Database error!", e);
            }
        }
        
        response.sendRedirect("AnimalServlet"); // Redirect to refresh the list
    }
}
