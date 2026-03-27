package persistance.csvManager;

import entity.BankAccount;
import entity.PaymentAccount;
import entity.SavingAccount;
import persistance.fileManager.DataReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements DataReader<BankAccount> {
    @Override
    public List<BankAccount> read(String path) {
        List<BankAccount> accountList = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) return accountList;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                if (data.length == 8) {
                    accountList.add(new SavingAccount(
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            data[3],
                            Double.parseDouble(data[4]),
                            data[5],
                            Double.parseDouble(data[6]),
                            Integer.parseInt(data[7])
                    ));
                } else if (data.length == 6) {
                    accountList.add(new PaymentAccount(
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            Double.parseDouble(data[5])
                    ));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Lỗi đọc file hoặc định dạng dữ liệu: " + e.getMessage());
        }
        return accountList;

    }
}
