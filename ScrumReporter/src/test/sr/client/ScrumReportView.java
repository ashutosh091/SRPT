/**
 * 
 */
package test.sr.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author synerzip
 *
 */
public class ScrumReportView implements Serializable
{
	
	private Date scrumDate;
	
	public Date getScrumDate()
	{
		return scrumDate;
	}

	public void setScrumDate(Date scrumDate)
	{
		this.scrumDate = scrumDate;
	}
}
