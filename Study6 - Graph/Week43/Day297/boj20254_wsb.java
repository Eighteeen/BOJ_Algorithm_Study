import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] scores = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] rules = {56, 24, 14, 6};

        //// 더 깔끔
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            sum += rules[i] * scores[i];
        }

        System.out.println(sum);
        br.close();
    }
}