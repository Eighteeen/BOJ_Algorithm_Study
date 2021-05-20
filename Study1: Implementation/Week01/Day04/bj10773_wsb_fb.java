import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        Stack<Integer> money = new Stack<>();
        int now;
            //// list사용해서 구현한게 좀 신박하네요. :22 list로도 꽤 괜찮게 짜여진 것 같아요
            //// -> 하지만 문제를 보고 바로 스택 활용을 하는 문제라고 생각하지 못해 다른 분들의 코드를 보고 반성했어요. 그럼에도 좋은 피드백 감사해요
        for(int i = 0; i < K; i++){
            now = Integer.parseInt(br.readLine());
            if(now == 0) money.pop();
            else money.push(now);
        }
        System.out.print(money.stream().mapToInt(Integer::intValue).sum());
    }
}