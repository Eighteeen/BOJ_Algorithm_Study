import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> cardQueue = placeCard(N);

        System.out.println(lastCardNumAfterAction(cardQueue));
        br.close();
    }

    //// 와 감탄했어요 개깔끔간결멋짐
    static Queue placeCard(int N){
        Queue cards = new LinkedList<>(IntStream.rangeClosed(1, N).boxed().collect(Collectors.toList()));
        return cards;
    }

    static Integer lastCardNumAfterAction(Queue<Integer> cards){
        //// 애초에 1개 초과로 체크하면 if를 또 할 필요 없었군요 배워갑니다
        while(cards.size() > 1){
            cards.poll();
            cards.offer(cards.poll());
        }

        return cards.peek();
    }
}