import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

// 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] divideInfo = br.readLine().split(" ");
        BigInteger n = new BigInteger(divideInfo[0]), m = new BigInteger(divideInfo[1]);
        sb.append(n.divide(m)).append("\n");
        sb.append(n.mod(m)).append("\n");

        System.out.print(sb);
        br.close();
    }
}