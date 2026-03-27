package service;

import entity.BankAccount;
import exception.NotFoundBankAccountException;
import java.util.List;

public interface BankAccountService {
    List<BankAccount> findAll();
    void add(BankAccount account);
    void delete(String accountCode) throws NotFoundBankAccountException;
    List<BankAccount> search(String keyword);
    void findByCode(String accountCode);
}
