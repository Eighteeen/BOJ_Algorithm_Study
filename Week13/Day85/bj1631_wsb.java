import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    static int N, M;
    static char[] discBars, wantBars;
    static int cntMove;
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

        moveTowerOfHanoi(N, 'A', getTempBar('A', wantBars[checkDiscIdx]), wantBars[checkDiscIdx]);
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

    static boolean isWantBar(int wantIdx) {
        removeDontNeedDisc();
        return checkDiscIdx == wantIdx;
    }

    static void moveDiscOnce(char toOnce, char toLastDisc, int lastDiscIdx) {
        for (int i = 0; i < lastDiscIdx; i++) {
            discBars[i] = toOnce;
        }
        discBars[lastDiscIdx] = toLastDisc;
    }

    static void moveTowerOfHanoi(int disc, char from, char tmp, char to) {
        if (M == 0) return;

        int aboveDisc = disc - 1;
        if (Math.pow(2, aboveDisc) <= M) {
            moveDiscOnce(tmp, to, aboveDisc);
            M -= Math.pow(2, aboveDisc);

            int checkIdx = (aboveDisc == 0 ? 0 : aboveDisc - 1);
            if (to != wantBars[checkIdx] && isWantBar(checkIdx)) {
                from = discBars[checkIdx];
                to = wantBars[checkIdx];
                tmp = getTempBar(from, to);
                moveTowerOfHanoi(disc, from, tmp, to);
            } else {
                moveTowerOfHanoi(aboveDisc, tmp, from, to);
            }
        }
        // else {
            moveTowerOfHanoi(aboveDisc, from, to, tmp);
        // }
    }
    
    static char getTempBar(char from, char to) {
        // if (from == 'A') return (to == 'B' ? 'C' : 'B');
        // if (from == 'B') return (to == 'A' ? 'C' : 'A');
        // return (to == 'A' ? 'B' : 'A');
        List<Character> bars = new ArrayList<>(Arrays.asList('A', 'B', 'C'));
        bars.remove(Character.valueOf(from));
        bars.remove(Character.valueOf(to));
        return bars.get(0);
    }

    static void printDiscBars() {
        StringBuilder sb = new StringBuilder();
        for (char bar : discBars) sb.append(bar);
        System.out.println(sb);
    }
}