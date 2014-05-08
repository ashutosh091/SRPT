package test.sr.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScrumReporter implements EntryPoint
{
	private static final String ADD_REPORT = "addReport";

	private static final String REPORT_DETAIL = "reportDetail";

	private static final String REPORTS_LIST = "reportsList";

	private FlexTable reportsList = new FlexTable();
	
	private Button addReportButton = new Button("Add Report");
	
	private TextBox datePicker = new TextBox();
	
	private TextArea scrumText = new TextArea();
	
	private ListBox userSelectList = new ListBox();
	
	private TextBox noOfHrs = new TextBox();
	
	private FormPanel addReportForm = new FormPanel();
	
	private Grid addReportGrid = new Grid(5, 2);
	
	private FlexTable reportDetail = new FlexTable();
	
	private Hyperlink reportsListLink = new Hyperlink();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		final ScrumReportServiceAsync serviceProxy = (ScrumReportServiceAsync) GWT.create(ScrumReportService.class);
		ServiceDefTarget target = (ServiceDefTarget) serviceProxy;
		//Window.alert(GWT.getModuleBaseURL() + "scrumMasterService.svc");
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "scrumMasterService.svc");
		
		
		// Create add report Panel
		scrumText.setVisibleLines(10);
		scrumText.setWidth("500");
		
		Date d = new Date();
		datePicker.setText(getDisplayDate(d));
		
		// load user select list only once
		loadUsersList(serviceProxy);
		
		addWidget(0, "Select User", userSelectList);
		addWidget(1, "Scrum Date", datePicker);
		addWidget(2, "Task Description", scrumText);
		addWidget(3, "No of Hrs", noOfHrs);
		addReportGrid.setWidget(4, 0, addReportButton);
		
		addReportForm.add(addReportGrid);
		
		RootPanel.get(REPORTS_LIST).add(reportsList);
		RootPanel.get(REPORT_DETAIL).add(reportDetail);
		RootPanel.get(REPORT_DETAIL).setVisible(false);
		//addReportForm.add(reportsListPanel);
		RootPanel.get(ADD_REPORT).add(addReportForm);
		
		// add link to reports list on top
		reportsListLink.setText("Scrum Reports List");
		reportsListLink.setTargetHistoryToken("reportsListLink");
		reportsListLink.addClickListener(new ShowReportsListCallBack(serviceProxy));
		RootPanel.get("reportsListLink").add(reportsListLink);
		
		// load scrum list
		serviceProxy.getScrumsList(new ScrumListCallBack(reportsList, serviceProxy));
		
		// add listener on add report
		addReportButton.addClickListener(new AddReportClickListener(serviceProxy));
		addReportButton.setFocus(true);
	}

	
	private void addWidget(int row, String text, Widget widget)
	{
		//HorizontalPanel panel = new HorizontalPanel();
		Label label = new Label();
		label.setText(text);
		addReportGrid.setWidget(row, 0, label);
		addReportGrid.setWidget(row, 1, widget);
	}

	private void loadUsersList(ScrumReportServiceAsync serviceProxy)
	{
		serviceProxy.getUsers(new LoadUsersListCallBack());
	}

	private String getDisplayDate(Date d)
	{
		return (d.getMonth() + 1) +"/" + d.getDate() + "/" + (d.getYear() + 1900);
	}
	
	private Date getDate(String dateStr)
	{
		Date d = null;
		try
		{
			String[] str = dateStr.split("/");
			int month = Integer.parseInt(str[0]) - 1;
			int date = Integer.parseInt(str[1]);
			int year = Integer.parseInt(str[2]) - 1900;
			
			d = new Date(year, month, date);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Enter Proper Date");
		}
		return d;
	}
	
	private void loadScrumReport(String date, ScrumReportServiceAsync serviceProxy)
	{
		Date scrumDate = getDate(date);
		loadScrumReport(scrumDate, serviceProxy);
	}
	
	private void loadScrumReport(Date date, ScrumReportServiceAsync serviceProxy)
	{
		serviceProxy.getScrumData(date, new ShowReportDetailsCallBack(serviceProxy));
		showPanel(REPORT_DETAIL);
	}
	
	void showPanel(String panelName)
	{
		RootPanel.get(panelName).setVisible(true);
	}
	
	void hidePanel(String panelName)
	{
		RootPanel.get(panelName).setVisible(false);
	}
	
	private void resetAddForm()
	{
		Date d = new Date();
		datePicker.setText(getDisplayDate(d));
		noOfHrs.setText("");
		scrumText.setText("");
		userSelectList.setSelectedIndex(0);
	}

	public String validateScrumInfo()
	{
		String message = "";
		try
		{
			getDate(datePicker.getText());
		}
		catch (Exception e)
		{
			message = message + e.getMessage() + "\n";
		}
		if (scrumText.getText() == null || scrumText.getText().trim().length() == 0)
		{
			message = message + "Enter Task" + "\n";
		}
		try
		{
			Double hrsSpent = new Double(noOfHrs.getText());
		}
		catch (NumberFormatException e)
		{
			message = message + "Enter proper number of Hours" + "\n";
		}
		
		return message;
	}

	public class AddReportClickListener implements ClickListener
	{
		private ScrumReportServiceAsync serviceProxy;
		
		public AddReportClickListener(ScrumReportServiceAsync serviceProxy)
		{
			this.serviceProxy = serviceProxy;
		}
		
		public void onClick(Widget sender)
		{
			String message = validateScrumInfo();
			
			if (message.length() > 0)
			{
				Window.alert(message);
				return;
			}
			
			ScrumView view = new ScrumView();
			view.setTask(scrumText.getText());
			view.setScrumDate(getDate(datePicker.getText()));
			Double hrsSpent = new Double(noOfHrs.getText());
			view.setHoursSpent(hrsSpent);
			
			// find selected user
			String userId = userSelectList.getValue(userSelectList.getSelectedIndex());
			view.setUserId(new Long(userId));
			
			serviceProxy.saveScrumData(view, new AddScrumCallBack(serviceProxy, view));
		}
	}
	
	public class LoadUsersListCallBack implements AsyncCallback
	{
		public void onFailure(Throwable caught)
		{
			Window.alert("Can't load user list - " + caught.getMessage());
		}

		public void onSuccess(Object result)
		{
			userSelectList.clear();
			//userSelectList.addItem("Please Select", "");
			ArrayList userList = (ArrayList) result;
			if (userList != null && !userList.isEmpty())
			{
				for (int i = 0; i < userList.size(); i++)
				{
					UserView userView = (UserView) userList.get(i);
					userSelectList.addItem(userView.getUserName(), String.valueOf(userView.getUserId()));
				}
			}
		}
	}
	
	public class ScrumListCallBack implements AsyncCallback
	{
		private FlexTable reportsList = null;
		
		private ScrumReportServiceAsync serviceProxy;

		public ScrumListCallBack(FlexTable reportsList, ScrumReportServiceAsync serviceProxy)
		{
			this.reportsList = reportsList;
			this.serviceProxy = serviceProxy;
		}

		public void onFailure(Throwable caught)
		{
			Window.alert(caught.getMessage());
		}

		public void onSuccess(Object result)
		{
			ArrayList scrumList = (ArrayList) result;
			reportsList.setHTML(0, 0, "<b>Date</b>");
			
			for (int i = 0; i < scrumList.size(); i++)
			{
				ScrumReportView view = (ScrumReportView) scrumList.get(i);
				Hyperlink href = new Hyperlink();
				href.setText(getDisplayDate(view.getScrumDate()));
				href.setTargetHistoryToken(getDisplayDate(view.getScrumDate()));
				reportsList.setWidget(i + 1, 0, href);
				
				href.addClickListener(new ClickListener() 
				{
					public void onClick(Widget sender)
					{
						Hyperlink link = (Hyperlink) sender;
						loadScrumReport(link.getText(), serviceProxy);
					}
					
				});
			}
		}
	}
	
	
	public class ShowReportsListCallBack implements ClickListener
	{
		private ScrumReportServiceAsync serviceProxy;

		public ShowReportsListCallBack(ScrumReportServiceAsync serviceProxy)
		{
			this.serviceProxy = serviceProxy;
		}

		public void onClick(Widget sender)
		{
			hidePanel(REPORT_DETAIL);
			showPanel(REPORTS_LIST);
		}

	}
	
	public class AddScrumCallBack implements AsyncCallback
	{

		private ScrumReportServiceAsync serviceProxy;
		
		private ScrumView view;

		public AddScrumCallBack(ScrumReportServiceAsync serviceProxy, ScrumView view)
		{
			this.serviceProxy = serviceProxy;
			this.view = view;
		}

		public void onFailure(Throwable caught)
		{
			Window.alert("Can not save scrum info " + caught.getMessage());
		}

		public void onSuccess(Object result)
		{
			serviceProxy.getScrumsList(new ScrumListCallBack(reportsList, serviceProxy));
			// clear the form
			resetAddForm();
			
			// hide report list section and show reports details section
			hidePanel(REPORTS_LIST);
			loadScrumReport(view.getScrumDate(), serviceProxy);
		}
	}

	
	public class ShowReportDetailsCallBack implements AsyncCallback
	{
		private ScrumReportServiceAsync serviceProxy;

		public ShowReportDetailsCallBack(ScrumReportServiceAsync serviceProxy)
		{
			this.serviceProxy = serviceProxy;
		}

		public void onFailure(Throwable caught)
		{
			Window.alert("Can not show report details - " + caught.getMessage());
		}

		public void onSuccess(Object result)
		{
			RootPanel.get(REPORT_DETAIL).remove(reportDetail);
			reportDetail = new FlexTable();
			reportDetail.setHTML(0, 0, "<b>Date</b>");
			reportDetail.setHTML(0, 1, "<b>User</b>");
			reportDetail.setHTML(0, 2, "<b>Task</b>");
			reportDetail.setHTML(0, 3, "<b>Hrs</b>");
			ArrayList scrumDetails = (ArrayList) result;
			if (scrumDetails != null && !scrumDetails.isEmpty())
			{
				for (int i = 0 ; i < scrumDetails.size(); i++)
				{
					ScrumView reportView = (ScrumView) scrumDetails.get(i);
					reportDetail.setText(i + 1, 0, getDisplayDate(reportView.getScrumDate()));
					reportDetail.setText(i + 1, 1, reportView.getUserName());
					reportDetail.setText(i + 1, 2, reportView.getTask());
					reportDetail.setText(i + 1, 3, reportView.getHoursSpent().toString());
				}
			}
			RootPanel.get(REPORT_DETAIL).add(reportDetail);
			hidePanel(REPORTS_LIST);
		}
		
	}

}


