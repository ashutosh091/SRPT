/**
 * 
 */
package test.sr.server;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.sr.client.ScrumData;
import test.sr.client.ScrumReportView;
import test.sr.client.ScrumView;
import test.sr.client.UserView;
import test.sr.client.Users;

/**
 * @author Ashutosh
 *
 */
public class XPToolHelper
{

	@SuppressWarnings("unchecked")
	public static ArrayList convertScrumDataListToScrumViewList(List<ScrumData> scrumDetails)
	{
		ArrayList result = new ArrayList();
		if (scrumDetails != null && !scrumDetails.isEmpty())
		{
			for (ScrumData data: scrumDetails)
			{
				result.add(getScrumView(data));
			}
		}
		return result;
	}

	public static ScrumView getScrumView(ScrumData data)
	{
		ScrumView scrumView = new ScrumView();
		if (data != null)
		{
			Double hrsSpent = 0d;
			if (data.getHoursSpent() != null)
			{
				hrsSpent = data.getHoursSpent().doubleValue();
			}
			scrumView.setHoursSpent(hrsSpent);
			scrumView.setScrumDataId(data.getScrumDataId());
			scrumView.setScrumDate(data.getScrumDate());
			scrumView.setTask(data.getTask());
			scrumView.setUserId(data.getUserId());
			scrumView.setUserName(data.getUser() != null ? data.getUser().getUsername() : "");
		}
		return scrumView;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList convertScrumDateListToScrumReportViewList(List<Date> scrumDates)
	{
		ArrayList result = new ArrayList();
		if (scrumDates != null && !scrumDates.isEmpty())
		{
			for (Date date: scrumDates)
			{
				result.add(getScrumReportView(date));
			}
		}
		return result;
	}

	public static ScrumReportView getScrumReportView(Date date)
	{
		ScrumReportView view = new ScrumReportView();
		if (date != null)
		{
			view.setScrumDate(date);
		}
		return view;
	}

	public static ScrumData convertScrumViewToScrumData(ScrumView view)
	{
		ScrumData data = new ScrumData();
		if (view != null)
		{
			data.setHoursSpent(new BigDecimal(view.getHoursSpent()));
			data.setScrumDataId(view.getScrumDataId());
			data.setScrumDate(view.getScrumDate());
			data.setTask(view.getTask());
			data.setUserId(view.getUserId());
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList convertUsersListToUserViewList(List<Users> users)
	{
		ArrayList result = new ArrayList();
		if (users != null && !users.isEmpty())
		{
			for (Users user: users)
			{
				result.add(getUserView(user));
			}
		}
		return result;
	}

	public static UserView getUserView(Users user)
	{
		UserView view = new UserView();
		if (user != null)
		{
			view.setUserId(user.getUserId());
			view.setUserName(user.getUsername());
		}
		return view;
	}

}
