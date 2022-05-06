package fa.training.controller.Trip;

import java.io.IOException;
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
 * Servlet implementation class TripListServlet
 */
public class TripListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TripListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	EmployeeDAO employeeDAO = new EmployeeDAO();
	TripDAO tripDAO = new TripDAO();

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
					} catch (Exception ex) {
						index = 1;
					}

					int pageSize = 4;
					int totalPage = tripDAO.getCountTrip();
					int maxPage = totalPage / pageSize;
					if (totalPage % pageSize != 0) {
						maxPage++;
					}
					request.setAttribute("pageTripCurrentIndex", 1);

					List<Trip> triplist = tripDAO.getListTripPage(index, pageSize);
					request.setAttribute("maxPage", maxPage);
					request.setAttribute("pageIndex", index);
					request.setAttribute("triplist", triplist);
					request.getRequestDispatcher("assets/view/trip_manager/triplist.jsp").forward(request, response);
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