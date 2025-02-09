package servlets;

import dao.ToolDAO;
import models.bean.Tool;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ToolServlet")
public class ToolServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ToolDAO toolDAO = new ToolDAO(); // ✅ Instantiate inside method
        List<Tool> tools = toolDAO.getAvailableTools();
        request.setAttribute("tools", tools);
        request.getRequestDispatcher("viewTools.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ToolDAO toolDAO = new ToolDAO(); // ✅ Instantiate inside method
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String toolName = request.getParameter("toolName");
            String toolType = request.getParameter("toolType");
            String availabilityStatus = request.getParameter("availabilityStatus");

            // ✅ Validation: Ensure non-null inputs
            if (toolName == null || toolType == null || availabilityStatus == null) {
                response.sendRedirect("ToolServlet?error=InvalidInput");
                return;
            }

            // ✅ Insert tool into the database
            if (toolDAO.addTool(toolName, toolType, availabilityStatus)) {
                response.sendRedirect("ToolServlet?success=1");
            } else {
                response.sendRedirect("ToolServlet?error=1");
            }
        }
        
    }
}
