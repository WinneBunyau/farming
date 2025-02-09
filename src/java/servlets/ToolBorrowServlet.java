package servlets;

import dao.ToolBorrowRequestDAO;
import dao.ToolDAO; // ✅ Added for updating tool availability
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ToolBorrowServlet")
public class ToolBorrowServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer farmerID = (Integer) session.getAttribute("farmerID");

        if (farmerID == null) {
            response.sendRedirect("login.jsp?error=NotLoggedIn");
            return;
        }

        try {
            int toolID = Integer.parseInt(request.getParameter("toolID"));

            ToolBorrowRequestDAO toolBorrowRequestDAO = new ToolBorrowRequestDAO();
            ToolDAO toolDAO = new ToolDAO(); // ✅ Added for updating tool availability

            // ✅ Request the tool
            if (toolBorrowRequestDAO.requestTool(farmerID, toolID)) {
                // ✅ Update tool availability (set to "Unavailable")
                toolDAO.updateToolAvailability(toolID, "Unavailable");

                response.sendRedirect("viewTools.jsp?success=1");
            } else {
                response.sendRedirect("viewTools.jsp?error=1");
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid tool ID: " + e.getMessage()); // ✅ Logging for debugging
            response.sendRedirect("viewTools.jsp?error=InvalidToolID");
        }
    }
}
