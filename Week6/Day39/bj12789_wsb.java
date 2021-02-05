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
            //// '수열을 pop한 뒤의 count를 얻어온다? 어떻게 pop 했을때를 말하는거지? 어떤 count를 말하는거지? 일단 이걸 하는게 이 알고리즘에서 어떤 역할을 하는거지? 왜 count를 학생번호와 같은지 비교해서 같다면 canNum을 증가시키는걸까? 일단 다 읽어봐야겠다.'
            //// 함수로 추상화했음에도 함수이름이 너무 구체적인 구현을 설명해서 전체적인 로직을 이해하기 어려운 부분이 있는 것 같아요 ㅠㅠ 
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