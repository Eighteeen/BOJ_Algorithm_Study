import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] conditionInfo = br.readLine().split(" ");
        int N = Integer.parseInt(conditionInfo[0]), S = Integer.parseInt(conditionInfo[1]);
        int[] numArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int cntSequenceS = 0;
        int range = (1 << N);
        for (int i = 1; i < range; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) == 0) continue;
                sum += numArr[j];
            }
            if (sum == S) cntSequenceS++;
        }

        System.out.println(cntSequenceS);
        br.close();
    }
}