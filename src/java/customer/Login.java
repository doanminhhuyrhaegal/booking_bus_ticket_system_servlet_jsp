package customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import function.CookieHandle;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/customer.Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String action = request.getParameter("action");
            if(action.equalsIgnoreCase("Login")) {
                UserDAO userDAO = new UserDAO();
                User user = userDAO.get(request.getParameter("username"));
                
                // Check if user exist and password is correct
                if(user!= null && user.getPassword().equals(request.getParameter("password"))) {
                    // Set username to cookie
                    response.addCookie(CookieHandle.set("user_login", user.getUsername(), 60*60*24));
                    // Direct to home
                    response.sendRedirect(request.getContextPath() + "/customer/home.jsp");
                }
                else {
                    // Set alert to cookie if login fail
                    response.addCookie(CookieHandle.set("customer_login_alert", "Login failed", 60));
                    // Direct to login
                    response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
                }
            }
        }
        catch(Exception e) {
            
        }
            
    }

}
