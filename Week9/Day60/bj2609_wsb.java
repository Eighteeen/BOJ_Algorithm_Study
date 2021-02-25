import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] numStrArr = br.readLine().split(" ");
        int num1 = Integer.parseInt(numStrArr[0]);
        int num2 = Integer.parseInt(numStrArr[1]);

        int maxDivisor = GCD(num1, num2);
        sb.append(maxDivisor).append("\n").append(num1 * num2 / maxDivisor);
        System.out.print(sb);

        br.close();
    }

    static int GCD(int num1, int num2){
        if(num2 == 0) return num1;
        return GCD(num2, num1 % num2);
    }
}