import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int[] numNote = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sb.append(getBitmaskOrOperation(numNote)).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    static int getBitmaskOrOperation(int[] numArr) {
        int bitmask = 0;
        for (int num : numArr) {
            bitmask |= num;
        }
        return bitmask;
    }
}