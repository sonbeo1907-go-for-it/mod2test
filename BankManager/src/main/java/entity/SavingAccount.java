package entity;

public class SavingAccount extends BankAccount{
    private double depositAmount;
    private String depositDate;
    private double interestRate;
    private int term;


    public SavingAccount(int id, String accountCode, String accountHolderName, String createdAt,
                         double depositAmount, String depositDate, double interestRate, int term) {
        super(id, accountCode, accountHolderName, createdAt);
        this.depositAmount = depositAmount;
        this.depositDate = depositDate;
        this.interestRate = interestRate;
        this.term = term;
    }

    @Override
    public String toCSVLine() {
        return String.format("%d,%s,%s,%s,%.1f,%s,%.1f,%d",
                id, accountCode, accountHolderName, createdAt,
                depositAmount, depositDate, interestRate, term);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Deposit: %.1f, Date: %s, Rate: %.1f, Term: %d",
                depositAmount, depositDate, interestRate, term);
    }
}
