package fa.training.controller.User;

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
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    EmployeeDAO employeeDAO = new EmployeeDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					request.setAttribute("user", e.getAccount());
					request.setAttribute("id", e.getEmployeeID());
					request.setAttribute("fullname", e.getEmployeeName());
					request.setAttribute("phone", e.getEmployeePhone());
					request.setAttribute("address", e.getEmployeeAddress());
					request.setAttribute("email", e.getEmployeeEmail());
					request.setAttribute("account", e.getAccount());
					request.setAttribute("password", e.getPassword());
					request.setAttribute("sex", e.getSex());
					String dob = new SimpleDateFormat("dd/MM/yyyy").format(e.getEmployeeBirthdate());
					request.setAttribute("dateofbirth", dob);
					request.setAttribute("department", e.getDepartment());
					request.getRequestDispatcher("assets/view/user_manager/profile.jsp").forward(request,
							response);
				}
			}
		} catch (Exception e) {
			request.setAttribute("Error", e);
			request.getRequestDispatcher("assets/view/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					boolean isError = false;
					
					String eid = request.getParameter("id");
					request.setAttribute("id", eid);
					Employee em = employeeDAO.getEmployeeByID(eid);
					
					String fullname = request.getParameter("fullname");
					String phone = request.getParameter("phone");
					String address = request.getParameter("address");
					String email = request.getParameter("email");
					String sex = request.getParameter("sex");
					String dob = request.getParameter("dateofbirth");
					String password = request.getParameter("password");
					PrintWriter out = response.getWriter();

					if (employeeDAO.checkEmailExisted(email)) {
						if (email.equals(em.getEmployeeEmail())) {
							isError = false;
						} else {
							isError = true;
							out.println("<script type=\"text/javascript\">");
							out.println("alert('Email is already existed');");
							out.println("location='profile';");
							out.println("</script>");
						}
					}
					if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$")) {
						isError = true;
						out.println("<script type=\"text/javascript\">");
						out.println(
								"alert('Password must have atleast 1 upercase, lowercase, number and at least 6 characters');");
						out.println("location='employeedetails?id=" + eid + "';");
						out.println("</script>");
					}
					if (!isError) {
						java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
						Date dateofbirth = new java.sql.Date(date.getTime());
						employeeDAO.editProfile( address, dateofbirth, email, fullname, phone,password,
								 sex, eid);
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Profile has been UPDATED !');");
						out.println("location='profile';");
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
