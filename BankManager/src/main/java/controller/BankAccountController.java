package controller;

import controller.helper.DataValidator;
import entity.BankAccount;
import entity.PaymentAccount;
import entity.SavingAccount;
import exception.BankAccountAlreadyExistException;
import exception.NotFoundBankAccountException;
import service.BankAccountService;
import service.impl.BankAccountServiceImpl;

import java.util.List;
import java.util.Scanner;

public class BankAccountController {
    private final BankAccountService service = new BankAccountServiceImpl();

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n---- CHƯƠNG TRÌNH QUẢN LÝ TÀI KHOẢN NGÂN HÀNG ----");
            System.out.println("1. Thêm mới");
            System.out.println("2. Xóa");
            System.out.println("3. Xem danh sách");
            System.out.println("4. Tìm kiếm");
            System.out.println("5. Thoát");
            try {
                choice = DataValidator.getValidIntRange("Chọn chức năng: ",1,5);
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1: addNewAccount(); break;
                case 2: deleteAccount(); break;
                case 3: showAllAccounts(); break;
                case 4: searchAccounts(); break;
                case 5: System.out.println("Tạm biệt!"); break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 5);
    }

    private void addNewAccount() {
        try {
            int type = DataValidator.getValidIntRange("Chọn loại tài khoản: 1. Tiết kiệm | 2. Thanh toán: ",1,2);

            String code = DataValidator.getValidString("Nhập mã tài khoản: ");
            String name = DataValidator.getValidString("Nhập tên chủ tài khoản: ");
            String date = DataValidator.getValidDate("Nhập ngày tạo (dd/mm/yyyy): ");

            if (type == 1) {
                double deposit = DataValidator.getValidPositiveDouble("Nhập số tiền gửi: ");
                String depositDate = DataValidator.getValidDate("Nhập ngày gửi (dd/mm/yyyy): ");
                double rate = DataValidator.getValidPositiveDouble("Nhập lãi suất: ");
                int term = DataValidator.getValidIntRange("Nhập kỳ hạn (tháng): ",1,12);

                service.add(new SavingAccount(0, code, name, date, deposit, depositDate, rate, term));
            } else {
                String cardNumber = DataValidator.getValidString("Nhập số thẻ: ");
                double balance = DataValidator.getValidPositiveDouble("Nhập số tiền trong tài khoản: ");

                service.add(new PaymentAccount(0, code, name, date, cardNumber, balance));
            }
            System.out.println("Thêm mới thành công!");
        } catch (BankAccountAlreadyExistException e){
            System.err.println(e.getMessage());
        }
    }

    private void deleteAccount() {

        try {
            String code = DataValidator.getValidString("Nhập mã tài khoản cần xóa: ");
            service.findByCode(code);
            if (DataValidator.getConfirm("Bạn có chắc chắn muốn xóa tài khoản " + code + "?")) {
                service.delete(code);
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Đã hủy thao tác xóa.");
            }

        } catch (NotFoundBankAccountException e) {
            System.err.println(e.getMessage());
        }
    }

    private void showAllAccounts() {
        List<BankAccount> list = service.findAll();
        if (list.isEmpty()) {
            System.out.println("Danh sách trống.");
        } else {
            list.forEach(System.out::println); // Gọi toString() đã override
        }
    }

    private void searchAccounts() {
        String keyword = DataValidator.getValidString("Nhập mã hoặc tên cần tìm: ");
        List<BankAccount> results = service.search(keyword);

        if (results.isEmpty()) {
            System.out.println("Không tìm thấy kết quả.");
        } else {
            results.forEach(System.out::println);
        }
    }

}
