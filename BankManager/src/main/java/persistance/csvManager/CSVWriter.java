package persistance.csvManager;

import entity.BankAccount;
import persistance.fileManager.DataWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter implements DataWriter<BankAccount> {
    @Override
    public void saveAll(String path, List<BankAccount> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (BankAccount account : data){
                bw.write(account.toCSVLine());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi thao tác File CSV: " + e.getMessage());
        }
    }

    @Override
    public void append(String path, BankAccount item) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.newLine();
            bw.write(item.toCSVLine());
        } catch (IOException e) {
            System.err.println("Lỗi thao tác File CSV: " + e.getMessage());
        }
    }
}
