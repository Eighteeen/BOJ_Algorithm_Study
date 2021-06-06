import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔 :22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numStrArr = br.readLine().split(" ");
        int N = Integer.parseInt(numStrArr[0]), K = Integer.parseInt(numStrArr[1]);

        int maxNum = 0;
        for (int i = 1; i <= K; i++) {
            int multiplyNum = N * i;
            maxNum = Math.max(maxNum, getReverseNum(multiplyNum));
        }

        System.out.println(maxNum);
        br.close();
    }

    static int getReverseNum(int num) {
        String numStr = Integer.toString(num);
        String reverseNumStr = new StringBuilder(numStr).reverse().toString();
        return Integer.parseInt(reverseNumStr);
    }
}