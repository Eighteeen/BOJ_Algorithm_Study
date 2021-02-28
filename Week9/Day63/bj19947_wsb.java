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