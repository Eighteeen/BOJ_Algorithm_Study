// 문제풀이 실패 : 모범답안
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    static int N, M;
    static char[] discBars, wantBars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] moveInfo = br.readLine().split(" ");
        N = Integer.parseInt(moveInfo[0]);
        M = Integer.parseInt(moveInfo[1]);

        discBars = new char[N];
        Arrays.fill(discBars, 'A');
        wantBars = br.readLine().toCharArray();
        removeDontNeedDisc();

        if (N > 0) moveTowerOfHanoi(N, 'A', getTempBar('A', wantBars[N - 1]), wantBars[N - 1]);
        printDiscBars();

        br.close();
    }
    
    // 이미 목적 위치에 있는 밑판들은 옮길 필요가 없으므로 걔네 제외하고 생각함
    static void removeDontNeedDisc() {
        for (int i = N - 1; i >= 0; i--) {
            if (discBars[i] != wantBars[i]) return;
            N--;
        }
    }

    // discToTmp개의 윗놈을 tmp쪽으로 옮기고
    // to로 discToTmp놈 하나 보냄
    static void moveDiscOnce(int discToTmp, char tmp, char to) {
        for (int i = 0; i < discToTmp; i++) {
            discBars[i] = tmp; 
        }
        discBars[discToTmp] = to;
    }

    static void moveTowerOfHanoi(int disc, char from, char tmp, char to) {
        // M번째가 오면 그만 옮김
        if (M == 0) return;

        // 맨 밑에 있는거 빼고 몇개?
        int aboveDisc = disc - 1;
        // 왜 2의 aboveDisc승이 나오는진 모르겠음
        if (Math.pow(2, aboveDisc) <= M) {
            // 
            moveDiscOnce(aboveDisc, tmp, to);
            M -= Math.pow(2, aboveDisc);
            if (M == 0) return;
            
            removeDontNeedDisc();
            if (N <= aboveDisc) {
                from = tmp;
                to = wantBars[N - 1];
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