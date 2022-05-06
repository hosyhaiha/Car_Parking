package fa.training.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.EmployeeDAO;
import fa.training.entity.Employee;

/**
 * Servlet Filter implementation class Filter
 */
public class LoginFilter implements javax.servlet.Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see LoginFilter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see LoginFilter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String account = req.getParameter("account");
		EmployeeDAO employeeDAO = new EmployeeDAO();
	
		chain.doFilter(request, response);
		if (account != null) {
			Employee e = null;
			try {
				e = employeeDAO.getEmployeeByAccount(account);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			String dep = e.getDepartment();
			if (dep.equalsIgnoreCase("employee")) {
				res.sendRedirect("employeelist");
			}
			if (dep.equalsIgnoreCase("parking")) {
				res.sendRedirect("carlist");
			}
			if (dep.equalsIgnoreCase("service")) {
				res.sendRedirect("triplist");
			}
		}
	}

	/**
	 * @see LoginFilter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
