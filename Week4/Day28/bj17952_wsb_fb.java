import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

//// 읽기 쉽고 알고리즘도 효율적인 것 같습니다 :22 잘짜셨어요!!
class Main{
    static int WHETHER = 0, SCORE = 1, TIME = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String assignment[];
        int totalScore = 0;
        Stack<Integer> scoreStack = new Stack<>();
        Stack<Integer> timeStack = new Stack<>();

        for(int i = 0; i < N; i++){
            assignment = br.readLine().split(" ");
            //// 0, 1, 2 상수에 대해 이름을 붙여줬다면 더 읽기 편했을 것 같읍니다
            //// -> 바꿔봤으용~!
            if(assignment[WHETHER].equals("1")){
                scoreStack.push(Integer.parseInt(assignment[SCORE]));
                timeStack.push(Integer.parseInt(assignment[TIME]) - 1);
                totalScore += getScore(scoreStack, timeStack);
                //// 여기에 continue문을 삽입하면 else문을 안 써도 됩니다.
                //// 클린코드를 지향하는 개발자들 다수가 깨끗한 코드를 위해 else문을 지양한다고 합니다. 한번 고려해보시면 좋을 것 같습니다. (한 예로, '객체지향 생활체조' 9원칙 중 2번째가 'else 예약어 금지'입니다)
                //// -> 객체지향 생활체조라는 원칙은 완전 처음 안 정보네요. 정보 공유 너무 감사해요! 바꿔봤어용
                continue;
            }

            if(!timeStack.empty()){
                timeStack.set(timeStack.size() - 1, timeStack.peek() - 1);
                totalScore += getScore(scoreStack, timeStack);
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