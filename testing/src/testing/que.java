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
    public void que() throws FileNotFoundException, IOException, InterruptedException { //����� ��� ��������� �������� �� �����, ������ ����� � ������ �������� �� �����
        Scanner fa = new Scanner(System.in);
        //������ ��� ���������� �������� � ��������� ������ �� �����
        List <String> lines = new ArrayList<>();  //����� ������ ��� ���� �����
        List <String> que = new ArrayList<>(); //������ ��� ��������
        List <String> ans1 = new ArrayList<>(); //������ ��� ��������� ������
        List <String> ans2 = new ArrayList<>();
        List <String> ans3 = new ArrayList<>();
        List <String> ans4 = new ArrayList<>();
        List <String> rans = new ArrayList<>(); //������ ��� ���������� �������
        
        File file = new File ("������� � ������.txt");
        if (!file.exists()) {
            System.out.println (" ���� � ��������� �� ������!\n ��������� ����");
            System.exit(0);
        }
        BufferedReader bfr = new BufferedReader (new FileReader(file));
        String str = "";
        while ((str=bfr.readLine())!=null){ //���������� � ����� ������ ���� ����� �� �����
            lines.add(str);
        }
        String[] rows = lines.toArray(new String[lines.size()]); //����������� ������ � ��������� ������ 
        for (String row : rows) { //���������� ������ ������ �� ������ �������
            if (row.contains("������")) {
                que.add(row);
            }
            if (row.contains("�)")) {
                ans1.add(row);
            }
            if (row.contains("�)")) {
                ans2.add(row);
            }
            if (row.contains("�)")) {
                ans3.add(row);
            }
            if (row.contains("�)")) {
                ans4.add(row);
            }
            if (row.contains("���������� �����")) {
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
        String mine = new String(); //������ ��� ������ �������� ��������� ������ 
        queLength = questions.length;
        get_res name = new get_res();
        name.flname();
        firstdate = new Date();
        firstdate.setSeconds(firstdate.getSeconds() + 5); //���������� �������� 5 ���, ������� �������� �� ������
        name.time(firstdate);
        timer = new Timer();
        get_res.getTime getTime = new get_res().new  getTime();
        timer.schedule(getTime, 0, 1000); //������ ��� �������� ������� ������ �������
        System.out.println("\n���� �������� ����� 5 ������"); 
        try {
            for (int i=5; i>0; i--) {//�������� ��������� �� 5 ������
                System.out.println(i);
                Thread.sleep(1000); 
            }
        } catch (InterruptedException ex) {
                System.out.println("����� �������!");
        }
        for (int i=0; i<questions.length; i++){ //���� ��� ������ �������� � �������� �������, ���������� ������� � ������ ������ ������������ ��� ����� (�������� ���� ������ ���� �,�,�,�), � ������ ����� �������� ��������� �������� ����������� �������, � ����� ����� ����������� �������� ������ - ���������� �����
            System.out.println ("\n��� ������ �������� '�����' � ������");
            name.new_time(firstdate);
            System.out.println ("\n"  + questions[i]);
            System.out.println (answers1[i]+"\n"+answers2[i]+"\n"+answers3[i]+"\n"+answers4[i]);
            System.out.print ("��� �����: ");
            mine  = fa.nextLine();
            if (mine.equals("�����")){
                menu menu = new menu();
                menu.menu();
                break;
            }
            while (!mine.matches("[����]") || mine.matches("\\d+") || !mine.equals("�����")){ 
                if (mine.equals("�����")){
                    break;
                } else if (mine.matches("\\d+")) {
                    System.out.println("������� ���������� ��������� ��������");
                } else if (!mine.matches("[����]")) {
                    System.out.println("�� ����� ������������ ��������!"); 
                } else break;
                System.out.print ("��� �����: ");
                mine = fa.nextLine();
            } 
            System.out.println(right_answers[i]);
            rw[i] = right_answers[i].replace("���������� �����:", "");
            if (mine.trim().equals(rw[i].trim())) {
                count++;
            }
        }
        timer.cancel(); //��������� ������ �������
    }
    public void get () throws IOException, InterruptedException {
        Date endtime = new Date();
        get_res get_res = new get_res();
        get_res.get_res(count, queLength); //��������� � ����� ����������� ����� �� �����
        get_res.final_time(firstdate, endtime, count);
    }
}