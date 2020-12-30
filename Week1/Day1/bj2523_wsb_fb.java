import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int len = 2 * N;

        for(int i = 1; i < len; i++){
            sb.append("*".repeat(i <= N ? i : len - i)).append("\n");
        }
        System.out.print(sb);
    }
}