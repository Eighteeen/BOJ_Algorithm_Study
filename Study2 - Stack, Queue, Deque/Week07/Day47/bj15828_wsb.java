import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//// 깔꼬미 효율쓰
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> router = new LinkedList<>();
        int info;

        while((info = Integer.parseInt(br.readLine())) != -1){
            if(info == 0){
                router.poll();
                continue;
            }

            if(router.size() >= N) continue;
            router.offer(info);
        }
        router.forEach(r -> sb.append(r).append(" "));

        //// 맞다 이렇게 할수있었지~
        System.out.print(sb.length() == 0 ? "empty" : sb);
        br.close();
    }
}