package fa.training.controller.Trip;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.EmployeeDAO;
import fa.training.dao.TripDAO;
import fa.training.entity.Employee;

/**
 * Servlet implementation class DeleteTripServlet
 */
public class DeleteTripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteTripServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	EmployeeDAO employeeDAO = new EmployeeDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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

					TripDAO tripDAO = new TripDAO();

					int tripID = Integer.parseInt(request.getParameter("id"));
					int countTicket = tripDAO.getCountTicketByTripID(tripID);
					int countOffice = tripDAO.getCountOfficeByTripID(tripID);
					if (countTicket == 0 && countOffice == 0) {
						tripDAO.deleteTrip(tripID);
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Delete successfully');");
						out.println("location='triplist';");
						out.println("</script>");
					} else {
						out.println("<script type=\"text/javascript\">");
						out.println("location='triplist';");
						out.println("alert('Cannot delete this trip.');");
						out.println("</script>");
					}
				}
			}
		} catch (Exception e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher("assets/view/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}