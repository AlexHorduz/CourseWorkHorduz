package com.coursework.CourseWorkHorduz.exceptions;

public class FailedPasswordConfirmationException extends Exception{
    public FailedPasswordConfirmationException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public FailedPasswordConfirmationException(String errorMessage) {
        super(errorMessage);
    }
}
