<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, javax.servlet.http.*, javax.servlet.*" %>
<%
    HttpSession adminSession = request.getSession(false);
    if (adminSession == null || adminSession.getAttribute("adminId") == null) {
        response.sendRedirect("adminLogin.jsp?error=Please login first");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <!-- Google Fonts -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,500,700">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: #ecf0f5;
            margin: 0;
            padding: 0;
        }
        /* Sidebar Styling */
        .sidebar {
            width: 260px;
            height: 100vh;
            position: fixed;
            background: #2c3e50;
            box-shadow: 2px 0 8px rgba(0, 0, 0, 0.15);
            padding-top: 20px;
        }
        .sidebar h2 {
            color: #ecf0f1;
            text-align: center;
            margin-bottom: 30px;
            font-weight: 500;
            font-size: 1.4rem;
        }
        .sidebar a {
            display: block;
            color: #bdc3c7;
            padding: 12px 20px;
            text-decoration: none;
            transition: background 0.3s, color 0.3s;
            font-size: 1rem;
        }
        .sidebar a:hover {
            background: #34495e;
            color: #fff;
        }
        .sidebar .dropdown-toggle {
            cursor: pointer;
        }
        .sidebar .dropdown-menu {
            background: #34495e;
            border: none;
            margin-left: 15px;
        }
        .sidebar .dropdown-item {
            color: #bdc3c7;
            padding: 10px 15px;
        }
        .sidebar .dropdown-item:hover {
            background: #3b5998;
            color: #fff;
        }
        .logout-link {
            margin: 20px;
            background: #e74c3c;
            text-align: center;
            border-radius: 4px;
            padding: 10px 0;
            color: #fff;
            text-decoration: none;
            transition: background 0.3s;
        }
        .logout-link:hover {
            background: #c0392b;
        }
        /* Main Content Styling */
        .content {
            margin-left: 280px;
            padding: 30px;
        }
        /* Premium Box Style */
        .box {
            background: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border: 1px solid #ddd;
            margin-bottom: 30px;
            overflow: hidden;
        }
        .box-header {
            background: linear-gradient(135deg, #f39c12, #d35400);
            padding: 15px 20px;
        }
        .box-header h3 {
            margin: 0;
            color: #fff;
            font-weight: 500;
        }
        .box-body {
            padding: 20px;
            color: #444;
            font-size: 1rem;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <a href="adminDashboard.jsp">Dashboard</a>
        <a href="farmerManagement.jsp">Farmer Management</a>
        <a href="adminFarmManagement.jsp">Farm Management</a>
        
        <!-- Dropdown for Animal Management -->
        <div class="dropdown">
            <a class="dropdown-toggle" data-bs-toggle="dropdown">Animal Management</a>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="adminAnimalManagement.jsp">Animal List</a></li>
                <li><a class="dropdown-item" href="adminAnimalTypeManagement.jsp">Animal Types</a></li>
                <li><a class="dropdown-item" href="adminAnimalProduceManagement.jsp">Animal Produce</a></li>
            </ul>
        </div>

        <!-- Dropdown for Crop Management -->
        <div class="dropdown">
            <a class="dropdown-toggle" data-bs-toggle="dropdown">Crop Management</a>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="adminCropManagement.jsp">Crop List</a></li>
                <li><a class="dropdown-item" href="adminCropTypeManagement.jsp">Crop Types</a></li>
                <li><a class="dropdown-item" href="adminCropProduceManagement.jsp">Crop Produce</a></li>
            </ul>
        </div>

        <a href="adminToolManagement.jsp">Tool Management</a>
        <a href="LogoutServlet" class="logout-link">Logout</a>
    </div>

    <div class="content">
        <div class="box">
            <div class="box-header">
                <h3>Welcome, Admin!</h3>
            </div>
            <div class="box-body">
                <p>Manage all farming-related entities from this dashboard.</p>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
