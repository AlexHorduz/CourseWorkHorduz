package com.coursework.CourseWorkHorduz.exceptions;

public class NoSuchTopicException extends Exception{
    public NoSuchTopicException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public NoSuchTopicException(String errorMessage) {
        super(errorMessage);
    }
}
