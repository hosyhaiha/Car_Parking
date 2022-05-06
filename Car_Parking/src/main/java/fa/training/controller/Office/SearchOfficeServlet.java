package fa.training.controller.Office;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * Servlet implementation class SearchOfficeServlet
 */
public class SearchOfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchOfficeServlet() {
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
		BookingOfficeDAO officedao = new BookingOfficeDAO();
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
					String search = request.getParameter("enameOffice");
					String filterby = request.getParameter("eselectOffice");

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
					int totalPage = officedao.getCountOfficeSearch(filterby, search);
					int maxSearchPage = totalPage / pageSize;
					if (totalPage % pageSize != 0) {
						maxSearchPage++;
					}

					List<Office> list = new ArrayList<>();
					list = officedao.getListOfficeSearch(filterby, search, index, pageSize);
					request.setAttribute("pageSearchCurrentIndex", pageIndex);
					request.setAttribute("maxSearchPage", maxSearchPage);
					PrintWriter out = response.getWriter();
					if (list.isEmpty()) {
						out.print("<h5>No matches</h5>");
					} else {
						out.print("<table class=\"table table-bordered\">\r\n" + "							<thead>\r\n"
								+ "								<tr>\r\n"
								+ "									<th>ID</th>\r\n"
								+ "									<th>Booking office</th>\r\n"
								+ "									<th>Trip</th>\r\n"
								+ "									<th>Action</th>\r\n"
								+ "								</tr>\r\n" + "							</thead>\r\n"
								+ "							<tbody >");
						for (Office office : list) {
							out.print("<input type=\"hidden\" name=\"searchValue\" id =\"searchValue\" value=\""
									+ search + "\">	"
									+ "<input type=\"hidden\" name=\"selectValue\" id =\"selectValue\" value=\""
									+ filterby + "\">	"
									+ "<input type=\"hidden\" name=\"pageSearchCurrentIndex\" id =\"pageSearchCurrentIndex\" value=\""
									+ pageIndex + "\"> " + "<tr>\r\n" + "					<td>" + office.getOfficeID()
									+ "</td>\r\n" + "									<td>" + office.getOfficeName()
									+ "</td>\r\n" + "									<td>" + office.getTripName()
									+ "</td>\r\n" + "									<td><a href=\"detailoffice?id="
									+ office.getOfficeID() + "\">View</a></td>" + "								</tr>");
						}
						out.print("</tbody>\r\n" + "</table>");
						out.print("<nav aria-label=\"paging\">\r\n"
								+ "								<input type=\"hidden\" name=\"maxSearchPage\" id=\"maxSearchPage\"\r\n"
								+ "									value=" + maxSearchPage + ">\r\n"
								+ "								<ul class=\"pagination\">\r\n"
								+ "									<li class=\"page-item\"><a class=\"page-link\"\r\n"
								+ "										onclick=\"btnSearchOfficePrevious()\" href=\"#\">Previous</a></li>");
						for (int i = 1; i <= maxSearchPage; i++) {
							out.print("<li class=\"page-item\" aria-current=\"page\"><a\r\n"
									+ "											class=\"page-link\" id=\"currentSearchPage"
									+ i + "\"\r\n"
									+ "											onclick=\"pagingOfficeSearch(" + i
									+ ")\" href=\"#\">" + i + "</a></li>");
						}
						out.print("<li class=\"page-item\"><a class=\"page-link\"\r\n"
								+ "	onclick=\"btnSearchOfficeNext()\" href=\"#\">Next</a></li>");
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
