package com.khameneh.route.exception;

/**
 * Exception that raises when user sends invalid data 
 *   
 * @author Amir Khameneh amirkhameneh@yahoo.com
 */
public class InvalidArgumentException extends RuntimeException {

    private static final long serialVersionUID = -1262173968380116559L;

    public InvalidArgumentException() {
        super();
    }

    public InvalidArgumentException(String s) {
        super(s);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentException(Throwable cause) {
        super(cause);
    }

}

