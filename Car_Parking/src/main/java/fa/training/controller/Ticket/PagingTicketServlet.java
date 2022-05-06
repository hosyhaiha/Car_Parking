package fa.training.controller.Ticket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.EmployeeDAO;
import fa.training.dao.TicketDAO;
import fa.training.entity.Employee;
import fa.training.entity.Ticket;

/**
 * Servlet implementation class PagingTicketServlet
 */
public class PagingTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PagingTicketServlet() {
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
		// TODO Auto-generated method stub
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
					TicketDAO ticketDAO = new TicketDAO();
					List<Ticket> list = ticketDAO.getAllTicket(index, pageSize);
					request.setAttribute("pageCurrentIndex", pageIndex);
					PrintWriter out = response.getWriter();
					int count = (index - 1) * pageSize;
					for (Ticket ticket : list) {
						out.print("<input type=\"hidden\" name=\"pageCurrentIndex\"\r\n"
								+ "id=\"pageCurrentIndex\" value=\"" + pageIndex + "\">" + "<tr>\r\n"
								+ "									<td>" + ++count + "</td>\r\n"
								+ "									<td>" + ticket.getTripName() + "</td>\r\n"
								+ "									<td>" + ticket.getLicensePlate() + "</td>\r\n"
								+ "									<td>" + ticket.getCustomerName() + "</td>\r\n"
								+ "									<td>" + ticket.getBookingTime() + "</td>\r\n"
								+ "									<td><a href=\"deleteticket?id="
								+ ticket.getTicketID() + "\"onclick=\"return confirm('Are you sure you want to delete this item?');\"><i\r\n"
										+ "												class=\"fa fa-trash\" aria-hidden=\"true\"></i>Delete</a> " + " <a href=\"detailticket?id="
								+ ticket.getTicketID() + "\">View</a></td>" + "</tr>");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
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
