package admin;

import dao.UserDAO;
import function.CookieHandle;
import java.io.IOException;
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
@WebServlet(urlPatterns = {"/admin.UserEditInfo"})
public class UserEditInfo extends HttpServlet {

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
                
                if(new UserDAO().editInfo(user.getUsername(),request.getParameter("name"),request.getParameter("phone"),request.getParameter("email"))) {
                    // Set alert to cookie
                    response.addCookie(CookieHandle.set("admin_user_list_alert", "Saved successfully", 60));
                    // Direct to user_list
                    response.sendRedirect(request.getContextPath() + "/admin/user_list.jsp");
                }
                else {
                    // Set alert to cookie
                    response.addCookie(CookieHandle.set("admin_user_editinfo_alert", "Saved failed", 60));
                    // Direct to user_editinfo
                    response.sendRedirect(request.getContextPath() + "/admin/user_editinfo.jsp");
                }
                
                break;
                
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
