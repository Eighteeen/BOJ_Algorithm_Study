import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔합니다
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int p = Integer.parseInt(br.readLine());
        int q = Integer.parseInt(br.readLine());

        String result = "Yellow";
        if (p <= 50 && q <= 10) result = "White";
        else if (q >= 30) result = "Red";

        System.out.println(result);
        br.close();
    }
}