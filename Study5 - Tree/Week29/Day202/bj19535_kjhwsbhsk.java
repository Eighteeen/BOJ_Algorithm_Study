import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int nodeSize;
    static int[] nodeAdjacentCntArr;
    static List<Integer>[] nodeAdjacentListArr;

    static int cntD;
    static long cntG;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nodeSize = Integer.parseInt(br.readLine());
        nodeAdjacentCntArr = new int[nodeSize + 1];
        nodeAdjacentListArr = new ArrayList[nodeSize + 1];

        for (int i = 1; i <= nodeSize; i++) {
            nodeAdjacentListArr[i] = new ArrayList<>();
        }

        for (int i = 1; i < nodeSize; i++) {
            String[] nodeInfo = br.readLine().split(" ");
            int dataA = Integer.parseInt(nodeInfo[0]);
            int dataB = Integer.parseInt(nodeInfo[1]);

            nodeAdjacentCntArr[dataA]++;
            nodeAdjacentCntArr[dataB]++;

            nodeAdjacentListArr[dataA].add(dataB);
        }

        System.out.println(getResult());
        br.close();
    }

    static String getResult() {
        setCntDG();

        long threeMultipliedCntG = cntG * 3;
        if (cntD > threeMultipliedCntG) return "D";
        if (cntD < threeMultipliedCntG) return "G";
        return "DUDUDUNGA";
    }

    static void setCntDG() {
        for (int i = 1; i <= nodeSize; i++) {
            int adjacentCnt = nodeAdjacentCntArr[i];
            cntG += getCntG(adjacentCnt--);
            for (int nodeAdjacent : nodeAdjacentListArr[i]) {
                cntD += adjacentCnt * (nodeAdjacentCntArr[nodeAdjacent] - 1);
            }
        }
    }

    static long getCntG(int cntAdjacent) {
        if (cntAdjacent < 3) return 0;
        long longCntAdjacent = (long) cntAdjacent;
        return longCntAdjacent
            * (longCntAdjacent - 1)
            * (longCntAdjacent - 2)
            / 6;
    }
}