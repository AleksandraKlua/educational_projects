package testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class write_results {
    private  String repeat(int num) {
        String str = new String();
        StringBuilder builder = new StringBuilder(str);
        for (int i=0; i<num; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }
    public void write_results (String pdate,String lastname, String name, String pendt, String pc, String pm) throws IOException { //����������, ������� ������������ � ����
        File file = new File ("�����.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        BufferedReader bfr = new BufferedReader (new FileReader(file));
        String [] columns = {"  � �/� ", "����", "�������", "���", " ����������� ����� ", " ���������� ���������� ������� ", " ������ "}; //����� ��� �������, ������������ � ����
        try (OutputStream one = new FileOutputStream("�����.txt",true)) { 
            int id = 0;
            if(bfr.readLine()==null){ //���� � ����� ��� ������, �� ������� ������������ �����
                //���������� �������� ����� ��� ����������� ������ �����
                one.write((columns[0]).getBytes()); //������� ������ "� �/�" - 8 ������
                one.write((repeat(4)+columns[1]+repeat(4)).getBytes());//������� ������ "����" - 12 ������
                one.write((repeat(8)+columns[2]+repeat(6)).getBytes());//������� ������ "�������" - 21 ����
                one.write((repeat(10)+columns[3]+repeat(8)).getBytes());//������� ������ "���" - 21 ����
                one.write(columns[4].getBytes()); //������� ������ "����������� �����" - 19 ������
                one.write(columns[5].getBytes()); //������� ������ "���������� ���������� �������" - 31 ����
                one.write(columns[6].getBytes()); //������� ������ "������" - 8 ������
            } else {
                while ((bfr.readLine())!=null){
                    id++; //��������� ������ ������������ � ������
                }
            }
            if (Integer.toString(id).length()%2==0){ 
                int i = (8 - Integer.toString(id).length())/2;
                one.write(("\n"+repeat(i)+(id)+repeat(i)).getBytes()); 
            } else {
                int i = (9 - Integer.toString(id).length())/2;
                one.write(("\n"+repeat(i)+(id)+repeat(i-1)).getBytes());
            }
            one.write((" "+pdate+" ").getBytes());  
            if ((lastname.length())%2==0){
                int f = (columns[2].length()*3+1-lastname.length())/2;
                one.write((repeat(f)+lastname+repeat(f-1)).getBytes());
            } else {
                int f = (columns[2].length()*3-lastname.length())/2;
                one.write((repeat(f)+lastname+repeat(f)).getBytes());
            }   
            if ((name.length())%2==0){
                int n = (columns[3].length()*7+1-name.length())/2;
                one.write((repeat(n)+name+repeat(n-1)).getBytes());
            } else {
                int n = (columns[3].length()*7-name.length())/2;
                one.write((repeat(n)+name+repeat(n)).getBytes());
            }
            one.write((repeat(7)+pendt+repeat(7)).getBytes());
            int c; 
            if ((pc.length())%2==0){
                c = (columns[5].length()+1-pc.length())/2;
                one.write((repeat(c)+pc+repeat(c-1)).getBytes());
            } else {
                c = (columns[5].length()-pc.length())/2;
                one.write((repeat(c)+pc+repeat(c)).getBytes());
            }
            one.write((repeat(3)+pm+repeat(4)).getBytes());
            one.close();
        }
    }
}