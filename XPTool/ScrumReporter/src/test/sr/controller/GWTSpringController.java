/**
 * 
 */
package test.sr.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Ashutosh
 * 
 */
public class GWTSpringController extends RemoteServiceServlet implements Controller, ServletContextAware
{
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext)
	{
		this.servletContext = servletContext;
	}

	public ServletContext getServletContext()
	{
		return servletContext;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		super.doPost(request, response);
		return null;
	}
	
	public Object getService(String serviceName)
	{
		WebApplicationContext applicationContext = (WebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		return applicationContext.getBean(serviceName);
	}
}
