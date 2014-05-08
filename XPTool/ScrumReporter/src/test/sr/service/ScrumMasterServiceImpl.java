package test.sr.service;

import java.util.Date;
import java.util.List;

import test.sr.client.ScrumData;
import test.sr.client.Users;
import test.sr.dao.ScrumCustomDAO;
import test.sr.dao.ScrumDataDAO;
import test.sr.dao.UsersDAO;

public class ScrumMasterServiceImpl implements ScrumMasterService
{
	private static final String SCRUM_DATA_SEQ = "SCRUM_DATA_SEQ";

	private ScrumDataDAO scrumDataDAO;
	
	private UsersDAO usersDAO;
	
	private ScrumCustomDAO scrumCustomDAO;
	
	public ScrumDataDAO getScrumDataDAO()
	{
		return scrumDataDAO;
	}

	public void setScrumDataDAO(ScrumDataDAO scrumDataDAO)
	{
		this.scrumDataDAO = scrumDataDAO;
	}

	public UsersDAO getUsersDAO()
	{
		return usersDAO;
	}

	public void setUsersDAO(UsersDAO usersDAO)
	{
		this.usersDAO = usersDAO;
	}

	public ScrumCustomDAO getScrumCustomDAO()
	{
		return scrumCustomDAO;
	}

	public void setScrumCustomDAO(ScrumCustomDAO scrumCustomDAO)
	{
		this.scrumCustomDAO = scrumCustomDAO;
	}

	@Override
	public ScrumData getScrumDataById(Long scrumDataId)
	{
		return getScrumDataDAO().selectByPrimaryKey(scrumDataId);
	}

	@Override
	public List<ScrumData> getScrumData(Date scrumDate)
	{
		return getScrumCustomDAO().getScrumData(scrumDate);
	}

	@Override
	public List<Date> getScrumsList()
	{
		return getScrumCustomDAO().getScrumsList();
	}

	@Override
	public List<Users> getUsers()
	{
		return getScrumCustomDAO().getUsers();
	}

	@Override
	public void saveScrumData(ScrumData scrumData)
	{
		if (scrumData.getScrumDataId() == null)
		{
			scrumData.setScrumDataId(scrumCustomDAO.getNextSequenceId(SCRUM_DATA_SEQ));
		}
		getScrumDataDAO().insert(scrumData);
	}

}
