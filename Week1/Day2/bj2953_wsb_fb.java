import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
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
