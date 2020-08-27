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
    public void write_results (String pdate,String lastname, String name, String pendt, String pc, String pm) throws IOException { //результаты, которое записываются в файл
        File file = new File ("отчет.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        BufferedReader bfr = new BufferedReader (new FileReader(file));
        String [] columns = {"  № п/п ", "Дата", "Фамилия", "Имя", " Затраченное время ", " Количество правильных ответов ", " Оценка "}; //шапка для таблицы, записываемой в файл
        try (OutputStream one = new FileOutputStream("отчет.txt",true)) { 
            int id = 0;
            if(bfr.readLine()==null){ //если в файле нет данных, то сначала записывается шапка
                //повторение пробелов нужно для обозначения границ ячеек
                one.write((columns[0]).getBytes()); //граница ячейки "№ п/п" - 8 знаков
                one.write((repeat(4)+columns[1]+repeat(4)).getBytes());//граница ячейки "Дата" - 12 знаков
                one.write((repeat(8)+columns[2]+repeat(6)).getBytes());//граница ячейки "Фамилия" - 21 знак
                one.write((repeat(10)+columns[3]+repeat(8)).getBytes());//граница ячейки "Имя" - 21 знак
                one.write(columns[4].getBytes()); //граница ячейки "Затраченное время" - 19 знаков
                one.write(columns[5].getBytes()); //граница ячейки "Количества правильных ответов" - 31 знак
                one.write(columns[6].getBytes()); //граница ячейки "Оценка" - 8 знаков
            } else {
                while ((bfr.readLine())!=null){
                    id++; //получения номера пользователя в отчете
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