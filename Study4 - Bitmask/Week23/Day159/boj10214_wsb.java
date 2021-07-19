import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

////깔끔 :22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int YonseiScore = 0, KoreaScore = 0;
            for (int j = 0; j < 9; j++) {
                String[] scoreStringArr = br.readLine().split(" ");
                YonseiScore += Integer.parseInt(scoreStringArr[0]);
                KoreaScore += Integer.parseInt(scoreStringArr[1]);
            }
            
            String result = "Draw";
            if (YonseiScore > KoreaScore) result = "Yonsei";
            else if (YonseiScore < KoreaScore) result = "Korea";

            sb.append(result).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}