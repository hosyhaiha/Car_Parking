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
import fa.training.dao.ParkingDAO;
import fa.training.entity.Employee;
import fa.training.entity.Parking;

/**
 * Servlet implementation class EditCarServlet
 */
public class EditCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditCarServlet() {
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

					ParkingDAO pd = new ParkingDAO();
					ArrayList<Parking> listPark = pd.showListParkBlank();
					CarDAO cd = new CarDAO();
					ArrayList<String> listCPN = cd.showCarCPN();
					String selectCPN = request.getParameter("company");
					String selectPKL = request.getParameter("packinglot");
					int packinglot = Integer.parseInt(request.getParameter("packinglot"));
					Parking p = pd.getParkByID(packinglot);
					if (p.getParkStatus().equals("Full")) {
						listPark.add(p);
					}
					request.setAttribute("selectCPN", selectCPN);
					request.setAttribute("selectPKL", selectPKL);
					request.setAttribute("CPN", listCPN);
					request.setAttribute("park", listPark);
					request.getRequestDispatcher("assets/view/car_manager/editcar.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
					String licenseplate = request.getParameter("licenseplate");
					String cartype = request.getParameter("cartype");
					String carcolor = request.getParameter("carcolor");
					String company = request.getParameter("company");
					int pakinglot = Integer.parseInt(request.getParameter("pakinglot"));
					CarDAO cd = new CarDAO();
					PrintWriter out = response.getWriter();
					try {
						cd.updateCar(licenseplate, carcolor, cartype, company, pakinglot);
						
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Update successfully');");
						out.println("location='carlist';");
						out.println("</script>");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
