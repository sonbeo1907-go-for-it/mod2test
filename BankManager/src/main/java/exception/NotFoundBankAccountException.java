package exception;

public class NotFoundBankAccountException extends RuntimeException {
  public NotFoundBankAccountException(String message) {
    super(message);
  }
}
