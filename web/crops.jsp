<!-- crops.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.bean.Crop" %>
<%@ page import="models.bean.CropType" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Crops</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Manage Crops</h1>
        <table class="table table-bordered mt-3">
            <thead class="table-success">
                <tr>
                    <th>Crop Name</th>
                    <th>Crop Type</th>
                    <th>Planting Date</th>
                    <th>Harvest Date</th>
                    <th>Yield</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Crop> crops = (List<Crop>) request.getAttribute("crops");
                    if (crops != null && !crops.isEmpty()) {
                        for (Crop crop : crops) { 
                %>
                <tr>
                    <td><%= crop.getCropName() %></td>
                    <td><%= crop.getCropType() %></td>
                    <td><%= crop.getPlantingDate() %></td>
                    <td><%= crop.getHarvestDate() %></td>
                    <td><%= crop.getYield() %> kg</td>
                   <td>
    <a href="CropProduceServlet?crop_id=<%= crop.getCropId() %>" class="btn btn-success">Produce</a>
    <a href="EditCropServlet?crop_id=<%= crop.getCropId() %>" class="btn btn-warning">Edit</a>
    <form action="CropServlet" method="post" style="display:inline;">
        <input type="hidden" name="crop_id" value="<%= crop.getCropId() %>">
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
                    <td colspan="6" class="text-center">No crops added yet.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <h3>Add New Crop</h3>
        <form action="CropServlet" method="post" class="mt-3">
            <input type="text" name="crop_name" placeholder="Crop Name" required class="form-control mb-2">
            
            <label>Select Crop Type:</label>
            <select name="crop_type_id" required class="form-control mb-2">
                <% 
                    List<CropType> cropTypes = (List<CropType>) request.getAttribute("cropTypes");
                    if (cropTypes != null && !cropTypes.isEmpty()) {
                        for (CropType type : cropTypes) {
                %>
                    <option value="<%= type.getCropTypeId() %>"><%= type.getCropTypeName() %></option>
                <% 
                        }
                    } else { 
                %>
                    <option value="">No Crop Types Available</option>
                <% } %>
            </select>
            
            <input type="date" name="planting_date" required class="form-control mb-2">
            <input type="date" name="harvest_date" required class="form-control mb-2">
            <input type="number" name="yield" placeholder="Yield (kg)" required class="form-control mb-2">
            <button type="submit" class="btn btn-success w-100">Add Crop</button>
        </form>
    </div>
</body>
</html>
