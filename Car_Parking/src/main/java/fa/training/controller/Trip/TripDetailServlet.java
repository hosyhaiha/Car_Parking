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
 * Servlet implementation class TripDetailServlet
 */
public class TripDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TripDAO tripDAO = new TripDAO();

	public TripDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	EmployeeDAO employeeDAO = new EmployeeDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

					int tripID = Integer.parseInt(request.getParameter("id"));
					Trip trip = tripDAO.getTripByID(tripID);

					request.setAttribute("tripID", trip.getTripID());
					request.setAttribute("destination", trip.getDestination());
					request.setAttribute("driver", trip.getDriver());
					request.setAttribute("cartype", trip.getCarType());
					request.setAttribute("maxnumberticket", trip.getMaximumOnlineTicketNumber());
					request.setAttribute("bookedticketnumber", trip.getBookedTicketNumber());
					String time = new SimpleDateFormat("HH:mm", Locale.TAIWAN).format(trip.getDepartureTime());
					request.setAttribute("departuretime", time);
					String date = new SimpleDateFormat("yyyy-MM-dd").format(trip.getDepartureDate());
					request.setAttribute("departuredate", date);
					request.getRequestDispatcher("assets/view/trip_manager/tripdetails.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher("assets/view/error.jsp").forward(request, response);
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
					int tripID = Integer.parseInt(request.getParameter("tripID"));
					Trip trip = tripDAO.getTripByID(tripID);
					int bookedTicketNumber = Integer.parseInt(request.getParameter("bookedticketnumber"));
					String destination = request.getParameter("destination");
					String driver = request.getParameter("driver");
					int maximumOnlineTicketNumber = Integer.parseInt(request.getParameter("maxnumberticket"));
					String cartype = request.getParameter("cartype");

					java.util.Date d = new SimpleDateFormat("HH:mm", Locale.TAIWAN)
							.parse(request.getParameter("departuretime"));
					java.sql.Time departuretime = new java.sql.Time(d.getTime());

					String departureDate = request.getParameter("departuredate");
					java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);
					Date departuredate = new java.sql.Date(date.getTime());

					trip.setBookedTicketNumber(bookedTicketNumber);
					trip.setDestination(destination);
					trip.setDriver(driver);
					trip.setMaximumOnlineTicketNumber(maximumOnlineTicketNumber);
					trip.setCarType(cartype);
					trip.setDepartureDate(departuredate);
					trip.setDepartureTime(departuretime);
					tripDAO.updateTrip(trip);

					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Update successfully');");
					out.println("location='triplist';");
					out.println("</script>");

					// request.getRequestDispatcher("assets/view/trip_manager/triplist.jsp").forward(request,
					// response);

				}
			}
		} catch (Exception e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher("assets/view/error.jsp").forward(request, response);
		}
	}

}