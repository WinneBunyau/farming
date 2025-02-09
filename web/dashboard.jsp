<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Farmer Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-success">
        <div class="container">
            <a class="navbar-brand" href="#">Farmer Dashboard</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="farm.jsp">Farm</a></li>
                    <li class="nav-item"><a class="nav-link" href="CropServlet">Crops</a></li>
                    <li class="nav-item"><a class="nav-link" href="AnimalServlet">Animals</a></li>
                    <li class="nav-item"><a class="nav-link" href="ToolServlet">Tools</a></li>
                </ul>
            </div>
            <div class="d-flex">
                <!-- Logout Button -->
                <a href="LogoutServlet" class="btn btn-danger">Logout</a>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h1 class="text-center">Welcome to the Farmer Dashboard</h1>

        <!-- Optional Logout Success Message -->
        <% if (request.getParameter("logout") != null) { %>
            <div class="alert alert-success text-center mt-3">You have successfully logged out.</div>
        <% } %>

        <div class="row mt-4">
            <div class="col-md-3">
                <div class="card text-white bg-success mb-3">
                    <div class="card-body text-center">
                        <h5 class="card-title">Farm Management</h5>
                        <p>Book a farm and manage details.</p>
                        <a href="farm.jsp" class="btn btn-light">Go to Farms</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white bg-success mb-3">
                    <div class="card-body text-center">
                        <h5 class="card-title">Crop Management</h5>
                        <p>Manage the crops you are growing.</p>
                        <a href="CropServlet" class="btn btn-light">Go to Crops</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white bg-success mb-3">
                    <div class="card-body text-center">
                        <h5 class="card-title">Animal Management</h5>
                        <p>Manage livestock on your farm.</p>
                        <a href="AnimalServlet" class="btn btn-light">Go to Animals</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white bg-primary mb-3">
                    <div class="card-body text-center">
                        <h5 class="card-title">Tool Borrowing</h5>
                        <p>Request tools for farming.</p>
                        <a href="ToolServlet" class="btn btn-light">View Tools</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
