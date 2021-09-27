import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int graphSize;
    static int[] beforeCalcNums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        graphSize = Integer.parseInt(br.readLine());

        sb.append(getMinNumOfCalc(1, graphSize))
            .append("\n")
            .append(getWayToMake(graphSize, 1));

        System.out.print(sb);
        br.close();
    }

    static int getMinNumOfCalc(int from, int to) {
        Queue<Integer> bfsCalcNumQueue = new LinkedList<>();
        int[] minCalcs = new int[graphSize + 1];
        boolean[] isVisited = new boolean[graphSize + 1];
        beforeCalcNums = new int[graphSize + 1];

        bfsCalcNumQueue.offer(from);
        isVisited[from] = true;

        while (!bfsCalcNumQueue.isEmpty()) {
            int current = bfsCalcNumQueue.poll();
            if (current == to) break;

            int[] nextNums = {getNumForCalc1(current), getNumForCalc2(current), getNumForCalc3(current)};
            for (int next : nextNums) {
                if (next > to || isVisited[next]) continue;
                isVisited[next] = true;
                minCalcs[next] = minCalcs[current] + 1;
                beforeCalcNums[next] = current;
                bfsCalcNumQueue.offer(next);
            }
        }

        return minCalcs[to];
    }

    static int getNumForCalc1(int origin) {
        return origin * 3;
    }

    static int getNumForCalc2(int origin) {
        return origin * 2;
    }

    static int getNumForCalc3(int origin) {
        return origin + 1;
    }

    static String getWayToMake(int from, int to) {
        StringBuilder sb = new StringBuilder();

        while (from >= to) {
            sb.append(from).append(" ");
            from = beforeCalcNums[from];
        }

        return sb.toString();
    }
}