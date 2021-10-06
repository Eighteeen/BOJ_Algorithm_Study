import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔 : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int d1 = Integer.parseInt(br.readLine());
        int d2 = Integer.parseInt(br.readLine());
        final float pi = 3.141592f;
        
        int diameter = d2 * 2;
        float circumference = diameter * pi;
        System.out.printf("%.6f\n", d1 * 2 + circumference);

        br.close();
    }
}