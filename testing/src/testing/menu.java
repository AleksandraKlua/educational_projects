package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class menu {
    public void menu () throws IOException, FileNotFoundException, InterruptedException{ //метод для отображения пунктов меню с интсрукцией перед началом теста
        Scanner scan = new Scanner (System.in);
        String command = "";
        while (!command.equals("3")) {
                System.out.println("\n   МЕНЮ");
                System.out.println("\n  1 - Начать тестирование\n  2 - Открыть файл с результатами \n  3 - Выйти");
                System.out.print ("   Нажмите: ");
                command = scan.nextLine();
                while (!command.matches("[123]")){
                    System.out.print ("  Пожалуйста, введите, существующий пункт меню: ");
                    command = scan.nextLine();
                }
                switch (Integer.valueOf(command)){
                    case 1: //запуск теста после ввода "да"
                        System.out.println ("\n Перед началом тестирования ознакомтесь с инструкцией: ");
                        System.out.println ("\n Вам предстоить ответить на 20 вопросов. На каждый вопрос предусмотрено 4 варианта ответа.\n Каждый вопрос имеет только ОДИН правильный ответ. Перед началом теста вам нужно задать время,\n необходимое для прохождения тестирования.\n Как только время истечет, программа завершится и нужно будет начать сначала.\n Когда тест завершится, на экране вы увидите свои результаты. Их вы так же сможете посмотреть\n в текстовой файле.\n Удачи!!!");
                        System.out.print ("\n Если вы готовы, напишите 'да'\n Для возврата в меню - 0:  ");
                        String answ = scan.nextLine();
                        OUTER:
                        while (!answ.equals("0")) {
                            switch (answ) {
                                case "да":
                                    que que = new que();
                                    que.que();
                                    que.get();
                                    break OUTER;

                                case "0":
                                    break OUTER;
                                default:
                                    System.out.print("Вы ввели недопустимое значение! \nПопробуйте снова: ");
                                    answ = scan.nextLine();
                                    break;
                            }
                        }
                        break;
                    case 2: //открытие файла отчета
                        File file = new File ("отчет.txt");
                        System.out.println ("Открытие файла...");
                        Runtime runtime = Runtime.getRuntime();
                        Process process = runtime.exec ("C:\\Windows\\notepad.exe "+ file);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }
        }
    } 
}
