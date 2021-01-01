import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
        ////?연산자를 잘 활용해서 깔끔한 코드를 짠거 같아욧.
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int calN = N - 1;

        for(int i = 0; i < calN; i++){
            sb.append(" ".repeat(calN - i))
                .append("*")
                .append(" ".repeat(i == 0 ? 0 : i * 2 - 1))
                .append(i == 0 ? "\n" : "*\n");
        }
        sb.append("*".repeat(2 * N - 1));
        System.out.print(sb);
    }
}
