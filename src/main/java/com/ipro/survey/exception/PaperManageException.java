package com.ipro.survey.exception;

/**
 * @Author weiqiang.yuan
 * @Time 15/7/7 22:40.
 */
public class PaperManageException extends RuntimeException {
    public PaperManageException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaperManageException(String message) {
        super(message);
    }
}
