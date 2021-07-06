import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static final int MOD_NUM = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int D = Integer.parseInt(br.readLine());
        int[] checkChildren = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.print(getCheckListOfJMPABU(checkChildren));
        br.close();
    }

    static StringBuilder getCheckListOfJMPABU(int[] checkChildren) {
        StringBuilder sb = new StringBuilder();

        long nodeNum = 0;
        int generation = 0;

        long nthNode = 1;
        long prevSiblingNode = 1;
        long prevLastNodeNum = 0;

        for (int checkChild : checkChildren) {
            nthNode = ((nthNode - 1) * ++generation + checkChild) % MOD_NUM;
            nodeNum = (prevLastNodeNum + nthNode) % MOD_NUM;
            sb.append(nodeNum).append("\n");

            prevSiblingNode *= generation;
            prevSiblingNode %= MOD_NUM;
            prevLastNodeNum += prevSiblingNode;
            prevLastNodeNum %= MOD_NUM;
        }

        return sb;
    }
}