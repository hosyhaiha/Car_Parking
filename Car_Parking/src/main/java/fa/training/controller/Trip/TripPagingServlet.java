package fa.training.controller.Trip;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class TripPagingServlet
 */
public class TripPagingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TripPagingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	EmployeeDAO employeeDAO = new EmployeeDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TripDAO tripDAO = new TripDAO();
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

					String pageIndex = request.getParameter("pageIndex");
					if (pageIndex == null) {
						pageIndex = "1";
					}
					int index = 0;

					try {
						index = Integer.parseInt(pageIndex);
					} catch (Exception e) {
						index = 1;
					}

					int pageSize = 4;
					List<Trip> list = tripDAO.getListTripPage(index, pageSize);
					request.setAttribute("pageTripCurrentIndex", pageIndex);
					PrintWriter out = response.getWriter();
					int count = (index - 1) * pageSize;
					for (Trip trip : list) {
						out.print("<input type=\"hidden\" name=\"pageTripCurrentIndex\" id=\"pageTripCurrentIndex\"\r\n"
								+ "									value=\"" + pageIndex + "\">\r\n"
								+ "								<tr>\r\n" + "									<th>"
								+ (++count) + "</th>\r\n" + "									<td>"
								+ trip.getDestination() + "</td>\r\n" + "									<td>"
								+ trip.getDepartureTime() + "</td>\r\n" + "									<td>"
								+ trip.getDriver() + "</td>\r\n" + "									<td>"
								+ trip.getCarType() + "</td>\r\n" + "									<td>"
								+ trip.getBookedTicketNumber() + "</td>\r\n"
								+ "									<td>\r\n"
								+ "										<a href=\"tripdetails?id=" + trip.getTripID()
								+ "\">\r\n"
								+ "											<i class=\"fa fa-search\" aria-hidden=\"true\"></i>&nbsp;Edit</a>&ensp;\r\n"
								+ "										<a href=\"deletetrip?id=" + trip.getTripID()
								+ "\"\r\n"
								+ "											onclick=\"return confirm('Are you sure you want to delete this item?');\">\r\n"
								+ "											<i class=\"fa fa-times\" aria-hidden=\"true\"></i>&nbsp;Detele</a>\r\n"
								+ "									</td>\r\n" + "								</tr>");
					}

				}
			}
		} catch (Exception e) {
			request.setAttribute("Error", e);
			request.getRequestDispatcher("assets/view/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}