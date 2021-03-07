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