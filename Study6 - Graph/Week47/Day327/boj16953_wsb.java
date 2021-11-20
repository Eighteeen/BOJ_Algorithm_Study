import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int targetNum;
    static boolean[] visited;
    static final int MAX_NUM = 1000000000;

    //// 무난쓰 : 22
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numInfo = br.readLine().split(" ");
        int A = Integer.parseInt(numInfo[0]);
        targetNum = Integer.parseInt(numInfo[1]);

        visited = new boolean[targetNum + 1];
        int minCnt = getMinCntOfChangeCalculation(A);

        System.out.println(minCnt == 0 ? -1 : minCnt);
        br.close();
    }

    static int getMinCntOfChangeCalculation(int origin) {
        if (origin > targetNum || visited[origin]) return 0;
        if (origin == targetNum) return 1;
        visited[origin] = true;

        int cnt1 = getMinCntOfChangeCalculation(origin * 2);
        if (cnt1 == 0) cnt1 = MAX_NUM;

        long calcNum = (long) origin * 10 + 1;
        int cnt2 = MAX_NUM;
        if (calcNum <= MAX_NUM) {
            cnt2 = getMinCntOfChangeCalculation((int) calcNum);
            if (cnt2 == 0) cnt2 = MAX_NUM;
        }

        int minCnt = Math.min(cnt1, cnt2);
        return minCnt == MAX_NUM ? 0 : 1 + minCnt;
    }
}