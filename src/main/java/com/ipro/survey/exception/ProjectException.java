package com.ipro.survey.exception;

/**
 * @Author weiqiang.yuan
 * @Time 15/7/7 22:40.
 */
public class ProjectException extends RuntimeException {
    public ProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectException(String message) {
        super(message);
    }
}
