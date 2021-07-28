import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int[] inOrderIdxArr, postOrderArr, preOrderArr;
    static int preOrderIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] inOrderArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        postOrderArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        inOrderIdxArr = getIdxArr(inOrderArr);

        preOrderArr = new int[n];
        setPreOrderArr(n, 0, 0);
        for (int order : preOrderArr) sb.append(order).append(" ");

        System.out.println(sb);
        br.close();
    }

    static void setPreOrderArr(int treeSize, int fromIdxOfInOrder, int fromIdxOfPostOrder) {
        if (treeSize == 0) return;

        int rootIdxOfPostOrder = fromIdxOfPostOrder + treeSize - 1;
        int root = postOrderArr[rootIdxOfPostOrder];
        preOrderArr[preOrderIdx++] = root;

        int rootIdxOfInOrder = inOrderIdxArr[root];
        int leftSubTreeSize = rootIdxOfInOrder - fromIdxOfInOrder;
        int rightSubTreeSize = treeSize - leftSubTreeSize - 1;
        setPreOrderArr(leftSubTreeSize, fromIdxOfInOrder, fromIdxOfPostOrder);
        setPreOrderArr(rightSubTreeSize, rootIdxOfInOrder + 1, rootIdxOfPostOrder - rightSubTreeSize);
    }

    static int[] getIdxArr(int[] differentNumArr) {
        int len = differentNumArr.length;
        int[] idxArr = new int[len + 1];
        for (int i = 0; i < len; i++) {
            idxArr[differentNumArr[i]] = i;
        }
        return idxArr;
    }
}