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

/**
 * Servlet implementation class OfficeListServlet
 */
public class OfficeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OfficeListServlet() {
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

					String pageIndex = request.getParameter("pageIndex");
					if (pageIndex == null) {
						pageIndex = "1";
					}

					int index = 0;

					try {
						index = Integer.parseInt(pageIndex);
					} catch (Exception ex) {
						index = 1;
					}

					int pageSize = 4;
					int totalPage = new BookingOfficeDAO().getCountOffice();
					int maxPage = totalPage / pageSize;
					if (totalPage % pageSize != 0) {
						maxPage++;
					}
					request.setAttribute("pageOfficeCurrentIndex", 1);

					request.setAttribute("listBookingOffice", new BookingOfficeDAO().getAllOffice(index, pageSize));
					request.setAttribute("maxPage", maxPage);
					request.setAttribute("pageIndex", index);
					request.getRequestDispatcher("assets/view/bookingoffice_manager/officelist.jsp").forward(request,
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
