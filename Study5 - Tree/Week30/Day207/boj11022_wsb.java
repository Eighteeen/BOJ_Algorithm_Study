import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲㅆ : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            String[] numStrArr = br.readLine().split(" ");
            int A = Integer.parseInt(numStrArr[0]);
            int B = Integer.parseInt(numStrArr[1]);
            
            sb.append(String.format("Case #%d: %d + %d = %d", i, A, B, A + B)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}