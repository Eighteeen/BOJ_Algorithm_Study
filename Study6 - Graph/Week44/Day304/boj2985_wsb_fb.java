import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int[] numArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getEquation(numArr));

        br.close();
    }

    static String getEquation(int[] numArr) {
        char symbol1 = getOperator(numArr[0], numArr[1], numArr[2]), symbol2 = '=';
        if (symbol1 == 0) {
            symbol1 = '=';
            symbol2 = getOperator(numArr[1], numArr[2], numArr[0]);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(numArr[0])
            .append(symbol1)
            .append(numArr[1])
            .append(symbol2)
            .append(numArr[2]);
        return sb.toString();
    }

    static char getOperator(int operand1, int operand2, int result) {
        if (operand1 + operand2 == result) return '+';
        if (operand1 - operand2 == result) return '-';
        if (operand1 * operand2 == result) return '*';
        if (operand1 / operand2 == result && operand1 % operand2 == 0) return '/';
        return '\u0000'; //// 널 문자 등이 더 직관적인 것 같습니다 : 22 -> 반영했습니다.
    }
}