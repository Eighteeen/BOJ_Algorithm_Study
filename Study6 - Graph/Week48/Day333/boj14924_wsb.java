import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] trainInfo = br.readLine().split(" ");
        int S = Integer.parseInt(trainInfo[0]);
        int T = Integer.parseInt(trainInfo[1]);
        int D = Integer.parseInt(trainInfo[2]);

        System.out.println(D / (S * 2) * T);
        br.close();
    }
}