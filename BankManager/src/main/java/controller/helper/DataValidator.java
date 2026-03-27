package controller.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DataValidator {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static int getValidIntRange(String label, int min, int max) {
        while (true) {
            int value = getValidPositiveInt(label); // Tận dụng hàm cũ đã có
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Lỗi: Vui lòng chỉ chọn từ " + min + " đến " + max + "!");
        }
    }

    public static String getValidString(String label) {
        while (true) {
            System.out.print(label);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Lỗi: Trường này là bắt buộc, không được để trống!");
        }
    }

    public static String getValidDate(String label) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);

        while (true) {
            System.out.print(label);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Lỗi: Ngày tháng không được để trống!");
                continue;
            }

            try {
                sdf.parse(input);
                return input;
            } catch (ParseException e) {
                System.out.println("Lỗi: Định dạng ngày không hợp lệ (Phải là dd/mm/yyyy)!");
            }
        }
    }

    public static double getValidPositiveDouble(String label) {
        while (true) {
            try {
                System.out.print(label);
                double value = Double.parseDouble(scanner.nextLine());
                if (value > 0) {
                    return value;
                }
                System.out.println("Lỗi: Giá trị phải là số dương (> 0)!");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập định dạng số!");
            }
        }
    }

    public static int getValidPositiveInt(String label) {
        while (true) {
            try {
                System.out.print(label);
                int value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                }
                System.out.println("Lỗi: Giá trị phải là số nguyên dương!");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên!");
            }
        }
    }

    public static boolean getConfirm(String label) {
        while (true) {
            System.out.print(label + " (Yes/No hoặc Y/N): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            }

            System.out.println("Lỗi: Vui lòng chỉ nhập Yes/No hoặc Y/N!");
        }
    }
}
