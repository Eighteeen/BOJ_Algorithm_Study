import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numStrArr = br.readLine().split(" ");
        double A = Double.parseDouble(numStrArr[0]), B = Double.parseDouble(numStrArr[1]), C = Double.parseDouble(numStrArr[2]);

        //// 아~ Math.max 사용할 수도 있네요
        System.out.println((int) Math.max((A / B * C), (A * B / C)));                                                                                                                                                                                                                                                                                                                                             

        br.close();
    }
}