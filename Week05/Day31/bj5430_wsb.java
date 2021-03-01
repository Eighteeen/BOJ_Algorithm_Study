import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;
//// 함수 활용을 잘하십니다! 
class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    //// reverse의 반의어로 advance는 대개 쓰지 않는 것 같습니다
    static int ERROR = -1, ADVANCE = 0, REVERSE = 1;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        String command;
        int arrLen;
        Deque<Integer> arrDeque;

        for(int i = 0; i < T; i++){
            command = br.readLine();
            arrLen = Integer.parseInt(br.readLine());
            String arrInfo = br.readLine();
            if(arrLen == 0){
                if(command.contains("D")) sb.append("error\n");
                else sb.append("[]\n");
                continue;
            }

            //// 오 스트림 활용 굳굳 : 22 오 이런방법도 있네요 굳!
            arrDeque = new ArrayDeque<>(Arrays.stream(arrInfo.substring(1, arrInfo.length() - 1).split(","))
                            .map(Integer::valueOf).collect(Collectors.toList()));

            int result = getResultAfterCommand(command, arrDeque);
            if(result == ERROR) sb.append("error\n");
            else printArr(arrDeque, result);
        }

        System.out.print(sb);
        br.close();
    }

    //// 무려 4개의 함수가 매개변수로 Deque 객체에 의존하네요. Deque을 멤버변수로 가지는 클래스를 만들면 더 좋았을 것 같습니다
    static int getResultAfterCommand(String command, Deque<Integer> arrDeque){
        int result = 0;

        for(char method : command.toCharArray()){    
            if(method == 'R'){
                result = (result + 1) % 2;
                continue;
            }

            if(method == 'D' && arrDeque.isEmpty()) return ERROR;

            if(result == ADVANCE) arrDeque.removeFirst();
            else arrDeque.removeLast();
        }

        return result;
    }

    static void printArr(Deque<Integer> arrDeque, int direction){
        if(direction == ERROR) return;

        sb.append("[");
        if(direction == ADVANCE) printAdvance(arrDeque);
        else printReverse(arrDeque);
        sb.append("]\n");
    }

    static void printAdvance(Deque<Integer> arrDeque){
        while(!arrDeque.isEmpty()){
            sb.append(arrDeque.removeFirst());
            if(!arrDeque.isEmpty()) sb.append(",");
        }
    }

    static void printReverse(Deque<Integer> arrDeque){
        while(!arrDeque.isEmpty()){
            sb.append(arrDeque.removeLast());
            if(!arrDeque.isEmpty()) sb.append(",");
        }
    }
}
