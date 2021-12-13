import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ!
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] dongsuInfo = br.readLine().split(" ");
        int K = Integer.parseInt(dongsuInfo[0]);
        int N = Integer.parseInt(dongsuInfo[1]);
        int M = Integer.parseInt(dongsuInfo[2]);

        int excessMoney = K * N - M;
        if (excessMoney < 0) {
            excessMoney = 0;
        }

        System.out.println(excessMoney);
        br.close();
    }
}