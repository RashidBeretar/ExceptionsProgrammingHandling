import java.io.EOFException;
import java.io.IOException;
import java.util.Scanner;

public class DZ2 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // System.out.println(T1());
        T2();
    }

    // Реализуйте метод, который запрашивает у пользователя ввод дробного числа
    // (типа float), и возвращает введенное значение. Ввод текста вместо числа не
    // должно приводить к падению приложения, вместо этого, необходимо повторно
    // запросить у пользователя ввод данных.
    static Float T1() {
        while (true) {
            System.out.println("Введите число для поиска: ");
            if (scanner.hasNextFloat()) {
                Float scannerFloat = scanner.nextFloat();
                return scannerFloat;
            } else {
                System.out.println("Вы ввели не то, повторите ввод");
                scanner.nextLine();
            }
        }
    }

    // Разработайте программу, которая выбросит Exception, когда пользователь вводит
    // пустую строку. Пользователю должно показаться сообщение, что пустые строки
    // вводить нельзя.
    public static void T2() {
        try {
            System.out.println("Вы ввели: " + scannerStringNotEmpty());
        } catch (MyStringDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String scannerStringNotEmpty() throws MyStringDataException {
        System.out.print("Введите строку: ");
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            throw new MyStringDataException("Пустые строки вводить нельзя!");
        } else {
            return input;
        }
    }
}

class MyStringDataException extends Exception {
    public MyStringDataException(String message) {
        super(message);
    }
}
