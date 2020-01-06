package seller;

import dao.SeatDAO;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TicketDAO;

/**
 * Servlet implementation class TicketCancel
 */
@WebServlet("/seller.TicketCancel")
public class TicketCancel extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketCancel() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.valueOf(request.getParameter("id"));
        TicketDAO ticketDAO = new TicketDAO();
        
        // Set seat status to 0
        new SeatDAO().edit(ticketDAO.get(id).getSeat(),false);
        // Remove ticket
        ticketDAO.remove(id);
        // Forward to ticket_list
        Route.forward(request, response, Route.LIST_TICKET_BOOKED);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
