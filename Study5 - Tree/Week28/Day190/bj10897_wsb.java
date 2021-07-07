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

    //// 저도 BigInteger 사용않는 로직 시도해보려고, 반복문 한번 돌 때마다 나머지 연산 한번씩 하게했더니 값이 꼬이길래 포기했는데..
    //// 연산되는 모든 것에 나머지 연산을 먼저 때리고 집어넣으면 제대로 구해지는군요
    //// 진짜 수학 자신없다 하시지만 누구보다 수학에 진심이신 것 같습니다. 배워갑니다
    
    //// 궁금한게 하나 있는데, 나머지를 10억으로 때리니 int형 안에서 충분히 값이 나오지 않나요?
    //// 혹시 어떤 부분이 int 범위를 초과하는건가요??
    static StringBuilder getCheckListOfJMPABU(int[] checkChildren) {
        StringBuilder sb = new StringBuilder();

        int nodeNum = 0;
        int generation = 0;

        int nthNode = 1;
        int prevSiblingNode = 1;
        int prevLastNodeNum = 0;

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