import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 끔  : 깔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] numCharArr = br.readLine().toCharArray();
        int remain = 0;
        //// 올 BigInteger 안 쓰는거 굿 : 22 굳굳굳
        for (char n : numCharArr) {
            remain = (remain * 10 + (n - '0')) % 20000303;
        }

        System.out.println(remain);
        br.close();
    }
}