package fa.training.controller.Office;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.BookingOfficeDAO;
import fa.training.dao.EmployeeDAO;
import fa.training.dao.TripDAO;
import fa.training.entity.Employee;

/**
 * Servlet implementation class AddOfficeServlet
 */
public class AddOfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOfficeServlet() {
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
					if (!em.getDepartment().equalsIgnoreCase("parking")) {
						request.getRequestDispatcher("assets/view/accessdenied.jsp").forward(request, response);
						return;
					}
					request.setAttribute("user", em.getAccount());

					request.setAttribute("listPlace", new BookingOfficeDAO().getAllPlace());
					request.setAttribute("listTrip", new TripDAO().getListTrip());
					request.getRequestDispatcher("assets/view/bookingoffice_manager/addoffice.jsp").forward(request,
							response);
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
					if (!em.getDepartment().equalsIgnoreCase("parking")) {
						request.getRequestDispatcher("assets/view/accessdenied.jsp").forward(request, response);
						return;
					}
					String bookOfficeName = request.getParameter("bookofficename");
					int trip = Integer.parseInt(request.getParameter("trip"));
					String phone = request.getParameter("phone");
					String place = request.getParameter("place");
					int price = Integer.parseInt(request.getParameter("price"));
					Date fromDate = Date.valueOf(request.getParameter("fromdate"));
					Date toDate = Date.valueOf(request.getParameter("todate"));
					BookingOfficeDAO officedao = new BookingOfficeDAO();
					if (fromDate.before(toDate)) {
						officedao.addNew(toDate, bookOfficeName, phone, place, price, fromDate, trip);
						PrintWriter out = response.getWriter();
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Add successfully');");
						out.println("location='officelist';");
						out.println("</script>");
					} else {
						PrintWriter out = response.getWriter();
						out.println("<script type=\"text/javascript\">");
						out.println("alert('From date must be before to date ');");
						out.println("location='addoffice';");
						out.println("</script>");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
