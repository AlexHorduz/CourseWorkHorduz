package com.coursework.CourseWorkHorduz.exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
