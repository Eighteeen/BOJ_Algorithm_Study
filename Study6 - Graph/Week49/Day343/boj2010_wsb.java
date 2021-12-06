import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int result = 1;
        while (N-- > 0) {   
            result += Integer.parseInt(br.readLine()) - 1;
        }

        System.out.println(result);
        br.close();
    }
}