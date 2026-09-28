package com.example.ticketEvent.exceptions;

public class TicketEventException extends RuntimeException{
    
    public TicketEventException() {
  }

  public TicketEventException(String message) {
    super(message);
  }

  public TicketEventException(String message, Throwable cause) {
    super(message, cause);
  }

  public TicketEventException(Throwable cause) {
    super(cause);
  }

  public TicketEventException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
