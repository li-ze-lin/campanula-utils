package org.campanula.utils.exception;

/**
 * @Author: Campanula
 * @Date 2019-12-14
 */
public class ListEmptyRuntimeException extends RuntimeException {

    public ListEmptyRuntimeException() {
        super("list is null");
    }

    public ListEmptyRuntimeException(String message) {
        super(message);
    }
}
