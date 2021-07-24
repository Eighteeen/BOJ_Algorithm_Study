import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] rainTreeInfo = br.readLine().split(" ");
        int N = Integer.parseInt(rainTreeInfo[0]);
        int W = Integer.parseInt(rainTreeInfo[1]);

        int[] adjacentNodeCntArr = new int[N + 1];

        while (--N > 0) {
            String[] nodeInfo = br.readLine().split(" ");
            int firstNum = Integer.parseInt(nodeInfo[0]);
            int secondNum = Integer.parseInt(nodeInfo[1]);

            adjacentNodeCntArr[firstNum]++;
            adjacentNodeCntArr[secondNum]++;
        }

        int leafNodeCnt = cntLeafNode(adjacentNodeCntArr, 1);
        System.out.println(String.format("%.4f", (double) W / leafNodeCnt));

        br.close();
    }

    static int cntLeafNode(int[] adjacentNodeCntArr, int root) {
        int cnt = 0;
        for (int adjacentCnt : adjacentNodeCntArr) {
            if (adjacentCnt == 1) cnt++;
        }
        if (adjacentNodeCntArr[root] == 1) cnt--;
        return cnt;
    }
}