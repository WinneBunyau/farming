<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.bean.Animal" %>
<%@ page import="java.util.List" %>
<%@ page import="models.bean.AnimalType" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Animal</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Edit Animal</h1>
        <%
            Animal animal = (Animal) request.getAttribute("animal");
            List<AnimalType> animalTypes = (List<AnimalType>) request.getAttribute("animalTypes");
            if (animal == null) {
        %>
        <p class="text-danger text-center">Animal not found!</p>
        <a href="AnimalServlet" class="btn btn-primary">Back</a>
        <%
            } else {
        %>
        <form action="AnimalServlet" method="post" class="mt-3">
            <input type="hidden" name="animal_id" value="<%= animal.getAnimalId() %>">
            <input type="hidden" name="action" value="update_animal">

            <label>Animal Name:</label>
            <input type="text" name="animal_name" value="<%= animal.getAnimalName() %>" required class="form-control mb-2">

            <label>Select Animal Type:</label>
            <select name="animal_type_id" required class="form-control mb-2">
                <%
                    if (animalTypes != null) {
                        for (AnimalType type : animalTypes) {
                %>
                <option value="<%= type.getAnimalTypeId() %>" <%= (type.getAnimalTypeId() == animal.getAnimalTypeId() ? "selected" : "") %>>
                    <%= type.getAnimalTypeName() %>
                </option>
                <%
                        }
                    }
                %>
            </select>

            <label>Birth Date:</label>
            <input type="date" name="birth_date" value="<%= animal.getBirthDate() %>" required class="form-control mb-2">

            <label>Weight (kg):</label>
            <input type="number" step="0.1" name="weight" value="<%= animal.getWeight() %>" required class="form-control mb-2">

            <button type="submit" class="btn btn-success w-100">Update Animal</button>
        </form>
        <a href="AnimalServlet" class="btn btn-secondary mt-3">Cancel</a>
        <%
            }
        %>
    </div>
</body>
</html>
