package entity;

public abstract class BankAccount {
    protected int id;
    protected String accountCode;
    protected String accountHolderName;
    protected String createdAt;


    public BankAccount() {
    }

    public BankAccount(int id, String accountCode, String accountHolderName, String createdAt) {
        this.id = id;
        this.accountCode = accountCode;
        this.accountHolderName = accountHolderName;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public abstract String toCSVLine();

    @Override
    public String toString(){
        return String.format("ID: %d, Code: %s, Holder: %s, Created At: %s",
                id, accountCode, accountHolderName, createdAt);
    }
}
