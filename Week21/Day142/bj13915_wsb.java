import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 무난쓰
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = br.readLine()) != null) {
            int N = Integer.parseInt(input);
            int[] levels = new int[N];
            for (int i = 0; i < N; i++) {
                int[] hotAirBalloons = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
                levels[i] = bitmaskOfNumArrAllAdd(hotAirBalloons);
            }
            sb.append(Arrays.stream(levels).distinct().count()).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static int bitmaskOfNumArrAllAdd(int[] numArr) {
        int bitmask = 0;
        for (int num : numArr) {
            bitmask |= (1 << num);
        }
        return bitmask;
    }
}