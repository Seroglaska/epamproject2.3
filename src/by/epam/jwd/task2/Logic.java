package by.epam.jwd.task2;

import java.util.Stack;

public class Logic {
    public double calculator(String s) {
        Stack<Character> stack = new Stack();
        Stack <Double> stack1 = new Stack();
        String num = new String();
        for(int i = 0; i < s.length(); i++){
            if(getPriority(s.charAt(i)) == 0){
                if(i == s.length() - 1){
                    num += s.charAt(i);
                }
                else{
                    if(getPriority(s.charAt(i + 1)) == 0) {
                        while (getPriority(s.charAt(i)) == 0) {
                            if (getPriority(s.charAt(i + 1)) != 0) {
                                num += s.charAt(i);
                                break;
                            }
                            num += s.charAt(i++);
                        }
                    }
                    else{
                        num += s.charAt(i);
                    }
                }
                stack1.push(Double.parseDouble(num));
                num = new String();
            }
            else if(getPriority(s.charAt(i)) == 1){
                if(s.charAt(i+1) == '-'){
                    i++;
                    while(getPriority(s.charAt(i)) != -1){
                        num += s.charAt(i++);
                    }
                    stack1.push(Double.parseDouble(num));
                    num = new String();
                }
                else{
                    stack.push(s.charAt(i));
                }
            }
            else if(getPriority(s.charAt(i)) > 1){
                while(!stack.empty()){
                    if(getPriority(stack.peek()) >= getPriority(s.charAt(i))){
                        double a = stack1.pop();
                        double b = stack1.pop();
                        if(stack.peek() == '+'){
                            stack1.push(b + a);
                        }
                        else if(stack.peek() == '-'){
                            stack1.push(b - a);
                        }
                        else if(stack.peek() == '*'){
                            stack1.push(b * a);
                        }
                        else{
                            stack1.push(b / a);
                        }
                        stack.pop();
                    }
                    else{
                        break;
                    }
                }
                stack.push(s.charAt(i));
            }
            else{
                while(getPriority(stack.peek()) != 1){
                    double a = stack1.pop();
                    double b = stack1.pop();
                    if(stack.peek() == '+'){
                        stack1.push(b + a);
                    }
                    else if(stack.peek() == '-'){
                        stack1.push(b - a);
                    }
                    else if(stack.peek() == '*'){
                        stack1.push(b * a);
                    }
                    else{
                        stack1.push(b / a);
                    }
                    stack.pop();
                }
                stack.pop();
            }
        }
        while(!stack.empty()){
            double a = stack1.pop();
            double b = stack1.pop();
            if(stack.peek() == '+'){
                stack1.push(b + a);
            }
            else if(stack.peek() == '-'){
                stack1.push(b - a);
            }
            else if(stack.peek() == '*'){
                stack1.push(b * a);
            }
            else{
                stack1.push(b / a);
            }
            stack.pop();
        }
        return stack1.pop();
    }


    public int getPriority(char el){
        if(el == '*' || el == '/'){
            return 3;
        }
        else if(el == '+' || el == '-'){
            return 2;
        }
        else if(el == '('){
            return 1;
        }
        else if(el == ')'){
            return -1;
        }
        else{
            return 0;
        }
    }
}
