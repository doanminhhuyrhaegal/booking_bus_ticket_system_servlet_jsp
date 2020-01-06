package admin;

import function.CookieHandle;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UserDAO;
import javax.servlet.http.Cookie;

/**
 *
 * @author Lenvo
 */
@WebServlet(urlPatterns = {"/admin.UserPages"})
public class UserPages extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Get current page
        int current = Integer.valueOf(request.getParameter("current"));
        // Get max page
        int l = 1;
        Cookie cookie = CookieHandle.find(request.getCookies(),"admin_user_list_length");
        if(cookie!=null) l = Integer.valueOf(cookie.getValue());
        
        String page = request.getParameter("page");
        switch(page) {
            case "<<":
                page = "1";
                break;
            case "<":
                if(current>1) --current;
                page = String.valueOf(current);
                break;
            case ">":
                if(current<l) ++current;
                page = String.valueOf(current);
                break;
            case ">>":
                page = String.valueOf(l);
                break;
            default:
                if(current<1) current = 1; else if(current>l) current = l;
                page = String.valueOf(current);
                break;
        }
        
        if(current<1) {
            current = 1;
            page = String.valueOf(current);
        }
        else if(current>l) {
            current = l;
            page = String.valueOf(current);
        }
        
        // Set page to cookie
        response.addCookie(CookieHandle.set("admin_user_list_page", page, 60*60*24));
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
