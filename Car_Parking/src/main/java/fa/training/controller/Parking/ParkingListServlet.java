package fa.training.controller.Parking;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.EmployeeDAO;
import fa.training.dao.ParkingDAO;
import fa.training.entity.Employee;
import fa.training.entity.Parking;

/**
 * Servlet implementation class ParkingListServlet
 */
public class ParkingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ParkingListServlet() {
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
		ParkingDAO d = new ParkingDAO();
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
					index = Integer.parseInt(pageIndex);
					int pageSize = 4;
					int totalPage = d.getCountPark();
					int maxPage = totalPage / pageSize;
					if (totalPage % pageSize != 0) {
						maxPage++;
					}
					request.setAttribute("pageCurrentIndex", 1);
					ArrayList<Parking> list = d.getListPark(index, pageSize);
					request.setAttribute("maxPage", maxPage);
					request.setAttribute("pageIndex", index);
					request.setAttribute("list", list);
					request.getRequestDispatcher("assets/view/parking_manager/parkinglist.jsp").forward(request,
							response);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
