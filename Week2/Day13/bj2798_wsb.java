import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String gameInfo[] = br.readLine().split(" ");
        int N = Integer.parseInt(gameInfo[0]);
        int M = Integer.parseInt(gameInfo[1]);
        int cards[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int nowSum, sumMax = 0;

        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){
                for(int k = j + 1; k < N; k++){
                    nowSum = cards[i] + cards[j] + cards[k];
                    if(nowSum == M){
                        System.out.print(nowSum);
                        return;
                    }
                    if(sumMax < nowSum && nowSum <= M) sumMax = nowSum;
                }
            }
        }

        System.out.print(sumMax);
    }
}