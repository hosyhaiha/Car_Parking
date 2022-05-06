package fa.training.controller.Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.EmployeeDAO;
import fa.training.entity.Employee;

/**
 * Servlet implementation class AddEmployeeServlet
 */
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEmployeeServlet() {
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
					Employee e = employeeDAO.getEmployeeByID(userCookie.getValue());
					if (!e.getDepartment().equalsIgnoreCase("employee")) {
						request.getRequestDispatcher("assets/view/accessdenied.jsp").forward(request, response);
						return;
					}
					request.setAttribute("user", e.getAccount());

					request.setAttribute("dep", employeeDAO.getDepartment());
					request.getRequestDispatcher("assets/view/employee_manager/addemployee.jsp").forward(request,
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
					boolean isError = false;

					String fullname = request.getParameter("fullname");
					String department = request.getParameter("department");
					String phone = request.getParameter("phone");
					String address = request.getParameter("address");
					String email = request.getParameter("email");
					String account = request.getParameter("account");
					String password = request.getParameter("password");
					String sex = request.getParameter("sex");
					String dob = request.getParameter("dateofbirth");
					PrintWriter out = response.getWriter();

					if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$")) {
						isError = true;
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Password must have atleast 1 upercase, lowercase, number and at least 6 characters');");
						out.println("location='addemployee';");
						out.println("</script>");
						
					}
					if (employeeDAO.checkEmailExisted(email)) {
						isError = true;
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Email is already existed');");
						out.println("location='addemployee';");
						out.println("</script>");
						
					}
					if (employeeDAO.checkAccountExisted(account)) {
						isError = true;
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Account is already existed');");
						out.println("location='addemployee';");
						out.println("</script>");
						
					}

					if (!isError) {
						java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
						Date dateofbirth = new java.sql.Date(date.getTime());
						employeeDAO.addEmployee(new Employee(account, department, address, dateofbirth, email, fullname,
								phone, password, sex));
						
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Add employee successfully');");
						out.println("location='employeelist';");
						out.println("</script>");
					}
				}

			}
		} catch (Exception e) {
			request.setAttribute("Error", e);
			request.getRequestDispatcher("assets/view/error.jsp").forward(request, response);
		}
	}

}
