import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //// max로만 썼는데 high 네이밍 배워갑니다 :22 네이밍을 잘하셨네요
        int highScore = 0, highNum = 0;

        for(int i = 1; i <= 5; i++){
            st = new StringTokenizer(br.readLine());
            int nowScore = 0;
            for(int j = 0; j < 4; j++){
             nowScore += Integer.parseInt(st.nextToken());
            }
            if(highScore < nowScore){
                highScore = nowScore;
                highNum = i;
            }
        }
        System.out.print(highNum + " " + highScore);
    }
}
