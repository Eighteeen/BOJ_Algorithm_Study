import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 끌끔 : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int k = Integer.parseInt(br.readLine());
            sb.append("=".repeat(k)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}