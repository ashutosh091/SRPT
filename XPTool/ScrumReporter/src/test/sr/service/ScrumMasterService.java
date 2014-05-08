/**
 * 
 */
package test.sr.service;

import java.util.Date;
import java.util.List;

import test.sr.client.ScrumData;
import test.sr.client.Users;


public interface ScrumMasterService
{
	ScrumData getScrumDataById(Long scrumDataId);

	List<ScrumData> getScrumData(Date scrumDate);

	List<Date> getScrumsList();

	void saveScrumData(ScrumData scrumData);

	List<Users> getUsers();

}
