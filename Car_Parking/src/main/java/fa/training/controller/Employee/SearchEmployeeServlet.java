package fa.training.controller.Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.EmployeeDAO;
import fa.training.entity.Employee;

/**
 * Servlet implementation class SearchEmployeeServlet
 */
public class SearchEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchEmployeeServlet() {
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
		response.setContentType("text/html;charset=UTF-8");
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
					if (!em.getDepartment().equalsIgnoreCase("employee")) {
						request.getRequestDispatcher("assets/view/accessdenied.jsp").forward(request, response);
						return;
					}
					String pageIndex = request.getParameter("pageIndex");
					String search = request.getParameter("ename");
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
					int totalPage = employeeDAO.getCountEmployeeSearch(select, search);
					int maxSearchPage = totalPage / pageSize;
					if (totalPage % pageSize != 0) {
						maxSearchPage++;
					}

					List<Employee> list = employeeDAO.getListEmployeeSearch(select, search, index, pageSize);
					request.setAttribute("pageSearchCurrentIndex", pageIndex);
					request.setAttribute("maxSearchPage", maxSearchPage);
					PrintWriter out = response.getWriter();
					if (list.isEmpty()) {
						out.println("<h5>No matches</h5>");
					} else {
						out.println("<table  class=\"table table-bordered\">\r\n"
								+ "							<thead>\r\n" + "								<tr>\r\n"
								+ "									<th>ID</th>\r\n"
								+ "									<th>Name</th>\r\n"
								+ "									<th>DateOfBirth</th>\r\n"
								+ "									<th>Address</th>\r\n"
								+ "									<th>Phone</th>\r\n"
								+ "									<th>Department</th>\r\n"
								+ "									<th>Action</th>\r\n"
								+ "								</tr>\r\n" + "							</thead>\r\n"
								+ "							<tbody>");
						for (Employee e : list) {
							out.println("<input type=\"hidden\" name=\"searchValue\" id =\"searchValue\" value=\""
									+ search + "\">	"
									+ "<input type=\"hidden\" name=\"selectValue\" id =\"selectValue\" value=\""
									+ select + "\">	"
									+ "<input type=\"hidden\" name=\"pageSearchCurrentIndex\" id =\"pageSearchCurrentIndex\" value=\""
									+ pageIndex + "\">	" + "<tr>\r\n" + "									<td>"
									+ e.getEmployeeID() + "</td>\r\n" + "									<td>"
									+ e.getEmployeeName() + "</td>\r\n" + "									<td>"
									+ e.getEmployeeBirthdate() + "</td>\r\n"
									+ "									<td>" + e.getEmployeeAddress() + "</td>\r\n"
									+ "									<td>" + e.getEmployeePhone() + "</td>\r\n"
									+ "									<td>" + e.getDepartment() + "</td>\r\n"
									+ "<td><a href=\"employeedetails?id=" + e.getEmployeeID()
									+ "\" class=\"link-primary\"><i class=\"fa fa-search\" aria-hidden=\"true\"></i> View  </a></td>\r\n"
									+ "								</tr>");
						}
						out.print("</tbody>\r\n" + "						</table>");
						out.print("<nav aria-label=\"paging\">\r\n"
								+ "							<input type=\"hidden\" name=\"maxSearchPage\" id=\"maxSearchPage\"\r\n"
								+ "								value=" + maxSearchPage + ">\r\n"
								+ "							<ul class=\"pagination\">\r\n"
								+ "								<li class=\"page-item\"><a class=\"page-link\"\r\n"
								+ "									onclick=\"btnSearchPrevious()\" href=\"#\">Previous</a></li>");
						for (int i = 1; i <= maxSearchPage; i++) {
							out.print("<li class=\"page-item\" aria-current=\"page\"><a\r\n"
									+ "										class=\"page-link\" id=\"currentSearchPage"
									+ i + "\"\r\n" + "onclick=\"pagingEmployeeSearch(" + i + ")\""
									+ "										\" href=\"#\">" + i + "</a></li>");
						}
						out.print("<li class=\"page-item\"><a class=\"page-link\"\r\n"
								+ "									onclick=\"btnSearchNext()\" href=\"#\">Next</a></li>\r\n"
								+ "							</ul>\r\n" + "						</nav>");
					}
				}
			}
		} catch (Exception e) {
			request.setAttribute("Error", e);
			request.getRequestDispatcher("assets/view/error.jsp").forward(request, response);
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
