package com.mercadolibre.quasarfirechallenge.infrastructure.exception;

public class MessagesException extends Exception {

    public MessagesException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessagesException(String message) {
        super(message);
    }
}
