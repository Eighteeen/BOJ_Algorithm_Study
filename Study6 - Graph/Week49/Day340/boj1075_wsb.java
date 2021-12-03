import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String numStr = br.readLine();
        int F = Integer.parseInt(br.readLine());

        //// 100으로 나눈 몫을 구하고 100을 곱하면 더 간단합니다!
        int dividend = Integer.parseInt(numStr.substring(0, numStr.length() - 2) + "00");
        while (dividend % F != 0) {
            dividend++;
        }

        String dividendStr = Integer.toString(dividend);
        String result = dividendStr.substring(dividendStr.length() - 2, dividendStr.length());
        
        System.out.println(result);
        br.close();
    }
}