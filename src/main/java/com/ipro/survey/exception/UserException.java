package com.ipro.survey.exception;

/**
 * @Author weiqiang.yuan
 * @Time 15/7/7 22:40.
 */
public class UserException extends RuntimeException {
    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(String message) {
        super(message);
    }
}
