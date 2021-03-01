import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

//// 함수 활용을 잘하십니다! 
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        String command;
        int arrLen;

        for(int i = 0; i < T; i++){
            command = br.readLine();
            arrLen = Integer.parseInt(br.readLine());
            String arrInfo = br.readLine();
            if(arrLen == 0){
                if(command.contains("D")) sb.append("error\n");
                else sb.append("[]\n");
                continue;
            }

            AC ac = new AC(arrInfo);
            ac.performCommand(command);
            sb.append(ac.toString());
        }

        System.out.print(sb);
        br.close();
    }
}

//// 무려 4개의 함수가 매개변수로 Deque 객체에 의존하네요. Deque을 멤버변수로 가지는 클래스를 만들면 더 좋았을 것 같습니다
//// -> 좋은 지적이에요 변경해봤어요
class AC{
    private Deque<Integer> deque;
    //// reverse의 반의어로 advance는 대개 쓰지 않는 것 같습니다
    //// -> 피드백 반영으로 DIRECT로 변경해봤다가 isDirect로 변수가 변경되었습니다
    public boolean isDirect = true, isError = false;

    //// 오 스트림 활용 굳굳 : 22 오 이런방법도 있네요 굳!
    public AC(String numStrArr){
        deque = new ArrayDeque<>(Arrays.stream(numStrArr.substring(1, numStrArr.length() - 1).split(","))
                    .map(Integer::valueOf).collect(Collectors.toList()));
    }

    public void performCommand(String command){
        for(char method : command.toCharArray()){    
            if(method == 'R'){
                isDirect = !isDirect;
                continue;
            }

            if(method == 'D' && deque.isEmpty()){
                isError = true;
                return;
            }

            if(isDirect) deque.removeFirst();
            else deque.removeLast();
        }
    }

    @Override
    public String toString() {
        if(isError) return "error\n";

        StringBuilder sb = new StringBuilder("[");
        if(isDirect) strAdvance(sb);
        else strReverse(sb);
        sb.append("]\n");
        return sb.toString();
    }

    private void strAdvance(StringBuilder sb){
        while(!deque.isEmpty()){
            sb.append(deque.removeFirst());
            if(!deque.isEmpty()) sb.append(",");
        }
    }

    private void strReverse(StringBuilder sb){
        while(!deque.isEmpty()){
            sb.append(deque.removeLast());
            if(!deque.isEmpty()) sb.append(",");
        }
    }
}