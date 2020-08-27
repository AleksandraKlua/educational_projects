package testing;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        menu start = new menu();
        System.out.println("  Здравствуйте!\n  Вас привествует программа тестирования!");
        System.out.println("  Тема теста: Наследование в Java");
        try {
            start.menu(); //непосредственное выполнение программы
        } catch (IOException | InterruptedException ex) {
        }
    } 
}