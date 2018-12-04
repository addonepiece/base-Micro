package dly.exception.code;

import lombok.Getter;

@Getter
public class ErrorMsg {
    protected String errCode;
    protected String errDesc;

    public ErrorMsg(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

}
