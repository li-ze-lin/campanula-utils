package org.campanula.utils.exception;

/**
 * @Author: Campanula
 * @Date 2019-12-14
 */
public class EmptyException extends Exception {

    public EmptyException(String message) {
        super(message);
    }

    public EmptyException(Throwable cause) {
        super(cause);
    }
}
