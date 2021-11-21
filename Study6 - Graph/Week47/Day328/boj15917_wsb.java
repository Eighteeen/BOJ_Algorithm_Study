import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            int a = Integer.parseInt(br.readLine());
            sb.append((a&(-a)) == a ? "1\n" : "0\n");
        }

        System.out.println(sb);
        br.close();
    }
}