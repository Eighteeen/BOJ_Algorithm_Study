import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
    ////굉장히 깔끔합니다....!
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
        
        //// 001 011 012 021 022 처리가 이 두줄로 이루어질 수 있다는게 신기하네요. 효율적인 로직 굳! :22 이게 이렇게 간단히,,,
        if(leftSumBall == 2) cntBox++;
        else cntBox += leftMaxBall;

        System.out.print(cntBox);
        
        br.close();
    }
}
