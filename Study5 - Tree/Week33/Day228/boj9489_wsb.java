import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해요
class Main {
    static int nodeSize;
    static int[] nodeSequence;
    static int[] parentArr, childCntArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String[] treeInfo = br.readLine().split(" ");
            nodeSize = Integer.parseInt(treeInfo[0]);
            int k = Integer.parseInt(treeInfo[1]);
            if (nodeSize == 0 && k == 0) break;

            nodeSequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (k == nodeSequence[0]) {
                sb.append(0).append("\n");
                continue;
            }

            parentArr = new int[nodeSize];
            childCntArr = new int[nodeSize];

            setTreeInfosUsingSequence();
            sb.append(getNumOfCousins(k)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    //// 항상 느끼지만 전위, 후위 연산자 활용을 잘 하시는 것 같아요
    static void setTreeInfosUsingSequence() {
        int parentIdx = -1;
        int prevData = nodeSequence[0];

        for (int i = 1; i < nodeSize; i++) {
            if (nodeSequence[i] > prevData + 1) parentArr[i] = nodeSequence[++parentIdx];
            else parentArr[i] = nodeSequence[parentIdx];
            childCntArr[parentIdx]++;

            prevData = nodeSequence[i];
        }
    }

    static int getNumOfCousins(int nodeData) {
        int numOfCousins = 0;

        int nodeIdx = indexOf(nodeSequence, nodeData);
        int parent = parentArr[nodeIdx];
        int parentIdx = indexOf(nodeSequence, parent);
        int grandparent = parentArr[parentIdx];

        for (int i = 0; i < nodeSize; i++) {
            if (parentArr[i] > grandparent) break;
            if (parentArr[i] == grandparent) numOfCousins += childCntArr[i];
        }
        numOfCousins -= childCntArr[parentIdx];

        return numOfCousins;
    }

    static int indexOf(int[] arr, int find) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == find) return i;
        }
        return -1;
    }
}