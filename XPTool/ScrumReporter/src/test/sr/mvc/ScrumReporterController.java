/**
 * 
 */
package test.sr.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

/**
 * @author Ashutosh
 * 
 */
public class ScrumReporterController extends ParameterizableViewController
{
	public ScrumReporterController()
	{
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return super.handleRequestInternal(request, response);
	}
}
