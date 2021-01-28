import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        char formula[] = br.readLine().toCharArray();
        int operand[] = getOperand(N);
        Stack<Double> calcStack = new Stack<>();

        for(char f : formula){
            double now = 0;

            if(f >= 'A') now = (double)operand[f - 'A'];
            else{
                double operand2 = calcStack.pop();
                double operand1 = calcStack.pop();
                now = calculator(f, operand1, operand2);
            }

            calcStack.push(now);
        }
        
        System.out.print(String.format("%.2f", calcStack.pop()));
        br.close();
    }

    static int[] getOperand(int N) throws IOException {
        int operand[] = new int[N];
        for(int i = 0; i < N; i++){
            operand[i] = Integer.parseInt(br.readLine());
        }
        return operand;
    }

    static double calculator(char operator, double operand1, double operand2){
        double result = 0;
        switch(operator){
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                result = operand1 / operand2;
                break;
        }
        return result;
    }
}