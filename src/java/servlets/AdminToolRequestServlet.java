package servlets;

import dao.ToolBorrowRequestDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AdminToolRequestServlet")
public class AdminToolRequestServlet extends HttpServlet {
    private ToolBorrowRequestDAO toolBorrowRequestDAO;
    private static final Logger LOGGER = Logger.getLogger(AdminToolRequestServlet.class.getName());

    @Override
    public void init() {
        toolBorrowRequestDAO = new ToolBorrowRequestDAO(); // Initialize DAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer adminID = (Integer) session.getAttribute("adminID");

        if (adminID == null) {
            response.sendRedirect("adminLogin.jsp?error=NotLoggedIn");
            return;
        }

        // Get parameters safely
        String requestIDParam = request.getParameter("requestID");
        String status = request.getParameter("status"); // Expected: "Approved" or "Rejected"

        if (requestIDParam == null || status == null || (!status.equals("Approved") && !status.equals("Rejected"))) {
            response.sendRedirect("viewRequests.jsp?error=InvalidParameters");
            return;
        }

        try {
            int requestID = Integer.parseInt(requestIDParam);

            boolean isUpdated = toolBorrowRequestDAO.updateRequestStatus(requestID, status, adminID);

            if (isUpdated) {
                response.sendRedirect("viewRequests.jsp?success=1");
            } else {
                response.sendRedirect("viewRequests.jsp?error=UpdateFailed");
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid requestID: " + requestIDParam, e);
            response.sendRedirect("viewRequests.jsp?error=InvalidID");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating request status", e);
            response.sendRedirect("viewRequests.jsp?error=ServerError");
        }
    }
}
