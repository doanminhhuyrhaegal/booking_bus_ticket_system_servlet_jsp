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
@WebServlet(urlPatterns = {"/admin.UserCreate"})
public class UserCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Set cookies: username, password, repeatpassword, name, phone, email, role
        response.addCookie(CookieHandle.set("admin_user_create_username", request.getParameter("username"), 60));
        response.addCookie(CookieHandle.set("admin_user_create_password", request.getParameter("password"), 60));
        response.addCookie(CookieHandle.set("admin_user_create_repeatpassword", request.getParameter("repeatpassword"), 60));
        response.addCookie(CookieHandle.set("admin_user_create_name", request.getParameter("name"), 60));
        response.addCookie(CookieHandle.set("admin_user_create_phone",request.getParameter("phone"), 60));
        response.addCookie(CookieHandle.set("admin_user_create_email", request.getParameter("email"), 60));
        response.addCookie(CookieHandle.set("admin_user_create_role", request.getParameter("role"), 60));
        
        switch(request.getParameter("edit")) {
            case "Save":
                UserDAO userDAO = new UserDAO();
                // Get user from username
                User user = userDAO.get(request.getParameter("username"));
                if(user!=null) {
                    // Set alert to cookie if username already exist
                    response.addCookie(CookieHandle.set("admin_user_create_alert", "Username already exist", 60));
                    // Direct to user_create
                    response.sendRedirect(request.getContextPath() + "/admin/user_create.jsp");
                    break;
                }

                if(!request.getParameter("password").equals(request.getParameter("repeatpassword"))) {
                    // Set alert to cookie if password and repeatpassword don't match
                    response.addCookie(CookieHandle.set("admin_user_create_alert", "Password and Repeat Password don't match", 60));
                    // Direct to user_create
                    response.sendRedirect(request.getContextPath() + "/admin/user_create.jsp");
                    break;
                }

                boolean role = false;
                if(request.getParameter("role").equals("seller")) role = true;
                
                if(userDAO.add(request.getParameter("username"), request.getParameter("password"), request.getParameter("name"), request.getParameter("phone"), request.getParameter("email"), role)) {
                    // Set alert to cookie if susscess
                    response.addCookie(CookieHandle.set("admin_user_list_alert", "Saved successfully", 60));
                    // Direct to user_list
                    response.sendRedirect(request.getContextPath() + "/admin/user_list.jsp");
                }
                else {
                    // Set alert to cookie if fail
                    response.addCookie(CookieHandle.set("admin_user_create_alert", "Saved failed", 60));
                    // Direct to user_create
                    response.sendRedirect(request.getContextPath() + "/admin/user_create.jsp");
                }
                
                break;
                
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
