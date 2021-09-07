import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲㄲ
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] problemInfo = br.readLine().split(" ");
        int N = Integer.parseInt(problemInfo[0]);
        int K = Integer.parseInt(problemInfo[1]);

        System.out.println(getKthDivisor(N, K));
        br.close();
    }

    static int getKthDivisor(int devidedNum, int K) {
        int divisorCnt = 0;
        for (int i = 1; i <= devidedNum; i++) {
            if (devidedNum % i > 0) continue;
            if (++divisorCnt == K) return i;
        }
        return 0;
    }
}