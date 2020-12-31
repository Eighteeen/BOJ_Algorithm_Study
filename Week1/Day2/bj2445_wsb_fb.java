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
            if(i <= n){
                sb.append("*".repeat(i));
                sb.append(" ".repeat(m - 2 * i));
                sb.append("*".repeat(i)).append("\n");
            }else{
                sb.append("*".repeat(m - i));
                sb.append(" ".repeat(2 * (i - n)));
                sb.append("*".repeat(m - i)).append("\n");
            }
        }
        System.out.print(sb);
    }
}
