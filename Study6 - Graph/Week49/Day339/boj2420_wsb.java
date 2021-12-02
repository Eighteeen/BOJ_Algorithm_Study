import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ : 22
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numArr = br.readLine().split(" ");
        long N = Long.parseLong(numArr[0]), M = Long.parseLong(numArr[1]);

        System.out.println(Math.abs(N - M));
        br.close();
    }
}