import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int num[] = new int[10001];

        for(int i = 0; i < N; i++){
            num[Integer.parseInt(br.readLine())]++;
        }

        for(int i = 0; i < 10001; i++){
            sb.append((i + "\n").repeat(num[i]));
        }
        System.out.print(sb);
    }
}