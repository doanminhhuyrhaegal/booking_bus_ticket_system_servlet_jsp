package seller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SeatDAO;

/**
 * Servlet implementation class SeatList
 */
@WebServlet("/seller.SeatList")
public class SeatList extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeatList() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get all seat
        request.setAttribute("listseat", new SeatDAO().getAll());
        // Forward to seat_list
        Route.forward(request, response, Route.LIST_SEAT_AVAILABLE);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        String info = request.getParameter("info");
        // Filter seat
        request.setAttribute("listseat", new SeatDAO().filter(key, info, ""));
        // Forward to seat_list
        Route.forward(request, response, Route.LIST_SEAT_AVAILABLE);
    }

}
