package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class menu {
    public void menu () throws IOException, FileNotFoundException, InterruptedException{ //����� ��� ����������� ������� ���� � ����������� ����� ������� �����
        Scanner scan = new Scanner (System.in);
        String command = "";
        while (!command.equals("3")) {
                System.out.println("\n   ����");
                System.out.println("\n  1 - ������ ������������\n  2 - ������� ���� � ������������ \n  3 - �����");
                System.out.print ("   �������: ");
                command = scan.nextLine();
                while (!command.matches("[123]")){
                    System.out.print ("  ����������, �������, ������������ ����� ����: ");
                    command = scan.nextLine();
                }
                switch (Integer.valueOf(command)){
                    case 1: //������ ����� ����� ����� "��"
                        System.out.println ("\n ����� ������� ������������ ����������� � �����������: ");
                        System.out.println ("\n ��� ���������� �������� �� 20 ��������. �� ������ ������ ������������� 4 �������� ������.\n ������ ������ ����� ������ ���� ���������� �����. ����� ������� ����� ��� ����� ������ �����,\n ����������� ��� ����������� ������������.\n ��� ������ ����� �������, ��������� ���������� � ����� ����� ������ �������.\n ����� ���� ����������, �� ������ �� ������� ���� ����������. �� �� ��� �� ������� ����������\n � ��������� �����.\n �����!!!");
                        System.out.print ("\n ���� �� ������, �������� '��'\n ��� �������� � ���� - 0:  ");
                        String answ = scan.nextLine();
                        OUTER:
                        while (!answ.equals("0")) {
                            switch (answ) {
                                case "��":
                                    que que = new que();
                                    que.que();
                                    que.get();
                                    break OUTER;

                                case "0":
                                    break OUTER;
                                default:
                                    System.out.print("�� ����� ������������ ��������! \n���������� �����: ");
                                    answ = scan.nextLine();
                                    break;
                            }
                        }
                        break;
                    case 2: //�������� ����� ������
                        File file = new File ("�����.txt");
                        System.out.println ("�������� �����...");
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
