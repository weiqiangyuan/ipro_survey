package com.ipro.survey.exception;

/**
 * @Author weiqiang.yuan
 * @Time 15/7/7 22:40.
 */
public class TaskException extends RuntimeException {
    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskException(String message) {
        super(message);
    }
}
