import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numInfo = br.readLine().split(" ");
        int R1 = Integer.parseInt(numInfo[0]);
        int S = Integer.parseInt(numInfo[1]);

        System.out.println(S * 2 - R1);
        br.close();
    }
}