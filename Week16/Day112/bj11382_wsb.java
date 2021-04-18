import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numStrArr = br.readLine().split(" ");
        long A = Long.parseLong(numStrArr[0]), B = Long.parseLong(numStrArr[1]), C = Long.parseLong(numStrArr[2]);

        System.out.println(A + B + C);
        br.close();
    }
}