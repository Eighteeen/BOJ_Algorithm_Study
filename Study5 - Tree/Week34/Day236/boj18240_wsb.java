import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int nodeSize;
    static int[] cntDepthNodeArr;
    static List<Queue<Integer>> depthNodeQueueList;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        nodeSize = Integer.parseInt(br.readLine());
        int[] addDepthArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (!setCntDepthNodeArr(addDepthArr)) {
            System.out.println(-1);
            return;
        }
        setDepthNodeQueueList();

        sb.append(depthNodeQueueList.get(0).poll()).append(" ");
        for (int depth : addDepthArr) {
            sb.append(depthNodeQueueList.get(depth).poll()).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    static boolean setCntDepthNodeArr(int[] addDepthArr) {
        cntDepthNodeArr = new int[nodeSize];
        cntDepthNodeArr[0] = 1;
        for (int depth : addDepthArr) {
            cntDepthNodeArr[depth]++;
            if (cntDepthNodeArr[depth - 1] * 2 < cntDepthNodeArr[depth]) return false;
        }
        return true;
    }

    //// 이런 방법도 있네요 알아가요
    static void setDepthNodeQueueList() {
        depthNodeQueueList = new ArrayList<>();
        for (int i = 0; i < nodeSize; i++) depthNodeQueueList.add(new LinkedList<>());

        boolean[] isSettingDepthArr = new boolean[nodeSize];
        int maxDepth = nodeSize - 1;
        int nodeNum = 0;

        while (maxDepth > 0) {
            int currentDepth = maxDepth;
            for (int i = maxDepth; i >= 0; i--) {
                if (i == maxDepth && cntDepthNodeArr[i] == 0) {
                    maxDepth--;
                    continue;
                }

                if (isSettingDepthArr[i]) {
                    isSettingDepthArr[i] = false;
                    continue;
                }

                currentDepth = i;
                break;
            }

            depthNodeQueueList.get(currentDepth).offer(++nodeNum);
            cntDepthNodeArr[currentDepth]--;
            isSettingDepthArr[currentDepth] = true;
        }
    }
}