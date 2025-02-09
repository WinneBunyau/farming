<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.bean.CropProduce" %>
<%@ page import="models.bean.Crop" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Crop Produce</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Manage Crop Produce</h1>

        <h3>Add New Crop Produce</h3>
        <form action="CropProduceServlet" method="post" class="mt-3">
            <input type="hidden" name="action" value="add">

            <label>Select Crop:</label>
            <select name="crop_id" required class="form-control mb-2">
                <% 
                    List<Crop> crops = (List<Crop>) request.getAttribute("crops");
                    if (crops != null && !crops.isEmpty()) {
                        for (Crop crop : crops) {
                %>
                    <option value="<%= crop.getCropId() %>"><%= crop.getCropName() %></option>
                <% 
                        }
                    } else { 
                %>
                    <option value="">No Crops Available</option>
                <% } %>
            </select>

            <label>Quantity (kg):</label>
            <input type="number" step="0.1" name="quantity" placeholder="Enter quantity" required class="form-control mb-2">

            <label>Storage Location:</label>
            <input type="text" name="crop_storage_location" placeholder="Enter storage location" class="form-control mb-2">

            <button type="submit" class="btn btn-success w-100">Add Produce</button>
        </form>

        <h3 class="mt-4">Your Crop Produce Records</h3>
        <table class="table table-bordered mt-3">
            <thead class="table-success">
                <tr>
                    <th>Crop ID</th>
                    <th>Quantity (kg)</th>
                    <th>Production Date</th>
                    <th>Storage Location</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<CropProduce> cropProduces = (List<CropProduce>) request.getAttribute("cropProduces");
                    if (cropProduces != null && !cropProduces.isEmpty()) {
                        for (CropProduce cp : cropProduces) { 
                %>
                <tr>
                    <td><%= cp.getCropId() %></td>
                    <td><%= cp.getQuantity() %></td>
                    <td><%= cp.getCropProduceDate() %></td>
                    <td><%= cp.getCropStorageLocation() %></td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="4" class="text-center">No crop produce records yet.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <a href="dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
    </div>
</body>
</html>
