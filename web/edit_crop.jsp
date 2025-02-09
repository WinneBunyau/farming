<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.bean.Crop" %>
<%@ page import="java.util.List" %>
<%@ page import="models.bean.CropType" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Crop</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Edit Crop</h1>
        <%
            Crop crop = (Crop) request.getAttribute("crop");
            List<CropType> cropTypes = (List<CropType>) request.getAttribute("cropTypes");
            if (crop == null) {
        %>
        <p class="text-danger text-center">Crop not found!</p>
        <a href="CropServlet" class="btn btn-primary">Back</a>
        <%
            } else {
        %>
        <form action="CropServlet" method="post" class="mt-3">
            <input type="hidden" name="crop_id" value="<%= crop.getCropId() %>">
            <input type="hidden" name="action" value="update">

            <label>Crop Name:</label>
            <input type="text" name="crop_name" value="<%= crop.getCropName() %>" required class="form-control mb-2">

            <label>Select Crop Type:</label>
            <select name="crop_type_id" required class="form-control mb-2">
                <%
                    if (cropTypes != null) {
                        for (CropType type : cropTypes) {
                %>
                <option value="<%= type.getCropTypeId() %>" <%= (String.valueOf(type.getCropTypeId()).equals(crop.getCropType()) ? "selected" : "") %>>
                    <%= type.getCropTypeName() %>
                </option>
                <%
                        }
                    }
                %>
            </select>

            <label>Planting Date:</label>
            <input type="date" name="planting_date" value="<%= crop.getPlantingDate() %>" required class="form-control mb-2">

            <label>Harvest Date:</label>
            <input type="date" name="harvest_date" value="<%= crop.getHarvestDate() %>" required class="form-control mb-2">

            <label>Yield (kg):</label>
            <input type="number" name="yield" value="<%= crop.getYield() %>" required class="form-control mb-2">

            <button type="submit" class="btn btn-success w-100">Update Crop</button>
        </form>
        <a href="CropServlet" class="btn btn-secondary mt-3">Cancel</a>
        <%
            }
        %>
    </div>
</body>
</html>
