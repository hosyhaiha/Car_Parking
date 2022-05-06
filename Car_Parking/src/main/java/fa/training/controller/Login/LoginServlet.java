package fa.training.controller.Login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.EmployeeDAO;
import fa.training.entity.Employee;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int USER_TIMEOUT = 60 * 60 * 24;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
			
			request.getRequestDispatcher("assets/view/login/login.jsp").forward(request, response);
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
			boolean ERROR = false;
			request.setAttribute("path", request.getContextPath());
			String msg = "";
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			boolean isAccountOK = account != null && account.trim().length() > 0;
			boolean isPasswordOK = password != null && password.trim().length() > 0;

			if (!isAccountOK || !isPasswordOK) {

				ERROR = true;
				msg = "Please enter account and password";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("assets/view/login/login.jsp").forward(request, response);

			}
			if (!employeeDAO.checkLogin(account, password)) {

				ERROR = true;
				msg = "Username or password is incorect";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("assets/view/login/login.jsp").forward(request, response);

			}
			if (!ERROR) {
				Employee e = employeeDAO.getEmployeeByAccount(account);

				Cookie userCookie = new Cookie("userCookie", String.valueOf(e.getEmployeeID()));
				Cookie accountCookie = new Cookie("accountCookie", e.getAccount());
				Cookie passwordCookie = new Cookie("passwordCookie", e.getPassword());

				userCookie.setMaxAge(USER_TIMEOUT);
				accountCookie.setMaxAge(USER_TIMEOUT);
				passwordCookie.setMaxAge(USER_TIMEOUT);

				response.addCookie(userCookie);
				response.addCookie(accountCookie);
				response.addCookie(passwordCookie);

//				if (e.getDepartment().equalsIgnoreCase("employee")) {
//					response.sendRedirect("employeelist");
//				}
//				if (e.getDepartment().equalsIgnoreCase("parking")) {
//					response.sendRedirect("parkinglist");
//				}
//				if (e.getDepartment().equalsIgnoreCase("service")) {
//					response.sendRedirect("triplist");
//				}

			}
		} catch (Exception e) {
			request.setAttribute("Error", e);
			request.getRequestDispatcher("assets/view/error.jsp").forward(request, response);
		}
	}

}
