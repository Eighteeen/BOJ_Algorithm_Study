import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] basketInfo1 = br.readLine().split(" ");
        String[] basketInfo2 = br.readLine().split(" ");

        int appleCntOfBasket1 = Integer.parseInt(basketInfo1[0]);
        int orangeCntOfBasket1 = Integer.parseInt(basketInfo1[1]);
        int appleCntOfBasket2 = Integer.parseInt(basketInfo2[0]);
        int orangeCntOfBasket2 = Integer.parseInt(basketInfo2[1]);

        System.out.println(Math.min(appleCntOfBasket1 + orangeCntOfBasket2, appleCntOfBasket2 + orangeCntOfBasket1));
        br.close();
    }
}