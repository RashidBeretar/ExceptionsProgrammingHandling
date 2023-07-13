/**
    Создайте класс Счетчик, у которого есть метод add(), увеличивающий
    значение внутренней int переменной на 1. Сделайте так, чтобы с объектом
    такого типа можно было работать в блоке try-with-resources. Подумайте, что
    должно происходить при закрытии этого ресурса? Напишите метод для
    проверки, закрыт ли ресурс. При попытке вызвать add() у закрытого
    ресурса, должен выброситься IOException.
 */

public class Task2 {
    public static void main(String[] args) {
        try (Counter counter1 = new Counter(3)){
            System.out.printf("Текущее значение счётчика %s \n", counter1);
            counter1.add();
            // counter1.closeCounter();
            counter1.add();
            System.out.printf("Текущее значение счётчика %s \n", counter1);
        }
        catch (CloseCounterException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }
}


class Counter implements AutoCloseable{
    private int counter;
    private boolean isOpen;

    public Counter(){
        this.isOpen = true;
        this.counter = 0;
    }

    public Counter(int count){
        this.counter = count;
        this.isOpen = true;
    }

    public int add() throws CloseCounterException{
        if (!isOpen){
            throw new CloseCounterException("Счётчик закрыт");
        }
        return ++counter;
    }

    public void closeCounter(){
        this.isOpen = false;
    }

    public void openCounter(){
        this.isOpen = true;
    }

    @Override
    public void close(){
        closeCounter();
        // this.isOpen = false;
    }

    @Override
    public String toString(){
        return ""+this.counter;
    }
}



class CloseCounterException extends Exception{
    public CloseCounterException(String mess){
        super(mess);
    }
}