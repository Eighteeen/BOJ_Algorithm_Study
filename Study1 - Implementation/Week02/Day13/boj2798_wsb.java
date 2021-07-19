import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
    ////깔끔합니다~ :22
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String gameInfo[] = br.readLine().split(" ");
        int N = Integer.parseInt(gameInfo[0]);
        int M = Integer.parseInt(gameInfo[1]);
        int cards[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int nowSum, sumMax = 0;
        ////i랑j랑k랑 크기를 다르게 두면 좋을거 같습니다!
        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){
                for(int k = j + 1; k < N; k++){
                    nowSum = cards[i] + cards[j] + cards[k];
                    //// 목표값과 같을때 바로 출력하고 종료해 주는건 시간효율을 위해서인가요??
                    ////목표값이랑 같을때 종료시키는거를 생각을 못했는데 배워갑니다! :22
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