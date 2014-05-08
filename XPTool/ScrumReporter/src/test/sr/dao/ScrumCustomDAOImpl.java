package test.sr.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import test.sr.client.ScrumData;
import test.sr.client.Users;

public class ScrumCustomDAOImpl extends SqlMapClientDaoSupport implements ScrumCustomDAO
{

	public Long getNextSequenceId(String sequenceName)
	{
		return (Long) getSqlMapClientTemplate().queryForObject("QUERIES.select_sequence_value", sequenceName);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ScrumData> getScrumData(Date scrumDate)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("scrumDate", scrumDate);
		
		return getSqlMapClientTemplate().queryForList("SCRUM_CUSTOM.getScrumData", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Date> getScrumsList()
	{
		return getSqlMapClientTemplate().queryForList("SCRUM_CUSTOM.getScrumsList");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getUsers()
	{
		return getSqlMapClientTemplate().queryForList("SCRUM_CUSTOM.getUsers");
	}

}