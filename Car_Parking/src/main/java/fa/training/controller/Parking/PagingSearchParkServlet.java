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
 * Servlet implementation class PagingSearchParkServlet
 */
public class PagingSearchParkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PagingSearchParkServlet() {
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

					ParkingDAO d = new ParkingDAO();
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
					int totalPage = d.getCountParkSearch(select, search);
					int maxSearchPage = totalPage / pageSize;
					if (totalPage % pageSize != 0) {
						maxSearchPage++;
					}
					ArrayList<Parking> list = d.getListParkSearch(select, search, index, pageSize);
					request.setAttribute("pageSearchCurrentIndex", pageIndex);
					request.setAttribute("maxSearchPage", maxSearchPage);
					PrintWriter out = response.getWriter();
					if (list.isEmpty()) {
						out.print("No matches");
					} else {
						out.print("<table class=\"table table-bordered\">\r\n" + "							<thead>\r\n"
								+ "								<tr>\r\n"
								+ "									<th>ID</th>\r\n"
								+ "									<th>Parking lot</th>\r\n"
								+ "									<th>Place</th>\r\n"
								+ "									<th>Area</th>\r\n"
								+ "									<th>Price</th>\r\n"
								+ "									<th>Status</th>\r\n"
								+ "									<th>Action</th>\r\n"
								+ "								</tr>\r\n" + "							</thead>\r\n"
								+ "							<tbody id=\"abc\">");

						for (Parking p : list) {
							out.print("<input type=\"hidden\" name=\"searchValue\" id =\"searchValue\" value=\""
									+ search + "\">"
									+ "<input type=\"hidden\" name=\"selectValue\" id =\"selectValue\" value=\""
									+ select + "\">"
									+ "<input type=\"hidden\" name=\"pageSearchCurrentIndex\" id =\"pageSearchCurrentIndex\" value=\""
									+ pageIndex + "\">	" + "<tr>\r\n" + "									<td>"
									+ p.getParkID() + "</td>\r\n" + "									<td>"
									+ p.getParkName() + "</td>\r\n" + "									<td>"
									+ p.getParkPlace() + "</td>\r\n" + "									<td>"
									+ p.getParkArea() + "</td>\r\n" + "									<td>"
									+ p.getParkPrice() + "</td>\r\n" + "									<td>"
									+ p.getParkStatus() + "</td>\r\n" + "									<td><a\r\n"
									+ "										href=\"editparking?id=" + p.getParkID()
									+ "&parkName=" + p.getParkName() + "&parkPlace=" + p.getParkPlace() + "\r\n"
									+ "										&parkArea=" + p.getParkArea()
									+ "&parkPrice=" + p.getParkPrice() + "&parkStatus=" + p.getParkStatus()
									+ "\"><i\r\n"
									+ "											class=\"fa fa-edit\"></i>Edit</a> </span> <a\r\n"
									+ "										href=\"deleteparking?id=" + p.getParkID()
									+ "\"\r\n"
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
								+ "									onclick=\"btnSearchParkPrevious()\" href=\"#\">Previous</a></li>");
						for (int i = 1; i <= maxSearchPage; i++) {
							out.print("<li class=\"page-item\" aria-current=\"page\"><a\r\n"
									+ "										class=\"page-link\" id=\"currentSearchPage"
									+ i + "\"\r\n" + "onclick=\"pagingParkSearch(" + i + ")\""
									+ "										\" href=\"#\">" + i + "</a></li>");
						}
						out.print("<li class=\"page-item\"><a class=\"page-link\"\r\n"
								+ "									onclick=\"btnSearchParkNext()\" href=\"#\">Next</a></li>\r\n"
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
