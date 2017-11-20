package br.com.empretek.inventory.domain.data;

public class InvalidDataException extends Exception {

  private static final long serialVersionUID = 6340784296372150446L;

  public InvalidDataException() {
    super();
  }

  public InvalidDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public InvalidDataException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidDataException(String message) {
    super(message);
  }

  public InvalidDataException(Throwable cause) {
    super(cause);
  }
}
