import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println((char) (44031 + Integer.parseInt(br.readLine())));
        br.close();
    }
}