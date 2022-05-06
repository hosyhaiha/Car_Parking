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
 * Servlet implementation class PagingServlet
 */
public class PagingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PagingServlet() {
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
					if (!em.getDepartment().equalsIgnoreCase("employee")) {
						request.getRequestDispatcher("assets/view/accessdenied.jsp").forward(request, response);
						return;
					}
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

					List<Employee> list = employeeDAO.getListEmployee(index, pageSize);
					request.setAttribute("pageCurrentIndex", pageIndex);
					PrintWriter out = response.getWriter();
					for (Employee e : list) {
						out.println("<input type=\"hidden\" name=\"pageCurrentIndex\" id =\"pageCurrentIndex\" value=\""
								+ pageIndex + "\">	" + "<tr>\r\n" + "									<td>"
								+ e.getEmployeeID() + "</td>\r\n" + "									<td>"
								+ e.getEmployeeName() + "</td>\r\n" + "									<td>"
								+ e.getEmployeeBirthdate() + "</td>\r\n" + "									<td>"
								+ e.getEmployeeAddress() + "</td>\r\n" + "									<td>"
								+ e.getEmployeePhone() + "</td>\r\n" + "									<td>"
								+ e.getDepartment() + "</td>\r\n" + "<td><a href=\"employeedetails?id="
								+ e.getEmployeeID() + "\" class=\"link-primary\"><i class=\"fa fa-search\" aria-hidden=\"true\"></i> View  </a></td>\r\n"
								+ "								</tr>");
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
