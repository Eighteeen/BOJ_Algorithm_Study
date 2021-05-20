import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    //// ㄲㄲ
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sumScore = 0;
        int maxScore = 0, minScore = 100;
        
        for (int i = 0; i < 4; i++) {
            int score = Integer.parseInt(br.readLine());
            sumScore += score;
            minScore = Math.min(minScore, score);
        }
        sumScore -= minScore;

        for (int i = 0; i < 2; i++) {
            maxScore = Math.max(maxScore, Integer.parseInt(br.readLine()));
        }
        sumScore += maxScore;

        System.out.println(sumScore);
        br.close();
    }
}