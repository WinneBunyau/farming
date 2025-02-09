<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.bean.Animal" %>
<%@ page import="models.bean.AnimalType" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Animals</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Manage Animals</h1>
        <table class="table table-bordered mt-3">
            <thead class="table-success">
                <tr>
                    <th>Animal Name</th>
                    <th>Animal Type</th>
                    <th>Birth Date</th>
                    <th>Weight (kg)</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Animal> animals = (List<Animal>) request.getAttribute("animals");
                    if (animals != null && !animals.isEmpty()) {
                        for (Animal animal : animals) { 
                %>
                <tr>
                    <td><%= animal.getAnimalName() %></td>
                    <td><%= animal.getAnimalTypeId() %></td>
                    <td><%= animal.getBirthDate() %></td>
                    <td><%= animal.getWeight() %></td>
                   <td>
    <a href="AnimalProduceServlet?animal_id=<%= animal.getAnimalId() %>" class="btn btn-success">Produce</a>
    <a href="EditAnimalServlet?animal_id=<%= animal.getAnimalId() %>" class="btn btn-warning">Edit</a>
    <form action="AnimalServlet" method="post" style="display:inline;">
        <input type="hidden" name="animal_id" value="<%= animal.getAnimalId() %>">
        <input type="hidden" name="action" value="delete">
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
</td>

                </tr>
                <% 
                        } 
                    } else { 
                %>
                <tr>
                    <td colspan="5" class="text-center">No animals added yet.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <h3>Add New Animal</h3>
        <form action="AnimalServlet" method="post" class="mt-3">
            <input type="hidden" name="action" value="add"> <!-- Ensure action is set -->
            <input type="text" name="animal_name" placeholder="Animal Name" required class="form-control mb-2">
            
            <label>Select Animal Type:</label>
            <select name="animal_type_id" required class="form-control mb-2">
                <% 
                    List<AnimalType> animalTypes = (List<AnimalType>) request.getAttribute("animalTypes");
                    if (animalTypes != null && !animalTypes.isEmpty()) {
                        for (AnimalType type : animalTypes) {
                %>
                    <option value="<%= type.getAnimalTypeId() %>"><%= type.getAnimalTypeName() %></option>
                <% 
                        }
                    } else { 
                %>
                    <option value="">No Animal Types Available</option>
                <% } %>
            </select>
            
            <input type="date" name="birth_date" required class="form-control mb-2">
            <input type="number" step="0.1" name="weight" placeholder="Weight (kg)" required class="form-control mb-2">
            <button type="submit" class="btn btn-success w-100">Add Animal</button>
        </form>
    </div>
</body>
</html>
