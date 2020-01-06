package admin;

import function.CookieHandle;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenvo
 */
@WebServlet(urlPatterns = {"/admin.Logout"})
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Remove username in cookie
        response.addCookie(CookieHandle.set("admin_login", "", 0));
        // Direct to login
        response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
