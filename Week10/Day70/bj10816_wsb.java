import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        Map<Integer, Integer> cardOrganized = organizeCards(cards);
        for(int wonder : wonderCards){
            if(cardOrganized.containsKey(wonder)) sb.append(cardOrganized.get(wonder));
            else sb.append(0);
            sb.append(' ');
        }

        System.out.println(sb);
        br.close();
    }

    static Map organizeCards(int[] cards){
        Map<Integer, Integer> cardMap = new HashMap<>();
        for(int card : cards){
            if(cardMap.containsKey(card)) cardMap.put(card, cardMap.get(card) + 1);
            else cardMap.put(card, 1);
        }
        return cardMap;
    }
}