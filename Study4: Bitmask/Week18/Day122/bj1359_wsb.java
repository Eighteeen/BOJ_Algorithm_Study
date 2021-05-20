import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 똑닮은 풀이네요. 깔끔!
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] lotteryTicketInfo = br.readLine().split(" ");
        int N = Integer.parseInt(lotteryTicketInfo[0]);
        int M = Integer.parseInt(lotteryTicketInfo[1]);
        int K = Integer.parseInt(lotteryTicketInfo[2]);

        int caseNum = 0;
        int pickCaseNum = 0;

        int range = (1 << N);
        for (int i = (1 << M) - 1; i < range; i++) {
            if (Integer.bitCount(i) != M) continue;
            caseNum++;
            
            int pickNum = 0;
            for (int j = 0; j < M; j++) {
                if ((i & (1 << j)) == 0) continue;
                pickNum++;
            }
            if (pickNum >= K) pickCaseNum++;
        }

        System.out.printf("%.9f\n", (double) pickCaseNum / caseNum);
        br.close();
    }
}