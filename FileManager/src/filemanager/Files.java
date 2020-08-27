package filemanager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Scanner;

public class Files {
    /*ищет файл в дереве или по любой другой указанной директории
        на основании сравнении введенного имени файла пользователем и содержимого всех 
        каталогов заданной директории. результатом будет вывод адреса файла*/
    void findFile(String name,File file) { 
        File[] list = file.listFiles();
        if(list!=null) {
            for (File fil : list) {   
                if (fil.isDirectory()) {   
                    findFile(name,fil);
                    if (name.equals(fil.getName())){
                        System.out.println("Абсолютный путь: " + fil.getAbsolutePath());
                    }
                }
                else if (name.equals(fil.getName())) {
                     System.out.println("Абсолютный путь: " + fil.getAbsolutePath());
                } 
            }
        }
    }
    
    /*в методе для создания файлов учитывается ряд условий:
    а) файл может быть создан по определенному адресу или в текущем каталоге 'FileManger';
    б) по указанному адресу может существовать файл с таким же именем;
    в) если файл создан - выводится соответсвующее сообщение;
    г) в файл можно записать любой текст при желании пользователя.
    Также учитывается, что пользователь может совершить ошибки при вводе.*/
    void create_file() throws IOException {
        Scanner f = new Scanner(System.in);
        System.out.println ("Для создания файла в определенной папке введите адрес и имя файла,\nдля создания файла в текущей введите только имя");
        System.out.println("Введите: ");
        String na = f.nextLine();
        File file = new File(na);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("Файл создан.");
        }
        else {
            System.out.println("Файл с таким именем уже существует.");
        }
        System.out.println ("Хотите записать что-то в файл?\nY/N");
        String answer = f.nextLine();
        switch (answer) {
            case "Y":
                System.out.println ("Введите то, что хотите записать: ");
                String text = f.nextLine();
                OutputStream fil = new FileOutputStream(file.getName(),true);
                fil.write(text.getBytes());
                fil.close();
                System.out.println ("Успешно!");
                break;
            case "N":
                System.out.println();
                break;
            default:
                System.out.println("Неверная команда!");
                break;
        }
    }
    
    /*файл открывается с помощью блокнота. для того чтобы открыть блокнот, 
    указан его путь. можно открыть файл из текущей папки или по определенному 
    адресу. учитывается, что файл с указанным именем может не существовать 
    по указанному адресу*/
    void open_file(){
        Scanner f = new Scanner(System.in);
        System.out.println ("Введите названия файла: ");
        String na = f.nextLine();
        File file = new File(na);
        if (!file.exists()) {
            System.out.println("Файла с таким именем не существует!"); 
        }
        else {
            Runtime runtime = Runtime.getRuntime();
            try {
                Process process = runtime.exec ("C:\\Windows\\notepad.exe "+ file.getAbsolutePath());
            } catch (IOException ex) {
            }
        }
    }
    
    /*удалить файл можно в текущей папке или по любому пути. учитывается, 
    что файл с указанным именем может не существовать по указанному адресу*/
    void delete_file(){ 
        Scanner n = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String na = n.nextLine();
        File file = new File(na);
        if (!file.exists()) {
            System.out.println("Файла с таким именем не существует!"); 
        }
        else {
            file.delete();
            System.out.println("Успешно!");
        }
    }
    
    /*получение свойств файла или каталога, расположенного в текущей папке или 
    по любому другому адресу. переменная даты необходима для преобразования 
    полученных значений с помощью метода .lastModified()*/
    void file_properties(){ 
        Scanner f = new Scanner(System.in);
        System.out.println("Введите имя файла или имя и его адрес: ");
        String na = f.nextLine();
        File file = new File(na);
        long m = file.lastModified();
        Date d = new Date(m);
        if (!file.exists()){
            System.out.println ("Файла с таким именем не существует!");
        }
        else {
            System.out.println ( "Имя файла: " + file.getName());
            System.out.println ( "Абсолютный путь: " + file.getAbsolutePath()) ;
            System.out.println ( "Корневой каталог: " + file.getParent());
            System.out.println ( file.canWrite()? "Доступен для записи." : "Не доступен для записи.") ;
            System.out.println ( file.canRead()? "Доступен для чтения." : "Не доступен для чтения.") ;
            System.out.println ( file.isDirectory()? "Является каталогом." : "Не является каталогом.") ;
            System.out.println ( file.isFile()? "Является обычным файлом." : "Может быть именованным каналом.") ;
            System.out.println (file.isAbsolute()? "Является абсолютным" : "Не вяляется абсолютным");
            System.out.println( "Последния изменения: " + d);
            System.out.println ( "Длина: " + file.length() + " байт" ) ;
        }
    }
}
