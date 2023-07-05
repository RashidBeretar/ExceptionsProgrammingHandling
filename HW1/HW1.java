package HW1;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class HW1 {
    static Random random = new Random();

    public static void main(String[] args) {
        // T1();
        // T2();
        T3();
    }

    // Реализуйте 3 метода, чтобы в каждом из них получить разные исключения
    static void T1() {
        // MethodForError1();
        // MethodForError2();
        // MethodForError3();
    }

    static void MethodForError1() {
        int a = 1;
        int b = 0;
        System.out.println(a / b);
    }

    static void MethodForError2() {
        int arr[] = new int[2];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        System.out.println(Arrays.toString(arr));
    }

    static void MethodForError3() {
        // int a = 1 + "3";
        // System.out.println(a);
    }

    // Реализуйте метод, принимающий в качестве аргументов два целочисленных
    // массива, и возвращающий новый массив, каждый элемент которого равен разности
    // элементов двух входящих массивов в той же ячейке. Если длины массивов не
    // равны, необходимо как-то оповестить пользователя.
    static void T2() {
        int[] arr1 = generateArray();
        int[] arr2 = generateArray();

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        int[] arr = differencesElementsArrays(arr1, arr2);

        // обработка ошибок
        String  errCode = "";

        if (arr == null) errCode = "differentlength";

        if(errCode != ""){
            if (errCode == "differentlength") {
                System.out.println("Ошибка! Длины массивов не равны");
                return;
            }
        }
        // /обработка ошибок

        System.out.println(Arrays.toString(arr));
    }

    static int[] generateArray() {
        int[] arr = new int[random.nextInt(2) + 4];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
        }
        return arr;
    }

    static int[] differencesElementsArrays(int[] arr1, int[] arr2){
        if (arr1.length != arr2.length) return null;

        int[] arr = new int[arr1.length];

        for (int i = 0; i < arr1.length; i++) {
            arr[i] = arr1[i] - arr2[i];
        }
        return arr;
    }

    // * Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив, каждый элемент которого равен частному элементов двух входящих массивов в той же ячейке. Если длины массивов не равны, необходимо как-то оповестить пользователя. Важно: При выполнении метода единственное исключение, которое пользователь может увидеть - RuntimeException, т.е. ваше.
    static void T3() {
        int[] arr1 = generateArray();
        int[] arr2 = generateArray();
        
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        int[] arr = divisionElementsArrays(arr1, arr2);

        System.out.println(Arrays.toString(arr));
    }
    
    static int[] divisionElementsArrays(int[] arr1, int[] arr2){
        if (arr1 == null) throw new RuntimeException("Ошибка! Первый массив не задан");
        if (arr2 == null) throw new RuntimeException("Ошибка! Второй массив не задан");

        if (arr1.length != arr2.length) throw new RuntimeException("Ошибка! Длины массивов не равны");

        int[] arr = new int[arr1.length];

        for (int i = 0; i < arr1.length; i++) {
            if (arr2[i] == 0) throw new RuntimeException(String.format("Ошибка! Нельзя делить на 0. Некоректное значение во втором массиве по индексу: %d", i));
            
            arr[i] = arr1[i] / arr2[i];
        }
        return arr;
    }
}
