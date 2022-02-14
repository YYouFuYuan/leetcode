package hot100;

import java.util.Stack;

public class P20 {
    public boolean isValid(String s) {
        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<str.length;i++){
            char ch = str[i];
            if(ch == '(' || ch == '[' || ch == '{'){
                stack.push(ch);
            }
            else if(ch == ')'){
                if(stack.isEmpty() || stack.pop() != '(')
                    return false;
            }
            else if(ch == ']'){
                if(stack.isEmpty() || stack.pop() != '[')
                    return false;

            }else {
                if(stack.isEmpty() || stack.pop() != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
