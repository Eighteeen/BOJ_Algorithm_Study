import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int graphSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] locationInfo = br.readLine().split(" ");
        int N = Integer.parseInt(locationInfo[0]);
        int K = Integer.parseInt(locationInfo[1]);
        graphSize = getForwardPoint(Math.max(N, K));

        System.out.println(getMinTimeToGo(N, K));
        br.close();
    }

    static int getMinTimeToGo(int from, int to) {
        Queue<Integer> bfsQueue = new LinkedList<>();
        int[] minTimes = new int[graphSize + 1];
        boolean[] isVisited = new boolean[graphSize + 1];

        bfsQueue.offer(from);
        isVisited[from] = true;

        while (!bfsQueue.isEmpty()) {
            int current = bfsQueue.poll();
            if (current == to) break;

            int[] nextNums = {getBackPoint(current), getForwardPoint(current), getTeleportPoint(current)};
            for (int next : nextNums) {
                if (isOutOfSize(next) || isVisited[next]) continue;
                isVisited[next] = true;
                minTimes[next] = minTimes[current] + 1;
                bfsQueue.offer(next);
            }
        }

        return minTimes[to];
    }

    static boolean isOutOfSize(int point) {
        return (point < 0 || point > graphSize);
    }

    static int getBackPoint(int origin) {
        return origin - 1;
    }

    static int getForwardPoint(int origin) {
        return origin + 1;
    }

    static int getTeleportPoint(int origin) {
        return origin * 2;
    }
}