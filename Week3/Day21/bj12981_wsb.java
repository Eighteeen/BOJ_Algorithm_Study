import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int balls[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int minBall = Arrays.stream(balls).min().getAsInt();
        int cntBox = minBall;
        int leftSumBall = 0, leftMaxBall = 0;

        for(int ball : balls){
            ball -= minBall;
            cntBox += ball / 3;
            ball = ball % 3;
            leftSumBall += ball;
            if(leftMaxBall < ball) leftMaxBall = ball;
        }
        
        if(leftSumBall == 2) cntBox++;
        else cntBox += leftMaxBall;

        System.out.print(cntBox);
        
        br.close();
    }
}