import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Invalidate the current session
        HttpSession session = request.getSession(false); // Avoid creating a new session
        if (session != null) {
            session.invalidate();
        }

        // Redirect to the index.jsp page with a logout confirmation
        response.sendRedirect("index.jsp?logout=true");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Handle GET requests if necessary (optional)
        doPost(request, response); // Forward to doPost for simplicity
    }
}
