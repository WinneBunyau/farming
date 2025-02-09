<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Tools</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Manage Tools</h1>
        <form action="ToolServlet" method="post" class="mt-3">
            <input type="hidden" name="action" value="add">
            <input type="text" name="toolName" placeholder="Tool Name" required class="form-control mb-2">
            <input type="text" name="toolType" placeholder="Tool Type" required class="form-control mb-2">
            <select name="availabilityStatus" class="form-control mb-2">
                <option value="Available">Available</option>
                <option value="Unavailable">Unavailable</option>
            </select>
            <button type="submit" class="btn btn-success w-100">Add Tool</button>
        </form>
        <a href="adminDashboard.jsp" class="btn btn-outline-success mt-3">Back to Admin Dashboard</a>
    </div>
</body>
</html>
