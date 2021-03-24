import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    static int N, M;
    static char[] discBars, wantBars;
    static int checkDiscIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] moveInfo = br.readLine().split(" ");
        N = Integer.parseInt(moveInfo[0]);
        M = Integer.parseInt(moveInfo[1]);

        discBars = new char[N];
        Arrays.fill(discBars, 'A');
        wantBars = br.readLine().toCharArray();
        checkDiscIdx = N - 1;
        removeDontNeedDisc();

        if (N > 0) moveTowerOfHanoi(N, 'A', getTempBar('A', wantBars[checkDiscIdx]), wantBars[checkDiscIdx]);
        printDiscBars();

        br.close();
    }

    static void removeDontNeedDisc() {
        for (int i = checkDiscIdx; i >= 0; i--) {
            if (discBars[i] != wantBars[i]) return;
            N--;
            checkDiscIdx--;
        }
    }

    static void moveDiscOnce(int discToTmp, char tmp, char to) {
        for (int i = 0; i < discToTmp; i++) {
            discBars[i] = tmp; 
        }
        discBars[discToTmp] = to;
    }

    static void moveTowerOfHanoi(int disc, char from, char tmp, char to) {
        if (M == 0) return;

        int aboveDisc = disc - 1;
        if (Math.pow(2, aboveDisc) <= M) {
            moveDiscOnce(aboveDisc, tmp, to);
            M -= Math.pow(2, aboveDisc);
            if (M == 0) return;
            
            removeDontNeedDisc();
            if (N <= aboveDisc) {
                from = tmp;
                to = wantBars[checkDiscIdx];
                tmp = getTempBar(from, to);
                moveTowerOfHanoi(N, from, tmp, to);
            } else {
                moveTowerOfHanoi(aboveDisc, tmp, from, to);
            }
        } else {
            moveTowerOfHanoi(aboveDisc, from, to, tmp);
        }
    }
    
    static char getTempBar(char bar1, char bar2) {
        List<Character> bars = new ArrayList<>(Arrays.asList('A', 'B', 'C'));
        bars.remove(Character.valueOf(bar1));
        bars.remove(Character.valueOf(bar2));
        return bars.get(0);
    }

    static void printDiscBars() {
        StringBuilder sb = new StringBuilder();
        for (char bar : discBars) sb.append(bar);
        System.out.println(sb);
    }
}