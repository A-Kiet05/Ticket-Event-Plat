package com.example.ticketEvent.config;

public class QrCodeGenerateException extends RuntimeException {
    
    public QrCodeGenerateException() {
        super();
    }

    public QrCodeGenerateException(String message) {
        super(message);
    }

    public QrCodeGenerateException(String message, Throwable cause) {
        super(message, cause);
    }

    public QrCodeGenerateException(Throwable cause) {
        super(cause);
    }

    public QrCodeGenerateException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}