package fa.training.controller.Car;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.CarDAO;
import fa.training.dao.EmployeeDAO;
import fa.training.entity.Employee;

/**
 * Servlet implementation class DeleteCar
 */
public class DeleteCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCarServlet() {
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
					String licensePlate = request.getParameter("licensePlate");
					CarDAO d = new CarDAO();

					try {
						d.deleteCar(licensePlate);
						PrintWriter out = response.getWriter();
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Delete successfully');");
						out.println("location='carlist';");
						out.println("</script>");
					} catch (Exception e) {
						PrintWriter out = response.getWriter();
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Can not delete this car');");
						out.println("location='carlist';");
						out.println("</script>");
					}

				}
			}
		} catch (Exception e) {
		

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
