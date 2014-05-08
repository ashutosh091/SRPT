/**
 * 
 */
package test.sr.client;

import java.io.Serializable;
import java.util.Date;

/**
 * @author synerzip
 *
 */
public class ScrumView implements Serializable
{
    private Long scrumDataId;

    private Date scrumDate;

    private String task;

    private Double hoursSpent;

    private String userName;
    
    private Long userId;

	public Long getScrumDataId()
	{
		return scrumDataId;
	}

	public void setScrumDataId(Long scrumDataId)
	{
		this.scrumDataId = scrumDataId;
	}

	public Date getScrumDate()
	{
		return scrumDate;
	}

	public void setScrumDate(Date scrumDate)
	{
		this.scrumDate = scrumDate;
	}

	public String getTask()
	{
		return task;
	}

	public void setTask(String task)
	{
		this.task = task;
	}

	public Double getHoursSpent()
	{
		return hoursSpent;
	}

	public void setHoursSpent(Double hoursSpent)
	{
		this.hoursSpent = hoursSpent;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
    
    
}
