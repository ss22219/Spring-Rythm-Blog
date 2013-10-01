package com.mvc.exception;

public class ServiceException extends  Exception {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ServiceException(String message){
        super(message);
        this.message = message;
    }
}
