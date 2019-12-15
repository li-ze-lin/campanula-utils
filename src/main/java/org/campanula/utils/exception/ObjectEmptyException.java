package org.campanula.utils.exception;

/**
 * @Author: Campanula
 * @Date 2019-12-14
 */
public class ObjectEmptyException extends Exception {

    public ObjectEmptyException() {
        super("object is null");
    }

    public ObjectEmptyException(String message) {
        super(message);
    }
}
