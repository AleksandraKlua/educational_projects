package testing;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class get_res {
    public static String lastname;
    public static String name;
    public static String mins;
    public static int mark;
    public static Date finaldate; //время окончания теста
    public void flname () {
        Scanner fa = new Scanner(System.in);
        System.out.println ("\n Представьтесь.\n Разрешен ввод только букв кириллицы");
        System.out.print ("\n Введите вашу фамилию: "); //ввод имени и фамилии с допустимым вводом только букв кириллицы
        lastname = fa.nextLine();
        while (!lastname.matches("[а-яА-Я]+")){
            if (lastname.trim().length() == 0){
                System.out.println (" Не оставляйте строку пустой");
            } else System.out.println (" Недопустимое значение");
            System.out.print ("\n Введите вашу фамилию: ");
            lastname = fa.nextLine();
        }
        System.out.print (" Введите ваше имя: ");
        name = fa.nextLine(); 
        while (!name.matches("[а-яА-Я]+")){
            if (name.trim().length() == 0){
                System.out.println (" Не оставляйте строку пустой");
            } else System.out.println (" Недопустимое значение");
            System.out.print ("\n Введите ваше имя: ");
            name = fa.nextLine();
        }
    }
    public void time(Date firstdate) {
        Scanner fa = new Scanner(System.in);
        System.out.print("\nВведите время в минутах: "); //время, за которое решается тест, с проверкой на ввод только числа
        mins = fa.nextLine();
        while (!mins.matches("\\d+")){
            System.out.println ("Пожалуйста, введите, числовое значение");
            System.out.print("\nВведите время в минутах: ");
            mins = fa.nextLine();
        }
        finaldate = new Date(); //время окончания теста
        finaldate.setMinutes(firstdate.getMinutes() + Integer.valueOf(mins));
        finaldate.setSeconds(firstdate.getSeconds());
    }
    public void new_time (Date firstdate) throws IOException, InterruptedException {
        SimpleDateFormat format1 = new SimpleDateFormat("mm:ss");
        Date lastdate = new Date();
        int l = firstdate.getMinutes() + Integer.valueOf(mins) - lastdate.getMinutes();
        int a = firstdate.getSeconds() - lastdate.getSeconds();
        lastdate.setMinutes(l);
        lastdate.setSeconds(a);
        System.out.println ("Оставшееся время: " + format1.format(lastdate));
    }
    public class getTime extends TimerTask { //обновляет время и сравнивает текущую дату с конечной на предмет равенства или превосходства, если равны - сразу выход из программы
    @Override
        public void run() {
            Date lastdate = new Date();
            if (lastdate.after(finaldate)) { 
                try {
                    System.out.println ("\nВремя вышло!");
                    que que = new que();
                    que.get();
                    System.exit(0);
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(get_res.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    public void get_res(int count, int queLength) throws IOException, InterruptedException { //метод для получения результатов
        if (count >= queLength*0.4){ //высчитывание оценки
            mark = 3;
            if (count >= queLength*0.6){
                mark = 4;
                if (count >= queLength*0.8){
                    mark = 5;
                } 
            }
        } else { mark = 2; }
    }
    public void final_time (Date firstdate, Date endtime, int count) throws IOException {//время завершения теста
        endtime.setMinutes(endtime.getMinutes() - firstdate.getMinutes());
        endtime.setSeconds(endtime.getSeconds() - firstdate.getSeconds());
        SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.YYYY"); 
        String pdate = format2.format(firstdate); //эта дата нужна только для записи в файл
        SimpleDateFormat format1 = new SimpleDateFormat("mm:ss");
        System.out.println("\nЗатраченное время: " + format1.format(endtime) + " мин:сек"); //вывод результатов теста на экран
        String pendt = format1.format(endtime);
        System.out.println ("Количество правильных ответов: " + count);
        System.out.println ("Оценка: " + mark);
        String pm = Integer.toString(mark);
        String pc = Integer.toString(count);
        write_results write = new write_results(); //запись результатов в файл
        write.write_results(pdate, lastname, name, pendt, pc, pm);
    }
}