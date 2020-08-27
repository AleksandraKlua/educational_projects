import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;

public class CalculatorRPN {
    public static void main(String[] args) {
        CalculatorRPN c = new CalculatorRPN();
        String result = c.evaluate("2+3/6");
        System.out.println(result);
    }
    
    private static boolean isNumber (String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    private ArrayList Zeroes (ArrayList list) { //neccessary element for negative numbers 
        ArrayList ready_list = new ArrayList();
        String[] array = (String[]) list.toArray(new String[0]);
        for (int i=0; i<list.size(); i++){
            if (array[i].equals("-")){
                if (i==0) {
                    ready_list.add("0");
                }
                else if (array[i-1].equals("(")) {
                    ready_list.add("0");
                }
            }
            ready_list.add(array[i]);
        }
        return ready_list;
    }
    
    public String evaluate(String statement) {
        if (statement == null) return null;
        else if (statement.equals("")) return null;
        Stack <String> operators = new Stack <>();
        String output = new String();
        String rest = new String();
        int brackets = 0; //brackets counter
        ArrayList <String> values = new ArrayList<>();
        Stack <Double> result = new Stack<>();
        String[] n = statement.split("(?=[-()+/*])");
        for (int i = 0; i<n.length; i++){
            if (i==0) {
                if (isNumber(n[i].substring(0))){
                    values.add(n[i]);
                }  
                if (n[i].contains("(")){ //at the beginning of an expression there can be either a digit or open brackets
                        String[] open_brack = n[i].split("(?=[(])");
                        brackets = brackets + open_brack.length;
                        String interval = open_brack[open_brack.length-1].substring(0,1);
                        String num = open_brack[open_brack.length-1].substring(1);
                        if (isNumber(num)) {
                            for (int k=0; k<open_brack.length-1; k++) {
                                values.add(open_brack[k]);
                            }
                            values.add(interval);
                            values.add(num);
                        }   
                        else {
                            return null;
                        }
                    } 
                } 
            else {
                if (n[i].equals("(") | n[i].equals(")")) brackets++;
                if (n[i].matches("[-()+/*]")){
                    values.add(n[i]);
                }
                else {
                    String op = n[i].substring(0,1);
                    if (op.equals("(")) brackets++;
                    String num = n[i].substring(1);
                    if (isNumber(num)) {
                        values.add(op);
                        values.add(num);
                    }
                    else {
                        return null;
                    }
                }
            }
        }
        if (brackets%2!=0) {
            return null;
        }
        values = Zeroes(values);
        for (int i=0; i<values.size(); i++) {
            if (i != values.size()-1) {
                if (values.get(i).matches("[+-/*]")) {
                    if (values.get(i).equals(values.get(i+1))) return null;
                }
            }
        }
        Object[] array  = values.toArray();
        for (Object array1 : array) {
            if ("(".equals(array1.toString())) {
                operators.push("("); 
            } else if ("+".equals(array1.toString())) {
                if (!operators.isEmpty()) {
                    if (operators.peek().equals("+") | operators.peek().equals("-") | operators.peek().equals("*") | operators.peek().equals("/")) {
                        output = output + operators.pop() + " ";
                    } 
                } operators.push("+");
            } else if ("-".equals(array1.toString())) {
                if (!operators.isEmpty()) {
                    if (operators.peek().equals("+") | operators.peek().equals("-") | operators.peek().equals("*") | operators.peek().equals("/")) {
                        output = output + operators.pop() + " ";
                    } 
                } operators.push("-");
            } else if ("*".equals(array1.toString())) {
                if (!operators.isEmpty()) {
                    if (operators.peek().equals("*") | operators.peek().equals("/")) {
                        output = output + operators.pop() + " ";
                    } 
                } operators.push("*");
            } else if ("/".equals(array1.toString())) {
                if (!operators.isEmpty()) {
                    if (operators.peek().equals("*") | operators.peek().equals("/")) {
                        output = output + operators.pop() + " ";
                    } 
                } operators.push("/");
            } else if (")".equals(array1.toString())) {
                while(!operators.peek().equals("(")) {
                    output = output + operators.pop() + " ";
                } operators.pop();
            } else if (isNumber(array1.toString())) {
                output = output + array1.toString() + " ";
            } 
        }
        
        while (!operators.isEmpty()){
            rest = rest + operators.pop() + " ";
        }
        output = output.concat(rest);
        String [] str = output.split(" ");
        for (String str1 : str) {
            if (isNumber(str1)) {
                result.push(Double.parseDouble(str1));
            } else {
                double val = result.pop();
                double val2 = result.pop();
                switch (str1) {
                    case "+":
                        result.push(val2+val);
                        break;
                    case "-":
                        result.push(val2-val);
                        break;
                    case "*":
                        result.push(val2*val);
                        break;
                    case "/":
                        if (val==0) return null;
                        else result.push(val2/val);
                        break;
                }
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat( "#.####" );
        String res = decimalFormat.format(Double.parseDouble(result.pop().toString()));
        return res.replace (",", ".");
    }
}
