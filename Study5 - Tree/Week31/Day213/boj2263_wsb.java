import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int[] inOrderIdxArr, postOrderArr;
    static StringBuilder preOrders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] inOrderArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        postOrderArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        inOrderIdxArr = getIdxArr(inOrderArr);

        preOrders = new StringBuilder();
        setPreOrders(n, 0, 0);

        System.out.println(preOrders);
        br.close();
    }

    static void setPreOrders(int treeSize, int fromIdxOfInOrder, int fromIdxOfPostOrder) {
        if (treeSize == 0) return;

        int rootIdxOfPostOrder = fromIdxOfPostOrder + treeSize - 1;
        int root = postOrderArr[rootIdxOfPostOrder];
        preOrders.append(root).append(" ");

        int rootIdxOfInOrder = inOrderIdxArr[root];
        int leftSubTreeSize = rootIdxOfInOrder - fromIdxOfInOrder;
        int rightSubTreeSize = treeSize - leftSubTreeSize - 1;
        setPreOrders(leftSubTreeSize, fromIdxOfInOrder, fromIdxOfPostOrder);
        setPreOrders(rightSubTreeSize, rootIdxOfInOrder + 1, rootIdxOfPostOrder - rightSubTreeSize);
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