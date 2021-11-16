import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 무난합니다 : 22
class Main {
    static int[] pickNums;
    static boolean[] visited;
    static int deathIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] gameInfo = br.readLine().split(" ");
        int N = Integer.parseInt(gameInfo[0]);
        deathIdx = Integer.parseInt(gameInfo[1]);

        pickNums = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            pickNums[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(getDeathNum());
        br.close();
    }

    static int getDeathNum() {
        return getDeathNum(0) - 1;
    }

    //// DFS를 선호하시는군요 : 22
    static int getDeathNum(int pickNum) {
        if (visited[pickNum]) return 0;
        if (pickNum == deathIdx) return 1;
        visited[pickNum] = true;

        int deathNum = 1;
        int plusDeathNum = getDeathNum(pickNums[pickNum]);
        if (plusDeathNum == 0) return 0;

        return deathNum + plusDeathNum;
    }
}