import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String assignment[];
        int totalScore = 0;
        Stack<Integer> scoreStack = new Stack<>();
        Stack<Integer> timeStack = new Stack<>();

        for(int i = 0; i < N; i++){
            assignment = br.readLine().split(" ");
            if(assignment[0].equals("1")){
                scoreStack.push(Integer.parseInt(assignment[1]));
                timeStack.push(Integer.parseInt(assignment[2]) - 1);
                totalScore += getScore(scoreStack, timeStack);
            }
            else{
                if(!timeStack.empty()){
                    timeStack.set(timeStack.size() - 1, timeStack.peek() - 1);
                    totalScore += getScore(scoreStack, timeStack);
                }
            }
        }

        System.out.print(totalScore);
        br.close();
    }

    static int getScore(Stack<Integer> scoreStack, Stack<Integer> timeStack){
        int score = 0;
        if(timeStack.peek() == 0){
            score = scoreStack.pop();
            timeStack.pop();
        }
        return score;
    }
}