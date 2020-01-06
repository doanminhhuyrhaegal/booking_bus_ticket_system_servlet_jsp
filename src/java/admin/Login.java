package admin;

import function.CookieHandle;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
@WebServlet(urlPatterns = {"/admin.Login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        if(request.getParameter("usename").equals("admin") && request.getParameter("password").equals("admin")) {
            // Set true to cookie
            response.addCookie(CookieHandle.set("admin_login", "true", 60*60*24));
            // Direct to user_list
            response.sendRedirect(request.getContextPath() + "/admin/user_list.jsp");
        }
        else {
            // Set false to cookie
            response.addCookie(CookieHandle.set("admin_login", "false", 60*60*24));
            // Direct to user_list
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
