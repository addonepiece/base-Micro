package com.dly.exception;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.Serializable;

public class ProjectErrException extends ProjectException implements Serializable {

    private static final long serialVersionUID = 2178473241793876583L;

    public ProjectErrException(String errCode, String errDesc, Throwable cause) {
        super(errCode, errDesc, cause);

        if (cause instanceof MySQLIntegrityConstraintViolationException) {
            this.errCode = "999990";
            this.setMessage("操作数据库时记录重复");
        }
    }

    public ProjectErrException(String errCode, String errDesc) {
        super(errCode, errDesc);
    }

    public ProjectErrException(String errDesc, Throwable cause) {
        super(errDesc, cause);
    }

    public ProjectErrException(String errDesc) {
        super(errDesc);
    }

    public int getLevel() {
        return ERROR;
    }

}