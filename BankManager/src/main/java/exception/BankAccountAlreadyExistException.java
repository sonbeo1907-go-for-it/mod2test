package exception;

public class BankAccountAlreadyExistException extends BankManagerException {
    public BankAccountAlreadyExistException(String message) {
        super(message);
    }
}
