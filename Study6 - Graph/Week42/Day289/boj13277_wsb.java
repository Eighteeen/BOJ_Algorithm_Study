import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

//// ㄲㄲ
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numInfo = br.readLine().split(" ");
        BigInteger num1 = new BigInteger(numInfo[0]);
        BigInteger num2 = new BigInteger(numInfo[1]);

        System.out.println(num1.multiply(num2));
        br.close();
    }
}