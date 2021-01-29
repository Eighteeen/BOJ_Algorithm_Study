import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        char tech[] = new StringBuilder(br.readLine()).reverse().toString().toCharArray();
        Deque<Integer> cardDeque = new ArrayDeque<>();
        int num = 1;

        for(char t : tech){
            switch(t){
                case '1':
                    cardDeque.addFirst(num++);
                    break;
                case '2':
                    int temp = cardDeque.removeFirst();
                    cardDeque.addFirst(num++);
                    cardDeque.addFirst(temp);
                    break;
                case '3':
                    cardDeque.addLast(num++);
                    break;
            }
        }

        cardDeque.forEach(c -> sb.append(c + " "));

        System.out.print(sb);
        br.close();
    }
}