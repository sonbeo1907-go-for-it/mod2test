package service.impl;

import entity.BankAccount;
import exception.BankAccountAlreadyExistException;
import exception.NotFoundBankAccountException;
import repository.BankAccountRepository;
import repository.impl.BankAccountRepositoryImpl;
import service.BankAccountService;

import java.util.List;
import java.util.stream.Collectors;

public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository = new BankAccountRepositoryImpl();

    @Override
    public List<BankAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public void add(BankAccount account) {
        BankAccount accountCheck = repository.findByCode(account.getAccountCode());
        if (accountCheck != null){
            throw new BankAccountAlreadyExistException("Tài khoản ngân hàng đã tồm tại.");
        }
        int id = repository.getNextId();
        account.setId(id);
        repository.add(account);
    }

    @Override
    public void delete(String accountCode) throws NotFoundBankAccountException {
        this.findByCode(accountCode);
        repository.delete(accountCode);
    }

    @Override
    public List<BankAccount> search(String keyword) {
        String lowerKeyword = keyword.toLowerCase();

        return repository.findAll().stream()
                .filter(acc -> acc.getAccountCode().toLowerCase().contains(lowerKeyword) ||
                        acc.getAccountHolderName().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    @Override
    public void findByCode(String accountCode){
        BankAccount account = repository.findByCode(accountCode);

        if (account==null){
            throw new NotFoundBankAccountException("Tài khoản ngân hàng không tồm tại.");
        }


    }
}
