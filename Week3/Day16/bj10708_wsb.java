import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 효율적이고 코드도 깔꼼쓰~
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int friendNum = Integer.parseInt(br.readLine());
        int gameLen = Integer.parseInt(br.readLine());
        int targets[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int predictTargets[], friendScores[] = new int[friendNum];

        for(int target : targets){
            predictTargets = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < friendNum; j++){
                if(target == predictTargets[j]) friendScores[j]++;
                else friendScores[target - 1]++;
            }
        }

        for(int score : friendScores) sb.append(score + "\n");
        System.out.print(sb);
    }
}