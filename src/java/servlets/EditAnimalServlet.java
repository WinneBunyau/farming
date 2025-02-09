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

@WebServlet("/EditAnimalServlet")
public class EditAnimalServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int animalId = Integer.parseInt(request.getParameter("animal_id"));

        try (Connection conn = DBConnection.getConnection()) {
            // Fetch animal details
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM animals WHERE animal_id = ?");
            stmt.setInt(1, animalId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Animal animal = new Animal(
                    rs.getInt("animal_id"),
                    rs.getInt("farmer_id"),
                    rs.getString("animal_name"),
                    rs.getInt("animal_type_id"),
                    rs.getString("birth_date"),
                    rs.getDouble("weight")
                );
                request.setAttribute("animal", animal);
            }

            // Fetch all animal types for dropdown
            List<AnimalType> animalTypes = new ArrayList<>();
            stmt = conn.prepareStatement("SELECT * FROM animal_type");
            rs = stmt.executeQuery();
            while (rs.next()) {
                animalTypes.add(new AnimalType(
                    rs.getInt("animal_type_id"),
                    rs.getString("animal_type_name")
                ));
            }
            request.setAttribute("animalTypes", animalTypes);
            request.getRequestDispatcher("edit_animal.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Database error!", e);
        }
    }
}
