import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 깔껌 : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] barnInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = barnInfo[0], W = barnInfo[1], H = barnInfo[2], L = barnInfo[3];

        int wCow = W / L, hCow = H / L;
        int maxCow = wCow * hCow;
        if (maxCow > N) maxCow = N;

        System.out.println(maxCow);
        br.close();
    }
}