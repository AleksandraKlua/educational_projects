package testing;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        menu start = new menu();
        System.out.println("  ������������!\n  ��� ����������� ��������� ������������!");
        System.out.println("  ���� �����: ������������ � Java");
        try {
            start.menu(); //���������������� ���������� ���������
        } catch (IOException | InterruptedException ex) {
        }
    } 
}