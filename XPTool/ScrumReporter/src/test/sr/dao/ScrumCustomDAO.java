package test.sr.dao;

import java.util.Date;
import java.util.List;

import test.sr.client.ScrumData;
import test.sr.client.Users;


public interface ScrumCustomDAO
{

	List<Users> getUsers();

	List<Date> getScrumsList();

	List<ScrumData> getScrumData(Date scrumDate);
	
	Long getNextSequenceId(String sequenceName);

}