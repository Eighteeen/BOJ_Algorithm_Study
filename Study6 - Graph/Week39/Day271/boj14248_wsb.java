import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔
class Main {
    static int numOfStones;
    static int[] jumpDistances;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        numOfStones = Integer.parseInt(br.readLine());
        jumpDistances = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int s = Integer.parseInt(br.readLine());
        visited = new boolean[numOfStones];

        System.out.println(getMaxNumOfCanVisit(s - 1));
        br.close();
    }

    static int getMaxNumOfCanVisit(int fromIdx) {
        if (visited[fromIdx]) return 0;
        visited[fromIdx] = true;

        int visitCnt = 0;

        int leftJumpStoneIdx = fromIdx - jumpDistances[fromIdx];
        if (leftJumpStoneIdx >= 0) visitCnt = getMaxNumOfCanVisit(leftJumpStoneIdx);

        int rightJumpStoneIdx = fromIdx + jumpDistances[fromIdx];
        if (rightJumpStoneIdx < numOfStones) {
            visited[rightJumpStoneIdx] = false;
            visitCnt += getMaxNumOfCanVisit(rightJumpStoneIdx);
        }

        return visitCnt + 1;
    }
}