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
import model.Ticket;

/**
 * Servlet implementation class TicketList
 */
@WebServlet("/customer.TicketList")
public class TicketList extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketList() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/customer/ticket_list.jsp");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String action = request.getParameter("action");
            if(action.equalsIgnoreCase("Cancel")) {
                TicketDAO ticketDAO = new TicketDAO();
                SeatDAO seatDAO = new SeatDAO();
                String i = request.getParameter("iid");
                long num = Long.parseLong(i);
                Ticket ticket = ticketDAO.get(num);
                
                // Cancel ticket
                if(ticketDAO.cancel(num)) {
                    // Set seat status to false
                    seatDAO.edit(ticket.getSeat(),false);
                    // Set alert to cookie if success
                    response.addCookie(CookieHandle.set("customer_ticket_list_alert", "Saved successfully", 60));
                    // Direct to ticket_list
                    response.sendRedirect(request.getContextPath() + "/customer/ticket_list.jsp");
                }
                else {
                    // Set alert to cookie if fail
                    response.addCookie(CookieHandle.set("customer_ticket_booking_alert", "Saved failed", 60));
                    // Direct to ticket_booking
                    response.sendRedirect(request.getContextPath() + "/customer/ticket_booking.jsp");
                }
            }
            // Cancel all ticket
            else if(action.equalsIgnoreCase("Cancel All")) {
                if(new TicketDAO().clear(request.getParameter("customer"))) {
                    // Direct to ticket_list
                    response.sendRedirect(request.getContextPath() + "/customer/ticket_list.jsp");
                }
            }
        }
        catch(NumberFormatException | IOException e) {
            
        }
        
    }

}
