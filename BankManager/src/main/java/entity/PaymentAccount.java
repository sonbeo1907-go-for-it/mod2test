package entity;

public class PaymentAccount extends BankAccount{
    private String cardNumber;
    private double balance;

    public PaymentAccount(int id, String accountCode, String accountHolderName, String createdAt,
                          String cardNumber, double balance) {
        super(id, accountCode, accountHolderName, createdAt);
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    @Override
    public String toCSVLine() {
        return String.format("%d,%s,%s,%s,%s,%.1f",
                id, accountCode, accountHolderName, createdAt,
                cardNumber, balance);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Card Number: %s, Balance: %.1f",
                cardNumber, balance);
    }
}
