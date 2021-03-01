// 문제 실패 - 모범 답안
// 5년 이상이면 5, 3, 1년 각각 투자해보고
// 3년이상이면 3, 1년 투자해보는 식으로
// 모든 투자기간에 대해 결과를 구해보고 가장 이득이 많이 남는 것을 재귀 반환 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    static final double RATE_OF_FIVE_YEARS = 1.35;
    static final double RATE_OF_THREE_YEARS = 1.2;
    static final double RATE_OF_ONE_YEAR = 1.05;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] investInfo = br.readLine().split(" ");

        System.out.println(resultInvest(Integer.parseInt(investInfo[0]), Integer.parseInt(investInfo[1])));
        br.close();
    }

    static int resultInvest(int assets, int period){
        if(period == 0) return assets;

        int caseA = 0, caseB = 0, caseC = 0;
        if(period >= 5) caseA = resultInvest((int)(assets * RATE_OF_FIVE_YEARS), period - 5);
        if(period >= 3) caseB = resultInvest((int)(assets * RATE_OF_THREE_YEARS), period - 3);
        caseC = resultInvest((int)(assets * RATE_OF_ONE_YEAR), period - 1);

        return Math.max(caseA, Math.max(caseB, caseC));
    }
}