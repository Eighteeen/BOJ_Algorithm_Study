import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] wallInfo = br.readLine().split(" ");
        long N = Long.parseLong(wallInfo[0]);
        long M = Long.parseLong(wallInfo[1]);

        System.out.println(N * M / 2);
        br.close();
    }
}