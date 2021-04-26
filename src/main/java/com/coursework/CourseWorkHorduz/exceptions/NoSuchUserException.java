package com.coursework.CourseWorkHorduz.exceptions;

public class NoSuchUserException extends Exception{
    public NoSuchUserException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public NoSuchUserException(String errorMessage) {
        super(errorMessage);
    }
}
