<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="db.DBConnection" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Farmer Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Farmer Registration</h1>

        <%-- Display error messages if registration fails --%>
        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger"><%= request.getParameter("error") %></div>
        <% } %>

        <form action="RegisterServlet" method="post" class="w-50 mx-auto mt-3">
            <div class="mb-3">
                <label class="form-label">Full Name:</label>
                <input type="text" name="name" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Email:</label>
                <input type="email" name="email" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Password:</label>
                <input type="password" name="password" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Confirm Password:</label>
                <input type="password" name="confirm_password" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Phone Number:</label>
                <input type="text" name="phone" class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label">Farm Location:</label>
                <input type="text" name="farm_location" class="form-control">
            </div>
            <button type="submit" class="btn btn-success w-100">Register</button>
        </form>

        <div class="text-center mt-3">
            <a href="index.jsp" class="btn btn-outline-success">Back to Home</a>
            <p class="mt-2">Already have an account? <a href="login.jsp">Login here</a></p>
        </div>
    </div>
</body>
</html>
