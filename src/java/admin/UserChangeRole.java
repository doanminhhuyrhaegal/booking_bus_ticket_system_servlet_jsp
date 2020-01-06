package admin;

import dao.UserDAO;
import function.CookieHandle;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Lenvo
 */
@WebServlet(urlPatterns = {"/admin.UserChangeRole"})
public class UserChangeRole extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        switch(request.getParameter("edit")) {
            case "Save":
                UserDAO userDAO = new UserDAO();
                // Get user from username
                User user = userDAO.get(request.getParameter("username"));
                
                // Set role
                boolean role = false;
                if(request.getParameter("role").equals("seller")) role = true;
                
                if(userDAO.changeRole(request.getParameter("username"), role)) {
                    // Set alert to cookie if susscess
                    response.addCookie(CookieHandle.set("admin_user_list_alert", "Saved successfully", 60));
                    // Direct to user_list
                    response.sendRedirect(request.getContextPath() + "/admin/user_list.jsp");
                }
                else {
                    // Set alert to cookie if susscess
                    response.addCookie(CookieHandle.set("admin_user_changerole_alert", "Saved failed", 60));
                    // Direct to user_changerole
                    response.sendRedirect(request.getContextPath() + "/admin/user_changerole.jsp");
                }
                
                break;
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
