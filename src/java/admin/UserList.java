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
@WebServlet(urlPatterns = {"/admin.UserList"})
public class UserList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        switch(request.getParameter("edit")) {
            case "Change role":
                // Set username to cookie
                response.addCookie(CookieHandle.set("admin_user_changerole", request.getParameter("username"), 60));
                // Direct to user_changerole
                response.sendRedirect(request.getContextPath() + "/admin/user_changerole.jsp");
                break;
                
            case "Change password":
                // Set username to cookie
                response.addCookie(CookieHandle.set("admin_user_changepassword", request.getParameter("username"), 60));
                // Direct to user_changepassword
                response.sendRedirect(request.getContextPath() + "/admin/user_changepassword.jsp");
                break;
                
            case "Edit info":
                // Set username to cookie
                response.addCookie(CookieHandle.set("admin_user_editinfo", request.getParameter("username"), 60));
                // Direct to user_editinfo
                response.sendRedirect(request.getContextPath() + "/admin/user_editinfo.jsp");
                break;
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
