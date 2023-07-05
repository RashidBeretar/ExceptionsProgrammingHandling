import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

public class S1 {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        // System.out.printf("Длина массива: %d \n", T1(new int[] { 2, 3, 4, 5, 98 }));
        // T2();
        // T3();
        T4();
    }

    static int T1(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        return arr.length;
    }

    static void T2() {
        while (true) {
            System.out.println("Введите число для поиска: ");
            if (scanner.hasNextInt()) {
                int searchNumber = scanner.nextInt();
                int[] array = new int[random.nextInt(5) + 1];
                if (random.nextInt(2) == 0) {
                    array = null;
                }
                if (array != null) {
                    for (int i = 0; i < array.length; i++) {
                        array[i] = random.nextInt(10);
                        System.out.printf("%d \t", array[i]);
                    }
                    System.out.println();
                }

                int codeResult = processArray(array, searchNumber);

                if (codeResult == -1) {
                    System.out.println("Длина массива < 3 элементов");
                } else if (codeResult == -2) {
                    System.out.println("Элемент не найден:");
                } else if (codeResult == -3) {
                    System.out.println("Массив некорректно проинициализирован");
                } else {
                    System.out.println("Массив успешно обработан");
                    System.out.printf("Элемент найден по индексу: %d\n", codeResult);
                }
            } else {
                System.out.println("Вы ввели не число, повторите ввод:");
                scanner.nextInt();
            }
        }
    }

    static int processArray(int[] arr, int searchNumber) {
        if (arr == null) {
            return -3;
        }
        if (arr.length < 3) {
            return -1;
        }
        Arrays.sort(arr);
        int searchRes = Arrays.binarySearch(arr, searchNumber);
        for (int el : arr) {
            System.out.printf("%d\t", el);
        }
        System.out.println();
        if (searchNumber < 0) {
            return -2;
        }
        return searchRes;
    }

    static void T3() {
        for (int i = 0; i < 5; i++) {
            System.out.printf("\n*** Итерация %d ***\n\n", i + 1);
            var arr = generateArray();
            System.out.println(Arrays.deepToString(arr));
            processArray(arr);
        }
    }

    static void processArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != arr.length) {
                throw new RuntimeException("Некорректная размерность массива");
            }
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0 && arr[i][j] != 1) {
                    throw new RuntimeException(String.format("Некорректное значение массива по индексу %d %d", i, j));
                } else {
                    sum += arr[i][j];
                }
            }
        }
        System.out.printf("Сумма элементов массива: %d\n", sum);
    }

    static int[][] generateArray() {
        int[][] arr = new int[random.nextInt(2) + 4][5];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt(2);
                if (random.nextInt(1000) == 0) {
                    arr[i][j] = 2; // для ошибки
                }
            }
        }
        return arr;
    }

    static void T4() {
        for (int i = 0; i < 5; i++) {
            System.out.printf("\n*** Итерация %d ***\n\n", i + 1);
            var arr = generateArray();
            System.out.println(Arrays.deepToString(arr));
            int errCode = processArrayV2(arr);
            if (errCode == -1) {
                System.out.println("Некорректная размерность массива");
            } else if (errCode == -2) {
                // System.out.printf("Некорректное значение массива по индексу %d %d", i, j);
                System.out.println("Некорректное значение массива по индексу");
            } else {
                System.out.printf("Сумма элементов массива: %d\n", errCode);
            }
        }
    }

    static int processArrayV2(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != arr.length) {
                return -1;
            }
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0 && arr[i][j] != 1) {
                    return -2;
                } else {
                    sum += arr[i][j];
                }
            }
        }
        return sum;
    }
}
