package dly.exception;

import java.io.Serializable;

public class ProjectWarnException extends ProjectException implements Serializable {

    private static final long serialVersionUID = -6896031811980308825L;

    public ProjectWarnException( String errCode, String errDesc, Throwable cause )
    {
        super(errCode, errDesc, cause);
    }

    public ProjectWarnException( String errCode, String errDesc )
    {
        super(errCode, errDesc);
    }

    public int getLevel()
    {
        return WARN;
    }
}
