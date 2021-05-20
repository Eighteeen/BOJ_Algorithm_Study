// 문제풀이 실패 : 모범답안
// 제일 밑판부터 하나하나 원하는 위치로 이동시키면 그게 그냥 자동으로 최소이동횟수가 되는구나..

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

        // N개를 A에서 원하는 곳으로 옮김
        if (N > 0) moveTowerOfHanoi(N, 'A', getTempBar('A', wantBars[N - 1]), wantBars[N - 1]);
        printDiscBars();

        br.close();
    }
    
    // 이미 원하는 위치에 있는 밑판들은 옮길 필요가 없으므로 걔네 제외하고 생각함
    static void removeDontNeedDisc() {
        for (int i = N - 1; i >= 0; i--) {
            if (discBars[i] != wantBars[i]) return;
            N--;
        }
    }

    // 위의 discTmp개를 tmp로 보내고 그 아래에 있던 1개를 원하는 위치로 보냄
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
        // aboveDisc개를 옮기는데 드는 횟수가 M보다 작거나 같으면
        // 어차피 그 중간순서는 알 필요 없으니까 정석대로 옮기는 과정을 생략한다.
        if (Math.pow(2, aboveDisc) <= M) {
            moveDiscOnce(aboveDisc, tmp, to);
            M -= Math.pow(2, aboveDisc); // 실행된 이동횟수만큼 뺀다
            if (M == 0) return; // 원하는 이동횟수 찾았으면 끝냄
            
            removeDontNeedDisc(); // 이미 원하는 위치에 간 큰 원판들 제외

            // 원하는 위치에 간 큰 원판이 1개 이상일때
            // tmp에서 -> 다음으로 옮겨야 할 큰 원판의 목적지쪽으로 옮기게 함
            if (N <= aboveDisc) {
                from = tmp;
                to = wantBars[N - 1];
                tmp = getTempBar(from, to);
                moveTowerOfHanoi(N, from, tmp, to);
            } else {
                // 원하는 위치로 간게 없으면 tmp로 옮겨진 애들을 목적지로 보내기
                moveTowerOfHanoi(aboveDisc, tmp, from, to);
            }
        } else {
            // 윗놈들을 임시 막대기로 옮기게 한다
            moveTowerOfHanoi(aboveDisc, from, to, tmp);
        }
    }
    
    // 중간에 거쳐갈 막대기 찾기
    static char getTempBar(char bar1, char bar2) {
        List<Character> bars = new ArrayList<>(Arrays.asList('A', 'B', 'C'));
        bars.remove(Character.valueOf(bar1));
        bars.remove(Character.valueOf(bar2));
        return bars.get(0);
    }

    // 현재 각 원판의 위치 출력
    static void printDiscBars() {
        StringBuilder sb = new StringBuilder();
        for (char bar : discBars) sb.append(bar);
        System.out.println(sb);
    }
}