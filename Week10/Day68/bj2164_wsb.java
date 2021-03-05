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

    static Queue placeCard(int N){
        Queue cards = new LinkedList<>(IntStream.rangeClosed(1, N).boxed().collect(Collectors.toList()));
        return cards;
    }

    static Integer lastCardNumAfterAction(Queue<Integer> cards){
        while(cards.size() > 1){
            cards.poll();
            cards.offer(cards.poll());
        }

        return cards.peek();
    }
}