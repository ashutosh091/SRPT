package test.sr.server;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.sr.client.ScrumData;
import test.sr.client.ScrumReportService;
import test.sr.client.ScrumView;
import test.sr.controller.GWTSpringController;
import test.sr.service.ScrumMasterService;

/**
 * @author Ashutosh
 *
 */
public class ScrumReportServiceImpl extends GWTSpringController implements ScrumReportService
{
	
	/* (non-Javadoc)
	 * @see test.sr.client.ScrumReportService#getScrumData(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getScrumData(Date scrumDate)
	{
		ScrumMasterService scrumMasterService = (ScrumMasterService) getService("scrumMasterService");
		List<ScrumData> scrumDetails = scrumMasterService.getScrumData(scrumDate);
		
		return XPToolHelper.convertScrumDataListToScrumViewList(scrumDetails);
	}

	/* (non-Javadoc)
	 * @see test.sr.client.ScrumReportService#getScrumsList()
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getScrumsList()
	{
		ScrumMasterService scrumMasterService = (ScrumMasterService) getService("scrumMasterService");
		List<Date> scrumDates = scrumMasterService.getScrumsList();
		return XPToolHelper.convertScrumDateListToScrumReportViewList(scrumDates);
	}

	/* (non-Javadoc)
	 * @see test.sr.client.ScrumReportService#saveScrumData(test.sr.client.ScrumView)
	 */
	@SuppressWarnings("unchecked")
	public void saveScrumData(ScrumView scrumData)
	{
		ScrumMasterService scrumMasterService = (ScrumMasterService) getService("scrumMasterService");
		scrumMasterService.saveScrumData(XPToolHelper.convertScrumViewToScrumData(scrumData));
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList getUsers()
	{
		ScrumMasterService scrumMasterService = (ScrumMasterService) getService("scrumMasterService");
		return XPToolHelper.convertUsersListToUserViewList(scrumMasterService.getUsers());
	}

}
