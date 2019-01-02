package com.dly.exception.code;

import com.dly.exception.ProjectException;
import com.dly.exception.ProjectErrException;
import com.dly.exception.ProjectWarnException;

public class ProjectErrorCode extends ErrorCode{

    public ProjectErrorCode( String errCode, String errDesc )
    {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    public ErrorMsg format( )
    {
        return new ErrorMsg(errCode, errDesc);
    }

    public ProjectException exception()
    {
        return new ProjectErrException( errCode, errDesc );
    }

    public ProjectException exception( Throwable cause )
    {
        return new ProjectErrException( errCode, errDesc, cause );
    }

    public ProjectException warn()
    {
        return new ProjectWarnException( errCode, errDesc );
    }

    public ProjectException warn( Throwable cause )
    {
        return new ProjectWarnException( errCode, errDesc, cause );
    }

}
