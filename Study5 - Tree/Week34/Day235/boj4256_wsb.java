import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// ㄲㄲ
class Main {
    static int[] inOrderIdxArr, preOrderArr;
    static int rootIdxOfPreOrder;
    static StringBuilder postOrders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            preOrderArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] inOrderArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            inOrderIdxArr = getIdxArr(inOrderArr);

            postOrders = new StringBuilder();
            rootIdxOfPreOrder = 0;
            setPostOrders(n, 0);

            sb.append(postOrders).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static void setPostOrders(int treeSize, int fromIdxOfInOrder) {
        if (treeSize == 0) return;

        int root = preOrderArr[rootIdxOfPreOrder++];
        int rootIdxOfInOrder = inOrderIdxArr[root];

        int leftSubTreeSize = rootIdxOfInOrder - fromIdxOfInOrder;
        int rightSubTreeSize = treeSize - leftSubTreeSize - 1;
        setPostOrders(leftSubTreeSize, fromIdxOfInOrder);
        setPostOrders(rightSubTreeSize, rootIdxOfInOrder + 1);

        postOrders.append(root).append(" ");
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