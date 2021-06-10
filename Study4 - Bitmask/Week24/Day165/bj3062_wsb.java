import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            sb.append(solve3062(br.readLine()) ? "YES\n" : "NO\n");
        }

        System.out.print(sb);
        br.close();
    }

    static boolean solve3062(String numStr) {
        int num = Integer.parseInt(numStr);
        int reverseNum = Integer.parseInt(new StringBuilder(numStr).reverse().toString());

        String sumNumStr = String.valueOf(num + reverseNum);
        return sumNumStr.equals(new StringBuilder(sumNumStr).reverse().toString());
    }
}