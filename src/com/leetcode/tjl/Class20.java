import java.util.Stack;

public class Class20 {

    public boolean isValid(String s) {
        Stack<Character> stack=new Stack<>();
        for (int i=0;i<s.length();i++){
            if (
                    s.charAt(i)=='('||
                            s.charAt(i)=='['||
                            s.charAt(i)=='{'
            ){
                stack.push(s.charAt(i));
            }
            else if (s.charAt(i)==')'){
                if (stack.empty()||stack.peek()!='('){
                    return false;
                }
                stack.pop();
            }
            else if (s.charAt(i)==']'){
                if (stack.empty()||stack.peek()!='['){
                    return false;
                }
                stack.pop();
            }
            else if (s.charAt(i)=='}'){
                if (stack.empty()||stack.peek()!='{'){
                    return false;
                }
                stack.pop();
            }

        }
        if (stack.empty()){

        return true;
        }
        return false;
    }
}
