import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//// 이보다 더 가독성이 좋을 수 없을 것 같습니다! : 22 메인 읽는데 아 뭘 하려는 거구나 하고 바로 이해할수 있어요!
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().split(" ")[0]);
        int wonderRemoves[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        MoveQueue moveQueue = new MoveQueue(N);
        int cntAllMove = 0;

        for(int w : wonderRemoves){
            cntAllMove += moveQueue.cntLessAfterRemoveWonder(w);
        }

        System.out.print(cntAllMove);
        br.close();
    }
}

//// 자바 국룰: 클래스명은 대문자로 시작해요!
//// -> Wow 이런 실수를 저지르다니... 함수만 계속 쓰다보니 이런 실수가 발생했네요 ㅠ 피드백 감사해요!
class MoveQueue{
    Deque<Integer> deque;

    public MoveQueue(int oneToThisNum){
        deque = new ArrayDeque<>(IntStream.range(1, oneToThisNum + 1).boxed().collect(Collectors.toList()));
    }

    //// 덱을 복제해서 실제로 시도하는 점이 메모리 사용과 시간 효율면에서 조금 아쉽습니당 :22
    //// -> 두분의 코드를 보니 훨씬 효율적인 방법을 찾으셔서 저도 아쉬웠습니다 피드백 감사해요 다른 방법으로 바꿔봤습니다!
    public int cntLessAfterRemoveWonder(int wonder){
        int cntMoveFirst = cntMoveFirstAfterRemoveWonder(wonder);
        return Math.min(cntMoveFirst, (deque.size() - cntMoveFirst + 1));
    }

    private int cntMoveFirstAfterRemoveWonder(int wonder){
        int cnt = 0;
        while(true){
            int current = deque.removeFirst();
            if(current == wonder) return cnt;
            deque.addLast(current);
            cnt++;
        }
    }
}
