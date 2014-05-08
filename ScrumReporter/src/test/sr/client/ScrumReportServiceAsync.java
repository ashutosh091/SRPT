/**
 * 
 */
package test.sr.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Ashutosh
 *
 */
public interface ScrumReportServiceAsync
{
	void getScrumsList(AsyncCallback as);
	
	void getScrumData(Date scrumDate, AsyncCallback as);
	
	void saveScrumData(ScrumView scrumData, AsyncCallback as);
	
	void getUsers(AsyncCallback as);
}
