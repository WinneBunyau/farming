<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.bean.Farm" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Farm Booking</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .farm-card {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 15px;
            margin-bottom: 20px;
            background-color: #f9f9f9;
            transition: 0.3s;
        }
        .farm-card:hover {
            background-color: #e0ffe0;
            transform: scale(1.02);
        }
        .book-btn {
            width: 100%;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Available Farms</h1>
        <div class="row">
            <% 
                List<Farm> farms = (List<Farm>) request.getAttribute("farms");
                if (farms != null && !farms.isEmpty()) {
                    for (Farm farm : farms) { 
            %>
            <div class="col-md-4">
                <div class="farm-card">
                    <h4><%= farm.getFarmName() %></h4>
                    <p><strong>Location:</strong> <%= farm.getFarmLocation() %></p>
                    <p><strong>Size:</strong> <%= farm.getFarmSize() %> acres</p>
                    <p><strong>Water Source:</strong> <%= farm.getWaterSource() %></p>
                    <form action="FarmServlet" method="post">
                        <input type="hidden" name="farm_id" value="<%= farm.getFarmId() %>">
                        <button type="submit" class="btn btn-success book-btn">Book Farm</button>
                    </form>
                </div>
            </div>
            <% 
                    } 
                } else { 
            %>
            <div class="col-12 text-center">
                <p class="text-danger">No available farms at the moment.</p>
            </div>
            <% } %>
        </div>
    </div>
</body>
</html>
