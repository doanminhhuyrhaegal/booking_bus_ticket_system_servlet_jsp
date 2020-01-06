package customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SeatDAO;
import dao.TicketDAO;
import function.CookieHandle;

/**
 * Servlet implementation class TicketBooking
 */
@WebServlet("/customer.TicketBooking")
public class TicketBooking extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketBooking() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param request
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/customer/ticket_booking.jsp");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String action = request.getParameter("action");
            if(action.equalsIgnoreCase("Book Now")) {								
                SeatDAO seatDAO = new SeatDAO();
                TicketDAO ticketDAO = new TicketDAO();
                
                // Find empty seat
                String seat = seatDAO.booking();
                if(seat==null) {
                    // Set alert to cookie if no seat available
                    response.addCookie(CookieHandle.set("customer_ticket_list_alert", "No available seat", 60));
                    // Direct to ticket_booking
                    response.sendRedirect(request.getContextPath() + "/customer/ticket_booking.jsp");
                }														
                else {
                    if(ticketDAO.booking(seat, request.getParameter("username"))) {
                        // Set alert to cookie if booking success
                        response.addCookie(CookieHandle.set("customer_ticket_list_alert", "Booking successfully", 60));
                        // Direct to ticket_list
                        response.sendRedirect(request.getContextPath() + "/customer/ticket_list.jsp");
                    }
                    else {
                        // Set alert to cookie if booking fail
                        response.addCookie(CookieHandle.set("customer_ticket_list_alert", "Booking failed", 60));
                        // Direct to ticket_booking
                        response.sendRedirect(request.getContextPath() + "/customer/ticket_booking.jsp");
                    }
                }
            }
            
        }
        catch(Exception e) {
            
        }
        
    }

}
