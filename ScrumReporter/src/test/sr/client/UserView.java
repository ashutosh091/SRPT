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
public class UserView implements Serializable
{
    private Long userId;

    private String userName;

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

    
}
