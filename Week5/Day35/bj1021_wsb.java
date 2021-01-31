import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().split(" ")[0]);
        int wonderRemoves[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        moveQueue moveQueue = new moveQueue(N);
        int cntAllMove = 0;

        for(int w : wonderRemoves){
            int cntMoveFirst = moveQueue.cntMoveFirst(w);
            int cntMoveLast = moveQueue.cntMoveLast(w);
            if(cntMoveFirst < cntMoveLast){
                moveQueue.removeAfterMoveFirstCnt(cntMoveFirst);
                cntAllMove += cntMoveFirst;
            }else{
                moveQueue.removeAfterMoveLastCnt(cntMoveLast);
                cntAllMove += cntMoveLast;
            }
        }

        System.out.print(cntAllMove);
        br.close();
    }
}

class moveQueue{
    Deque<Integer> deque;

    public moveQueue(int oneToThisNum){
        deque = new ArrayDeque<>(IntStream.range(1, oneToThisNum + 1).boxed().collect(Collectors.toList()));
    }

    public void removeAfterMoveFirstCnt(int cntMove){
        cntMove++;
        for(int i = 0; i < cntMove; i++){
            int current = deque.removeFirst();
            deque.addLast(current);
        }
        deque.removeLast();
    }

    public void removeAfterMoveLastCnt(int cntMove){
        for(int i = 0; i < cntMove; i++){
            int current = deque.removeLast();
            deque.addFirst(current);
        }
        deque.removeFirst();
    }

    public int cntMoveFirst(int wonder){
        Deque<Integer> copyDeque = new ArrayDeque<>(deque);
        int cnt = 0;

        while(true){
            int current = copyDeque.removeFirst();
            if(current == wonder) return cnt;
            cnt++;
        }
    }

    public int cntMoveLast(int wonder){
        Deque<Integer> copyDeque = new ArrayDeque<>(deque);
        int cnt = 0;

        while(true){
            int current = copyDeque.removeLast();
            cnt++;
            if(current == wonder) return cnt;
        }
    }
}