package exception;

public class BankAccountAlreadyExistException extends RuntimeException {
  public BankAccountAlreadyExistException(String message) {
    super(message);
  }
}
