package org.demo.formation.web.jsf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.demo.formation.web.jsf.util.DemoConstantes;

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class SecurityFilter implements Filter {
	private ServletContext context = null;
	/**
	 * Default constructor. 
	 */
	public SecurityFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		if((session == null || session.getAttribute(DemoConstantes.USER_SESSION_KEY) == null)
				&& !(uri.endsWith("login.jsf") || uri.endsWith("login.xhtml") || uri.endsWith("/") || uri.endsWith(".css") || uri.endsWith(".js"))){
			this.context.log("Unauthorized access request");
			res.sendRedirect(req.getContextPath()+"/");
		}else{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		fConfig.getServletContext().log("LoggerFilter initialized");
	}

}
