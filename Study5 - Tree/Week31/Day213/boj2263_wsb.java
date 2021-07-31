import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해요
class Main {
    static int[] inOrderIdxArr, postOrderArr;
    static StringBuilder preOrders;

    //// 488ms로 시간 효율이 굉장히 높게 채점 결과가 나와있는데 비결이 뭔가요
    //// -> inOrderIdxArr 로 idx를 찾는 순회를 줄여서 시간을 줄일 수 있었습니다.
    //// -> kjh의 코드에서의 keySet보다 idx로 바로 찾는 방법에서 시간 효율이 좀 더 좋게 나오지 않았나 싶습니다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] inOrderArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        postOrderArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //// Map<Integer, Integer> 보다는 요게 더 가볍겠네요. 배워갑니다
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

        //// 세심한 변수명 덕분에 이해하기 쉬워요 : 22
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