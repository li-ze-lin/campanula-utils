package org.campanula.utils.exception;

/**
 * @Author: Campanula
 * @Date 2019-12-14
 */
public class MethodNullException extends RuntimeException {

    public MethodNullException() {
        super("execute method is null");
    }
}
