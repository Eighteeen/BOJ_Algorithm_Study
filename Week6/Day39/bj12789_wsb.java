import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int students[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Stack<Integer> lineStack = new Stack<>();
        int canNum = 1;

        for(int s : students){
            canNum = getCntAfterPopSequence(lineStack, canNum);
            if(canNum == s){
                canNum++;
                continue;
            }
            
            lineStack.push(s);
        }
        canNum = getCntAfterPopSequence(lineStack, canNum);

        System.out.print(--canNum == N ? "Nice" : "Sad");
        br.close();
    }

    static int getCntAfterPopSequence(Stack<Integer> stack, int cnt){
        while(!stack.empty()){
            if(cnt != stack.peek()) return cnt;
            stack.pop();
            cnt++;
        }
        return cnt;
    }
}