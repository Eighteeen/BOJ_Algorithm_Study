import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
    ////깔끔합니당!!!
class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        //// 와우 formula, operand, calcStack까지 작명 좋네요
        char formula[] = br.readLine().toCharArray();
        int operand[] = getOperand(N);
        Stack<Double> calcStack = new Stack<>();

        for(char f : formula){
            //// current라는 작명은 어떤가요? now는 시간의 '현재'를 나타내는 느낌이 더 있으니까요
            //// -> 오호 current가 좀 더 의미와 맞는 것 같아요 감사합니다!
            double current = 0;

            if(f >= 'A') current = (double)operand[f - 'A'];
            else{
                double operand2 = calcStack.pop();
                double operand1 = calcStack.pop();
                current = calculator(f, operand1, operand2);
            }

            calcStack.push(current);
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
