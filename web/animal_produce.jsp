<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.bean.AnimalProduce" %>
<%@ page import="models.bean.Animal" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Animal Produce</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Manage Animal Produce</h1>

        <h3>Add New Animal Produce</h3>
        <form action="AnimalProduceServlet" method="post" class="mt-3">
            <input type="hidden" name="action" value="add">

            <label>Select Animal:</label>
            <select name="animal_id" required class="form-control mb-2">
                <% 
                    List<Animal> animals = (List<Animal>) request.getAttribute("animals");
                    if (animals != null && !animals.isEmpty()) {
                        for (Animal animal : animals) {
                %>
                    <option value="<%= animal.getAnimalId() %>"><%= animal.getAnimalName() %></option>
                <% 
                        }
                    } else { 
                %>
                    <option value="">No Animals Available</option>
                <% } %>
            </select>

            <label>Quantity (kg/liters):</label>
            <input type="number" step="0.1" name="quantity" placeholder="Enter quantity" required class="form-control mb-2">

            <label>Storage Location:</label>
            <input type="text" name="animal_storage_location" placeholder="Enter storage location" class="form-control mb-2">

            <button type="submit" class="btn btn-success w-100">Add Produce</button>
        </form>

        <h3 class="mt-4">Your Animal Produce Records</h3>
        <table class="table table-bordered mt-3">
            <thead class="table-success">
                <tr>
                    <th>Animal ID</th>
                    <th>Quantity (kg/liters)</th>
                    <th>Production Date</th>
                    <th>Storage Location</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<AnimalProduce> animalProduces = (List<AnimalProduce>) request.getAttribute("animalProduces");
                    if (animalProduces != null && !animalProduces.isEmpty()) {
                        for (AnimalProduce ap : animalProduces) { 
                %>
                <tr>
                    <td><%= ap.getAnimalId() %></td>
                    <td><%= ap.getQuantity() %></td>
                    <td><%= ap.getAnimalProduceDate() %></td>
                    <td><%= ap.getAnimalStorageLocation() %></td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="4" class="text-center">No animal produce records yet.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <a href="dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
    </div>
</body>
</html>
