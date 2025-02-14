<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, db.DBConnection" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Farm Management</title>
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
        .container {
            max-width: 1200px;
        }
        /* Premium Box Style */
        .box {
            background: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border: 1px solid #ddd;
            overflow: hidden;
            margin-bottom: 30px;
        }
        .box-header {
            background: linear-gradient(135deg, #f39c12, #d35400);
            padding: 15px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .box-header h3 {
            margin: 0;
            color: #fff;
            font-weight: 500;
            font-size: 1.5rem;
        }
        .box-header .btn {
            margin-left: 10px;
        }
        .box-body {
            padding: 20px;
            color: #444;
            font-size: 1rem;
        }
        /* Premium Table Style */
        .table {
            margin-bottom: 0;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="box">
            <div class="box-header">
                <h3>Admin Farm Management</h3>
                <div>
                    <a href="adminDashboard.jsp" class="btn btn-secondary">Go Back to Dashboard</a>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addFarmModal">Add Farm</button>
                </div>
            </div>
            <div class="box-body">
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Farm Name</th>
                            <th>Location</th>
                            <th>Size (acre)</th>
                            <th>Water Source</th>
                            <th>Rent Duration (/month)</th>
                            <th>Rent Amt (RM/month)</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                        Connection conn = null;
                        PreparedStatement ps = null;
                        ResultSet rs = null;
                        try {
                            conn = DBConnection.getConnection();
                            String sql = "SELECT * FROM Farms";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();
                            while (rs.next()) {
                        %>
                        <tr>
                            <td><%= rs.getInt("farmId") %></td>
                            <td><%= rs.getString("farm_name") %></td>
                            <td><%= rs.getString("farm_location") %></td>
                            <td><%= rs.getBigDecimal("farm_size") %></td>
                            <td><%= rs.getString("water_source") %></td>
                            <td><%= rs.getString("rent_duration") %></td>
                            <td><%= rs.getBigDecimal("rent_amt") %></td>
                            <td><%= rs.getString("status") %></td>
                            <td>
                                <button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editFarmModal<%= rs.getInt("farmId") %>">Edit</button>
                                <a href="DeleteFarmServlet?id=<%= rs.getInt("farmId") %>" class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                        <!-- Edit Farm Modal -->
                        <div class="modal fade" id="editFarmModal<%= rs.getInt("farmId") %>" tabindex="-1" aria-labelledby="editFarmModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Edit Farm</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="UpdateFarmServlet" method="post">
                                            <input type="hidden" name="farmId" value="<%= rs.getInt("farmId") %>">
                                            <div class="mb-3">
                                                <label class="form-label">Farm Name</label>
                                                <input type="text" class="form-control" name="farm_name" value="<%= rs.getString("farm_name") %>" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Location</label>
                                                <input type="text" class="form-control" name="farm_location" value="<%= rs.getString("farm_location") %>" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Size (acre)</label>
                                                <input type="number" step="0.01" class="form-control" name="farm_size" value="<%= rs.getBigDecimal("farm_size") %>" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Water Source</label>
                                                <input type="text" class="form-control" name="water_source" value="<%= rs.getString("water_source") %>">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Rent Duration (/month)</label>
                                                <input type="text" class="form-control" name="rent_duration" value="<%= rs.getString("rent_duration") %>">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Rent Amt (RM/month)</label>
                                                <input type="number" step="0.01" class="form-control" name="rent_amt" value="<%= rs.getBigDecimal("rent_amt") %>">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Status</label>
                                                <select class="form-control" name="status" required>
                                                    <option value="available" <%= "available".equals(rs.getString("status")) ? "selected" : "" %>>Available</option>
                                                    <option value="rented" <%= "rented".equals(rs.getString("status")) ? "selected" : "" %>>Rented</option>
                                                </select>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Update Farm</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% 
                            } 
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if(rs != null) try { rs.close(); } catch(Exception e) {}
                            if(ps != null) try { ps.close(); } catch(Exception e) {}
                            if(conn != null) try { conn.close(); } catch(Exception e) {}
                        }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Add Farm Modal -->
    <div class="modal fade" id="addFarmModal" tabindex="-1" aria-labelledby="addFarmModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Add Farm</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="AddFarmServlet" method="post">
                        <div class="mb-3">
                            <label class="form-label">Farm Name</label>
                            <input type="text" class="form-control" name="farm_name" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Location</label>
                            <input type="text" class="form-control" name="farm_location" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Size (acre)</label>
                            <input type="number" step="0.01" class="form-control" name="farm_size" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Water Source</label>
                            <input type="text" class="form-control" name="water_source">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Rent Duration (/month) </label>
                            <input type="text" class="form-control" name="rent_duration">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Rent Amt (RM/month)</label>
                            <input type="number" step="0.01" class="form-control" name="rent_amt">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Status</label>
                            <select class="form-control" name="status" required>
                                <option value="available">Available</option>
                                <option value="rented">Rented</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Add Farm</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
