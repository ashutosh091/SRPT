package test.sr.server;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import test.sr.client.ScrumReportService;
import test.sr.client.ScrumReportView;
import test.sr.client.ScrumView;
import test.sr.client.UserView;

/**
 * 
 */

/**
 * @author Ashutosh
 *
 */
public class ScrumReportServiceImpl extends RemoteServiceServlet implements ScrumReportService
{

	private ArrayList scrumList = new ArrayList();
	
	private ArrayList scrumReportList = new ArrayList();
	
	private ArrayList usersList = new ArrayList();
	
	public ScrumReportServiceImpl()
	{
		for (long scrumDataId = 0; scrumDataId < 20; scrumDataId++)
		{
			ScrumView scrumView = new ScrumView();
			scrumView.setUserName("user" + (scrumDataId % 3));
			Double hrsSpent = new Double(scrumDataId * scrumDataId / 10.0d);
			scrumView.setHoursSpent(hrsSpent);
			scrumView.setScrumDataId(new Long(scrumDataId));
			Date scrumDate = new Date(109, 1, (int)(scrumDataId % 2) + 1);
			scrumView.setScrumDate(scrumDate);
			scrumView.setTask("Task - " + scrumDataId);
			scrumList.add(scrumView);
		}
		
		for (int i = 0 ; i < 3; i++)
		{
			UserView userView = new UserView();
			userView.setUserId(new Long(i));
			userView.setUserName("user" + i);
			usersList.add(userView);
		}
		
		
	}
	/* (non-Javadoc)
	 * @see test.sr.client.ScrumReportService#getScrumData(java.lang.Long)
	 */
	public ArrayList getScrumData(Date scrumDate)
	{
		ArrayList scrumData = new ArrayList();
		for (int i = 0; i < scrumList.size(); i++)
		{
			ScrumView tempView = (ScrumView) scrumList.get(i);
			if (tempView.getScrumDate().equals(scrumDate))
			{
				scrumData.add(tempView);
			}
		}
		return scrumData;
	}

	/* (non-Javadoc)
	 * @see test.sr.client.ScrumReportService#getScrumsList()
	 */
	public ArrayList getScrumsList()
	{
		for (int i = 0; i < scrumList.size(); i++)
		{
			ScrumView tempView = (ScrumView) scrumList.get(i);
			Date scrumDate = tempView.getScrumDate();
			boolean found = false;
			for (int j = 0; j < scrumReportList.size(); j++)
			{
				ScrumReportView view = (ScrumReportView) scrumReportList.get(j);
				if (view.getScrumDate().equals(scrumDate))
				{
					found = true;
					break;
				}
			}
			
			if (!found)
			{
				ScrumReportView view = new ScrumReportView();
				view.setScrumDate(scrumDate);
				scrumReportList.add(view);
			}
		
		}
		return scrumReportList;
	}

	/* (non-Javadoc)
	 * @see test.sr.client.ScrumReportService#saveScrumData(test.sr.client.ScrumView)
	 */
	public void saveScrumData(ScrumView scrumData)
	{
		scrumList.add(scrumData);
	}
	public ArrayList getUsers()
	{
		return usersList;
	}

}
