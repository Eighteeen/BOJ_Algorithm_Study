import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long B1 = Long.parseLong(br.readLine(), 2);
        long B2 = Long.parseLong(br.readLine(), 2);

        System.out.println(Long.toBinaryString(B1 * B2));
        br.close();
    }
}