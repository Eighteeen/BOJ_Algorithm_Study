import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

//// 깔꼼하고 효율적이에요
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        char tech[] = new StringBuilder(br.readLine()).reverse().toString().toCharArray();
        Deque<Integer> cardDeque = new ArrayDeque<>();
        int num = 1;

        //// tech : techs 이름은 어때요
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

        //// forEach 활용 굳굳
        cardDeque.forEach(c -> sb.append(c + " "));

        System.out.print(sb);
        br.close();
    }
}