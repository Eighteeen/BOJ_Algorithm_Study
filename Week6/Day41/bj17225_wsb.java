import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String todayShopInfo[] = br.readLine().split(" ");
        int A = Integer.parseInt(todayShopInfo[0]);
        int B = Integer.parseInt(todayShopInfo[1]);
        int N = Integer.parseInt(todayShopInfo[2]);
        //// secondsToStartPacking 식으로 더 상세하게 표현됐으면 이름이 더 길더라도 변수 이름만 보고 이해할 수 있었을 것 같아요!
        //// '청색포장시작', '포장시작을 추가' 정도로만 표현돼있으니 의도를 알기 힘들었습니다 ㅠㅠ
        Deque<Integer> BPackStart = new ArrayDeque();
        Deque<Integer> RPackStart = new ArrayDeque();

        for(int i = 0; i < N; i++){
            String customerInfo[] = br.readLine().split(" ");
            int t = Integer.parseInt(customerInfo[0]);
            char c = customerInfo[1].charAt(0);
            int m = Integer.parseInt(customerInfo[2]);

            if(c == 'B') offerPackStart(BPackStart, A, t, m);
            else offerPackStart(RPackStart, B, t, m);
        }

        List<Integer> BPackList = new ArrayList<>();
        List<Integer> RPackList = new ArrayList<>();
        int cntPack = 0;
        while(!BPackStart.isEmpty() || !RPackStart.isEmpty()){
            while(!BPackStart.isEmpty() && (RPackStart.isEmpty() || RPackStart.peek() >= BPackStart.peek())){
                BPackStart.poll();
                BPackList.add(++cntPack);
            }
            
            while(!RPackStart.isEmpty() && (BPackStart.isEmpty() || BPackStart.peek() > RPackStart.peek())){
                RPackStart.poll();
                RPackList.add(++cntPack);
            }
        }

        System.out.print(getResult(BPackList, RPackList));
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