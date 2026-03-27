package exception;

public class NotFoundBankAccountException extends BankManagerException {
    public NotFoundBankAccountException(String message) {
        super(message);
    }
}
