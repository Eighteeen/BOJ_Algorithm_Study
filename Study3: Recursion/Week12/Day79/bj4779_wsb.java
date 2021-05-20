import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static final int MAX_SIZE = 12;
    static final int BASE_SIZE = 0;
    //// 하긴 걍 String[]로 해도 됐네요
    static String[] cantorSets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        cantorSets = new String[MAX_SIZE + 1];
        String input;
        while ((input = br.readLine()) != null) {
            int N = Integer.parseInt(input);
            makeCantorSet(N);
            sb.append(cantorSets[N]).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    //// 효율깔끔굳
    static void makeCantorSet(int size) {
        if (cantorSets[size] != null) return;
        if (size == 0) {
            makeBaseCantorSet();
            return;
        }

        int prevSize = size - 1;
        makeCantorSet(prevSize);

        String prevCantor = cantorSets[prevSize];
        StringBuilder newCantor = new StringBuilder(prevCantor).append(" ".repeat(prevCantor.length())).append(prevCantor);
        cantorSets[size] = newCantor.toString();
    }

    static void makeBaseCantorSet() {
        cantorSets[BASE_SIZE] = "-";
    }
}