import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//// 읽기도 편하고 효율적이네요
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int orders[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int entry = orders[0], removeRule = orders[1], converseRule = orders[2];
        Deque<Integer> personDeque = new ArrayDeque<>();
        int removeNum = 0;
        boolean isAdvance = true;

        fillOneToNumInDeque(personDeque, entry);

        while(!personDeque.isEmpty()){
            if(removeNum == converseRule) isAdvance = !isAdvance;
            removeNum %= converseRule;
            if(isAdvance) bw.write(progressAdvance(personDeque, removeRule) + "\n");
            else bw.write(progressRevere(personDeque, removeRule) + "\n");
            removeNum++;
        }

        bw.flush();
        br.close();
        bw.close();
    }
    
    static void fillOneToNumInDeque(Deque<Integer> deque, int num){
        for(int i = 1; i <= num; i++){
            deque.addLast(i);
        }
    }

    static int progressAdvance(Deque<Integer> deque, int ruleNum){
        for(int i = 1; i < ruleNum; i++){
            deque.addLast(deque.removeFirst());
        }
        return deque.removeFirst();
    }

    static int progressRevere(Deque<Integer> deque, int ruleNum){
        for(int i = 1; i < ruleNum; i++){
            deque.addFirst(deque.removeLast());
        }
        return deque.removeLast();
    }
}