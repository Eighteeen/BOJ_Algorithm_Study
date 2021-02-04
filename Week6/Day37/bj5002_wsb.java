import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
    //// 전체적으로 깔끔한거 같습니다.
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        char genders[] = br.readLine().toCharArray();
        Stack<Character> peopleStack = new Stack<>();
        int cntPeople = 0;
        int possibleSize = X + 1;

        for(char g : genders){
            if(peopleStack.empty()){
                peopleStack.push(g);
                continue;
            }

            cntPeople += cntPairEnterClub(peopleStack, g);

            if(peopleStack.size() > possibleSize) break;
        }
        cntPeople += cntOnlyEnterClub(peopleStack, X, possibleSize);

        System.out.print(cntPeople);
        br.close();
    }
    ////신선한 방법이에요
    static int cntPairEnterClub(Stack<Character> peopleStack, char gender){
        if(peopleStack.peek() != gender){
            peopleStack.pop();
            return 2;
        }

        peopleStack.push(gender);
        return 0;
    }

    //// possible이라는 변수가 사실상 remember+1이니, remember만 받아서 안쪽에서 int possible = remember + 1; 하면 매개변수가 줄어서 더 깔끔하지 않을까유?
    //// -> 함수는 어떤 문제를 추상화하니 remember과 possible을 따로 두어야 활용성에서 좀 더 좋다고 생각합니다.
        //// 예를 들어 클럽 지침이 바뀌거나 도어맨이 바뀌었을 때 remember과 possible의 차이가 더이상 1이 아닐 수도 있다고 생각해서 이 부분은 유지하도록 하겠습니다!
        //// 하지만 문제 자체로만 보았을 때는 코드의 간결함을 위해 좋은 피드백이라고 생각합니다. 피드백 감사해요!
    static int cntOnlyEnterClub(Stack<Character> peopleStack, int remember, int possible){
        if(peopleStack.size() < possible) return  peopleStack.size();
        return remember;
    }
}
