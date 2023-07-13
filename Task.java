import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Task
 */
public class Task {
    public static void main(String[] args) {
        // task1();
        // task2(new String[]{"1", "2", "3", "4", null, "5"}, 4);
        try{
            task3("C:\\test\\test.txt");
        }
        catch (MyFileNotFoundException e){
            System.out.printf("%s : %s", e.getMessage(), e.getFileName());
        }
    }

    //  *     1.  Создайте класс исключения, который будет выбрасываться при делении на
    //  *         0. Исключение должно отображать понятное для пользователя сообщение
    //  *         об ошибке.

    static void task1(){
        try{
            int a = 10/0;
        }
        catch (ArithmeticException e){
            throw new DivisionByZeroException("Ошибка деления на 0");
        }
    }

    // *     2.  Создайте класс исключений, которое будет возникать при обращении к
    // *         пустому элементу в массиве ссылочного типа. Исключение должно
    // *         отображать понятное для пользователя сообщение об ошибке

    static void task2(String[] arr, int index){
        try{
            if (arr[index] == null){
                throw new NullElementArrayException("Элемент массива не проинициализирован", index);
            }
            else{
                System.out.println(arr[index]);
            }
        }
        catch (NullElementArrayException e){
            System.out.println(e.getMessage() + "   " + e.getIndex());
        }
    }

    // *     3.  Создайте класс исключения, которое будет возникать при попытке открыть
    // *         несуществующий файл. Исключение должно отображать понятное для
    // *         пользователя сообщение об ошибке.

    static void task3(String fileName) throws MyFileNotFoundException{
        // FileReader fileReader = null;
        try(FileReader fileReader = new FileReader(fileName)){
            // fileReader = new FileReader(fileName);
            char[] buf = new char[10]; //1,2,3,4,5,6,7,8,9,10       11,12,13,14,15
            fileReader.read(buf);           //buf =  11,12,13,14,15,6,7,8,9,10
            for (int i = 0; i < buf.length; i++) {
                System.out.println(buf[i]);
            }
        }
        catch (FileNotFoundException e){
            throw new MyFileNotFoundException("Файл не найден", fileName);
        }
        catch (Exception e){
            System.out.println("Ошибка чтения");
        }
        // finally{
        //     try{
        //         fileReader.close();
        //     }
        //     catch (Exception e){
        //         System.out.println(e.getMessage());
        //     }
        // }
    }

}

class DivisionByZeroException extends ArithmeticException{
    public DivisionByZeroException(String mess){
        super(mess);
    }
}

class NullElementArrayException extends RuntimeException{
    private int index;

    public NullElementArrayException(String mess, int index){
        super(mess);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}

class MyFileNotFoundException extends Exception{
    private String fileName;

    public MyFileNotFoundException(String mess, String fileName){
        super(mess);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}