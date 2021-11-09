import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔 : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            String[] scoresInfo = br.readLine().split(" ");
            int S = Integer.parseInt(scoresInfo[0]);
            int T = Integer.parseInt(scoresInfo[1]);

            sb.append(getMinKickCntOfSameScores(S, T)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int getMinKickCntOfSameScores(int myScore, int otherScore) {
        if (myScore == otherScore) return 0;
        if (myScore > otherScore) return Integer.MAX_VALUE;

        return 1 + Math.min(getMinKickCntOfSameScores(myScore * 2, otherScore + 3),
            getMinKickCntOfSameScores(myScore + 1, otherScore));
    }
}