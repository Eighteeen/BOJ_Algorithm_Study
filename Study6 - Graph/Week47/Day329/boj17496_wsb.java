import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] farmInfo = br.readLine().split(" ");
        int N = Integer.parseInt(farmInfo[0]);
        int T = Integer.parseInt(farmInfo[1]);
        int C = Integer.parseInt(farmInfo[2]);
        int P = Integer.parseInt(farmInfo[3]);

        System.out.println((N - 1) / T * C * P);
        br.close();
    }
}