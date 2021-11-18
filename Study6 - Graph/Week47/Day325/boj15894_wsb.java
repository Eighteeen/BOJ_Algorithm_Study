import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깎끔 : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Long.parseLong(br.readLine()) * 4);
        br.close();
    }
}