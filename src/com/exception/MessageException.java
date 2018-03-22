package com.exception;

/**
 * Created by victor on 2018/3/8.
 */
public class MessageException extends Exception {
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public MessageException setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
