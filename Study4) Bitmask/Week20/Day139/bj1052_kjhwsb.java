import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] bottleInfo = br.readLine().split(" ");
        int N = Integer.parseInt(bottleInfo[0]);
        int K = Integer.parseInt(bottleInfo[1]);

        System.out.println(shouldBuyBottles(N, K));
        br.close();
    }

    static int shouldBuyBottles(int waterLiters, int limitBottles) {
        int waterBottles = Integer.bitCount(waterLiters);
        if (waterBottles <= limitBottles) return 0;

        return shouldBuyBottles(waterLiters + 1, limitBottles) + 1;
    }
}