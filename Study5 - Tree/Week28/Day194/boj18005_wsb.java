import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// Ggal Ggeum
//// 깔끔해요
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int result = 0;
        if (n % 2 == 0) {
            n /= 2;
            if (n % 2 == 0) result = 2;
            else result = 1;
        }

        System.out.println(result);
        br.close();
    }
}


