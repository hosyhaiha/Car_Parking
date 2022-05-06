package fa.training.controller.Car;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.CarDAO;
import fa.training.dao.EmployeeDAO;
import fa.training.entity.Car;
import fa.training.entity.Employee;

/**
 * Servlet implementation class PagingCarServlet
 */
public class PagingCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PagingCarServlet() {
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
					CarDAO d = new CarDAO();
					ArrayList<Car> list = d.getListCar(index, pageSize);
					request.setAttribute("pageCurrentIndex", pageIndex);
					PrintWriter out = response.getWriter();
					for (Car c : list) {
						out.print("<input type=\"hidden\" name=\"pageCurrentIndex\"\r\n"
								+ "										id=\"pageCurrentIndex\" value=\"" + pageIndex
								+ "\">" + "<tr>\r\n" + "									<td>" + c.getLicensePlate()
								+ "</td>\r\n" + "									<td>" + c.getCarType() + "</td>\r\n"
								+ "									<td>" + c.getCarColor() + "</td>\r\n"
								+ "									<td>" + c.getCompany() + "</td>\r\n"
								+ "									<td>" + c.getParkName() + "</td>\r\n"
								+ "									<td><a\r\n"
								+ "										href=\"editcar?licensePlate="
								+ c.getLicensePlate() + "&carType=" + c.getCarType() + "&carColor=" + c.getCarColor()
								+ "\r\n" + "										&company=" + c.getCompany()
								+ "&packinglot=" + c.getParkID() + "\"><i\r\n"
								+ "											class=\"fa fa-edit\"></i>Edit</a> </span> <a\r\n"
								+ "										href=\"deletecar?licensePlate="
								+ c.getLicensePlate() + "\"\r\n"
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
