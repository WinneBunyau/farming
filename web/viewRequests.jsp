<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.bean.ToolBorrowRequest" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tool Borrow Requests</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Tool Borrow Requests</h1>
        <table class="table table-bordered table-striped mt-3">
            <thead class="table-success">
                <tr>
                    <th>Request ID</th>
                    <th>Farmer ID</th>
                    <th>Tool ID</th>
                    <th>Request Date</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<ToolBorrowRequest> requests = (List<ToolBorrowRequest>) request.getAttribute("requests");
                    if (requests != null && !requests.isEmpty()) {
                        for (ToolBorrowRequest req : requests) { 
                %>
                <tr>
                    <td><%= req.getToolBorrowRequestID() %></td>
                    <td><%= req.getFarmerID() %></td>
                    <td><%= req.getToolID() %></td>
                    <td><%= req.getRequestDate() %></td>
                    <td><%= req.getStatus() %></td>
                    <td>
                        <form action="AdminToolRequestServlet" method="post">
                            <input type="hidden" name="requestID" value="<%= req.getToolBorrowRequestID() %>">
                            <button type="submit" name="status" value="Approved" class="btn btn-success btn-sm">Approve</button>
                            <button type="submit" name="status" value="Rejected" class="btn btn-danger btn-sm">Reject</button>
                        </form>
                    </td>
                </tr>
                <% 
                        } 
                    } else { 
                %>
                <tr>
                    <td colspan="6" class="text-center">No pending requests.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <a href="adminDashboard.jsp" class="btn btn-outline-success mt-3">Back to Admin Dashboard</a>
    </div>
</body>
</html>
