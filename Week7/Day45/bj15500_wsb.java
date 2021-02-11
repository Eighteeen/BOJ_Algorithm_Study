import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    //// 문제의 각 요소간의 의존 관계가 복잡하게 얽혀있어서, 함수나 클래스로 분리하기 정말 까다로운 문제였던 것 같아요..
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Stack<Integer>[] rods = new Stack[3];
        for(int i = 1; i < 3; i++) rods[i] = new Stack<>();
        //// Arrays.stream로 만든 stream을 이용하여 바로 스택에 추가할 수 있어요!
        //// Arrays.stream(...).forEach(item -> rods[1].push(item)); 이러면 됩니다
        rods[1].addAll(Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList()));

        final int SHOULD_TO_ROD = 3;
        StringBuilder moveSequenceSb = new StringBuilder();
        int nextMove = N, cntMove = 0;
        int from = 1, to = 2;

        while(nextMove > 0){
            while(!rods[from].empty() && rods[from].peek() != nextMove){
                rods[to].push(rods[from].pop());
                moveSequenceSb.append(getMoveResult(from, to));
                cntMove++;
            }

            if(!rods[from].empty()){
                rods[from].pop();
                moveSequenceSb.append(getMoveResult(from, SHOULD_TO_ROD));
                cntMove++;
                nextMove--;
            }

            //// 오.. from과 to를 바꿔주는 방식이 더 괜찮네요!
            if(rods[from].empty()){
                int tmp = from;
                from = to;
                to = tmp;
            }
        }
        
        bw.write(cntMove + "\n");
        bw.write(moveSequenceSb.toString());

        bw.flush();
        br.close();
        bw.close();
    }

    static StringBuilder getMoveResult(int from, int to){
        StringBuilder sb = new StringBuilder();
        sb.append(from).append(" ").append(to).append("\n");
        return sb;
    }
}