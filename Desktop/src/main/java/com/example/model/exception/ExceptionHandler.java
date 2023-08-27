package com.example.model.exception;

import org.controlsfx.control.Notifications;

import java.sql.SQLException;

public class ExceptionHandler {

    private Exception e;
    private static final ExceptionHandler instance = new ExceptionHandler();

    public ExceptionHandler() {
    }

    public void setE(Exception e){
        this.e = e;
        notifyUser();
    }

    public void notifyUser(){
        if(e instanceof SQLException sqlException) {
            Notifications.create().title(String.valueOf(sqlException.getErrorCode())).text(sqlException.getMessage()).showError();
        }else
            Notifications.create().title("Error").text(e.getMessage()).showError();
    }

    public static ExceptionHandler getInstance(){
        return instance;
    }
}
