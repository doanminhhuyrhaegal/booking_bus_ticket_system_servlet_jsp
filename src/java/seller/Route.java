package seller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Route {
    public static final String LIST_TICKET_BOOKED = "/seller/ticket_list.jsp"; 
    public static final String LIST_TICKET_UPDATE ="WEB-INF/views/updateticket.jsp"; 
    public static final String LIST_SEAT_AVAILABLE ="/seller/seat_list.jsp";
    public static final String LIST_FILTER ="WEB-INF/views/listfilter.jsp";
    
    public static void forward(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        request.getRequestDispatcher(route).forward(request, response);
    }
}
