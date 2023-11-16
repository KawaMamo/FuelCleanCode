package org.example.exceptions;

public class ReportException extends RuntimeException{
    String message;

    public ReportException(String message, String message1) {
        super(message);
        this.message = message1;
    }
}
