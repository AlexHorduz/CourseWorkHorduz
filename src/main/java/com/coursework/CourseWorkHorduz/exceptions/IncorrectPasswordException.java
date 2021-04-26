package com.coursework.CourseWorkHorduz.exceptions;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public IncorrectPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
