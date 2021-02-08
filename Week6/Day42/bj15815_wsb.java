import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char expression[] = br.readLine().toCharArray();
        Stack<Integer> calcStack = new Stack<>();

        for(char e : expression){
            if(e >= '0'){
                calcStack.push(e - '0');
                continue;
            }

            int operand2 = calcStack.pop();
            int operand1 = calcStack.pop();
            calcStack.push(calculator(operand1, operand2, e));
        }

        System.out.print(calcStack.pop());
        br.close();
    }

    static int calculator(int operand1, int operand2, char operator){
        switch(operator){
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
        }
        return -1;
    }
}