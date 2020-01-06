package seller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TicketDAO;

/**
 * Servlet implementation class TicketList
 */
@WebServlet("/seller.TicketList")
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
        // Get all ticket
        request.setAttribute("listticket", new TicketDAO().getAll());
        // Forward to ticket_list
        Route.forward(request, response, Route.LIST_TICKET_BOOKED);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        String info = request.getParameter("ticketfilter");
        // Filter ticket
        request.setAttribute("listticket", new TicketDAO().filter(key, info, ""));
        // Forward to ticket_lists
        Route.forward(request, response, Route.LIST_TICKET_BOOKED);
    }

}
