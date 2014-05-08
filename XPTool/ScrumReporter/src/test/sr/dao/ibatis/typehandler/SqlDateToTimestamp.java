/**
 * Copyright (C) 2007, MAXIMUS Corporation
 * All Rights Reserved
 * Created On: Jan 12, 2007, 5:51:42 PM
 */
package test.sr.dao.ibatis.typehandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;
import com.ibatis.sqlmap.engine.type.SimpleDateFormatter;

public class SqlDateToTimestamp implements TypeHandlerCallback
{
    private static final String DATE_FORMAT = "yyyy/MM/dd hh:mm:ss";

    /**
     * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#getResult(com.ibatis.sqlmap.client.extensions.ResultGetter)
     */
    public Object getResult(ResultGetter getter) throws SQLException
    {
        return getter.getTimestamp();
    }

    /**
     * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#setParameter(com.ibatis.sqlmap.client.extensions.ParameterSetter, java.lang.Object)
     */
    public void setParameter(ParameterSetter setter, Object parameter) throws SQLException
    {
        if(parameter != null)
        {
            setter.setTimestamp(new Timestamp(((Date) parameter).getTime()));
        } else {
            setter.setNull(Types.TIMESTAMP);
        }
    }

    /**
     * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#valueOf(java.lang.String)
     */
    public Object valueOf(String s)
    {
        return SimpleDateFormatter.format(DATE_FORMAT, s);
    }

}
