package fa.training.controller.Ticket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.EmployeeDAO;
import fa.training.dao.TicketDAO;
import fa.training.entity.Employee;

/**
 * Servlet implementation class TicketListServlet
 */
public class TicketListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TicketListServlet() {
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
					if (!em.getDepartment().equalsIgnoreCase("service")) {
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
					} catch (Exception ex) {
						index = 1;
					}

					int pageSize = 4;
					int totalPage = new TicketDAO().getCountTicket();
					int maxPage = totalPage / pageSize;
					if (totalPage % pageSize != 0) {
						maxPage++;
					}
					request.setAttribute("pageCurrentIndex", 1);

					request.setAttribute("listTicket", new TicketDAO().getAllTicket(index, pageSize));
					request.setAttribute("maxPage", maxPage);
					request.setAttribute("pageIndex", index);
					request.getRequestDispatcher("assets/view/ticket_manager/ticketlist.jsp").forward(request,
							response);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
