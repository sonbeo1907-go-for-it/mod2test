package repository;

import entity.BankAccount;
import java.util.List;

public interface BankAccountRepository {
    List<BankAccount> findAll();
    void add(BankAccount account);
    void delete(String accountCode);
    BankAccount findByCode(String accountCode);
    int getNextId();
}
