package fa.training.controller.Employee;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.EmployeeDAO;
import fa.training.entity.Employee;

/**
 * Servlet implementation class EmployeeDetailServlet
 */
public class EmployeeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeDetailServlet() {
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
					request.setAttribute("user", em.getAccount());
					String eid = request.getParameter("id");
					Employee e = employeeDAO.getEmployeeByID(eid);
					request.setAttribute("id", e.getEmployeeID());
					request.setAttribute("account", e.getAccount());
					request.setAttribute("department", e.getDepartment());
					request.setAttribute("address", e.getEmployeeAddress());
					String dob = new SimpleDateFormat("dd/MM/yyyy").format(e.getEmployeeBirthdate());
					request.setAttribute("dateofbirth", dob);
					request.setAttribute("email", e.getEmployeeEmail());
					request.setAttribute("fullname", e.getEmployeeName());
					request.setAttribute("phone", e.getEmployeePhone());
					request.setAttribute("sex", e.getSex());
					request.setAttribute("password", e.getPassword());
					request.getRequestDispatcher("assets/view/employee_manager/employeedetails.jsp").forward(request,
							response);
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
