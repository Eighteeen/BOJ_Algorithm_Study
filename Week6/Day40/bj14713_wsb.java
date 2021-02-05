import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Queue<String> strQueue[] = new LinkedList[N];
        for(int i = 0; i < N; i++){
            String strArr[] = br.readLine().split(" ");
            strQueue[i] = new LinkedList<>();
            for(String s : strArr) strQueue[i].offer(s);
        }
        String L[] = br.readLine().split(" ");
        boolean isPossible = true;

        for(String word : L){
            int i = 0;
            while(i != N){
                if(!strQueue[i].isEmpty() && strQueue[i].peek().equals(word)){
                    strQueue[i].poll();
                    break;
                }
                i++;
            }

            if(i == N){
                isPossible = false;
                break;
            }
        }

        for(Queue<String> queue : strQueue){
            if(!queue.isEmpty()){
                isPossible = false;
                break;
            }
        }

        System.out.print(isPossible ? "Possible" : "Impossible");
        br.close();
    }
}