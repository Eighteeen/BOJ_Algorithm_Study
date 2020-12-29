import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int m = 2 * n;

        for(int i = 1; i < m; i++){
            sb.append("*".repeat(i <= n ? i : m - i));
            sb.append(" ".repeat(i <= n ? m - 2 * i : 2 * (i - n)));
            sb.append("*".repeat(i <= n ? i : m - i)).append("\n");
        }
        System.out.print(sb);
    }
}