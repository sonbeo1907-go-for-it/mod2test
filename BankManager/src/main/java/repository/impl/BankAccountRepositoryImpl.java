package repository.impl;

import entity.BankAccount;
import persistance.csvManager.CSVReader;
import persistance.csvManager.CSVWriter;
import repository.BankAccountRepository;

import java.util.List;

public class BankAccountRepositoryImpl implements BankAccountRepository {

    private final String FILE_PATH = "data/bank_accounts.csv";
    private final CSVReader csvReader = new CSVReader();
    private final CSVWriter csvWriter = new CSVWriter();

    @Override
    public List<BankAccount> findAll() {
        return csvReader.read(FILE_PATH);
    }

    @Override
    public void add(BankAccount account) {
        csvWriter.append(FILE_PATH,account);
    }

    @Override
    public void delete(String accountCode) {
        List<BankAccount> accounts = findAll();
        accounts.removeIf(acc -> acc.getAccountCode().equals(accountCode));
        csvWriter.saveAll(FILE_PATH,accounts);
    }

    @Override
    public BankAccount findByCode(String accountCode) {
        return findAll().stream()
                .filter(acc -> acc.getAccountCode().equals(accountCode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int getNextId() {
        List<BankAccount> accounts = findAll();
        if (accounts.isEmpty()) {
            return 1;
        }
        return accounts.getLast().getId() + 1;
    }
}
