package fa.training.controller.Office;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.BookingOfficeDAO;
import fa.training.dao.EmployeeDAO;
import fa.training.entity.Employee;
import fa.training.entity.Office;

/**
 * Servlet implementation class DetailOfficeServlet
 */
public class DetailOfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailOfficeServlet() {
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

					int officeId = Integer.parseInt(request.getParameter("id"));
					Office office = new BookingOfficeDAO().getOfficeByID(officeId);
					request.setAttribute("officeName", office.getOfficeName());
					request.setAttribute("tripName", office.getTripName());
					request.setAttribute("officePhone", office.getOfficePhone());
					request.setAttribute("officePlace", office.getOfficePlace());
					request.setAttribute("officePrice", office.getOfficePrice());
					request.setAttribute("startContractDeadline", office.getStartContractDeadline());
					request.setAttribute("endContractDeadline", office.getEndContractDeadline());
					request.getRequestDispatcher("assets/view/bookingoffice_manager/officedetails.jsp").forward(request,
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
		doGet(request, response);
	}

}