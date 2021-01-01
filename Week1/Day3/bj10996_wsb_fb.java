import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int repeatNum = N / 2;
        
        String top = (N % 2 == 0 ? "* ".repeat(repeatNum) : "* ".repeat(repeatNum + 1)) + "\n";
        String bottom = (" *".repeat(repeatNum)) + "\n";

        for(int i = 0; i < N; i++){
            sb.append(top + bottom);
        }
        System.out.print(sb);
    }
}
