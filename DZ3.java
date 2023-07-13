import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DZ3 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        IWantExcellentRating();
    }

    // Напишите приложение, которое будет запрашивать у пользователя следующие
    // данные, разделенные пробелом:
    // Фамилия Имя Отчество номертелефона

    // Форматы данных:
    // фамилия, имя, отчество - строки
    // номертелефона - целое беззнаковое число без форматирования

    // Ввод всех элементов через пробел

    // Приложение должно проверить введенные данные по количеству. Если количество
    // не совпадает с требуемым, вернуть код ошибки, обработать его и показать
    // пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

    // Приложение должно попытаться распарсить полученные значения и выделить из них
    // требуемые параметры. Если форматы данных не совпадают, нужно бросить
    // исключение, соответствующее типу проблемы. Можно использовать встроенные типы
    // java и создать свои. Исключение должно быть корректно обработано,
    // пользователю выведено сообщение с информацией, что именно неверно.

    // Если всё введено и обработано верно, должен создаться файл с названием,
    // равным фамилии, в него в одну строку должны записаться полученные данные,
    // вида

    // <Фамилия><Имя><Отчество><номер_телефона>

    // Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

    // Не забудьте закрыть соединение с файлом.

    // При возникновении проблемы с чтением-записью в файл, исключение должно быть
    // корректно обработано, пользователь должен увидеть стектрейс ошибки.
    public static void IWantExcellentRating() {
        String[] strAr = scannerData();

        for (int i = 0; i < strAr.length; i++) {
            System.out.println(strAr[i]);
        }

        // Если всё введено и обработано верно, должен создаться файл с названием,
        // равным фамилии, в него в одну строку должны записаться полученные данные,
        // вида
        // <Фамилия><Имя><Отчество><номер_телефона>
        // Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
        // Не забудьте закрыть соединение с файлом.
        // При возникновении проблемы с чтением-записью в файл, исключение должно быть
        // корректно обработано, пользователь должен увидеть стектрейс ошибки.
        Boolean f_data_added = false;
        f_data_added = addFileData(strAr);
        if (f_data_added)
            System.out.println("Данные внесены");
    }

    static String[] scannerData() {
        while (true) {
            System.out.println("Введите данные: ");
            String str = scanner.nextLine();
            String[] strAr = str.split(" ");

            // Приложение должно проверить введенные данные по количеству. Если количество
            // не совпадает с требуемым, вернуть код ошибки, обработать его и показать
            // пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
            int codeResult = wordsCnt(strAr, 4);
            if (codeResult == -1) {
                System.out.println("Введено неверное количество слов");
                System.out.println();
                continue;
            }

            // Приложение должно попытаться распарсить полученные значения и выделить из них
            // требуемые параметры. Если форматы данных не совпадают, нужно бросить
            // исключение, соответствующее типу проблемы. Можно использовать встроенные типы
            // java и создать свои. Исключение должно быть корректно обработано,
            // пользователю выведено сообщение с информацией, что именно неверно.
            try {
                scannerValidData(strAr);
                System.out.println("Вы ввели корректные данные");
            } catch (MyValidDataException e) {
                System.out.println(e.getMessage());
                e.printStackTrace(); // пользователь должен увидеть стектрейс ошибки
                System.out.println();
                continue;
            }

            return strAr;
        }
    }

    static int wordsCnt(String[] strAr, int needCnt) {
        if (strAr.length != needCnt) {
            return -1;
        }
        return needCnt;
    }

    static Boolean addFileData(String[] strAr) {
        Boolean f_data_added = false;

        File file = new File(strAr[0]);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Не удалось создать файл " + strAr[0]);
                e.printStackTrace(); // пользователь должен увидеть стектрейс ошибки
                return f_data_added;
            }
        }

        try {
            String text = "<" + strAr[0] + "><" + strAr[1] + "><" + strAr[2] + "><" + strAr[3] + ">\n";
            Files.write(Paths.get(strAr[0]), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Не удалось дописать в файл " + strAr[0]);
            e.printStackTrace(); // пользователь должен увидеть стектрейс ошибки
            return f_data_added;
        }

        f_data_added = true;

        return f_data_added;
    }

    public static void scannerValidData(String[] strAr) throws MyValidDataException {
        Boolean f_f_ok = false;
        Boolean f_i_ok = false;
        Boolean f_o_ok = false;
        Boolean f_p_ok = false;

        Pattern pattern = Pattern.compile("^[-а-яА-ЯёЁ]+$");
        Matcher matcher = pattern.matcher(strAr[0]);
        if (matcher.find()) {
            f_f_ok = true;
        } else {
            pattern = Pattern.compile("^[a-zA-Z]+$");
            matcher = pattern.matcher(strAr[0]);
            if (matcher.find()) {
                f_f_ok = true;
            }
        }

        pattern = Pattern.compile("^[-а-яА-ЯёЁ]+$");
        matcher = pattern.matcher(strAr[1]);
        if (matcher.find()) {
            f_i_ok = true;
        } else {
            pattern = Pattern.compile("^[a-zA-Z]+$");
            matcher = pattern.matcher(strAr[1]);
            if (matcher.find()) {
                f_i_ok = true;
            }
        }

        pattern = Pattern.compile("^[-а-яА-ЯёЁ]+$");
        matcher = pattern.matcher(strAr[2]);
        if (matcher.find()) {
            f_o_ok = true;
        } else {
            pattern = Pattern.compile("^[a-zA-Z]+$");
            matcher = pattern.matcher(strAr[2]);
            if (matcher.find()) {
                f_o_ok = true;
            }
        }

        f_p_ok = strAr[3].chars().allMatch(x -> Character.isDigit(x));

        if (!(f_f_ok && f_i_ok && f_o_ok && f_p_ok)) {
            String throwmsg = "Ошибка при вводе: ";
            if (!f_f_ok)
                throwmsg += "Фамилия ";
            if (!f_i_ok)
                throwmsg += "Имя ";
            if (!f_o_ok)
                throwmsg += "Отчество ";
            if (!f_p_ok)
                throwmsg += "Телефон";

            throw new MyValidDataException(throwmsg);
        }
    }
}

class MyValidDataException extends Exception {
    public MyValidDataException(String message) {
        super(message);
    }
}
