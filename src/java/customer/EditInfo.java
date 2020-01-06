package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import function.CookieHandle;

/**
 * Servlet implementation class EditInfo
 */
@WebServlet("/customer.EditInfo")
public class EditInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/customer/editinfo.jsp");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String action = request.getParameter("action");
            if(action.equalsIgnoreCase("btnChangeInfo")) {				
                String name = request.getParameter("name");
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String pass = request.getParameter("password");
                String phone = request.getParameter("phone");
                UserDAO userDAO = new UserDAO();
                
                if(userDAO.edit(username,pass,name,phone,email,false)) {
                    // Set alert to cookie if edit success
                    response.addCookie(CookieHandle.set("customer_editinfo_alert", "Saved successfully", 60));
                }
                else {
                    // Set alert to cookie if edit fail
                    response.addCookie(CookieHandle.set("customer_editinfo_alert", "Saved failed", 60));
                }
                // Direct to editinfo
                response.sendRedirect(request.getContextPath() + "/customer/editinfo.jsp");
            }
        }
        catch(Exception e) {
            
        }
    }

}
