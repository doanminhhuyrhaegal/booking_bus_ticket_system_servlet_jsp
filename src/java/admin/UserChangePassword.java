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
@WebServlet(urlPatterns = {"/admin.UserChangePassword"})
public class UserChangePassword extends HttpServlet {

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
                
                if(!user.getPassword().equals(request.getParameter("currentpassword"))) {
                    // Set alert to cookie if current password wrong
                    response.addCookie(CookieHandle.set("admin_user_changepassword_alert", "Current password is incorrect", 60));
                    // Direct to user_changepassword
                    response.sendRedirect(request.getContextPath() + "/admin/user_changepassword.jsp");
                    break;
                }

                if(!request.getParameter("newpassword").equals(request.getParameter("repeatpassword"))) {
                    // Set alert to cookie if new password and repeat password don't match
                    response.addCookie(CookieHandle.set("admin_user_changepassword_alert", "New password and Repeat password don't match", 60));
                    // Direct to user_changepassword
                    response.sendRedirect(request.getContextPath() + "/admin/user_changepassword.jsp");
                    break;
                }

                if(userDAO.changePassword(user.getUsername(),request.getParameter("newpassword"))) {
                    // Set alert to cookie if success
                    response.addCookie(CookieHandle.set("admin_user_list_alert", "Saved successfully", 60));
                    // Direct to user_changepassword
                    response.sendRedirect(request.getContextPath() + "/admin/user_list.jsp");
                }
                else {
                    // Set alert to cookie if fail
                    response.addCookie(CookieHandle.set("admin_user_changepassword_alert", "Saved failed", 60));
                    // Direct to user_changepassword
                    response.sendRedirect(request.getContextPath() + "/admin/user_changepassword.jsp");
                }
                
                break;
            
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
