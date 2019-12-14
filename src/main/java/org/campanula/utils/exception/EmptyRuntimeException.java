package org.campanula.utils.exception;

/**
 * @Author: Campanula
 * @Date 2019-12-14
 */
public class EmptyRuntimeException extends RuntimeException {

    public EmptyRuntimeException(String message) {
        super(message);
    }

    public EmptyRuntimeException(Throwable cause) {
        super(cause);
    }
}
