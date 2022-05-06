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
 * Servlet implementation class PagingSearchCarServlet
 */
public class PagingSearchCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PagingSearchCarServlet() {
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

					CarDAO d = new CarDAO();
					String pageIndex = request.getParameter("pageIndex");
					String search = request.getParameter("esearch");
					String select = request.getParameter("eselect");
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
					int totalPage = d.getCountCarSearch(select, search);
					int maxSearchPage = totalPage / pageSize;
					if (totalPage % pageSize != 0) {
						maxSearchPage++;
					}
					ArrayList<Car> list = d.getListCarSearch(select, search, index, pageSize);
					request.setAttribute("pageSearchCurrentIndex", pageIndex);
					request.setAttribute("maxSearchPage", maxSearchPage);
					////////////////////////////////////////////////////
					PrintWriter out = response.getWriter();
					if (list.isEmpty()) {
						out.print("No matches");
					} else {
						out.print("<table class=\"table table-bordered\">\r\n" + "							<thead>\r\n"
								+ "								<tr>\r\n"
								+ "									<th>License plate</th>\r\n"
								+ "									<th>Car type</th>\r\n"
								+ "									<th>Car color</th>\r\n"
								+ "									<th>Company</th>\r\n"
								+ "									<th>Parking lot</th>\r\n"
								+ "									<th>Action</th>\r\n"
								+ "								</tr>\r\n" + "							</thead>\r\n"
								+ "							<tbody id=\"abc\">");

						for (Car c : list) {
							out.print("<input type=\"hidden\" name=\"searchValue\" id =\"searchValue\" value=\""
									+ search + "\">"
									+ "<input type=\"hidden\" name=\"selectValue\" id =\"selectValue\" value=\""
									+ select + "\">"
									+ "<input type=\"hidden\" name=\"pageSearchCurrentIndex\" id =\"pageSearchCurrentIndex\" value=\""
									+ pageIndex + "\">	" + "<tr>\r\n" + "									<td>"
									+ c.getLicensePlate() + "</td>\r\n" + "									<td>"
									+ c.getCarType() + "</td>\r\n" + "									<td>"
									+ c.getCarColor() + "</td>\r\n" + "									<td>"
									+ c.getCompany() + "</td>\r\n" + "									<td>"
									+ c.getParkName() + "</td>\r\n" + "									<td><a\r\n"
									+ "										href=\"editcar?licensePlate="
									+ c.getLicensePlate() + "&carType=" + c.getCarType() + "&carColor="
									+ c.getCarColor() + "\r\n" + "										&company="
									+ c.getCompany() + "&packinglot=" + c.getParkID() + "\"><i\r\n"
									+ "											class=\"fa fa-edit\"></i>Edit</a> </span> <a\r\n"
									+ "										href=\"deletecar?licensePlate="
									+ c.getLicensePlate() + "\"\r\n"
									+ "										onclick=\"return confirm('Are you sure you want to delete this item?');\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i>Delete</a>\r\n"
									+ "									</td>\r\n"
									+ "								</tr>");
						}
						out.print("</tbody>\r\n" + "						</table>");
						out.print("<nav aria-label=\"paging\">\r\n"
								+ "							<input type=\"hidden\" name=\"maxSearchPage\" id=\"maxSearchPage\"\r\n"
								+ "								value=" + maxSearchPage + ">\r\n"
								+ "							<ul class=\"pagination\">\r\n"
								+ "								<li class=\"page-item\"><a class=\"page-link\"\r\n"
								+ "									onclick=\"btnSearchCarPrevious()\" href=\"#\">Previous</a></li>");
						for (int i = 1; i <= maxSearchPage; i++) {
							out.print("<li class=\"page-item\" aria-current=\"page\"><a\r\n"
									+ "										class=\"page-link\" id=\"currentSearchPage"
									+ i + "\"\r\n" + "onclick=\"pagingCarSearch(" + i + ")\""
									+ "										\" href=\"#\">" + i + "</a></li>");
						}
						out.print("<li class=\"page-item\"><a class=\"page-link\"\r\n"
								+ "									onclick=\"btnSearchCarNext()\" href=\"#\">Next</a></li>\r\n"
								+ "							</ul>\r\n" + "						</nav>");
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
