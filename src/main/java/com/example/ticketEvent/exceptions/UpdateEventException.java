package com.example.ticketEvent.exceptions;

public class UpdateEventException extends TicketEventException {

  public UpdateEventException() {
  }

  public UpdateEventException(String message) {
    super(message);
  }

  public UpdateEventException(String message, Throwable cause) {
    super(message, cause);
  }

  public UpdateEventException(Throwable cause) {
    super(cause);
  }

  public UpdateEventException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}