package com.dly.exception;

import java.io.Serializable;

public class ExceptionStack implements Serializable {
    private static final long serialVersionUID = 4640946308434637949L;

    private Throwable throwable;
    private String[] rep;

    public ExceptionStack(Throwable throwable)
    {
        this.throwable = throwable;
    }

    public String[] getThrowableStrRep()
    {
        if( rep == null ){
            VectorWriter vw = new VectorWriter();
            if( throwable instanceof ProjectException ){
                ((ProjectException)throwable).printStackTrace(vw);
            }
            else{
                throwable.printStackTrace(vw);
            }

            rep = vw.toStringArray();
        }

        return rep;
    }

}