package filemanager;

public class Menu {
    //печать меню
    void print_menu() { 
        System.out.println("\nЧто вы хотите сделать?\n");
        System.out.println("1 - вывести местоположение файла в файловом дереве");
        System.out.println("2 - вывести содержимое каталога");
        System.out.println("3 - переместиться по уровням вложенности каталога");
        System.out.println("4 - вывести свойства выбранного файла");
        System.out.println("5 - удалить выбранный файл");
        System.out.println("6 - создать файл и записать");
        System.out.println("7 - переместить файл в другую папку");
        System.out.println("8 - прочитать содержимое файла с помощью блокнота");
        System.out.println("9 - создать новый каталог");
        System.out.println("\n0 - выйти из программы\n");
    }
}
