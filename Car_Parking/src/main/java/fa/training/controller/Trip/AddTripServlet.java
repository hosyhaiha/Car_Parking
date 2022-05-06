package fa.training.controller.Trip;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.EmployeeDAO;
import fa.training.dao.TripDAO;
import fa.training.entity.Employee;
import fa.training.entity.Trip;

/**
 * Servlet implementation class AddTripServlet
 */
public class AddTripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TripDAO tripDAO = new TripDAO();

	public AddTripServlet() {
		super();
	}

	EmployeeDAO employeeDAO = new EmployeeDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equalsIgnoreCase("userCookie")) {
					userCookie = cookies[i];
				}
			}
			if (userCookie == null) {
				response.sendRedirect("login");
			} else {
				Employee em = null;
				try {
					em = employeeDAO.getEmployeeByID(userCookie.getValue());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!em.getDepartment().equalsIgnoreCase("service")) {
					request.getRequestDispatcher("assets/view/accessdenied.jsp").forward(request, response);
					return;
				}
				request.setAttribute("user", em.getAccount());
		request.getRequestDispatcher("assets/view/trip_manager/addtrip.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Cookie[] cookies = request.getCookies();
			Cookie userCookie = null;
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equalsIgnoreCase("userCookie")) {
						userCookie = cookies[i];
					}
				}
				if (userCookie == null) {
					response.sendRedirect("login");
				} else {
					Employee em = employeeDAO.getEmployeeByID(userCookie.getValue());
					if (!em.getDepartment().equalsIgnoreCase("service")) {
						request.getRequestDispatcher("assets/view/accessdenied.jsp").forward(request, response);
						return;
					}
					request.setAttribute("user", em.getAccount());

					int bookedTicketNumber = 0;
					String carType = request.getParameter("cartype");
					String destination = request.getParameter("destination");
					String driver = request.getParameter("driver");
					int maximumOnlineTicketNumber = Integer.parseInt(request.getParameter("maxnumberticket"));

					java.util.Date d = new SimpleDateFormat("HH:mm", Locale.TAIWAN)
							.parse(request.getParameter("departuretime"));
					java.sql.Time departuretime = new java.sql.Time(d.getTime());

					java.util.Date date = new SimpleDateFormat("yyyy-MM-dd")
							.parse(request.getParameter("departuredate"));
					Date departuredate = new java.sql.Date(date.getTime());

					Trip trip = new Trip(maximumOnlineTicketNumber, bookedTicketNumber, carType, departuredate,
							departuretime, destination, driver, maximumOnlineTicketNumber);

					tripDAO.addTrip(trip);

					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Add successfully');");
					out.println("location='triplist';");
					out.println("</script>");

				}
			}
		} catch (Exception e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher("assets/view/error.jsp").forward(request, response);

		}
	}
}