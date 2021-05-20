import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    //// 깔끔~
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] numArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int prefixSum = 0;
        long resultSum = 0;
        for (int num : numArr) {
            resultSum += num * prefixSum;
            prefixSum += num;
        }

        System.out.println(resultSum);
        br.close();
    }
}