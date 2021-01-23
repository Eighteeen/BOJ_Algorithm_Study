import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> barStack = new Stack<>();
        int now, max = 0, cnt = 0;

        for(int i = 0; i < N; i++){
            barStack.push(Integer.parseInt(br.readLine()));
        }

        while(!barStack.empty()){
            now = barStack.pop();
            if(max < now) {
                cnt++;
                max = now;
            }
        }

        System.out.print(cnt);
        br.close();
    }
}