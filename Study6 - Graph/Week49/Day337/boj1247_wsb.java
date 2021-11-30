import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

//// ㄲ : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int N = Integer.parseInt(br.readLine());
            BigInteger sum = BigInteger.ZERO;
            for (int j = 0; j < N; j++) {
                sum = sum.add(new BigInteger(br.readLine()));
            }
            
            if (sum.equals(BigInteger.ZERO)) {
                sb.append("0\n");
            }
            else if (sum.compareTo(BigInteger.ZERO) > 0) {
                sb.append("+\n");
            }
            else {
                sb.append("-\n");
            }
        }

        System.out.print(sb);
        br.close();
    }
}