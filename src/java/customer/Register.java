package customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import function.CookieHandle;
import javax.servlet.http.Cookie;

/**
 * Servlet implementation class Register
 */
@WebServlet("/customer.Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/customer/register.jsp");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String action = request.getParameter("action");
            if(action.equalsIgnoreCase("Register")) {
                String user = request.getParameter("username");
                String pass = request.getParameter("password");
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                
                UserDAO userDAO = new UserDAO();
                // Check if user not exist
                if(userDAO.get(user)==null) {		
                    if(userDAO.add(user, pass, name, phone, email, false)) {
                        // Set alert to cookie if register success
                        response.addCookie(CookieHandle.set("customer_login_alert", "Register successfully", 60));
                        // Direct to login
                        response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
                    }
                    else {
                        // Set alert to cookie if register success
                        response.addCookie(CookieHandle.set("customer_register_alert", "Register failed", 60));
                        // Direct to register
                        response.sendRedirect(request.getContextPath() + "/customer/register.jsp");
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
    }

}
