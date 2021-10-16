import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

//// 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BigDecimal baseNum = BigDecimal.valueOf(2);
        int N = Integer.parseInt(br.readLine());
        BigDecimal result = baseNum;

        while (N-- > 1) {
            result = result.multiply(baseNum);
        }
        result = BigDecimal.valueOf(1).divide(result);

        System.out.println(result.toPlainString());
        br.close();
    }
}