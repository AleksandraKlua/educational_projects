package filemanager;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
 
public class FileManager {
    /*главный метод, в котором с помощью меню организуются все прописанные методы, 
    а с помощью цикла 'while' реализуется выбор пунктов меню. как только введен '0'
    осуществляется выход из программы. */
    public static void main(String[] args) { 
        Scanner scan = new Scanner (System.in);
        Scanner fa = new Scanner (System.in);
        System.out.println ("Программа Файловый менеджер\n");
        String com = new String();
        Menu menu = new Menu();
        while (!"0".equals(com)){
            menu.print_menu();
            com = fa.nextLine();
            Directory d = new Directory();
            Files ff = new Files();
            switch (com){
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    System.out.println("Текущее местоположение: " + Paths.get(".").toAbsolutePath().normalize().toString());
                    
                    System.out.println("Введите имя файла: ");
                    String name = scan.nextLine();
                    System.out.println("Введите корневой каталог для поиска: ");
                    String direct = scan.nextLine();
                    File ffile = new File(direct);
                    ff.findFile(name, ffile);
                    break;
                case "2":
                    System.out.println("Текущее местоположение: " + Paths.get(".").toAbsolutePath().normalize().toString());
                    Scanner f = new Scanner(System.in);
                    System.out.println ("Введите путь каталога, который хотите просмотреть: ");
                    String na = f.nextLine();
                    File file = new File(na);
                    d.directory(file);
                    break;
                case "3":
                    System.out.println("Текущее местоположение: " + Paths.get(".").toAbsolutePath().normalize().toString());
                    System.out.println ("\n1 - для возврата в меню\nВведите путь: ");
                    String n = scan.nextLine();
                    File file1 = new File(n);
                    while (!"1".equals(n)) {
                        d.directory(file1);
                        System.out.println ("\n1 - для возврата в меню\n2 <-- вернуться назад\nВведите путь: ");
                        n = scan.nextLine();
                        if ("2".equals(n)){
                            String neway = file1.getParent(); 
                            System.out.println (neway);
                            file1= new File(neway);
                        }
                        else {
                            file1 = new File(n);
                        }
                    } 
                    break;
                case "4":
                    System.out.println("Текущее местоположение: " + Paths.get(".").toAbsolutePath().normalize().toString());
                    ff.file_properties();
                    break;
                case "5":
                    System.out.println("Текущее местоположение: " + Paths.get(".").toAbsolutePath().normalize().toString());
                    ff.delete_file();
                    break;
                case "6":
                    System.out.println("Текущее местоположение: " + Paths.get(".").toAbsolutePath().normalize().toString());
                    try {
                        ff.create_file();
                    } catch (IOException ex) {
                    }
                    break;

                case "7":
                    System.out.println("Текущее местоположение: " + Paths.get(".").toAbsolutePath().normalize().toString());
                    d.change_directory();
                    break;
                case "8":
                    System.out.println("Текущее местоположение: " + Paths.get(".").toAbsolutePath().normalize().toString());
                    ff.open_file();
                    break;
                case "9":
                    System.out.println("Текущее местоположение: " + Paths.get(".").toAbsolutePath().normalize().toString());
                    d.create_directory();
                    break;
                default:
                    System.out.println("Неверная команда!");
                    break;  
            }
        }
    }
}