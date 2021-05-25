import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//// 깔끔합니다!
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        List<Integer> xPossible = new ArrayList<>();
        List<Integer> yPossible = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String[] numInfo = br.readLine().split(" ");
            Integer x = Integer.valueOf(numInfo[0]);
            Integer y = Integer.valueOf(numInfo[1]);

            if (xPossible.contains(x)) xPossible.remove(x);
            else xPossible.add(x);
            if (yPossible.contains(y)) yPossible.remove(y);
            else yPossible.add(y);
        }

        sb.append(xPossible.get(0)).append(" ").append(yPossible.get(0));
        System.out.println(sb);
        br.close();
    }
}