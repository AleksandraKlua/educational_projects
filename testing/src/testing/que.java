package testing;

import testing.menu;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class que {
    public static Timer timer = new Timer();
    public static int queLength;
    public static int count;
    public static Date firstdate;
    public void que() throws FileNotFoundException, IOException, InterruptedException { //метод для получения вопросов из файла, задачи время и вывода вопросов на экран
        Scanner fa = new Scanner(System.in);
        //списки для вычленения вопросов и вариантов ответа из файла
        List <String> lines = new ArrayList<>();  //общий список для всех строк
        List <String> que = new ArrayList<>(); //список для вопросов
        List <String> ans1 = new ArrayList<>(); //списки для вариантов ответа
        List <String> ans2 = new ArrayList<>();
        List <String> ans3 = new ArrayList<>();
        List <String> ans4 = new ArrayList<>();
        List <String> rans = new ArrayList<>(); //список для правильных ответов
        
        File file = new File ("вопросы и ответы.txt");
        if (!file.exists()) {
            System.out.println (" Файл с вопросами не найден!\n Загрузите файл");
            System.exit(0);
        }
        BufferedReader bfr = new BufferedReader (new FileReader(file));
        String str = "";
        while ((str=bfr.readLine())!=null){ //добавление в общий список всех строк из файла
            lines.add(str);
        }
        String[] rows = lines.toArray(new String[lines.size()]); //превращение списка в строковый массив 
        for (String row : rows) { //разделение общего списка на списки ответов
            if (row.contains("Вопрос")) {
                que.add(row);
            }
            if (row.contains("а)")) {
                ans1.add(row);
            }
            if (row.contains("б)")) {
                ans2.add(row);
            }
            if (row.contains("в)")) {
                ans3.add(row);
            }
            if (row.contains("г)")) {
                ans4.add(row);
            }
            if (row.contains("Правильный ответ")) {
                rans.add(row);
            }
        }
        String[] questions = que.toArray(new String[que.size()]);
        String[] answers1 = ans1.toArray(new String[ans1.size()]);
        String[] answers2 = ans2.toArray(new String[ans2.size()]);
        String[] answers3 = ans3.toArray(new String[ans3.size()]);
        String[] answers4 = ans4.toArray(new String[ans4.size()]);
        String[] right_answers = rans.toArray(new String[rans.size()]);
        String[] rw = new String[right_answers.length];
        String mine = new String(); //строка для записи вводимых вариантов ответа 
        queLength = questions.length;
        get_res name = new get_res();
        name.flname();
        firstdate = new Date();
        firstdate.setSeconds(firstdate.getSeconds() + 5); //необходимо добавить 5 сек, которые тратятся на отсчет
        name.time(firstdate);
        timer = new Timer();
        get_res.getTime getTime = new get_res().new  getTime();
        timer.schedule(getTime, 0, 1000); //таймер для проверки времени каждую секунду
        System.out.println("\nТест начнется через 5 секунд"); 
        try {
            for (int i=5; i>0; i--) {//задержка программы на 5 секунд
                System.out.println(i);
                Thread.sleep(1000); 
            }
        } catch (InterruptedException ex) {
                System.out.println("Поток прерван!");
        }
        for (int i=0; i<questions.length; i++){ //цикл для вывода вопросов и варинтов ответов, правильных ответов с учетом ошибок пользователя при вводе (возможен ввод только букв а,б,в,г), с каждым новым вопросом выводится значение оставшегося времени, а после ввода допустимого варианта ответа - правильный ответ
            System.out.println ("\nДля выхода напишите 'выход' в ответе");
            name.new_time(firstdate);
            System.out.println ("\n"  + questions[i]);
            System.out.println (answers1[i]+"\n"+answers2[i]+"\n"+answers3[i]+"\n"+answers4[i]);
            System.out.print ("Ваш ответ: ");
            mine  = fa.nextLine();
            if (mine.equals("выход")){
                menu menu = new menu();
                menu.menu();
                break;
            }
            while (!mine.matches("[абвг]") || mine.matches("\\d+") || !mine.equals("выход")){ 
                if (mine.equals("выход")){
                    break;
                } else if (mine.matches("\\d+")) {
                    System.out.println("Введите допустимое буквенное значение");
                } else if (!mine.matches("[абвг]")) {
                    System.out.println("Вы ввели недопустимое значение!"); 
                } else break;
                System.out.print ("Ваш ответ: ");
                mine = fa.nextLine();
            } 
            System.out.println(right_answers[i]);
            rw[i] = right_answers[i].replace("Правильный ответ:", "");
            if (mine.trim().equals(rw[i].trim())) {
                count++;
            }
        }
        timer.cancel(); //окончание работы таймера
    }
    public void get () throws IOException, InterruptedException {
        Date endtime = new Date();
        get_res get_res = new get_res();
        get_res.get_res(count, queLength); //получение и вывод результатов теста на экран
        get_res.final_time(firstdate, endtime, count);
    }
}