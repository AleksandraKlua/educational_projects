package filemanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Directory {
    /*данный метод исползуется для вывода содержимого любого 
    каталога и для перемещения по каталогам файлового дерева. при обнаружении каталога
    он открывается и на вывод поступают все содержащиемя в нем файлы с указанием, является ли
    он папкой или файлом.*/
    void directory(File file){
        if(file.isDirectory()) {
            for(File item : file.listFiles()){
                if(item.isDirectory()){
                    System.out.println(item.getName() + "  \tкаталог");
                }
                else{
                    System.out.println(item.getName() + "\tфайл");
                }
            }
        }
        if (!file.exists()) {
            System.out.println("Директория не найдена!");
        }
    }
    
    /*метод для перемещения и возможности переменования файла из любой папку в 
    другую папке. если файл перенесен, то выводится сообщение об успешной операции. 
    Также учитывается, что файла по указанному адресу может не существовать*/
    void change_directory() { 
        Scanner f = new Scanner(System.in);
        Scanner fa = new Scanner(System.in);
        System.out.println("Введите имя файла для просмота его пути: ");
        String na = f.nextLine();
        File file = new File(na);
        if (file.exists()){
            System.out.println ( "Абсолютный путь: " + file.getAbsolutePath());
            System.out.println("Введите новый путь и имя файла (можно изменить): ");
            String newdir = fa.nextLine();
            try {
                Path temp = Files.move
                (Paths.get(file.getAbsolutePath()), Paths.get(newdir));
            } catch (IOException ex) {
            }
            System.out.println("Успешно!");
        }
        else {
            System.out.println ("Файла с таким именем не существует!");
        }
    }
    
    /*при создании каталогая учитываются пожелания пользователя: 
    создать каталог по определенному пути или создать каталог в текущей папке 'FileManger';
    пользователь может ввести неверную команду или несуществующий путь*/
    void create_directory () { 
        Scanner f = new Scanner(System.in);
        System.out.println ("Для создания каталога в определенной папке введите адрес и название каталога,\nДля создания каталога в текущей  папке - только название");
        System.out.println("Введите: ");
        String name = f.nextLine();
        File file = new File(name);
        if (file.exists()){
            System.out.println("Каталог с таким именем уже существует!");
        }
        else {
            file.mkdir();
            System.out.println("Каталог успешно создан."); 
        }
    }
}
