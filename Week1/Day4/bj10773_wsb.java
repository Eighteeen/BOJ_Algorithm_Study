import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        List<Integer> money = new ArrayList<>();
        int sum = 0, now;
            //// list사용해서 구현한게 좀 신박하네요.
        for(int i = 0; i < K; i++){
            now = Integer.parseInt(br.readLine());
            if(now != 0) {
                sum += now;
                money.add(now);
            }else{
                sum -= money.get(money.size() - 1);
                money.remove(money.size() -1);
            }
        }
        System.out.print(sum);
    }
}
