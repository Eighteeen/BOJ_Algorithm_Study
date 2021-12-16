import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ :2
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int result = n % 8;
        if (result == 6) result = 4;
        else if (result == 7) result = 3;
        else if (result == 0) result = 2;

        System.out.println(result);
        br.close();
    }
}