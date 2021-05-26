import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 이야 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] competitionPlaceInfo = br.readLine().split(" ");
        int R = Integer.parseInt(competitionPlaceInfo[0]);
        int C = Integer.parseInt(competitionPlaceInfo[1]);
        int N = Integer.parseInt(competitionPlaceInfo[2]);

        int verticalCCTV = R / N;
        if (R % N != 0) verticalCCTV++;

        int horizontalCCTV = C / N;
        if (C % N != 0) horizontalCCTV++;

        System.out.println((long) verticalCCTV * horizontalCCTV);
        br.close();
    }
}