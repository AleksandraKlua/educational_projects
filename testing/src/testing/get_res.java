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
    public static Date finaldate; //����� ��������� �����
    public void flname () {
        Scanner fa = new Scanner(System.in);
        System.out.println ("\n �������������.\n �������� ���� ������ ���� ���������");
        System.out.print ("\n ������� ���� �������: "); //���� ����� � ������� � ���������� ������ ������ ���� ���������
        lastname = fa.nextLine();
        while (!lastname.matches("[�-��-�]+")){
            if (lastname.trim().length() == 0){
                System.out.println (" �� ���������� ������ ������");
            } else System.out.println (" ������������ ��������");
            System.out.print ("\n ������� ���� �������: ");
            lastname = fa.nextLine();
        }
        System.out.print (" ������� ���� ���: ");
        name = fa.nextLine(); 
        while (!name.matches("[�-��-�]+")){
            if (name.trim().length() == 0){
                System.out.println (" �� ���������� ������ ������");
            } else System.out.println (" ������������ ��������");
            System.out.print ("\n ������� ���� ���: ");
            name = fa.nextLine();
        }
    }
    public void time(Date firstdate) {
        Scanner fa = new Scanner(System.in);
        System.out.print("\n������� ����� � �������: "); //�����, �� ������� �������� ����, � ��������� �� ���� ������ �����
        mins = fa.nextLine();
        while (!mins.matches("\\d+")){
            System.out.println ("����������, �������, �������� ��������");
            System.out.print("\n������� ����� � �������: ");
            mins = fa.nextLine();
        }
        finaldate = new Date(); //����� ��������� �����
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
        System.out.println ("���������� �����: " + format1.format(lastdate));
    }
    public class getTime extends TimerTask { //��������� ����� � ���������� ������� ���� � �������� �� ������� ��������� ��� �������������, ���� ����� - ����� ����� �� ���������
    @Override
        public void run() {
            Date lastdate = new Date();
            if (lastdate.after(finaldate)) { 
                try {
                    System.out.println ("\n����� �����!");
                    que que = new que();
                    que.get();
                    System.exit(0);
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(get_res.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    public void get_res(int count, int queLength) throws IOException, InterruptedException { //����� ��� ��������� �����������
        if (count >= queLength*0.4){ //������������ ������
            mark = 3;
            if (count >= queLength*0.6){
                mark = 4;
                if (count >= queLength*0.8){
                    mark = 5;
                } 
            }
        } else { mark = 2; }
    }
    public void final_time (Date firstdate, Date endtime, int count) throws IOException {//����� ���������� �����
        endtime.setMinutes(endtime.getMinutes() - firstdate.getMinutes());
        endtime.setSeconds(endtime.getSeconds() - firstdate.getSeconds());
        SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.YYYY"); 
        String pdate = format2.format(firstdate); //��� ���� ����� ������ ��� ������ � ����
        SimpleDateFormat format1 = new SimpleDateFormat("mm:ss");
        System.out.println("\n����������� �����: " + format1.format(endtime) + " ���:���"); //����� ����������� ����� �� �����
        String pendt = format1.format(endtime);
        System.out.println ("���������� ���������� �������: " + count);
        System.out.println ("������: " + mark);
        String pm = Integer.toString(mark);
        String pc = Integer.toString(count);
        write_results write = new write_results(); //������ ����������� � ����
        write.write_results(pdate, lastname, name, pendt, pc, pm);
    }
}