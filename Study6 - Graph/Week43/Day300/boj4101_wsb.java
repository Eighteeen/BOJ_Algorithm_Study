import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] numInfo;
        while (!(numInfo = br.readLine().split(" "))[0].equals("0")) {
            int num1 = Integer.parseInt(numInfo[0]);
            int num2 = Integer.parseInt(numInfo[1]);

            sb.append(num1 > num2 ? "Yes\n" : "No\n");
        }

        System.out.print(sb);
        br.close();
    }
}