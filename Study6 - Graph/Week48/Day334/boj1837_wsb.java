import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

//// ㄲㄲ
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numInfo = br.readLine().split(" ");
        BigInteger P = new BigInteger(numInfo[0]);
        int K = Integer.parseInt(numInfo[1]);

        for (int i = 2; i < K; i++) {
            BigInteger bigI = BigInteger.valueOf(i);
            if (P.mod(bigI).equals(BigInteger.ZERO)) {
                System.out.printf("BAD %d\n", i);
                return;
            }
        }

        System.out.println("GOOD");
        br.close();
    }
}