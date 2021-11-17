import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 끔
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()) - 1;
        for (int i = 0; i <= N; i++) {
            String repeatStar = "*".repeat(i);
            sb.append(" ".repeat(N - i))
                .append(repeatStar)
                .append("*")
                .append(repeatStar)
                .append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}