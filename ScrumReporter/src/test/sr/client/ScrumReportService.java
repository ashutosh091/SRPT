/**
 * 
 */
package test.sr.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author Ashutosh
 *
 */
public interface ScrumReportService extends RemoteService
{
	ArrayList getScrumsList();
	
	ArrayList getScrumData(Date scrumDate);
	
	void saveScrumData(ScrumView scrumData);
	
	ArrayList getUsers();
}
