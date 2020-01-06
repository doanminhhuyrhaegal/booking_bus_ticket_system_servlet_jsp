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
@WebServlet(urlPatterns = {"/admin.UserSearchAndFilter"})
public class UserSearchAndFilter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Set cookies: key, info, sort, role
        response.addCookie(CookieHandle.set("admin_user_list_key", request.getParameter("key"), 60*60*24));
        response.addCookie(CookieHandle.set("admin_user_list_info", request.getParameter("info"), 60*60*24));
        response.addCookie(CookieHandle.set("admin_user_list_sort", request.getParameter("sort"), 60*60*24));
        response.addCookie(CookieHandle.set("admin_user_list_role", request.getParameter("role"), 60*60*24));
        
        // Direct to user_list
        response.sendRedirect(request.getContextPath() + "/admin/user_list.jsp");
        
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
