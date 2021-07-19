import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//// 랭크에 있던 가독성 낮은 코드 보다가 이 코드보니 눈이 정화됩니다 :22 가독성이 좋아요!
//// 덱과 리스트로 구현할 수 있는 코드였군요,,,
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String todayShopInfo[] = br.readLine().split(" ");
        int A = Integer.parseInt(todayShopInfo[0]);
        int B = Integer.parseInt(todayShopInfo[1]);
        int N = Integer.parseInt(todayShopInfo[2]);
        //// secondsToStartPacking, initSecondsToStartPacking 식으로 더 상세하게 표현됐으면 이름이 더 길더라도 변수 이름만 보고 이해할 수 있었을 것 같아요!
        //// '청색포장시작', '포장시작을 추가' 정도로만 표현돼있으니 의도를 알기 힘들었습니다 ㅠㅠ
        //// -> 변수명을 너무 간결화했네요 좋은 지적입니다!
        Deque<Integer> BlueSecondStartPack = new ArrayDeque();
        Deque<Integer> RedSecondStartPack = new ArrayDeque();

        for(int i = 0; i < N; i++){
            String customerInfo[] = br.readLine().split(" ");
            int t = Integer.parseInt(customerInfo[0]);
            char c = customerInfo[1].charAt(0);
            int m = Integer.parseInt(customerInfo[2]);

            if(c == 'B') offerPackStart(BlueSecondStartPack, A, t, m);
            else offerPackStart(RedSecondStartPack, B, t, m);
        }

        List<Integer> BPackNumList = new ArrayList<>();
        List<Integer> RPackNumList = new ArrayList<>();
        int cntPack = 0;
        while(!BlueSecondStartPack.isEmpty() || !RedSecondStartPack.isEmpty()){
            while(!BlueSecondStartPack.isEmpty() && (RedSecondStartPack.isEmpty() || RedSecondStartPack.peek() >= BlueSecondStartPack.peek())){
                BlueSecondStartPack.poll();
                BPackNumList.add(++cntPack);
            }
            
            while(!RedSecondStartPack.isEmpty() && (BlueSecondStartPack.isEmpty() || BlueSecondStartPack.peek() > RedSecondStartPack.peek())){
                RedSecondStartPack.poll();
                RPackNumList.add(++cntPack);
            }
        }

        System.out.print(getResult(BPackNumList, RPackNumList));
        br.close();
    }

    static void offerPackStart(Deque<Integer> pack, int takeTime, int orderTime, int cnt){
        if(!pack.isEmpty() && (pack.peekLast() + takeTime) > orderTime) orderTime = pack.peekLast() + takeTime;
        orderTime -= takeTime;
        for(int i = 0; i < cnt; i++){
            pack.offer(orderTime += takeTime);
        }
    }

    static String getResult(List<Integer> List1, List<Integer> List2){
        StringBuilder sb = new StringBuilder();

        sb.append(List1.size()).append("\n");
        List1.forEach(l -> sb.append(l).append(" "));
        sb.append("\n");
        sb.append(List2.size()).append("\n");
        List2.forEach(l -> sb.append(l).append(" "));

        return sb.toString();
    }
}