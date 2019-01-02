package com.dly.exception.code;

import com.dly.exception.ProjectErrException;
import com.dly.exception.ProjectException;
import com.dly.exception.ProjectWarnException;

import java.text.MessageFormat;

public class ProjectErrorCodeThreeArgs extends ErrorCode{

    private MessageFormat format;

    public ProjectErrorCodeThreeArgs( String errCode, String errDesc )
    {
        this.errCode = errCode;
        this.format = new MessageFormat(errDesc);
    }

    public ErrorMsg format( String arg1, String arg2, String arg3 )
    {
        Object args[] = {arg1, arg2, arg3};
        return new ErrorMsg(errCode, format.format(args));
    }

    public ProjectException exception(String arg1, String arg2, String arg3 )
    {
        Object args[] = {arg1, arg2, arg3};
        return new ProjectErrException( errCode, format.format(args) );
    }

    public ProjectException exception( String arg1, String arg2, String arg3, Throwable cause )
    {
        Object args[] = {arg1, arg2, arg3};
        return new ProjectErrException( errCode, format.format(args), cause );
    }

    public ProjectException warn( String arg1, String arg2, String arg3 )
    {
        Object args[] = {arg1, arg2, arg3};
        return new ProjectWarnException( errCode, format.format(args) );
    }

    public ProjectException warn( String arg1, String arg2, String arg3, Throwable cause )
    {
        Object args[] = {arg1, arg2, arg3};
        return new ProjectWarnException( errCode, format.format(args), cause );
    }
}