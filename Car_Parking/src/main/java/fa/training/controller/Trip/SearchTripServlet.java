package fa.training.controller.Trip;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * Servlet implementation class SearchTripServlet
 */
public class SearchTripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchTripServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	EmployeeDAO employeeDAO = new EmployeeDAO();

	TripDAO tripDAO = new TripDAO();

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

					String pageIndex = request.getParameter("pageIndex");
					String search = request.getParameter("esearch");
					String datefrom = request.getParameter("edate");
					String dateto = request.getParameter("dateTo");
					Date date;
					Date dateTo;
					if (datefrom.isEmpty() || dateto.isEmpty()) {
						date = Date.valueOf(tripDAO.getMinDate());
						dateTo = Date.valueOf(tripDAO.getMaxDate());

					} else {
						java.util.Date d = null;
						java.util.Date d2 = null;
						try {

							d = new SimpleDateFormat("yyyy-MM-dd").parse(datefrom);
						} catch (ParseException e1) {
							request.setAttribute("error", e1);
							request.getRequestDispatcher("assets/view/error.jsp").forward(request, response);
						}
						try {
							d2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateto);
						} catch (ParseException e1) {
							request.setAttribute("error", e1);
							request.getRequestDispatcher("assets/view/error.jsp").forward(request, response);
						}
						date = new java.sql.Date(d.getTime());
						dateTo = new java.sql.Date(d2.getTime());
						if (dateTo.before(date)) {
							Date temp = date;
							date = dateTo;
							dateTo = temp;
						}
					}
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
					int totalPage = 0, maxSearchPage = 0;
					List<Trip> list = new ArrayList<>();

					totalPage = tripDAO.getCountTripSearch(search, date, dateTo);
					maxSearchPage = totalPage / pageSize;
					if (totalPage % pageSize != 0) {
						maxSearchPage++;
					}

					list = tripDAO.getListTripSearch(search, date, dateTo, index, pageSize);
					request.setAttribute("pageSearchCurrentIndex", pageIndex);
					request.setAttribute("maxSearchPage", maxSearchPage);

					PrintWriter out = response.getWriter();
					int count = (index - 1) * pageSize;
					if (list.isEmpty()) {
						out.println("<h5>No matches</h5>");
					} else {

						out.print("<table class=\"table table-bordered\">\r\n" + "					<thead>\r\n"
								+ "						<tr>\r\n" + "							<th>No</th>\r\n"
								+ "							<th>Destination</th>\r\n"
								+ "							<th>Departure time</th>\r\n"
								+ "							<th>Driver</th>\r\n"
								+ "							<th>Car type</th>\r\n"
								+ "							<th>Booked ticket number</th>\r\n"
								+ "							<th>Action</th>\r\n" + "						</tr>\r\n"
								+ "					</thead>\r\n" + "						<tbody>");
						for (Trip trip : list) {
							out.print("<input type=\"hidden\" name=\"searchValue\" id=\"searchValue\"\r\n"
									+ "									value=\"" + search + "\">\r\n"
									+ "								<input type=\"hidden\" name=\"dateFrom\" id=\"dateFrom\"\r\n"
									+ "									" + date + "\">\r\n"
									+ "								<input type=\"hidden\" name=\"dateTo\" id=\"dateTo\"\r\n"
									+ "									value=\"" + dateTo + "	\">	\r\n"
									+ "								<input type=\"hidden\" name=\"pageSearchCurrentIndex\" id=\"pageSearchCurrentIndex\"\r\n"
									+ "									value=\"" + pageIndex + "	\">	\r\n"
									+ "								<tr>\r\n"
									+ "									<td>" + (++count) + "</td>\r\n"
									+ "									<td>" + trip.getDestination() + "</td>\r\n"
									+ "									<td>" + trip.getDepartureTime() + "</td>\r\n"
									+ "									<td>" + trip.getDriver() + "</td>\r\n"
									+ "									<td>" + trip.getCarType() + "</td>\r\n"
									+ "									<td>" + trip.getBookedTicketNumber()
									+ "</td>\r\n" + "									<td>\r\n"
									+ "										<a href=\"tripdetails?id="
									+ trip.getTripID() + "\">\r\n"
									+ "											<i class=\"fa fa-search\" aria-hidden=\"true\"></i>&nbsp;Edit</a>&ensp;\r\n"
									+ "										<a href=\"deletetrip?id=" + trip.getTripID()
									+ "\"\r\n"
									+ "											onclick=\"return confirm('Are you sure you want to delete this item?');\">\r\n"
									+ "											<i class=\"fa fa-times\" aria-hidden=\"true\"></i>&nbsp;Detele</a>\r\n"
									+ "									</td>\r\n"
									+ "								</tr>");
						}
						out.print("	</tbody>\r\n" + "					</table>");
						out.print("<nav aria-label=\"paging\">\r\n"
								+ "						<input type=\"hidden\" name=\"maxSearchPage\" id=\"maxSearchPage\" value=\""
								+ maxSearchPage + "\">\r\n" + "						<ul class=\"pagination\">\r\n"
								+ "							<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"btnSearchPreviousTrip()\">Previous</a></li>");
						for (int i = 1; i <= maxSearchPage; i++) {
							out.print("<li class=\"page-item\" aria-current=\"page\">\r\n"
									+ "										<a class=\"page-link\" id=\"currentSearchPage"
									+ i + "\"\r\n" + "										onclick=\"pagingTripSearch("
									+ i + ")\" href=\"#\">" + i + "</a>\r\n"
									+ "									</li>");
						}
						out.print(
								"<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"btnSearchNextTrip()\">Next</a></li>\r\n"
										+ "						</ul>\r\n" + "					</nav>");
					}

				}
			}
		} catch (Exception e) {
			request.setAttribute("error", e);
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