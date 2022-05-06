package fa.training.controller.Parking;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class PagingParkServlet
 */
public class PagingParkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PagingParkServlet() {
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
					} catch (Exception e) {
						index = 1;
					}
					int pageSize = 4;
					ParkingDAO d = new ParkingDAO();
					ArrayList<Parking> list = d.getListPark(index, pageSize);
					request.setAttribute("pageCurrentIndex", pageIndex);
					PrintWriter out = response.getWriter();
					for (Parking p : list) {
						out.print("<input type=\"hidden\" name=\"pageCurrentIndex\"\r\n"
								+ "										id=\"pageCurrentIndex\" value=\"" + pageIndex
								+ "\">" + "<tr>\r\n" + "									<td>" + p.getParkID()
								+ "</td>\r\n" + "									<td>" + p.getParkName()
								+ "</td>\r\n" + "									<td>" + p.getParkPlace()
								+ "</td>\r\n" + "									<td>" + p.getParkArea()
								+ "</td>\r\n" + "									<td>" + p.getParkPrice()
								+ "</td>\r\n" + "									<td>" + p.getParkStatus()
								+ "</td>\r\n" + "									<td><a\r\n"
								+ "										href=\"editparking?id=" + p.getParkID()
								+ "&parkName=" + p.getParkName() + "&parkPlace=" + p.getParkPlace() + "\r\n"
								+ "										&parkArea=" + p.getParkArea() + "&parkPrice="
								+ p.getParkPrice() + "&parkStatus=" + p.getParkStatus() + "\"><i\r\n"
								+ "											class=\"fa fa-edit\"></i>Edit</a> </span> <a\r\n"
								+ "										href=\"deleteparking?id=" + p.getParkID()
								+ "\"\r\n"
								+ "										onclick=\"return confirm('Are you sure you want to delete this item?');\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i>Delete</a>\r\n"
								+ "									</td>\r\n" + "								</tr>");
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
