import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int[] numArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int sumEvenNum = 0, minEvenNum = 100;
            for (int num : numArr) {
                if (num % 2 != 0) continue;
                sumEvenNum += num;
                minEvenNum = Math.min(minEvenNum, num);
            }
            sb.append(sumEvenNum).append(" ").append(minEvenNum).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}