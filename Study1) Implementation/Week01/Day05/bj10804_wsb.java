import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        List<Integer> card = new ArrayList<>();
        for(int i = 0; i < 20; i++) card.add(i + 1);

        for(int i = 0; i < 10; i++){
            st = new StringTokenizer(br.readLine());
            //// Collections.reverse()와 List<> subList() 배워갑니다. 이런거 활용을 되게 잘하시네요 :22:33
            Collections.reverse(card.subList(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
        }
        for(int i = 0; i < 20; i++) sb.append(card.get(i)).append(" ");
        System.out.print(sb);
    }
}