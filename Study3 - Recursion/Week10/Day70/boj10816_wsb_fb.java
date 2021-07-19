import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//// 굳굳!
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());
        int[] wonderCards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //// '카드를 정리한다(organize)'만으로는 어떤 일을 하는건지 알기 어려웠어요 ㅠㅠ
        //// '각각의 카드를 센다' 정도로 구체적으로 주어진다면 더 좋았을 것 같습니다! countOfEachCard = countEachCard();
        //// -> 메인은 구체적으로 반영하고 함수는 좀 더 추상적으로 바꿔봤어요!
        Map<Integer, Integer> cntEachCard = cntEachNum(cards);
        for(int wonder : wonderCards){
            if(cntEachCard.containsKey(wonder)) sb.append(cntEachCard.get(wonder));
            else sb.append(0);
            sb.append(' ');
        }

        System.out.println(sb);
        br.close();
    }

    static Map cntEachNum(int[] nums){
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int n : nums){
            if(numMap.containsKey(n)) numMap.put(n, numMap.get(n) + 1);
            else numMap.put(n, 1);
        }
        return numMap;
    }
}