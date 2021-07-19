import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 깔끔합니다! : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] visitNodeArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] parentNodeArr = getParentNodeArr(visitNodeArr);
        sb.append(parentNodeArr.length).append("\n");
        for (int parentNode : parentNodeArr) sb.append(parentNode).append(" ");

        System.out.println(sb);
        br.close();
    }

    static int[] getParentNodeArr(int[] visitNodeArr) {
        int visitLen = visitNodeArr.length;
        int treeSize = visitLen / 2 + 1;
        
        int[] parentNodeArr = new int[treeSize];
        Arrays.fill(parentNodeArr, -1);

        int rootNode = visitNodeArr[0];
        for (int i = 1; i < visitLen; i++) {
            int visitNode = visitNodeArr[i];
            if (parentNodeArr[visitNode] != -1 || visitNode == rootNode) continue;
            parentNodeArr[visitNode] = visitNodeArr[i - 1];
        }
        return parentNodeArr;
    }
}