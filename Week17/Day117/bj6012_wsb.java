import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        System.out.println(danceSum(1, N));

        br.close();
    }

    static int danceSum(int startCow, int endCow) {
        if (startCow == endCow) return 0;
        if (endCow - startCow == 1) return startCow * endCow;

        int danceSum = 0;
        int middleCow = (startCow + endCow) / 2;
        danceSum += danceSum(startCow, middleCow);
        danceSum += danceSum(middleCow + 1, endCow);

        return danceSum;
    }
}