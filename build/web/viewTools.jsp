<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.bean.Tool" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Available Tools</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Available Tools</h1>

        <table class="table table-bordered table-striped mt-4">
            <thead class="table-dark">
                <tr>
                    <th>Tool Name</th>
                    <th>Tool Type</th>
                    <th>Availability</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Tool> tools = (List<Tool>) request.getAttribute("tools");
                    if (tools != null && !tools.isEmpty()) {
                        for (Tool tool : tools) { 
                %>
                <tr>
                    <td><%= tool.getToolName() %></td>
                    <td><%= tool.getToolType() %></td>
                    <td><%= tool.getAvailabilityStatus() %></td>
                    <td>
                        <form action="ToolBorrowServlet" method="post">
                            <input type="hidden" name="toolID" value="<%= tool.getToolID() %>">
                            <button type="submit" class="btn btn-primary btn-sm">Request Tool</button>
                        </form>
                    </td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="4" class="text-center">No tools available.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
