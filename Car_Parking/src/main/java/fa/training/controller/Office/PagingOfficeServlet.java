package fa.training.controller.Office;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class PagingOfficeServlet
 */
public class PagingOfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PagingOfficeServlet() {
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
					} catch (Exception e) {
						index = 1;
					}
					int pageSize = 4;
					BookingOfficeDAO officedao = new BookingOfficeDAO();
					List<Office> list = officedao.getAllOffice(index, pageSize);
					request.setAttribute("pageOfficeCurrentIndex", pageIndex);
					PrintWriter out = response.getWriter();
					for (Office office : list) {
						out.print("<input type=\"hidden\" name=\"pageOfficeCurrentIndex\"\r\n"
								+ "									id=\"pageOfficeCurrentIndex\" value=\"" + pageIndex
								+ "\">\r\n" + "								<tr>\r\n"
								+ "									<td>" + office.getOfficeID() + "</td>\r\n"
								+ "									<td>" + office.getOfficeName() + "</td>\r\n"
								+ "									<td>" + office.getTripName() + "</td>\r\n"
								+ "									<td><a href=\"detailoffice?id="
								+ office.getOfficeID() + "\">View</a></td>\r\n" + "								</tr>");
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
