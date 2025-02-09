<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-success">
        <div class="container">
            <a class="navbar-brand" href="#">Admin Dashboard</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="adminTools.jsp">Manage Tools</a></li>
                    <li class="nav-item"><a class="nav-link" href="viewRequests.jsp">Tool Requests</a></li>
                </ul>
                <form action="LogoutServlet" method="post" class="ms-auto">
                    <button type="submit" class="btn btn-danger">Logout</button>
                </form>
            </div>
        </div>
    </nav>
    <div class="container mt-5">
        <h1 class="text-center">Welcome, Admin!</h1>
        <p class="text-center">Manage tools and approve requests.</p>
        <div class="row">
            <div class="col-md-6">
                <div class="card text-white bg-primary mb-3">
                    <div class="card-body text-center">
                        <h5 class="card-title">Manage Tools</h5>
                        <a href="adminTools.jsp" class="btn btn-light">Go to Tools</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card text-white bg-success mb-3">
                    <div class="card-body text-center">
                        <h5 class="card-title">Tool Borrow Requests</h5>
                        <a href="viewRequests.jsp" class="btn btn-light">View Requests</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
