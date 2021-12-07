import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] cardInfo = br.readLine().split(" ");
        int N = Integer.parseInt(cardInfo[0]);
        int M = Integer.parseInt(cardInfo[1]);
        int K = Integer.parseInt(cardInfo[2]);

        int frontX = N - M;
        int backX = N - K;
        int result = Math.min(M, K) + Math.min(frontX, backX);

        System.out.println(result);
        br.close();
    }
}