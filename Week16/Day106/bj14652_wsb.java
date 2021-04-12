import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] seatInfo = br.readLine().split(" ");
        int M = Integer.parseInt(seatInfo[1]);
        int K = Integer.parseInt(seatInfo[2]);

        System.out.printf("%d %d\n", K / M, K % M);
        br.close();
    }
}