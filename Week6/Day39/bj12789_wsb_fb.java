import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
    //// 전체적으로 깔끔해요!!
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
            //// -> 오호 함수이름이 너무 구체적이라서 헷갈린다는 의견은 제 생각에서 아예 없던 생각이라 되게 새로운 피드백이네요!
                //// 최대한 자세하고 친절한? 이름을 지어야지 라고 생각했는데, 오히려 너무 구체적이면 전체적인 그림을 흐릴 수도 있군요
                //// 새로운 관점의 피드백 너무 재밌네요! 구체적인 구현방식을 나타내기 보다는 무엇을 반환하는지에 초점을 두어서 함수명을 바꿔봤어요
            canNum = getLastNumOfSequence(lineStack, canNum);
            if(canNum == s){
                canNum++;
                continue;
            }
            
            lineStack.push(s);
        }
        canNum = getLastNumOfSequence(lineStack, canNum);

        System.out.print(--canNum == N ? "Nice" : "Sad");
        br.close();
    }

    static int getLastNumOfSequence(Stack<Integer> stack, int fromNum){
        while(!stack.empty()){
            if(fromNum != stack.peek()) return fromNum;
            stack.pop();
            fromNum++;
        }
        return fromNum;
    }
}
